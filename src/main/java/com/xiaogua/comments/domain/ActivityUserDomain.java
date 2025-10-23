package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.activity.ActivityPage;
import com.xiaogua.comments.bean.activity.ActivityPageCommonInfo;
import com.xiaogua.comments.bean.activity.ActivityPageInfo;
import com.xiaogua.comments.bean.activity.ActivityRest;
import com.xiaogua.comments.bean.activity.ActivityUserAdmin;
import com.xiaogua.comments.bean.activity.ActivityUserPage;
import com.xiaogua.comments.bean.activity.ActivityUserPageInfo;
import com.xiaogua.comments.bean.activity.ActivityUserRest;
import com.xiaogua.comments.bean.activity.ActivityUserSubmit;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bl.ActivityBl;
import com.xiaogua.comments.bl.ActivityUserBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.common.constant.RegexConstant;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.Activity;
import com.xiaogua.comments.dal.entity.ActivityUser;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.XlsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 活动用户 domain
 *
 * @author wangyc
 * @date 2021-5-16
 **/

@Service
public class ActivityUserDomain {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityUserDomain.class);

    @Autowired ActivityUserBl activityUserBl;

    @Autowired ActivityBl activityBl;

    @Autowired UserBl userBl;

    @Autowired UserDomain userDomain;

    @Autowired ActivityDomain activityDomain;

    /**
     * 报名
     *
     * @param submit   submit
     * @param userCode userCode
     * @return
     */
    public ResponseBuilder save(ActivityUserSubmit submit, String userCode) {
        VerifyParamsUtil.hasText(submit.getCompany(), "公司名称不可为空");
        VerifyParamsUtil.isTrue(submit.getName().length() <= 50, "公司名称不可超过50个字");
        VerifyParamsUtil.hasText(submit.getName(), "姓名不可为空");
        VerifyParamsUtil.isTrue(submit.getName().length() <= 50, "姓名不可超过50个字");
        VerifyParamsUtil.hasText(submit.getCompany(), "公司职位不可为空");
        VerifyParamsUtil.isTrue(submit.getName().length() <= 50, "公司职位不可超过50个字");
        if (StringUtils.hasText(submit.getEmail())) {
            VerifyParamsUtil.isTrue(submit.getName().length() <= 100, "邮箱不可超过100个字");
        }
        if (!StringUtils.isEmpty(submit.getMobile())) {
            VerifyParamsUtil.isTrue(submit.getMobile().length() <= 20, "手机号不可超过20位");
            VerifyParamsUtil.isTrue(Pattern.matches(RegexConstant.REGEX_MOBILE, submit.getMobile()), "手机号格式不正确");
        }

        User user = userBl.getUserByCode(userCode);
        if (user == null) {
            throw new CommentsRuntimeException(-1, "用户信息不存在");
        }

        Activity activity = activityBl.selectByCode(submit.getActivityCode());
        if (activity == null) {
            throw new CommentsRuntimeException(-1, "活动不存在");
        }
        if (StatusCode.ACTIVITY_UNREADY.getCode() == activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动不存在，不可报名");
        }
        if (StatusCode.ACTIVITY_SUCCESS.getCode() == activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动已结束，不可报名");
        }
        if (StatusCode.ACTIVITY_CANCEL.getCode() == activity.getStatus()) {
            throw new CommentsRuntimeException(-1, "活动已取消，不可报名");
        }

        ResponseBuilder builder = new ResponseBuilder();

        ActivityUser activityUser = activityUserBl.save(submit, user);
        builder.setData(activityUser.getCode());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 验证是否已报名
     *
     * @param activityCode 活动编号
     * @param userCode     用户编号
     * @return
     */
    public ResponseBuilder validateExist(String activityCode, String userCode) {
        ActivityUser activityUser = activityUserBl.validateExist(activityCode, userCode);
        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(activityUser != null);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取我报名的活动
     *
     * @param pageInfo 分页信息
     * @param userCode 用户编号
     * @return
     */
    public ResponsePageBuilder getApplyActivity(ActivityPageInfo pageInfo, String userCode) {
        ActivityUserPageInfo userPageInfo = new ActivityUserPageInfo();
        userPageInfo.setUserCode(userCode);
        userPageInfo.setPageNumber(pageInfo.getPageNumber());
        userPageInfo.setPageSize(pageInfo.getPageSize());

        ActivityUserPage activityUserPage = activityUserBl.getPage(userPageInfo);

        ActivityPageCommonInfo activityPageInfo = new ActivityPageCommonInfo();
        activityPageInfo.setPageNumber(1);
        activityPageInfo.setPageSize(10000);
        activityPageInfo.setName(pageInfo.getName());
        activityPageInfo.setIsFree(pageInfo.getIsFree());
        List<Integer> statusList = Arrays
            .asList(StatusCode.ACTIVITY_READY.getCode(), StatusCode.ACTIVITY_RUNNING.getCode(),
                    StatusCode.ACTIVITY_SUCCESS.getCode(), StatusCode.ACTIVITY_CANCEL.getCode());
        if (pageInfo.getStatus() != null) {
            if (!statusList.contains(pageInfo.getStatus())) {
                throw new CommentsRuntimeException(-1, "状态码错误");
            }
            activityPageInfo.setStatusList(Arrays.asList(pageInfo.getStatus()));
        } else {
            activityPageInfo.setStatusList(statusList);
        }

        ActivityPage activityPage = activityBl.getPage(activityPageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();
        if (!CollectionUtils.isEmpty(activityPage.getActivities())) {
            List<ActivityRest> activityRests = activityDomain.convertToActivities(activityPage.getActivities());
            Map<String, ActivityRest> activityMap =
                activityRests.stream().collect(Collectors.toMap(a -> a.getCode(), a -> a));

            List<ActivityUserRest> userRests = new ArrayList<>();
            for (ActivityUser user : activityUserPage.getActivityUsers()) {
                userRests.add(convertToActivityUser(user, activityMap.containsKey(user.getActivityCode()) ? activityMap
                    .get(user.getActivityCode()) : null));
            }
            builder.setData(userRests);
        }

        builder.setPageInfo(activityUserPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取已参加活动报名用户信息
     * @param code 报名活动用户编号
     * @param userCode 登录用户编号
     * @return
     */
    public ResponseBuilder getActivityUserDetail(String code, String userCode) {
        ActivityUser activityUser = activityUserBl.get(code);

        if (activityUser == null) {
            throw new CommentsRuntimeException(-1, "票券信息不存在");
        }
        if (!userCode.equals(activityUser.getUserCode())) {
            throw new CommentsRuntimeException(-1, "此票券信息不属于您，不可查询");
        }

        Activity activity = activityBl.selectByCode(activityUser.getActivityCode());
        ResponseBuilder builder = new ResponseBuilder();
        ActivityUserRest rest = convertToActivityUser(activityUser, new ActivityRest(activity));

        builder.setData(rest);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 管理平台-分页获取活动用户信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder adminGetActivityUsersPage(ActivityUserPageInfo pageInfo) {
        pageInfo.setUserCode(null);
        ActivityUserPage activityUserPage = activityUserBl.getPage(pageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换活动用户信息
        if (activityUserPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToActivityUserAdmins(activityUserPage.getActivityUsers()));
        }

        builder.setPageInfo(activityUserPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换活动用户信息Admin
     *
     * @param activityUsers 活动用户信息
     * @return
     */
    private List<ActivityUserAdmin> convertToActivityUserAdmins(List<ActivityUser> activityUsers) {
        List<ActivityUserAdmin> activityUserAdminList = new ArrayList<>();
        for (ActivityUser activityUser : activityUsers) {
            activityUserAdminList.add(convertToActivityUserAdmin(activityUser));
        }

        return activityUserAdminList;
    }

    /**
     * 转换活动用户信息Admin
     *
     * @param activityUser 活动用户信息Admin
     * @return
     */
    private ActivityUserAdmin convertToActivityUserAdmin(ActivityUser activityUser) {
        ActivityUserAdmin activityUserAdmin = new ActivityUserAdmin(activityUser);
        activityUserAdmin.setUserRest(userDomain.getUserRest(activityUser.getUserCode()));
        return activityUserAdmin;
    }

    /**
     * 下载参加活动用户信息
     *
     * @param response     response
     * @param activityCode 活动编号
     */
    public void downloadUsers(HttpServletResponse response, String activityCode) {
        List<ActivityUser> activityUsers = activityUserBl.getAllUsers(activityCode);
        List<ActivityUserAdmin> activityUserAdmins = convertToActivityUserAdmins(activityUsers);
        // 下载数据
        try {
            XlsUtils.download(response, "jxls/allActivityUsers.xls", "users", activityUserAdmins, "ActivityUsers.xls");
        } catch (IOException e) {
            throw new CommentsRuntimeException("数据导出错误");
        }
    }

    /**
     * 转换活动用户信息Admin
     *
     * @param activityUser 活动用户信息
     * @param activity     活动信息
     * @return
     */
    private ActivityUserRest convertToActivityUser(ActivityUser activityUser, ActivityRest activity) {
        ActivityUserRest activityUserAdmin = new ActivityUserRest(activityUser);
        activityUserAdmin.setActivityRest(activity);
        return activityUserAdmin;
    }

}


