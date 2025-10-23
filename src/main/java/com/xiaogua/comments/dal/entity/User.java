package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 用户信息表
 * 表名 : t_user
 * </pre>
 * @author: Mybatis Generator
 */
public class User {
    /**
     * <pre>
     * id
     * 表字段 : t_user.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 头像
     * 表字段 : t_user.avatar
     * </pre>
     */
    private String avatar;

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_user.email
     * </pre>
     */
    private String email;

    /**
     * <pre>
     * 用户名
     * 表字段 : t_user.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 手机号
     * 表字段 : t_user.mobile
     * </pre>
     */
    private String mobile;

    /**
     * <pre>
     * 手机认证状态
     * 表字段 : t_user.mobile_valid_code
     * </pre>
     */
    private String mobileValidCode;

    /**
     * <pre>
     * 密码
     * 表字段 : t_user.password
     * </pre>
     */
    private String password;

    /**
     * <pre>
     * 邮箱认证状态
     * 表字段 : t_user.email_valid_code
     * </pre>
     */
    private String emailValidCode;

    /**
     * <pre>
     * 盐值
     * 表字段 : t_user.salt
     * </pre>
     */
    private Integer salt;

    /**
     * <pre>
     * 生日
     * 表字段 : t_user.birthday
     * </pre>
     */
    private String birthday;

    /**
     * <pre>
     * 性别
     * 表字段 : t_user.sex
     * </pre>
     */
    private String sex;

    /**
     * <pre>
     * 省
     * 表字段 : t_user.province
     * </pre>
     */
    private String province;

    /**
     * <pre>
     * 市
     * 表字段 : t_user.city
     * </pre>
     */
    private String city;

    /**
     * <pre>
     * 区县
     * 表字段 : t_user.district
     * </pre>
     */
    private String district;

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_user.openId
     * </pre>
     */
    private String openId;

    /**
     * <pre>
     * 微信wxOpenId
     * 表字段 : t_user.wxOpenId
     * </pre>
     */
    private String wxOpenId;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_user.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_user.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 头像
     * 表字段 : t_user.avatar
     * </pre>
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * <pre>
     * 头像
     * 表字段 : t_user.avatar
     * </pre>
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_user.email
     * </pre>
     */
    public String getEmail() {
        return email;
    }

    /**
     * <pre>
     * 邮箱
     * 表字段 : t_user.email
     * </pre>
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * <pre>
     * 用户名
     * 表字段 : t_user.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 用户名
     * 表字段 : t_user.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 手机号
     * 表字段 : t_user.mobile
     * </pre>
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * <pre>
     * 手机号
     * 表字段 : t_user.mobile
     * </pre>
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * <pre>
     * 手机认证状态
     * 表字段 : t_user.mobile_valid_code
     * </pre>
     */
    public String getMobileValidCode() {
        return mobileValidCode;
    }

    /**
     * <pre>
     * 手机认证状态
     * 表字段 : t_user.mobile_valid_code
     * </pre>
     */
    public void setMobileValidCode(String mobileValidCode) {
        this.mobileValidCode = mobileValidCode == null ? null : mobileValidCode.trim();
    }

    /**
     * <pre>
     * 密码
     * 表字段 : t_user.password
     * </pre>
     */
    public String getPassword() {
        return password;
    }

    /**
     * <pre>
     * 密码
     * 表字段 : t_user.password
     * </pre>
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * <pre>
     * 邮箱认证状态
     * 表字段 : t_user.email_valid_code
     * </pre>
     */
    public String getEmailValidCode() {
        return emailValidCode;
    }

    /**
     * <pre>
     * 邮箱认证状态
     * 表字段 : t_user.email_valid_code
     * </pre>
     */
    public void setEmailValidCode(String emailValidCode) {
        this.emailValidCode = emailValidCode == null ? null : emailValidCode.trim();
    }

    /**
     * <pre>
     * 盐值
     * 表字段 : t_user.salt
     * </pre>
     */
    public Integer getSalt() {
        return salt;
    }

    /**
     * <pre>
     * 盐值
     * 表字段 : t_user.salt
     * </pre>
     */
    public void setSalt(Integer salt) {
        this.salt = salt;
    }

    /**
     * <pre>
     * 生日
     * 表字段 : t_user.birthday
     * </pre>
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * <pre>
     * 生日
     * 表字段 : t_user.birthday
     * </pre>
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    /**
     * <pre>
     * 性别
     * 表字段 : t_user.sex
     * </pre>
     */
    public String getSex() {
        return sex;
    }

    /**
     * <pre>
     * 性别
     * 表字段 : t_user.sex
     * </pre>
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * <pre>
     * 省
     * 表字段 : t_user.province
     * </pre>
     */
    public String getProvince() {
        return province;
    }

    /**
     * <pre>
     * 省
     * 表字段 : t_user.province
     * </pre>
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * <pre>
     * 市
     * 表字段 : t_user.city
     * </pre>
     */
    public String getCity() {
        return city;
    }

    /**
     * <pre>
     * 市
     * 表字段 : t_user.city
     * </pre>
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * <pre>
     * 区县
     * 表字段 : t_user.district
     * </pre>
     */
    public String getDistrict() {
        return district;
    }

    /**
     * <pre>
     * 区县
     * 表字段 : t_user.district
     * </pre>
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_user.openId
     * </pre>
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_user.openId
     * </pre>
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_user.wxOpenId
     * </pre>
     */
    public String getWxOpenId() {
        return wxOpenId;
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_user.wxOpenId
     * </pre>
     */
    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId == null ? null : wxOpenId.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}