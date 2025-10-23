package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.ActivityUser;
import java.util.List;

/**
 * 分页活动用户信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class ActivityUserPage {

    PagingInfo pagingInfo;

    List<ActivityUser> activityUsers;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ActivityUser> getActivityUsers() {
        return activityUsers;
    }

    public void setActivityUsers(List<ActivityUser> activityUsers) {
        this.activityUsers = activityUsers;
    }
}
