package com.xiaogua.comments.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.config.MinioConfig;
import com.xiaogua.comments.config.WXConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 小程序消息通知
 * @author Clover
 * @date 2021-4-5
 **/
@Component
public class MessageUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageUtil.class);

    @Autowired
    private WxUtil wxUtil;
    @Autowired
    private WXConfig wxConfig;
    @Autowired
    private MinioConfig minioConfig;
    public static MessageUtil messageUtil;

    @PostConstruct
    public void init() {
        messageUtil = this;
        messageUtil.wxUtil = this.wxUtil;
        messageUtil.wxConfig = this.wxConfig;
        messageUtil.minioConfig = this.minioConfig;
    }

    public static void sendMessage(SubscribeMessageDto subscribeMessageDto) {
        List<String> toUserList = subscribeMessageDto.getToUser();
        LOGGER.info("发送消息【" + JSON.toJSONString(subscribeMessageDto) + "】");
        for (String openId : toUserList) {
            try {
                String accessToken = messageUtil.wxUtil.getAccessToken();
                String msgSecCheckUrl = messageUtil.wxConfig.getSendSubscribeMessageUrl();
                String url = String.format(msgSecCheckUrl, accessToken);

                Map<String, Object> messageMap = new HashMap<>();
                messageMap.put("touser", openId);
                messageMap.put("template_id", subscribeMessageDto.getTemplateId());
                messageMap.put("page", subscribeMessageDto.getPage());

                Map<String, Object> data = subscribeMessageDto.getData();
                Map<String, Object> dataMap = new HashMap<>();
                if (data != null && !data.isEmpty()) {
                    for (Map.Entry<String, Object> entry : data.entrySet()) {
                        Map<String, Object> valueMap = new HashMap<>();
                        String key = entry.getKey();
                        valueMap.put("value", entry.getValue());
                        dataMap.put(key, valueMap);
                    }
                }
                messageMap.put("data", dataMap);

                String programState = messageUtil.minioConfig.getProgramState();
                messageMap.put("miniprogram_state", programState);
                messageMap.put("lang", "zh_CN");
                LOGGER.info("请求参数为【" + JSON.toJSONString(messageMap) + "】");

                String result = HttpUtil.doPost(url, JSON.toJSONString(messageMap));
                JSONObject jsonObject = JSON.parseObject(result);
                if (0 == jsonObject.getInteger("errcode")) {
                    LOGGER.info("用户openId为【" + openId + "】发送消息成功");
                } else {
                    String errmsg = jsonObject.getString("errmsg");
                    LOGGER.warn("用户openId为【" + openId + "】发送消息失败，原因为【" + errmsg + "】");
                }
            } catch (Exception e) {
                LOGGER.error(e.toString());
                throw new RuntimeException("用户openId为【" + openId + "】发送消息失败");
            }
        }

    }
}
