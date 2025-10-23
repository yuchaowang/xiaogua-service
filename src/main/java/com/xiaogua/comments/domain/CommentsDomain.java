package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.comments.CommentsCommonPageInfo;
import com.xiaogua.comments.bean.comments.CommentsInfoRest;
import com.xiaogua.comments.bean.comments.CommentsInfoSimpleRest;
import com.xiaogua.comments.bean.comments.CommentsInfoSubmit;
import com.xiaogua.comments.bean.comments.CommentsPage;
import com.xiaogua.comments.bean.comments.CommentsPageInfoDal;
import com.xiaogua.comments.bean.comments.CommentsReplyPage;
import com.xiaogua.comments.bean.comments.CommentsReplyPageInfo;
import com.xiaogua.comments.bean.comments.CommentsReplyPageRest;
import com.xiaogua.comments.bean.comments.CommentsReplyRest;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bl.CommentsBl;
import com.xiaogua.comments.bl.CommentsInfoLikeBl;
import com.xiaogua.comments.bl.CommentsReplyBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.dal.entity.CommentsInfo;
import com.xiaogua.comments.dal.entity.CommentsInfoLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class CommentsDomain {

    @Autowired
    private CommentsBl commentsBl;

    @Autowired
    private CommentsInfoLikeBl commentsInfoLikeBl;

    @Autowired
    private CommentsReplyBl commentsReplyBl;

    @Autowired
    private UserBl userBl;

    @Autowired
    private CommentsReplyDomain commentsReplyDomain;

    @Autowired
    private CommentsLikeDomain commentsLikeDomain;

    @Autowired
    private BrandDomain brandDomain;

    @Autowired
    private WxUtil wxUtil;

    /**
     * 分页获取评论信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getCommentsPage(CommentsCommonPageInfo pageInfo, UserLoginState userLoginState) {
        CommentsPageInfoDal pageInfoDal = new CommentsPageInfoDal(pageInfo);
        CommentsPage commentsPage = commentsBl.getCommentsPage(pageInfoDal);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(commentsPage.getCommentsInfos())) {
            builder.setData(convertToCommentsInfoRests(commentsPage.getCommentsInfos(), userLoginState));
        }

        builder.setPageInfo(commentsPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取我的评论信息
     *
     * @param pageInfo 分页信息
     * @param userLoginState 用户登录信息
     * @return
     */
    public ResponsePageBuilder getMyCommentsPage(CommentsCommonPageInfo pageInfo, UserLoginState userLoginState) {
        CommentsPageInfoDal pageInfoDal = new CommentsPageInfoDal(pageInfo);
        pageInfoDal.setUserCode(userLoginState.getCode());

        CommentsPage commentsPage = commentsBl.getCommentsPage(pageInfoDal);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(commentsPage.getCommentsInfos())) {
            builder.setData(convertToCommentsInfoSimpleRests(commentsPage.getCommentsInfos(), userLoginState));
        }

        builder.setPageInfo(commentsPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换评论信息Rest
     *
     * @param commentsInfos 评论信息
     * @return
     */
    private List<CommentsInfoRest> convertToCommentsInfoRests(List<CommentsInfo> commentsInfos,
        UserLoginState userLoginState) {
        List<CommentsInfoRest> commentsInfoRests = new ArrayList<>();
        for (CommentsInfo commentsInfo : commentsInfos) {
            commentsInfoRests.add(convertToCommentsInfoRest(commentsInfo, userLoginState));
        }

        return commentsInfoRests;
    }

    /**
     * 转换评论信息Rest
     *
     * @param commentsInfo 评论信息
     * @return
     */
    private CommentsInfoRest convertToCommentsInfoRest(CommentsInfo commentsInfo, UserLoginState userLoginState) {
        CommentsInfoRest commentsInfoRest = new CommentsInfoRest(commentsInfo);

        // 补充是否点赞
        if (userLoginState != null) {
            commentsInfoRest.setLiked(commentsLikeDomain.isLiked(commentsInfo.getCode(), userLoginState.getCode()));
        }

        // 补充回复信息
        CommentsReplyPageRest commentsReplyPageRest =
            getDefaultCommentsReplyPage(commentsInfo.getCode(), userLoginState);

        commentsInfoRest.setCommentsReplyPageRest(commentsReplyPageRest);
        return commentsInfoRest;
    }

    /**
     * 批量转换评论信息 Simple Rest
     *
     * @param commentsInfos 评论信息
     * @return
     */
    private List<CommentsInfoSimpleRest> convertToCommentsInfoSimpleRests(List<CommentsInfo> commentsInfos,
                                                                          UserLoginState userLoginState) {
        List<CommentsInfoSimpleRest> rests = new ArrayList<>();
        for (CommentsInfo commentsInfo : commentsInfos) {
            rests.add(convertToCommentsInfoSimpleRest(commentsInfo, userLoginState));
        }

        return rests;
    }

    /**
     * 转换评论信息 Simple Rest
     *
     * @param commentsInfo 评论信息
     * @return
     */
    private CommentsInfoSimpleRest convertToCommentsInfoSimpleRest(CommentsInfo commentsInfo, UserLoginState userLoginState) {
        CommentsInfoSimpleRest rest = new CommentsInfoSimpleRest(commentsInfo);

        if (StringUtils.isNotEmpty(rest.getToCode())) {
            rest.setBrandRest(brandDomain.getBrandSimpleRest(rest.getToCode()));
        }

        // 补充是否点赞
        if (userLoginState != null) {
            rest.setLiked(commentsLikeDomain.isLiked(commentsInfo.getCode(), userLoginState.getCode()));
        }

        return rest;
    }

    /**
     * 补充分页回复信息
     *
     * @param code 评论编号
     * @return
     */
    private CommentsReplyPageRest getDefaultCommentsReplyPage(String code, UserLoginState userLoginState) {
        CommentsReplyPageInfo pageInfo = new CommentsReplyPageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setPageSize(10);
        pageInfo.setCommentsCode(code);
        CommentsReplyPage commentsReplyPage = commentsReplyBl.getCommentsReplyPage(pageInfo, false);

        CommentsReplyPageRest commentsReplyPageRest = new CommentsReplyPageRest();
        if (!CollectionUtils.isEmpty(commentsReplyPage.getCommentsReplyList())) {
            List<CommentsReplyRest> commentsReplyRests = commentsReplyDomain
                .convertToCommentsReplyRests(commentsReplyPage.getCommentsReplyList(), userLoginState);
            commentsReplyPageRest.setPagingInfo(commentsReplyPage.getPagingInfo());
            commentsReplyPageRest.setCommentsReplyList(commentsReplyRests);
        }

        return commentsReplyPageRest;
    }

    /**
     * 获取单条评论信息
     *
     * @param code 评论编号
     * @return
     */
    public ResponseBuilder getComment(String code, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(code, "评论编号不可为空");

        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(code);
        ResponseBuilder builder = new ResponseBuilder();
        if (commentsInfo != null) {
            CommentsInfoRest commentsInfoRest = convertToCommentsInfoRest(commentsInfo, userLoginState);
            builder.setData(commentsInfoRest);
        }
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 保存评论
     *
     * @param submit 评论信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder save(CommentsInfoSubmit submit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        commentsBl.validate(submit);

        // 微信验证评论内容合法性
        wxUtil.validateContent(submit.getContent());

        CommentsInfo commentsInfo = commentsBl.save(submit, user);
        brandDomain.updateCommentNum(submit.getToCode());

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 点赞
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder like(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "评论编号不可为空");
        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(code);
        VerifyParamsUtil.isTrue(commentsInfo != null, "评论不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        CommentsInfoLike like = commentsInfoLikeBl.getCommentsInfoLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like == null, "已点赞，请勿重复点赞");

        // 点赞
        int count = commentsBl.like(code, user);

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
        VerifyParamsUtil.hasText(code, "评论编号不可为空");
        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(code);
        VerifyParamsUtil.isTrue(commentsInfo != null, "评论不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        CommentsInfoLike like = commentsInfoLikeBl.getCommentsInfoLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like != null, "未点赞，不可取消点赞");

        int count = commentsBl.unlike(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 删除评论
     *
     * @param code     评论编号
     * @param userCode 用户编号
     * @return
     */
    public ResponseBuilder delete(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "评论编号不可为空");

        CommentsInfo commentsInfo = commentsBl.getCommentsInfo(code);
        VerifyParamsUtil.isTrue(commentsInfo != null, "评论不存在");
        VerifyParamsUtil.isTrue(commentsInfo.getUserCode().equals(userCode), "评论不属于当前用户，不可删除");

        commentsBl.delete(code);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 通过品牌编号获取最大点赞数评论
     *
     * @param brandCode
     * @return
     */
    public CommentsInfoRest getMaxLikeNumCommentsInfoRest(String brandCode) {
        CommentsInfo commentsInfo = commentsBl.getMaxLikeNumCommentsInfo(brandCode);
        if (commentsInfo == null) {
            return new CommentsInfoRest();
        }

        return convertToCommentsInfoRest(commentsInfo, null);
    }
}


