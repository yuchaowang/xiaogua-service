package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 活动用户信息表
 * 表名 : t_activity_user
 * </pre>
 * @author: Mybatis Generator
 */
public class ActivityUser {
    /**
     * <pre>
     * id
     * 表字段 : t_activity_user.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 活动用户编号
     * 表字段 : t_activity_user.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity_user.activity_code
     * </pre>
     */
    private String activityCode;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_activity_user.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 用户名
     * 表字段 : t_activity_user.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 手机号
     * 表字段 : t_activity_user.mobile
     * </pre>
     */
    private String mobile;

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_activity_user.email
     * </pre>
     */
    private String email;

    /**
     * <pre>
     * 公司名称
     * 表字段 : t_activity_user.company
     * </pre>
     */
    private String company;

    /**
     * <pre>
     * 职位
     * 表字段 : t_activity_user.position
     * </pre>
     */
    private String position;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity_user.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity_user.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_activity_user.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_activity_user.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 活动用户编号
     * 表字段 : t_activity_user.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 活动用户编号
     * 表字段 : t_activity_user.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity_user.activity_code
     * </pre>
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * <pre>
     * 活动编号
     * 表字段 : t_activity_user.activity_code
     * </pre>
     */
    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode == null ? null : activityCode.trim();
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_activity_user.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_activity_user.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 用户名
     * 表字段 : t_activity_user.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 用户名
     * 表字段 : t_activity_user.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 手机号
     * 表字段 : t_activity_user.mobile
     * </pre>
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * <pre>
     * 手机号
     * 表字段 : t_activity_user.mobile
     * </pre>
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_activity_user.email
     * </pre>
     */
    public String getEmail() {
        return email;
    }

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_activity_user.email
     * </pre>
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * <pre>
     * 公司名称
     * 表字段 : t_activity_user.company
     * </pre>
     */
    public String getCompany() {
        return company;
    }

    /**
     * <pre>
     * 公司名称
     * 表字段 : t_activity_user.company
     * </pre>
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * <pre>
     * 职位
     * 表字段 : t_activity_user.position
     * </pre>
     */
    public String getPosition() {
        return position;
    }

    /**
     * <pre>
     * 职位
     * 表字段 : t_activity_user.position
     * </pre>
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity_user.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_activity_user.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity_user.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_activity_user.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}