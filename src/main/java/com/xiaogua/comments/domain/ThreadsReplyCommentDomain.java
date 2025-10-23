package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentAdminRest;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPage;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPageInfo;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentRest;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentSubmit;
import com.xiaogua.comments.bl.ThreadsInfoBl;
import com.xiaogua.comments.bl.ThreadsReplyBl;
import com.xiaogua.comments.bl.ThreadsReplyCommentBl;
import com.xiaogua.comments.bl.ThreadsReplyCommentLikeBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.dal.entity.ThreadsReply;
import com.xiaogua.comments.dal.entity.ThreadsReplyComment;
import com.xiaogua.comments.dal.entity.ThreadsReplyCommentLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 帖子回复评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class ThreadsReplyCommentDomain {

    @Autowired
    ThreadsReplyBl threadsReplyBl;

    @Autowired
    UserBl userBl;

    @Autowired
    ThreadsInfoBl threadsInfoBl;

    @Autowired
    FileDomain fileDomain;

    @Autowired
    ThreadsReplyCommentBl threadsReplyCommentBl;

    @Autowired
    ThreadsReplyCommentLikeBl threadsReplyCommentLikeBl;

    @Autowired
    WxUtil wxUtil;

    /**
     * 保存帖子回复评论
     *
     * @param submit 帖子回复评论信息
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder save(ThreadsReplyCommentSubmit submit, String userCode) {
        VerifyParamsUtil.notNull(submit, "评论不可为空");
        VerifyParamsUtil.hasText(submit.getReplyCode(), "帖子回复编号不可为空");
        VerifyParamsUtil.hasText(submit.getContent(), "回复内容不可为空");
        VerifyParamsUtil.isTrue(submit.getContent().length() <= 500, "回复内容不可超过500");
        ThreadsReply threadsReply = threadsReplyBl.getThreadsReply(submit.getReplyCode());
        VerifyParamsUtil.notNull(threadsReply, "帖子回复不存在");
        VerifyParamsUtil.isTrue(!threadsReply.getDeleted(), "帖子回复不存在");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(submit.getReplyCommentCode())) {
            ThreadsReplyComment threadsReplyComment = threadsReplyCommentBl.getReplyComment(submit.getReplyCommentCode());
            VerifyParamsUtil.notNull(threadsReplyComment, "帖子回复评论不存在");
            VerifyParamsUtil.isTrue(!threadsReplyComment.getDeleted(), "帖子回复评论不存在");
            VerifyParamsUtil.isTrue(threadsReplyComment.getReplyCode().equals(submit.getReplyCode()), "帖子回复评论不属于该帖子回复");
        }

        // 微信验证帖子回复评论内容合法性
        wxUtil.validateContent(submit.getContent());

        ThreadsReplyComment threadsReplyComment= threadsReplyCommentBl.save(submit, userCode);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(threadsReplyComment.getCode());
        return builder;
    }

    /**
     * 分页获取帖子回复评论信息
     *
     * @param pageInfo 分页信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponsePageBuilder getThreadsReplyCommentPage(ThreadsReplyCommentPageInfo pageInfo, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(pageInfo.getReplyCode(), "帖子回复编号不可为空");

        ThreadsReplyCommentPage replyCommentPage = threadsReplyCommentBl.getThreadsReplyCommentPage(pageInfo, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论回复信息
        if (replyCommentPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToThreadsReplyCommentRests(replyCommentPage.getReplyComments(), userLoginState));
        }

        builder.setPageInfo(replyCommentPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 点赞
     *
     * @param code     帖子回复评论编号
     * @param userCode 用户编号
     * @return
     */
    public ResponseBuilder like(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "回复评论编号不可为空");
        ThreadsReplyComment replyComment = threadsReplyCommentBl.getReplyComment(code);
        VerifyParamsUtil.notNull(replyComment != null && !replyComment.getDeleted(), "回复评论不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsReplyCommentLike like = threadsReplyCommentLikeBl.getThreadsReplyCommetnLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like == null, "已点赞，请勿重复点赞");

        int count = threadsReplyCommentBl.like(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 取消点赞
     *
     * @param code     评论编号
     * @param userCode 用户编号
     * @return
     */
    public ResponseBuilder unlike(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "回复评论编号不可为空");
        ThreadsReplyComment replyComment = threadsReplyCommentBl.getReplyComment(code);
        VerifyParamsUtil.notNull(replyComment != null && !replyComment.getDeleted(), "回复评论不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsReplyCommentLike like = threadsReplyCommentLikeBl.getThreadsReplyCommetnLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like != null, "未点赞，不可取消点赞");

        int count = threadsReplyCommentBl.unlike(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 批量转换ThreadsReplyCommentRest
     *
     * @param replyComments 帖子回复评论列表
     * @param userLoginState 用户信息
     * @return
     */
    public List<ThreadsReplyCommentRest> convertToThreadsReplyCommentRests(List<ThreadsReplyComment> replyComments,
        UserLoginState userLoginState) {
        List<ThreadsReplyCommentRest> replyCommentRests = new ArrayList<>();
        for (ThreadsReplyComment replyComment : replyComments) {
            replyCommentRests.add(convertToThreadsReplyCommentRest(replyComment, userLoginState));
        }

        return replyCommentRests;
    }

    /**
     * 批量转换ThreadsReplyCommentRest
     *
     * @param replyComments 帖子回复评论列表
     * @return
     */
    public List<ThreadsReplyCommentAdminRest> convertToThreadsReplyCommentAdminRests(List<ThreadsReplyComment> replyComments) {
        List<ThreadsReplyCommentAdminRest> replyCommentRests = new ArrayList<>();
        for (ThreadsReplyComment replyComment : replyComments) {
            replyCommentRests.add(convertToThreadsReplyCommentAdminRest(replyComment));
        }

        return replyCommentRests;
    }

    /**
     * 转换帖子回复信息Rest
     *
     * @param replyComment 帖子回复评论信息
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsReplyCommentRest convertToThreadsReplyCommentRest(ThreadsReplyComment replyComment, UserLoginState userLoginState) {
        ThreadsReplyCommentRest replyCommentRest = new ThreadsReplyCommentRest(replyComment);

        // 下载头像
        if (!StringUtils.isEmpty(replyComment.getFromAvatar())) {
            replyCommentRest.setFromAvatar(fileDomain.getFileUrl(replyComment.getFromAvatar()));
        }
        if (!StringUtils.isEmpty(replyComment.getToAvatar())) {
            replyCommentRest.setToAvatar(fileDomain.getFileUrl(replyComment.getToAvatar()));
        }

        // 补充是否点赞
        if (userLoginState != null) {
            replyCommentRest.setLiked(threadsReplyCommentLikeBl.isLiked(replyComment.getCode(), userLoginState.getCode()));
        }

        return replyCommentRest;
    }

    /**
     * 转换帖子回复信息Rest
     *
     * @param replyComment 帖子回复评论信息
     * @return
     */
    private ThreadsReplyCommentAdminRest convertToThreadsReplyCommentAdminRest(ThreadsReplyComment replyComment) {
        ThreadsReplyCommentAdminRest replyCommentRest = new ThreadsReplyCommentAdminRest(replyComment);

        // 下载头像
        if (!StringUtils.isEmpty(replyComment.getFromAvatar())) {
            replyCommentRest.setFromAvatar(fileDomain.getFileUrl(replyComment.getFromAvatar()));
        }
        if (!StringUtils.isEmpty(replyComment.getToAvatar())) {
            replyCommentRest.setToAvatar(fileDomain.getFileUrl(replyComment.getToAvatar()));
        }

        return replyCommentRest;
    }
}


