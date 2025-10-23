package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPage;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPageInfo;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentSubmit;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.ThreadsReplyCommentMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import com.xiaogua.comments.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * 帖子回复评论 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class ThreadsReplyCommentBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyCommentBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsReplyCommentMapper threadsReplyCommentMapper;

    @Autowired
    private UserBl userBl;

    @Autowired
    ThreadsReplyBl threadsReplyBl;

    @Autowired
    ThreadsReplyCommentLikeBl threadsReplyCommentLikeBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 新增帖子回复评论
     *
     * @param submit   评论信息
     * @param userCode 用户编号
     */
    public ThreadsReplyComment save(ThreadsReplyCommentSubmit submit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        ThreadsReplyComment threadsReplyComment = mappingThreadsReplyComment(submit, user);

        threadsReplyCommentMapper.insertSelective(threadsReplyComment);
        //帖子回复评论通知
        messageBl.sendThreadsReplyCommentMessage(threadsReplyComment);
        return threadsReplyComment;
    }

    /**
     * 分页获取帖子回复信息
     *
     * @param pageInfo    分页信息
     * @param showDeleted 是否展示删除信息
     * @return
     */
    public ThreadsReplyCommentPage getThreadsReplyCommentPage(ThreadsReplyCommentPageInfo pageInfo, boolean showDeleted) {
        int count = threadsReplyCommentMapper.count(pageInfo.getReplyCode(), showDeleted);

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<ThreadsReplyComment> replyComments = threadsReplyCommentMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getReplyCode(), showDeleted);

        ThreadsReplyCommentPage replyCommentPage = new ThreadsReplyCommentPage();

        if (!CollectionUtils.isEmpty(replyComments)) {
            replyCommentPage.setReplyComments(replyComments);
        }
        replyCommentPage.setPagingInfo(pagingInfo);
        return replyCommentPage;
    }

//    /**
//     * 统计帖子回复数量
//     * @param threadsInfoCode 帖子编号
//     * @param showDeleted 是否展示删除
//     * @return
//     */
//    public int count(String threadsInfoCode, boolean showDeleted) {
//        return threadsReplyMapper.count(threadsInfoCode, showDeleted);
//    }
//
//
    /**
     * 获取回复评论信息
     *
     * @param code 回复评论编号
     * @return
     */
    public ThreadsReplyComment getReplyComment(String code) {
        ThreadsReplyComment threadsReplyComment = threadsReplyCommentMapper.selectByCode(code);

        return threadsReplyComment;
    }

    /**
     * 点赞
     *
     * @param code 帖子回复评论编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int like(String code, User user) {
        ThreadsReplyComment replyComment = threadsReplyCommentMapper.selectByCode(code);
        if (replyComment == null || replyComment.getDeleted()) {
            throw new CommentsRuntimeException(-1, "回复评论不存在");
        }

        int likeNum = replyComment.getLikeNum();
        likeNum++;
        replyComment.setLikeNum(likeNum);

        // 增加评论点赞信息
        threadsReplyCommentLikeBl.save(code, replyComment.getThreadsCode(), user);
        threadsReplyCommentMapper.updateByPrimaryKeySelective(replyComment);

        String threadsCode = replyComment.getThreadsCode();
        //帖子回复的评论被赞通知
        messageBl.sendThreadsReplyCommentLikeMessage(code, user, replyComment, threadsCode);

        return likeNum;
    }

    /**
     * 取消点赞
     *
     * @param code 帖子回复评论编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int unlike(String code, User user) {
        ThreadsReplyComment replyComment = threadsReplyCommentMapper.selectByCode(code);
        if (replyComment == null || replyComment.getDeleted()) {
            throw new CommentsRuntimeException(-1, "回复评论不存在");
        }

        int likeNum = replyComment.getLikeNum();
        likeNum--;
        replyComment.setLikeNum(likeNum);

        // 增加评论点赞信息
        threadsReplyCommentLikeBl.delete(code, user.getCode());

        threadsReplyCommentMapper.updateByPrimaryKeySelective(replyComment);

        return likeNum;
    }

    /**
     * 转换dal.ThreadsReply
     *
     * @param submit 帖子回复提交信息
     * @param user 用户信息
     * @return
     */
    private ThreadsReplyComment mappingThreadsReplyComment(ThreadsReplyCommentSubmit submit, User user) {
        ThreadsReplyComment threadsReplyComment = new ThreadsReplyComment();

        ThreadsReply threadsReply = threadsReplyBl.getThreadsReply(submit.getReplyCode());
        if (threadsReply == null || threadsReply.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子回复不存在");
        }

        threadsReplyComment.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_REPLY_COMMENT.getCode()));
        threadsReplyComment.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        threadsReplyComment.setContent(submit.getContent());
        threadsReplyComment.setDeleted(false);
        threadsReplyComment.setFromName(user.getName());
        threadsReplyComment.setFromAvatar(user.getAvatar());
        threadsReplyComment.setReplyCode(submit.getReplyCode());

        threadsReplyComment.setToAvatar(threadsReply.getFromAvatar());
        threadsReplyComment.setToName(threadsReply.getFromName());
        threadsReplyComment.setToUsercode(threadsReply.getUserCode());
        if (!StringUtils.isEmpty(submit.getReplyCommentCode())) {
            ThreadsReplyComment replyComment = threadsReplyCommentMapper.selectByCode(submit.getReplyCommentCode());
            if (replyComment == null || replyComment.getDeleted()) {
                throw new CommentsRuntimeException(-1, "帖子回复评论不存在");
            }
            threadsReplyComment.setToUsercode(replyComment.getUserCode());
            threadsReplyComment.setToName(replyComment.getFromName());
            threadsReplyComment.setToAvatar(replyComment.getFromAvatar());
            threadsReplyComment.setReplyCommentCode(submit.getReplyCommentCode());
        }

        threadsReplyComment.setThreadsCode(threadsReply.getThreadsCode());
        threadsReplyComment.setUserCode(user.getCode());

        return threadsReplyComment;
    }

//    /**
//     * 删除评论回复
//     *
//     * @param code
//     */
//    public void delete(String code, User user) {
//        CommentsReply commentsReply = commentsReplyMapper.selectByCode(code);
//
//        if (commentsReply == null || commentsReply.getDeleted()) {
//            throw new CommentsRuntimeException(-1, "评论回复不存在");
//        }
//
//        if (!commentsReply.getUserCode().equals(user.getCode())) {
//            throw new CommentsRuntimeException(-1, "回复不属于你，不可删除");
//        }
//
//        commentsReply.setDeleted(true);
//
//        commentsReplyMapper.updateByPrimaryKeySelective(commentsReply);
//    }
}
