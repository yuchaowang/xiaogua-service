package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPage;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageInfo;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplySubmit;
import com.xiaogua.comments.common.constant.CreditEvent;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.CreditRule;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import com.xiaogua.comments.dal.entity.ThreadsReply;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.CreditRuleMapper;
import com.xiaogua.comments.dal.mapper.ThreadsReplyMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.RedisCache;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 帖子回复 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class ThreadsReplyBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsReplyMapper threadsReplyMapper;

    @Autowired
    private ThreadsInfoBl threadsInfoBl;

    @Autowired
    private UserBl userBl;

    @Autowired
    private FileBl fileBl;

    @Autowired
    private MessageBl messageBl;

    @Autowired
    private ThreadsReplyLikeBl threadsReplyLikeBl;

    /**
     * 新增帖子回复
     *
     * @param submit   回复信息
     * @param userCode 用户编号
     */
    public ThreadsReply save(ThreadsReplySubmit submit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(submit.getThreadsCode());
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        ThreadsReply threadsReply = mappingThreadsReply(submit, user);
        threadsReplyMapper.insertSelective(threadsReply);

        //帖子回复通知
        messageBl.sendThreadsReplyMessage(threadsInfo, threadsReply);
        return threadsReply;
    }

    /**
     * 分页获取帖子回复信息
     *
     * @param pageInfo    分页信息
     * @param showDeleted 是否展示删除信息
     * @return
     */
    public ThreadsReplyPage getThreadsReplyPage(ThreadsReplyPageInfo pageInfo, boolean showDeleted) {
        int count = threadsReplyMapper.count(pageInfo.getThreadsCode(), showDeleted, null, null);

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<ThreadsReply> threadsReplyList = threadsReplyMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getThreadsCode(), showDeleted);

        ThreadsReplyPage threadsReplyPage = new ThreadsReplyPage();

        if (!CollectionUtils.isEmpty(threadsReplyList)) {
            threadsReplyPage.setThreadsReplyList(threadsReplyList);
        }
        threadsReplyPage.setPagingInfo(pagingInfo);
        return threadsReplyPage;
    }

    /**
     * 统计帖子回复数量
     * @param threadsInfoCode 帖子编号
     * @param showDeleted 是否展示删除
     * @return
     */
    public int count(String threadsInfoCode, boolean showDeleted) {
        return threadsReplyMapper.count(threadsInfoCode, showDeleted, null, null);
    }

    /**
     * 统计帖子回复数量
     * @param threadsInfoCode 帖子编号
     * @param showDeleted 是否展示删除
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    public int count(String threadsInfoCode, boolean showDeleted, String startDate, String endDate) {
        return threadsReplyMapper.count(threadsInfoCode, showDeleted, startDate, endDate);
    }

    /**
     * 获取评论回复信息
     *
     * @param code
     * @return
     */
    public ThreadsReply getThreadsReply(String code) {
        ThreadsReply threadsReply = threadsReplyMapper.selectByCode(code);

        return threadsReply;
    }

    /**
     * 点赞
     *
     * @param code 帖子回复编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int like(String code, User user) {
        ThreadsReply threadsReply = threadsReplyMapper.selectByCode(code);
        if (threadsReply == null || threadsReply.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子回复不存在");
        }

        int likeNum = threadsReply.getLikeNum();
        likeNum++;
        threadsReply.setLikeNum(likeNum);

        // 增加帖子回复点赞信息
        threadsReplyLikeBl.save(code, user);

        threadsReplyMapper.updateByPrimaryKeySelective(threadsReply);

        String threadsCode = threadsReply.getThreadsCode();
        //帖子回复被赞通知
        messageBl.sendThreadsReplyLikeMessage(code, user, threadsReply, threadsCode);
        return likeNum;
    }


    /**
     * 取消点赞
     *
     * @param code 帖子回复编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int unlike(String code, User user) {
        ThreadsReply threadsReply = threadsReplyMapper.selectByCode(code);
        if (threadsReply == null || threadsReply.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子回复不存在");
        }


        int likeNum = threadsReply.getLikeNum();
        likeNum--;
        threadsReply.setLikeNum(likeNum);

        // 增加评论点赞信息
        threadsReplyLikeBl.delete(code, user.getCode());

        threadsReplyMapper.updateByPrimaryKeySelective(threadsReply);

        return likeNum;
    }

    /**
     * 转换dal.ThreadsReply
     *
     * @param submit 帖子回复提交信息
     * @param user 用户信息
     * @return
     */
    private ThreadsReply mappingThreadsReply(ThreadsReplySubmit submit, User user) {
        ThreadsReply threadsReply = new ThreadsReply();
        threadsReply.setThreadsCode(submit.getThreadsCode());
        threadsReply.setContent(submit.getContent());
        threadsReply.setUserCode(user.getCode());
        threadsReply.setDeleted(false);
        threadsReply.setFromName(user.getName());
        threadsReply.setFromAvatar(user.getAvatar());
        threadsReply.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_REPLY.getCode()));
        threadsReply.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        return threadsReply;
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
