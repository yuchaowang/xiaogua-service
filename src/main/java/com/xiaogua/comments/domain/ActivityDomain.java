package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.activity.ActivityAdmin;
import com.xiaogua.comments.bean.activity.ActivityPage;
import com.xiaogua.comments.bean.activity.ActivityPageCommonInfo;
import com.xiaogua.comments.bean.activity.ActivityPageInfo;
import com.xiaogua.comments.bean.activity.ActivityRest;
import com.xiaogua.comments.bean.activity.ActivitySubmit;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bl.ActivityBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.Activity;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 活动 domain
 *
 * @author wangyc
 * @date 2021-6-2
 **/

@Service
public class ActivityDomain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityDomain.class);

    @Autowired
    ActivityBl activityBl;

    @Autowired
    UserBl userBl;

    @Autowired
    UserDomain userDomain;

    @Autowired
    FileDomain fileDomain;
    /**
     * 保存活动信息
     *
     * @param submit submit
     * @param userCode userCode
     * @return
     */
    public ResponseBuilder save(ActivitySubmit submit, String userCode) {
        VerifyParamsUtil.hasText(submit.getName(), "活动名称不可为空");
        VerifyParamsUtil.isTrue(submit.getName().length() <= 50, "公司名称不可超过50个字");
        VerifyParamsUtil.hasText(submit.getAddress(), "活动地址不可为空");
        VerifyParamsUtil.isTrue(submit.getAddress().length() <= 200, "活动地址不可超过200个字");
        VerifyParamsUtil.hasText(submit.getStartTime(), "开始时间不可为空");
        VerifyParamsUtil.hasText(submit.getEndTime(), "结束时间不可为空");
        VerifyParamsUtil.hasText(submit.getDescription(), "活动详情不可为空");

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户信息不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();

        Activity activity = activityBl.save(submit, user);
        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 发布活动
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder deploy(String code) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        if (StatusCode.ACTIVITY_UNREADY.getCode() != activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动不为待发布状态");
        }

        activityBl.updateStatus(code, StatusCode.ACTIVITY_READY.getCode());

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 开启活动
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder start(String code) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        if (StatusCode.ACTIVITY_READY.getCode() != activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动不为待开始状态");
        }

        activityBl.updateStatus(code, StatusCode.ACTIVITY_RUNNING.getCode());

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 活动结束
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder close(String code) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        if (StatusCode.ACTIVITY_RUNNING.getCode() != activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动不为进行中状态");
        }

        activityBl.updateStatus(code, StatusCode.ACTIVITY_SUCCESS.getCode());

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 活动结束
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder cancel(String code) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        if (StatusCode.ACTIVITY_SUCCESS.getCode() == activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动已完成，无需取消");
        }

        activityBl.updateStatus(code, StatusCode.ACTIVITY_CANCEL.getCode());

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 更新小程序直播id
     * @param code 活动编号
     * @param streamingId 小程序直播id
     * @return
     */
    public ResponseBuilder updateStreamingId(String code, String streamingId) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        activityBl.updateStreamingId(code, streamingId);
        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 更新直播地址
     * @param code 活动编号
     * @param streamingUrl 直播地址
     * @return
     */
    public ResponseBuilder updateStreamingUrl(String code, String streamingUrl) {
        Activity activity = activityBl.selectByCode(code);
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        activityBl.updateStreamingUrl(code, streamingUrl);
        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activity.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 管理平台-获取活动信息
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder adminGet(String code) {
        Activity activity = activityBl.selectByCode(code);
        ActivityAdmin activityAdmin = convertToActivityAdmin(activity);

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activityAdmin);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 管理平台-分页获取活动信息
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder adminGetActivitysPage(ActivityPageInfo pageInfo) {
        ActivityPageCommonInfo pageCommonInfo = new ActivityPageCommonInfo(pageInfo);
        if (pageInfo.getStatus() != null) {
            pageCommonInfo.setStatusList(Arrays.asList(pageInfo.getStatus()));
        }

        ActivityPage activityPage = activityBl.getPage(pageCommonInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换品牌信息
        if (activityPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToActivityAdmins(activityPage.getActivities()));
        }

        builder.setPageInfo(activityPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 用户-分页获取活动信息
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getActivityPage(ActivityPageInfo pageInfo) {
        ActivityPageCommonInfo pageCommonInfo = new ActivityPageCommonInfo(pageInfo);
        List<Integer> statusList = Arrays.asList(StatusCode.ACTIVITY_READY.getCode(),
            StatusCode.ACTIVITY_RUNNING.getCode(),
            StatusCode.ACTIVITY_SUCCESS.getCode(),
            StatusCode.ACTIVITY_CANCEL.getCode());
        if (pageInfo.getStatus() != null) {
            if (!statusList.contains(pageInfo.getStatus())) {
                throw new CommentsRuntimeException(-1, "状态码错误");
            }
            pageCommonInfo.setStatusList(Arrays.asList(pageInfo.getStatus()));
        } else {
            pageCommonInfo.setStatusList(statusList);
        }

        ActivityPage activityPage = activityBl.getPage(pageCommonInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换活动信息
        if (activityPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToActivities(activityPage.getActivities()));
        }

        builder.setPageInfo(activityPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 用户-获取单个活动信息
     * @param code 活动编号
     * @return
     */
    public ResponseBuilder get(String code) {
        List<Integer> statusList = Arrays.asList(StatusCode.ACTIVITY_READY.getCode(),
            StatusCode.ACTIVITY_RUNNING.getCode(),
            StatusCode.ACTIVITY_SUCCESS.getCode(),
            StatusCode.ACTIVITY_CANCEL.getCode());

        Activity activity = activityBl.selectByCode(code);

        if (activity == null || !statusList.contains(activity.getStatus())) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();

        // 转换活动信息
        builder.setData(convertToActivity(activity));
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换活动信息Admin
     *
     * @param activitys 活动信息
     * @return
     */
    private List<ActivityAdmin> convertToActivityAdmins(List<Activity> activitys) {
        List<ActivityAdmin> activityAdmins = new ArrayList<>();
        for (Activity activity : activitys) {
            activityAdmins.add(convertToActivityAdmin(activity));
        }

        return activityAdmins;
    }

    /**
     * 转换活动用户信息Admin
     *
     * @param activity   活动信息
     * @return
     */
    private ActivityAdmin convertToActivityAdmin(Activity activity) {
        ActivityAdmin activityAdmin = new ActivityAdmin(activity);
        activityAdmin.setUserRest(userDomain.getUserRest(activity.getUserCode()));
        if (!StringUtils.isEmpty(activity.getTitlePageCode())) {
            activityAdmin.setTitlePageUrl(fileDomain.getFileUrl(activity.getTitlePageCode()));
        }
        return activityAdmin;
    }


    /**
     * 批量转换活动信息Rest
     *
     * @param activitys 活动信息
     * @return
     */
    public List<ActivityRest> convertToActivities(List<Activity> activitys) {
        List<ActivityRest> activities = new ArrayList<>();
        for (Activity activity : activitys) {
            activities.add(convertToActivity(activity));
        }

        return activities;
    }

    /**
     * 转换活动用户信息Admin
     *
     * @param activity   活动信息
     * @return
     */
    private ActivityRest convertToActivity(Activity activity) {
        ActivityRest activityRest = new ActivityRest(activity);

        if (!StringUtils.isEmpty(activity.getTitlePageCode())) {
            activityRest.setTitlePageUrl(fileDomain.getFileUrl(activity.getTitlePageCode()));
        }
        return activityRest;
    }
}


