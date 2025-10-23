package com.xiaogua.comments.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.config.OpenWXConfig;
import com.xiaogua.comments.config.WXConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信工具
 * @author wangyc
 * @date 2021-02-09 23:16
 */
@Component
public class OpenWxUtil {

    @Autowired
    OpenWXConfig openWXConfig;

    protected CloseableHttpClient httpClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(OpenWxUtil.class);

    private static final String ACCESS_TOKEN = "access_token";
    private static final String OPEN_ID = "openid";

    /**
     * 获取微信accessToken
     * @return
     */
    public JSONObject getInfo(String code) {
        String getTokenUrl = openWXConfig.getGetToken();
        String url = String.format(getTokenUrl, openWXConfig.getAppid(), openWXConfig.getAppsecret(), code);
        String accessToken = "";
        String openid = "";
        try {
            String content = HttpUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(content);
            LOGGER.info(JSON.toJSONString(jsonObject));
            accessToken = jsonObject.getString(ACCESS_TOKEN);
            openid = jsonObject.getString(OPEN_ID);
            LOGGER.info("微信开放平台获取的accessToken为：" + accessToken);
            LOGGER.info("微信开放平台获取的openid为：" + openid);
        } catch (Exception e) {
            LOGGER.error("获取微信开放平台accessToken失败", e);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accessToken", accessToken);
        jsonObject.put("openid", openid);
        return jsonObject;
    }

    /**
     * 获取微信用户信息
     * @return
     */
    public JSONObject getUserInfo(String code) {
        JSONObject jsonObject = getInfo(code);
        String accessToken = jsonObject.getString("accessToken");
        String openid = jsonObject.getString("openid");
        if (StringUtils.isNotEmpty(accessToken) && StringUtils.isNotEmpty(openid)) {
            String getUserInfo = openWXConfig.getGetUserInfo();
            String url = String.format(getUserInfo, accessToken, openid);
            try {
                String content = HttpUtil.doGet(url);
                //解决中文乱码
                String json = new String(content.getBytes("ISO-8859-1"), "UTF-8");
                JSONObject jo = JSONObject.parseObject(json);
                LOGGER.info("获取到的微信开放平台的用户信息为：" + JSON.toJSONString(jo));
                return jo;
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("获取微信开放平台的用户信息失败", e);
            }
        }
        return null;
    }
}
