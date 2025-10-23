package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.dal.entity.User;

import java.util.List;

/**
 * 当前用户信息表
 * @author: Mybatis Generator
 */
public class MyUser {

    /**
     * 用户编号
     */
    private String code;

    /**
     * 头像
     */
    private FileRest avatarFile;

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
     * 手机认证状态
     */
    private String mobileValidCode;

    /**
     * 邮箱认证状态
     */
    private String emailValidCode;

    /**
     * 生日
     */
    private String birthday;

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
     * 区县
     */
    private String district;

    /**
     * 更新时间（业务）
     */
    private String updateDate;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 身份信息
     */
    private List<UserTypeRest> userTypeRests;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public FileRest getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(FileRest avatarFile) {
        this.avatarFile = avatarFile;
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

    public String getMobileValidCode() {
        return mobileValidCode;
    }

    public void setMobileValidCode(String mobileValidCode) {
        this.mobileValidCode = mobileValidCode;
    }

    public String getEmailValidCode() {
        return emailValidCode;
    }

    public void setEmailValidCode(String emailValidCode) {
        this.emailValidCode = emailValidCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public List<UserTypeRest> getUserTypeRests() {
        return userTypeRests;
    }

    public void setUserTypeRests(List<UserTypeRest> userTypeRests) {
        this.userTypeRests = userTypeRests;
    }

    public MyUser() {}

    public MyUser(User user) {
        this.code = user.getCode();
        this.email = user.getEmail();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.mobileValidCode = user.getMobileValidCode();
        this.emailValidCode = user.getEmailValidCode();
        this.birthday = user.getBirthday();
        this.sex = user.getSex();
        this.province = user.getProvince();
        this.city = user.getCity();
        this.district = user.getDistrict();
        this.updateDate = user.getUpdateDate();
        this.createDate = user.getCreateDate();
    }
}