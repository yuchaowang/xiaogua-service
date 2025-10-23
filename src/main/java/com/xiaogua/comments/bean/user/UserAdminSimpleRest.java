package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.dal.entity.User;

/**
 * 管理后台-用户简易信息
 * @author wangyc
 * @date 2021-02-19 18:00
 */
public class UserAdminSimpleRest {

    /**
     * 用户编号
     */
    private String code;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户名
     */
    private String name;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 性别
     */
    private String sex;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public UserAdminSimpleRest() {}

    public UserAdminSimpleRest(User user) {
        this.code = user.getCode();
        this.avatarUrl = user.getAvatar();
        this.email = user.getEmail();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.sex = user.getSex();
        this.province = user.getProvince();
        this.city = user.getCity();
        this.createDate = user.getCreateDate();
    }
}
