package com.xiaogua.comments.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.config.WXConfig;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
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

/**
 * 微信工具
 * @author wangyc
 * @date 2021-02-09 23:16
 */
@Component
public class WxUtil {

    @Autowired
    WXConfig wxConfig;

    protected CloseableHttpClient httpClient;

    private static final Logger LOGGER = LoggerFactory.getLogger(WxUtil.class);

    private static final String CONTENT = "content";
    private static final String ACCESS_TOKEN = "access_token";
    private static final String ERRCODE = "errcode";
    private static final Integer RISKY_CODE = 87014;

    /**
     * 获取微信accessToken
     * @return
     */
    public String getAccessToken() {
        String getTokenUrl = wxConfig.getGetToken();
        String url = String.format(getTokenUrl, wxConfig.getAppid(), wxConfig.getAppsecret());
        String accessToken = "";
        try {
            String content = HttpUtil.doGet(url);
            JSONObject jsonObject = JSONObject.parseObject(content);
            accessToken = jsonObject.getString(ACCESS_TOKEN);
        } catch (Exception e) {
            LOGGER.error("获取微信accessToken失败", e);
        }
        return accessToken;
    }

    /**
     * 验证推送信息合法性
     * @param content 推送信息
     * @return
     */
    public boolean validateContent(String content) {
        String msgSecCheckUrl = wxConfig.getMsgSecCheck();
        String url = String.format(msgSecCheckUrl, getAccessToken());
        Map<String, Object> validateParam = new HashMap<>(1);
        validateParam.put(CONTENT, content);

        boolean validateSuccess = false;
        String result;
        try {
            result = HttpUtil.doPost(url, JSON.toJSONString(validateParam));
        } catch (Exception e) {
            LOGGER.error("微信验证推送信息失败", e);
            throw new CommentsRuntimeException("验证推送信息失败");
        }
        if (StringUtils.isEmpty(result)) {
            LOGGER.error("微信验证推送信息失败,返回结果为空");
            throw new CommentsRuntimeException("验证推送信息失败");
        }

        JSONObject jsonObject = JSONObject.parseObject(result);
        Integer code = jsonObject.getInteger(ERRCODE);
        if (code.intValue() == 0) {
            validateSuccess = true;
        } else if (code.intValue() == RISKY_CODE) {
            throw new CommentsRuntimeException("含有违规内容");
        } else {
            throw new CommentsRuntimeException("含有违规内容");
        }

        return validateSuccess;
    }

    /**
     * 获取openId及sessionKey
     * @param code
     * @return
     * @throws IOException
     */
    public JSONObject getWxInfo(String code) throws IOException {
        LOGGER.info("code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=" + wxConfig.getAppid();//自己的appid
        url += "&secret=" + wxConfig.getAppsecret();//自己的appSecret
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);    //GET方式
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()          // 设置连接超时时间(单位毫秒)
                .setConnectTimeout(5000)                    // 设置请求超时时间(单位毫秒)
                .setConnectionRequestTimeout(5000)             // socket读写超时时间(单位毫秒)
                .setSocketTimeout(5000)                    // 设置是否允许重定向(默认为true)
                .setRedirectsEnabled(false).build();           // 将上面的配置信息 运用到这个Get请求里
        httpget.setConfig(requestConfig);                         // 由客户端执行(发送)Get请求
        response = httpClient.execute(httpget);                   // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        LOGGER.info("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            LOGGER.info("响应内容长度为:" + responseEntity.getContentLength());
            LOGGER.info("响应内容为:" + res);
        }
        // 释放资源
        if (httpClient != null) {
            httpClient.close();
        }
        if (response != null) {
            response.close();
        }
        JSONObject jo = JSON.parseObject(res);
        String openId = jo.getString("openid");
        String sessionKey = jo.getString("session_key");
        LOGGER.info("openid：" + openId);
        LOGGER.info("sessionKey：" + sessionKey);
        return jo;
    }
}
