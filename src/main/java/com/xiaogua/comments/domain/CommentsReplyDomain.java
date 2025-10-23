package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.comments.CommentsReplyPage;
import com.xiaogua.comments.bean.comments.CommentsReplyPageInfo;
import com.xiaogua.comments.bean.comments.CommentsReplyRest;
import com.xiaogua.comments.bean.commentsReply.CommentsReplySubmit;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bl.*;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class CommentsReplyDomain {

    @Autowired
    private CommentsReplyBl commentsReplyBl;

    @Autowired
    private UserBl userBl;

    @Autowired
    CreditBl creditBl;

    @Autowired
    CommentsBl commentsBl;

    @Autowired
    CommentsReplyLikeBl commentsReplyLikeBl;

    @Autowired
    WxUtil wxUtil;

    @Autowired
    private MessageBl messageBl;

    /**
     * 保存评论回复信息
     *
     * @param submit 评论回复信息
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder save(CommentsReplySubmit submit, String userCode) {
        VerifyParamsUtil.notNull(submit, "回复不可为空");
        VerifyParamsUtil.hasText(submit.getCommentsCode(), "评论编号不可为空");
        VerifyParamsUtil.hasText(submit.getContent(), "回复内容不可为空");
        VerifyParamsUtil.isTrue(submit.getContent().length() <= 500, "回复内容不可超过500");
        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(submit.getCommentsCode());
        VerifyParamsUtil.notNull(commentsInfo, "评论信息不存在");

        // 微信验证评论回复内容合法性
        wxUtil.validateContent(submit.getContent());

        CommentsReply commentsReply = commentsReplyBl.save(submit, userCode);

        // 非本人评论回复加分
        if (!commentsInfo.getUserCode().equals(userCode)) {
            creditBl.commentsReply(userCode, commentsInfo.getUserCode(), commentsReply.getCommentsCode());
        }

        //评论回复通知
        messageBl.sendCommentReplyMessage(commentsReply);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取评论回复信息
     *
     * @param pageInfo
     * @return
     */
    public ResponsePageBuilder getCommentsReplyPage(CommentsReplyPageInfo pageInfo, UserLoginState userLoginState) {
        CommentsReplyPage commentsReplyPage = commentsReplyBl.getCommentsReplyPage(pageInfo, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论回复信息
        if (commentsReplyPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToCommentsReplyRests(commentsReplyPage.getCommentsReplyList(), userLoginState));
        }

        builder.setPageInfo(commentsReplyPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 点赞
     *
     * @param code
     * @return
     */
    public ResponseBuilder like(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "评论回复编号不可为空");
        CommentsReply commentsReply = commentsReplyBl.getCommentsReply(code);
        VerifyParamsUtil.notNull(commentsReply, "评论回复不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        CommentsReplyLike like = commentsReplyLikeBl.getCommentsReplyLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like == null, "已点赞，请勿重复点赞");

        int count = commentsReplyBl.like(code, user);

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
        VerifyParamsUtil.hasText(code, "评论回复编号不可为空");
        CommentsReply commentsReply = commentsReplyBl.getCommentsReply(code);
        VerifyParamsUtil.notNull(commentsReply, "评论回复不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        CommentsReplyLike like = commentsReplyLikeBl.getCommentsReplyLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like != null, "未点赞，不可取消点赞");

        int count = commentsReplyBl.unlike(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 批量转换CommentsReply
     *
     * @param commentsReplyList
     * @return
     */
    public List<CommentsReplyRest> convertToCommentsReplyRests(List<CommentsReply> commentsReplyList,
        UserLoginState userLoginState) {
        List<CommentsReplyRest> commentsReplyRestList = new ArrayList<>();
        for (CommentsReply commentsReply : commentsReplyList) {
            commentsReplyRestList.add(convertToCommentsReplyRest(commentsReply, userLoginState));
        }

        return commentsReplyRestList;
    }

    /**
     * 转换评论回复信息Rest
     *
     * @param commentsReply 评论回复信息
     * @return
     */
    private CommentsReplyRest convertToCommentsReplyRest(CommentsReply commentsReply, UserLoginState userLoginState) {
        CommentsReplyRest commentsInfoRest = new CommentsReplyRest(commentsReply);

        // 补充是否点赞
        if (userLoginState != null) {
            commentsInfoRest.setLiked(commentsReplyLikeBl.isLiked(commentsReply.getCode(), userLoginState.getCode()));
        }

        return commentsInfoRest;
    }

    /**
     * 删除评论回复
     *
     * @param code 回复编号
     * @return
     */
    public ResponseBuilder delete(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "回复编号不可为空");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException("用户不存在");
        }

        commentsReplyBl.delete(code, user);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }
}


