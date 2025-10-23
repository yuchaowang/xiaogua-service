package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.comments.CommentsReplyPage;
import com.xiaogua.comments.bean.comments.CommentsReplyPageInfo;
import com.xiaogua.comments.bean.commentsReply.CommentsReplySubmit;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.CommentsInfo;
import com.xiaogua.comments.dal.entity.CommentsReply;
import com.xiaogua.comments.dal.entity.FileInfo;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.CommentsReplyMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 评论回复 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class CommentsReplyBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsReplyBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsReplyMapper commentsReplyMapper;

    @Autowired
    private CommentsBl commentsBl;

    @Autowired
    private UserBl userBl;

    @Autowired
    private FileBl fileBl;

    @Autowired
    private CommentsReplyLikeBl commentsReplyLikeBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 新增评论回复
     *
     * @param submit   回复信息
     * @param userCode 用户编号
     */
    public CommentsReply save(CommentsReplySubmit submit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        CommentsReply commentsReply = mappingCommentsReply(submit, user);
        commentsReplyMapper.insertSelective(commentsReply);

        return commentsReply;
    }

    /**
     * 分页获取评论回复信息
     *
     * @param pageInfo    分页信息
     * @param showDeleted 是否展示删除信息
     * @return
     */
    public CommentsReplyPage getCommentsReplyPage(CommentsReplyPageInfo pageInfo, boolean showDeleted) {
        int count = commentsReplyMapper.count(pageInfo.getCommentsCode(), showDeleted, null, null);

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<CommentsReply> commentsReplyList = commentsReplyMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getCommentsCode(), showDeleted);

        CommentsReplyPage commentsPage = new CommentsReplyPage();

        if (!CollectionUtils.isEmpty(commentsReplyList)) {
            commentsPage.setCommentsReplyList(commentsReplyList);
        }
        commentsPage.setPagingInfo(pagingInfo);
        return commentsPage;
    }

    /**
     * 统计点评回复数量
     *
     * @param commentsInfoCode 点评编号
     * @param showDeleted      是否统计删除
     * @param startDate        开始时间
     * @param endDate          结束时间
     * @return
     */
    public int count(String commentsInfoCode, boolean showDeleted, String startDate, String endDate) {
        return commentsReplyMapper.count(commentsInfoCode, showDeleted, startDate, endDate);
    }

    /**
     * 获取评论回复信息
     *
     * @param code
     * @return
     */
    public CommentsReply getCommentsReply(String code) {
        CommentsReply commentsReply = commentsReplyMapper.selectByCodeWithOutDel(code);

        return commentsReply;
    }

    /**
     * 点赞
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int like(String code, User user) {
        CommentsReply commentsReply = commentsReplyMapper.selectByCodeWithOutDel(code);
        if (commentsReply == null) {
            throw new CommentsRuntimeException(-1, "评论回复不存在");
        }

        int likeNum = commentsReply.getLikeNum();
        likeNum++;
        commentsReply.setLikeNum(likeNum);

        // 增加评论点赞信息
        commentsReplyLikeBl.save(code, user);

        commentsReplyMapper.updateByPrimaryKeySelective(commentsReply);

        //评论回复点赞通知
        messageBl.sendCommentsReplyLikeMessage(code, user, commentsReply);
        return likeNum;
    }

    /**
     * 取消点赞
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int unlike(String code, User user) {
        CommentsReply commentsReply = commentsReplyMapper.selectByCodeWithOutDel(code);
        if (commentsReply == null) {
            throw new CommentsRuntimeException(-1, "评论回复不存在");
        }

        int likeNum = commentsReply.getLikeNum();
        likeNum--;
        commentsReply.setLikeNum(likeNum);

        // 增加评论点赞信息
        commentsReplyLikeBl.delete(code, user.getCode());

        commentsReplyMapper.updateByPrimaryKeySelective(commentsReply);

        return likeNum;
    }

    /**
     * 转换dal.CommentsReply
     *
     * @param submit
     * @param user
     * @return
     */
    private CommentsReply mappingCommentsReply(CommentsReplySubmit submit, User user) {
        CommentsReply commentsReply = new CommentsReply();

        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(submit.getCommentsCode());
        if (commentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        if (StringUtils.hasText(submit.getReplyCode())) {
            CommentsReply replyCommentsReply = commentsReplyMapper.selectByCode(submit.getReplyCode());
            if (replyCommentsReply == null) {
                throw new CommentsRuntimeException(-1, "回复不存在");
            }
            commentsReply.setReplyCode(submit.getReplyCode());
            commentsReply.setToUsercode(replyCommentsReply.getUserCode());
            commentsReply.setToAvatar(replyCommentsReply.getFromAvatar());
            commentsReply.setToName(replyCommentsReply.getFromName());
        } else {
            //被回复人应该是被回复的那条评论的评论人
            commentsReply.setToUsercode(commentsInfo.getUserCode());
            commentsReply.setToAvatar(commentsInfo.getFromAvatar());
            commentsReply.setToName(commentsInfo.getFromName());
        }

        commentsReply.setCommentsCode(submit.getCommentsCode());
        commentsReply.setContent(submit.getContent());
        commentsReply.setUserCode(user.getCode());
        commentsReply.setDeleted(false);

        FileInfo userAvatar = fileBl.getFileInfo(user.getAvatar());
        if (userAvatar != null) {
            commentsReply.setFromAvatar(userAvatar.getUrl());
        }
        commentsReply.setFromName(user.getName());



        commentsReply.setCode(BizCodeUtil.genLongBizCode(TableCode.T_COMMENTS_REPLY.getCode()));
        commentsReply.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return commentsReply;
    }

    /**
     * 删除评论回复
     *
     * @param code
     */
    public void delete(String code, User user) {
        CommentsReply commentsReply = commentsReplyMapper.selectByCode(code);

        if (commentsReply == null || commentsReply.getDeleted()) {
            throw new CommentsRuntimeException(-1, "评论回复不存在");
        }

        if (!commentsReply.getUserCode().equals(user.getCode())) {
            throw new CommentsRuntimeException(-1, "回复不属于你，不可删除");
        }

        commentsReply.setDeleted(true);

        commentsReplyMapper.updateByPrimaryKeySelective(commentsReply);
    }
}
