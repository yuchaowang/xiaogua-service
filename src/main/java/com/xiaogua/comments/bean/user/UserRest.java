package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.dal.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户信息表
 */
@ApiModel("用户信息")
public class UserRest {
    /**
     * 用户编号
     */
    @ApiModelProperty("用户编号")
    private String code;

    /**
     * 头像
     */
    @ApiModelProperty("头像")
    private FileRest avatarFile;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    /**
     * 生日
     */
    @ApiModelProperty("生日")
    private String birthday;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;

    /**
     * 区县
     */
    @ApiModelProperty("区县")
    private String district;

    /**
     * 创建时间（业务）
     */
    @ApiModelProperty("创建时间（业务）")
    private String createDate;

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public UserRest() {}

    public UserRest(User user) {
        this.code = user.getCode();
        this.email = user.getEmail();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.birthday = user.getBirthday();
        this.sex = user.getSex();
        this.province = user.getProvince();
        this.city = user.getCity();
        this.district = user.getDistrict();
        this.createDate = user.getCreateDate();
    }
}