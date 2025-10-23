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
@ConfigurationProperties(prefix = "wx")
public class WXConfig {

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
     * 推送信息安全检测
     */
    private String msgSecCheck;

    /**
     * 微信发送订阅消息
     */
    private String sendSubscribeMessageUrl;

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

    public String getMsgSecCheck() {
        return msgSecCheck;
    }

    public void setMsgSecCheck(String msgSecCheck) {
        this.msgSecCheck = msgSecCheck;
    }

    public String getSendSubscribeMessageUrl() {
        return sendSubscribeMessageUrl;
    }

    public void setSendSubscribeMessageUrl(String sendSubscribeMessageUrl) {
        this.sendSubscribeMessageUrl = sendSubscribeMessageUrl;
    }
}
