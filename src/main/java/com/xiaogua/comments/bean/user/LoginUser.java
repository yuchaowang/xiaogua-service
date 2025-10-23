package com.xiaogua.comments.bean.user;

/**
 * 登录用户信息
 * @author wangyc
 * @date 2020-11-21 17:49
 */
public class LoginUser {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

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

}
