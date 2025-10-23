package com.xiaogua.comments.domain;

import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.threads.ThreadsInfoAdminRest;
import com.xiaogua.comments.bean.threads.ThreadsInfoAdminSimpleRest;
import com.xiaogua.comments.bean.threads.ThreadsInfoRest;
import com.xiaogua.comments.bean.threads.ThreadsInfoSimpleRest;
import com.xiaogua.comments.bean.threads.ThreadsPage;
import com.xiaogua.comments.bean.threads.ThreadsPageInfo;
import com.xiaogua.comments.bean.threads.ThreadsPageInfoDal;
import com.xiaogua.comments.bean.threads.ThreadsSubmit;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyAdminPageRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyAdminRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPage;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageInfo;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyRest;
import com.xiaogua.comments.bl.*;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.controller.admin.ThreadsInfoController;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.ThreadsReplyMapper;
import com.xiaogua.comments.dal.mapper.VisitorMapper;
import com.xiaogua.comments.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 帖子domain
 *
 * @author wangyc
 * @date 2021-01-20
 */
@Service
public class ThreadsInfoDomain {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsInfoDomain.class);

    @Autowired
    private ThreadsInfoBl threadsInfoBl;

    @Autowired
    private ThreadsReplyBl threadsReplyBl;

    @Autowired
    private FileDomain fileDomain;

    @Autowired
    private ThreadsReplyDomain threadsReplyDomain;

    @Autowired
    private UserBl userBl;

    @Autowired
    private MessageBl messageBl;

    @Autowired
    private ThreadsInfoLikeBl threadsInfoLikeBl;

    @Autowired
    private CreditBl creditBl;

    @Autowired
    private WxUtil wxUtil;

    /**
     * 分页获取帖子
     *
     * @param pageInfo       分页信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponsePageBuilder getPage(ThreadsPageInfo pageInfo, UserLoginState userLoginState) {
        ThreadsPageInfoDal threadsPageInfoDal = new ThreadsPageInfoDal(pageInfo);
        ThreadsPage threadsInfoPage = threadsInfoBl.getPage(threadsPageInfoDal, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(threadsInfoPage.getThreadsInfos())) {
            builder.setData(convertToThreadsInfoSimpleRests(threadsInfoPage.getThreadsInfos(), userLoginState));
        }

        builder.setPageInfo(threadsInfoPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取帖子-admin
     *
     * @param pageInfo       分页信息
     * @return
     */
    public ResponsePageBuilder getAdminPage(ThreadsPageInfo pageInfo) {
        ThreadsPageInfoDal threadsPageInfoDal = new ThreadsPageInfoDal(pageInfo);
        ThreadsPage threadsInfoPage = threadsInfoBl.getPage(threadsPageInfoDal, true);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(threadsInfoPage.getThreadsInfos())) {
            builder.setData(convertToThreadsInfoAdminSimpleRests(threadsInfoPage.getThreadsInfos()));
        }

        builder.setPageInfo(threadsInfoPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取帖子
     *
     * @param pageInfo       分页信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponsePageBuilder getMyCommentsPage(ThreadsPageInfo pageInfo, UserLoginState userLoginState) {
        ThreadsPageInfoDal threadsPageInfoDal = new ThreadsPageInfoDal(pageInfo);
        threadsPageInfoDal.setUserCode(userLoginState.getCode());
        ThreadsPage threadsInfoPage = threadsInfoBl.getPage(threadsPageInfoDal, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换评论信息
        if (!CollectionUtils.isEmpty(threadsInfoPage.getThreadsInfos())) {
            builder.setData(convertToThreadsInfoSimpleRests(threadsInfoPage.getThreadsInfos(), userLoginState));
        }

        builder.setPageInfo(threadsInfoPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }


    /**
     * 保存帖子
     *
     * @param submit         提交信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponseBuilder save(ThreadsSubmit submit, UserLoginState userLoginState) {
        VerifyParamsUtil.notNull(submit, "提交内容不可为空");
        VerifyParamsUtil.hasText(submit.getTitle(), "标题不可为空");
        VerifyParamsUtil.isTrue(submit.getTitle().length() <= 30, "标题不可超过30个字");
        VerifyParamsUtil.hasText(submit.getContent(), "帖子内容不可为空");

        // 微信验证帖子内容合法性
        wxUtil.validateContent(submit.getContent());
        wxUtil.validateContent(submit.getTitle());

        String threadsInfoCode = threadsInfoBl.save(submit, userLoginState.getCode());

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(threadsInfoCode);
        return builder;
    }

    /**
     * 获取单个帖子详情
     *
     * @param code           帖子编号
     * @param userLoginState 用户信息
     * @return
     */
    public ResponseBuilder getThreadsInfo(String code, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(convertToThreadsInfoRest(threadsInfo, userLoginState));
        return builder;
    }

    /**
     * 获取单个帖子详情
     *
     * @param code           帖子编号
     * @return
     */
    public ResponseBuilder getAdminThreadsInfo(String code) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(code);
        if (threadsInfo == null) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(convertToThreadsInfoAdminRest(threadsInfo));
        return builder;
    }

    /**
     * 帖子加精
     * @param code 帖子编号
     * @param userLoginState 操作人信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder essence(String code, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(code);
        if (threadsInfo == null) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        VerifyParamsUtil.isTrue(!threadsInfo.getIsEssence(), "帖子已加精，请勿重复操作");

        // 加精
        threadsInfoBl.essence(code);

        // 加精加分
        JSONObject jsonObject = creditBl.essence(userLoginState.getCode(), threadsInfo.getUserCode(), threadsInfo.getCode());

        // 帖子加精发送消息通知
        messageBl.sendThreadsEssenceMessage(code, threadsInfo, jsonObject);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }


    /**
     * 取消帖子加精
     *
     * @param code 帖子编号
     * @param userLoginState 操作人信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder unessence(String code, UserLoginState userLoginState) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(code);
        if (threadsInfo == null) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        VerifyParamsUtil.isTrue(threadsInfo.getIsEssence(), "帖子未加精，无需取消");

        // 取消加精
        threadsInfoBl.unessence(code);

        // 取消加精扣分
        creditBl.unessence(userLoginState.getCode(), threadsInfo.getUserCode(), threadsInfo.getCode());

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 删除帖子
     * @param code 帖子编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder delete(String code) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");

        ThreadsInfoWithBLOBs threadsInfo = threadsInfoBl.getThreadsInfo(code);
        if (threadsInfo == null) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        threadsInfoBl.delete(code);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换帖子简单信息Rest
     *
     * @param threadsInfos   帖子信息
     * @param userLoginState 用户信息
     * @return
     */
    private List<ThreadsInfoSimpleRest> convertToThreadsInfoSimpleRests(List<ThreadsInfoWithBLOBs> threadsInfos,
        UserLoginState userLoginState) {
        List<ThreadsInfoSimpleRest> threadsInfoSimpleRests = new ArrayList<>();
        for (ThreadsInfoWithBLOBs threadsInfo : threadsInfos) {
            threadsInfoSimpleRests.add(convertToThreadsInfoSimpleRest(threadsInfo, userLoginState));
        }

        return threadsInfoSimpleRests;
    }

    /**
     * 批量转换帖子简单信息Rest-管理后台
     *
     * @param threadsInfos   帖子信息
     * @return
     */
    private List<ThreadsInfoAdminSimpleRest> convertToThreadsInfoAdminSimpleRests(List<ThreadsInfoWithBLOBs> threadsInfos) {
        List<ThreadsInfoAdminSimpleRest> threadsInfoAdminSimpleRests = new ArrayList<>();
        for (ThreadsInfoWithBLOBs threadsInfo : threadsInfos) {
            threadsInfoAdminSimpleRests.add(convertToThreadsInfoAdminSimpleRest(threadsInfo));
        }

        return threadsInfoAdminSimpleRests;
    }

    /**
     * 点赞帖子
     *
     * @param code     帖子编号
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder like(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");
        ThreadsInfo threadsInfo = threadsInfoBl.getThreadsInfo(code);
        VerifyParamsUtil.isTrue(threadsInfo != null && !threadsInfo.getDeleted(), "帖子不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsLike like = threadsInfoLikeBl.getThreadsLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like == null, "已点赞，请勿重复点赞");

        // 点赞
        int count = threadsInfoBl.like(code, user);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(count);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 取消点赞帖子
     *
     * @param code     帖子编号
     * @param userCode 用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder unlike(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "帖子编号不可为空");
        ThreadsInfo threadsInfo = threadsInfoBl.getThreadsInfo(code);
        VerifyParamsUtil.isTrue(threadsInfo != null && !threadsInfo.getDeleted(), "帖子不存在");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户不存在");
        }

        ThreadsLike like = threadsInfoLikeBl.getThreadsLike(code, user.getCode());
        VerifyParamsUtil.isTrue(like != null, "未点赞，不可取消点赞");

        int count = threadsInfoBl.unlike(code, user);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);

        return builder;
    }

    /**
     * 转换帖子简单信息Rest
     *
     * @param threadsInfo    帖子信息
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsInfoSimpleRest convertToThreadsInfoSimpleRest(ThreadsInfoWithBLOBs threadsInfo,
        UserLoginState userLoginState) {
        ThreadsInfoSimpleRest threadsInfoSimpleRest = new ThreadsInfoSimpleRest(threadsInfo);

        // 下载头像
        if (!StringUtils.isEmpty(threadsInfoSimpleRest.getUserAvatar())) {
            threadsInfoSimpleRest.setUserAvatar(fileDomain.getFileUrl(threadsInfoSimpleRest.getUserAvatar()));
        }
        // 下载封面
        if (!StringUtils.isEmpty(threadsInfoSimpleRest.getCover())) {
            threadsInfoSimpleRest.setCover(fileDomain.getFileUrl(threadsInfoSimpleRest.getCover()));
        }

        int replyNum = threadsReplyBl.count(threadsInfo.getCode(), false);
        threadsInfoSimpleRest.setReplyNum(replyNum);

        // 补充是否点赞
        if (userLoginState != null) {
            threadsInfoSimpleRest.setLiked(threadsInfoLikeBl.isLiked(threadsInfo.getCode(), userLoginState.getCode()));
        }
        return threadsInfoSimpleRest;
    }

    /**
     * 转换帖子简单信息Rest-管理后台
     *
     * @param threadsInfo    帖子信息
     * @return
     */
    private ThreadsInfoAdminSimpleRest convertToThreadsInfoAdminSimpleRest(ThreadsInfoWithBLOBs threadsInfo) {
        ThreadsInfoAdminSimpleRest threadsInfoAdminSimpleRest = new ThreadsInfoAdminSimpleRest(threadsInfo);

        // 下载头像
        if (!StringUtils.isEmpty(threadsInfoAdminSimpleRest.getUserAvatar())) {
            threadsInfoAdminSimpleRest.setUserAvatar(fileDomain.getFileUrl(threadsInfoAdminSimpleRest.getUserAvatar()));
        }
        // 下载封面
        if (!StringUtils.isEmpty(threadsInfoAdminSimpleRest.getCover())) {
            threadsInfoAdminSimpleRest.setCover(fileDomain.getFileUrl(threadsInfoAdminSimpleRest.getCover()));
        }

        int replyNum = threadsReplyBl.count(threadsInfo.getCode(), false);
        threadsInfoAdminSimpleRest.setReplyNum(replyNum);

        return threadsInfoAdminSimpleRest;
    }

    /**
     * 转换ThreadsInfoRest
     *
     * @param threadsInfo    帖子信息
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsInfoRest convertToThreadsInfoRest(ThreadsInfoWithBLOBs threadsInfo, UserLoginState userLoginState) {
        ThreadsInfoRest threadsInfoRest = new ThreadsInfoRest(threadsInfo);

        // 下载头像
        if (!StringUtils.isEmpty(threadsInfoRest.getUserAvatar())) {
            threadsInfoRest.setUserAvatar(fileDomain.getFileUrl(threadsInfoRest.getUserAvatar()));
        }
        // 下载封面
        if (!StringUtils.isEmpty(threadsInfoRest.getCover())) {
            threadsInfoRest.setCover(fileDomain.getFileUrl(threadsInfoRest.getCover()));
        }

        // 补充是否点赞
        threadsInfoRest.setLiked(threadsInfoLikeBl.isLiked(threadsInfo.getCode(), userLoginState.getCode()));

        // 补充回复信息
        int replyNum = threadsReplyBl.count(threadsInfo.getCode(), false);
        threadsInfoRest.setReplyNum(replyNum);
        threadsInfoRest.setReplyPageRest(getDefaultThreadsReplyPage(threadsInfo.getCode(), userLoginState));

        return threadsInfoRest;
    }

    /**
     * 转换ThreadsInfoAdminRest
     *
     * @param threadsInfo    帖子信息
     * @return
     */
    private ThreadsInfoAdminRest convertToThreadsInfoAdminRest(ThreadsInfoWithBLOBs threadsInfo) {
        ThreadsInfoAdminRest threadsInfoRest = new ThreadsInfoAdminRest(threadsInfo);

        // 下载头像
        if (!StringUtils.isEmpty(threadsInfoRest.getUserAvatar())) {
            threadsInfoRest.setUserAvatar(fileDomain.getFileUrl(threadsInfoRest.getUserAvatar()));
        }
        // 下载封面
        if (!StringUtils.isEmpty(threadsInfoRest.getCover())) {
            threadsInfoRest.setCover(fileDomain.getFileUrl(threadsInfoRest.getCover()));
        }

        // 补充回复信息
        int replyNum = threadsReplyBl.count(threadsInfo.getCode(), false);
        threadsInfoRest.setReplyNum(replyNum);
        threadsInfoRest.setReplyPageRest(getDefaultThreadsReplyAdminPage(threadsInfo.getCode()));

        return threadsInfoRest;
    }

    /**
     * 补充分页回复信息
     *
     * @param code           帖子编号
     * @param userLoginState 用户信息
     * @return
     */
    private ThreadsReplyPageRest getDefaultThreadsReplyPage(String code, UserLoginState userLoginState) {
        ThreadsReplyPageInfo pageInfo = new ThreadsReplyPageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setPageSize(10);
        pageInfo.setThreadsCode(code);
        ThreadsReplyPage threadsReplyPage = threadsReplyBl.getThreadsReplyPage(pageInfo, false);

        ThreadsReplyPageRest threadsReplyPageRest = new ThreadsReplyPageRest();
        if (!CollectionUtils.isEmpty(threadsReplyPage.getThreadsReplyList())) {
            List<ThreadsReplyRest> threadsReplyRests =
                threadsReplyDomain.convertToThreadsReplyRests(threadsReplyPage.getThreadsReplyList(), userLoginState);
            threadsReplyPageRest.setPagingInfo(threadsReplyPage.getPagingInfo());
            threadsReplyPageRest.setThreadsReplyRests(threadsReplyRests);
        }

        return threadsReplyPageRest;
    }

    /**
     * 补充分页回复信息-管理平台
     *
     * @param code           帖子编号
     * @return
     */
    private ThreadsReplyAdminPageRest getDefaultThreadsReplyAdminPage(String code) {
        ThreadsReplyPageInfo pageInfo = new ThreadsReplyPageInfo();
        pageInfo.setPageNumber(1);
        pageInfo.setPageSize(10);
        pageInfo.setThreadsCode(code);
        ThreadsReplyPage threadsReplyPage = threadsReplyBl.getThreadsReplyPage(pageInfo, true);

        ThreadsReplyAdminPageRest threadsReplyAdminPageRest = new ThreadsReplyAdminPageRest();
        if (!CollectionUtils.isEmpty(threadsReplyPage.getThreadsReplyList())) {
            List<ThreadsReplyAdminRest> threadsReplyRests =
                threadsReplyDomain.convertToThreadsReplyAdminRests(threadsReplyPage.getThreadsReplyList());
            threadsReplyAdminPageRest.setPagingInfo(threadsReplyPage.getPagingInfo());
            threadsReplyAdminPageRest.setThreadsReplyRests(threadsReplyRests);
        }

        return threadsReplyAdminPageRest;
    }
}
