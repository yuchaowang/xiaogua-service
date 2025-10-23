package com.xiaogua.comments.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by wangyc on 2018/10/10.
 *
 * @version 2018/10/10 14:16 wangyc
 */
@Component
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "wx.open")
public class OpenWXConfig {

    /**
     * appid
     */
    private String appid;

    /**
     * appsecret
     */
    private String appsecret;

    /**
     * 获取accessToken
     */
    private String getToken;

    /**
     * 获取用户信息
     */
    private String getUserInfo;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getGetToken() {
        return getToken;
    }

    public void setGetToken(String getToken) {
        this.getToken = getToken;
    }

    public String getGetUserInfo() {
        return getUserInfo;
    }

    public void setGetUserInfo(String getUserInfo) {
        this.getUserInfo = getUserInfo;
    }
}
