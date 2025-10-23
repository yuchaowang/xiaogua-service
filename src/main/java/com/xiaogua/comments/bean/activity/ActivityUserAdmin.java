package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.dal.entity.ActivityUser;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 活动用户信息表
 * 表名 : t_activity_user
 * </pre>
 * @author: Mybatis Generator
 */
@Data
@NoArgsConstructor
public class ActivityUserAdmin {

    /**
     * 活动用户编号
     */
    private String code;

    /**
     * 活动编号
     */
    private String activityCode;

    /**
     * 用户编号
     */
    private String userCode;

    /**
     * 实际用户信息
     */
    private UserRest userRest;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 职位
     */
    private String position;

    /**
     * 更新时间（业务）
     */
    private String updateDate;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    public ActivityUserAdmin(ActivityUser activityUser) {
        super();
        this.activityCode = activityUser.getActivityCode();
        this.code = activityUser.getCode();
        this.company = activityUser.getCompany();
        this.createDate = activityUser.getCreateDate();
        this.mobile = activityUser.getMobile();
        this.email = activityUser.getEmail();
        this.name = activityUser.getName();
        this.position = activityUser.getPosition();
        this.updateDate = activityUser.getUpdateDate();
        this.userCode = activityUser.getUserCode();
    }
}