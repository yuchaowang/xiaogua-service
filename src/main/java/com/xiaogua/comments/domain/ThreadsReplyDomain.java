package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyAdminRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPage;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageInfo;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplySubmit;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentAdminPageRest;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentAdminRest;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPage;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPageInfo;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPageRest;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentRest;
import com.xiaogua.comments.bl.CreditBl;
import com.xiaogua.comments.bl.ThreadsInfoBl;
import com.xiaogua.comments.bl.ThreadsReplyBl;
import com.xiaogua.comments.bl.ThreadsReplyCommentBl;
import com.xiaogua.comments.bl.ThreadsReplyLikeBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.dal.entity.ThreadsInfo;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import com.xiaogua.comments.dal.entity.ThreadsReply;
import com.xiaogua.comments.dal.entity.ThreadsReplyLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class ThreadsReplyDomain {

    @Autowired
    private ThreadsReplyBl threadsReplyBl;

    @Autowired
    private ThreadsReplyCommentBl threadsReplyCommentBl;

    @Autowired
    ThreadsReplyCommentDomain threadsReplyCommentDomain;

    @Autowired
    ThreadsInfoBl threadsInfoBl;

    @Autowired
    FileDomain fileDomain;

    @Autowired
    private UserBl userBl;

    @Autowired
    private ThreadsReplyLikeBl threadsReplyLikeBl;

    @Autowired
    private CreditBl creditBl;

    @Autowired
    WxUtil wxUtil;

    /**
     * 保存评论回复信息
     *
     * @param submit 评论回复信息
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder save(ThreadsReplySubmit submit, String userCode) {
        VerifyParamsUtil.notNull(submit, "回复不可为空");
        VerifyParamsUtil.hasText(submit.getThreadsCode(), "帖子编号不可为空");
        VerifyParamsUtil.hasText(submit.getContent(), "回复内容不可为空");
        VerifyParamsUtil.isTrue(submit.getContent().length() <= 500, "回复内容不可超过500");
        ThreadsInfo threadsInfo = threadsInfoBl.getThreadsInfo(submit.getThreadsCode());
        VerifyParamsUtil.notNull(threadsInfo, "帖子不存在");
        VerifyParamsUtil.isTrue(!threadsInfo.getDeleted(), "帖子不存在");

        // 微信验证帖子回复内容合法性
        wxUtil.validateContent(submit.getContent());

        ThreadsReply threadsReply= threadsReplyBl.save(submit, userCode);

        // 非本人回复加分
        if (!threadsInfo.getUserCode().equals(userCode)) {
            creditBl.threadsReply(userCode, threadsInfo.getUserCode(), threadsInfo.getCode());
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(threadsReply.getCode());
        return builder;
    }

    /**
     * 分页获取帖子回复信息
     *
     * @param pageInfo 分页信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponsePageBuilder getThreadsReplyPage(ThreadsReplyPageInfo pageInfo, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(pageInfo.getThreadsCode(), "帖子编号不可为空");
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(pageInfo.getThreadsCode());
        VerifyParamsUtil.isTrue(threadsInfo != null && !threadsInfo.getDeleted(), "帖子不存在");


        ThreadsReplyPage threadsReplyPage = threadsReplyBl.getThreadsReplyPage(pageInfo, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论回复信息
        if (threadsReplyPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToThreadsReplyRests(threadsReplyPage.getThreadsReplyList(), userLoginState));
        }

        builder.setPageInfo(threadsReplyPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 点赞
     *
     * @param code     帖子回复编号
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder like(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "帖子回复编号不可为空");
        ThreadsReply threadsReply = threadsReplyBl.getThreadsReply(code);
        VerifyParamsUtil.isTrue(threadsReply != null && !threadsReply.getDeleted(), "帖子回复不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsReplyLike like = threadsReplyLikeBl.getThreadsReplyLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like == null, "已点赞，请勿重复点赞");

        int count = threadsReplyBl.like(code, user);

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
        VerifyParamsUtil.hasText(code, "帖子回复编号不可为空");
        ThreadsReply threadsReply = threadsReplyBl.getThreadsReply(code);
        VerifyParamsUtil.isTrue(threadsReply != null && !threadsReply.getDeleted(), "帖子回复不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsReplyLike like = threadsReplyLikeBl.getThreadsReplyLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like != null, "未点赞，不可取消点赞");

        int count = threadsReplyBl.unlike(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 批量转换ThreadsReplyRest
     *
     * @param threadsReplyList 帖子回复列表
     * @param userLoginState 用户信息
     * @return
     */
    public List<ThreadsReplyRest> convertToThreadsReplyRests(List<ThreadsReply> threadsReplyList,
        UserLoginState userLoginState) {
        List<ThreadsReplyRest> threadsReplyRests = new ArrayList<>();
        for (ThreadsReply threadsReply : threadsReplyList) {
            threadsReplyRests.add(convertToThreadsReplyRest(threadsReply, userLoginState));
        }

        return threadsReplyRests;
    }

    /**
     * 批量转换ThreadsReplyAdminRest
     *
     * @param threadsReplyList 帖子回复列表
     * @return
     */
    public List<ThreadsReplyAdminRest> convertToThreadsReplyAdminRests(List<ThreadsReply> threadsReplyList) {
        List<ThreadsReplyAdminRest> threadsReplyAdminRests = new ArrayList<>();
        for (ThreadsReply threadsReply : threadsReplyList) {
            threadsReplyAdminRests.add(convertToThreadsReplyAdminRest(threadsReply));
        }

        return threadsReplyAdminRests;
    }

    /**
     * 转换帖子回复信息Rest
     *
     * @param threadsReply 帖子回复信息
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsReplyRest convertToThreadsReplyRest(ThreadsReply threadsReply, UserLoginState userLoginState) {
        ThreadsReplyRest threadsReplyRest = new ThreadsReplyRest(threadsReply);


        // 下载头像
        if (!StringUtils.isEmpty(threadsReply.getFromAvatar())) {
            threadsReplyRest.setFromAvatar(fileDomain.getFileUrl(threadsReply.getFromAvatar()));
        }

        // 补充是否点赞
        if (userLoginState != null) {
            threadsReplyRest.setLiked(threadsReplyLikeBl.isLiked(threadsReply.getCode(), userLoginState.getCode()));
        }

        // 补全帖子回复评论信息
        threadsReplyRest.setReplyCommentPageRest(getThreadsReplyCommentPage(threadsReply.getCode(), userLoginState));

        return threadsReplyRest;
    }

    /**
     * 转换帖子回复信息Rest-管理后台
     *
     * @param threadsReply 帖子回复信息
     * @return
     */
    private ThreadsReplyAdminRest convertToThreadsReplyAdminRest(ThreadsReply threadsReply) {
        ThreadsReplyAdminRest adminRest = new ThreadsReplyAdminRest(threadsReply);

        // 下载头像
        if (!StringUtils.isEmpty(threadsReply.getFromAvatar())) {
            adminRest.setFromAvatar(fileDomain.getFileUrl(threadsReply.getFromAvatar()));
        }

        // 补全帖子回复评论信息
        adminRest.setReplyCommentPageRest(getDefaultThreadsReplyCommentAdminPage(threadsReply.getCode()));

        return adminRest;
    }

    /**
     * 补充分页回复评论信息
     *
     * @param code 帖子回复编号
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsReplyCommentPageRest getThreadsReplyCommentPage(String code, UserLoginState userLoginState) {
        ThreadsReplyCommentPageInfo pageInfo = new ThreadsReplyCommentPageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setPageSize(10);
        pageInfo.setReplyCode(code);
        ThreadsReplyCommentPage replyCommentPage = threadsReplyCommentBl.getThreadsReplyCommentPage(pageInfo, false);

        ThreadsReplyCommentPageRest replyCommentPageRest = new ThreadsReplyCommentPageRest();
        if (!CollectionUtils.isEmpty(replyCommentPage.getReplyComments())) {
            List<ThreadsReplyCommentRest> replyCommentRests = threadsReplyCommentDomain
                .convertToThreadsReplyCommentRests(replyCommentPage.getReplyComments(), userLoginState);
            replyCommentPageRest.setPagingInfo(replyCommentPage.getPagingInfo());
            replyCommentPageRest.setReplyCommentRests(replyCommentRests);
        }

        return replyCommentPageRest;
    }

    /**
     * 补充帖子回复评论信息-管理后台
     *
     * @param code 帖子回复编号
     * @return
     */
    private ThreadsReplyCommentAdminPageRest getDefaultThreadsReplyCommentAdminPage(String code) {
        ThreadsReplyCommentPageInfo pageInfo = new ThreadsReplyCommentPageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setPageSize(10);
        pageInfo.setReplyCode(code);
        ThreadsReplyCommentPage replyCommentPage = threadsReplyCommentBl.getThreadsReplyCommentPage(pageInfo, true);

        ThreadsReplyCommentAdminPageRest replyCommentPageRest = new ThreadsReplyCommentAdminPageRest();
        if (!CollectionUtils.isEmpty(replyCommentPage.getReplyComments())) {
            List<ThreadsReplyCommentAdminRest> replyCommentRests = threadsReplyCommentDomain
                .convertToThreadsReplyCommentAdminRests(replyCommentPage.getReplyComments());
            replyCommentPageRest.setPagingInfo(replyCommentPage.getPagingInfo());
            replyCommentPageRest.setReplyCommentRests(replyCommentRests);
        }

        return replyCommentPageRest;
    }
}


