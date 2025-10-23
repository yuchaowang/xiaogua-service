package com.xiaogua.comments.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 注册用户信息
 *
 * @author wangyc
 * @date 2020-11-21 17:49
 */
@ApiModel(value = "注册用户信息")
public class RegisterUser {

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 确认密码
     */
    @ApiModelProperty(value = "确认密码")
    private String ensurePassword;

    /**
     * 是否同意条款
     */
    @ApiModelProperty(value = "是否同意条款")
    private int isAgree;

    /**
     * 邀请信息
     */
    @ApiModelProperty(value = "邀请信息")
    private String invitedCode;

    /**
     * 短信验证码
     */
    @ApiModelProperty(value = "短信验证码")
    private String smsCheckCode;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnsurePassword() {
        return ensurePassword;
    }

    public void setEnsurePassword(String ensurePassword) {
        this.ensurePassword = ensurePassword;
    }

    public int getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(int isAgree) {
        this.isAgree = isAgree;
    }

    public String getInvitedCode() {
        return invitedCode;
    }

    public void setInvitedCode(String invitedCode) {
        this.invitedCode = invitedCode;
    }

    public String getSmsCheckCode() {
        return smsCheckCode;
    }

    public void setSmsCheckCode(String smsCheckCode) {
        this.smsCheckCode = smsCheckCode;
    }
}
