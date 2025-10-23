package com.xiaogua.comments.bean.message;

import java.util.List;
import java.util.Map;

public class SubscribeMessageDto {
    //接收者（用户）的 openid (支持多个)
    private List<String> toUser;
    //所需下发的订阅模板id
    private String templateId;
    //点击模板卡片后的跳转页面，仅限本小程序内的页面。支持带参数,（示例index?foo=bar）。该字段不填则模板无跳转。
    private String page;
    //模板内容，格式形如 { "key1": { "value": any }, "key2": { "value": any } }
    private Map<String, Object> data;
    //跳转小程序类型：developer为开发版；trial为体验版；formal为正式版；默认为正式版
    private String miniprogramState;
    //进入小程序查看”的语言类型，支持zh_CN(简体中文)、en_US(英文)、zh_HK(繁体中文)、zh_TW(繁体中文)，默认为zh_CN
    private String lang;

    public List<String> getToUser() {
        return toUser;
    }

    public void setToUser(List<String> toUser) {
        this.toUser = toUser;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMiniprogramState() {
        return miniprogramState;
    }

    public void setMiniprogramState(String miniprogramState) {
        this.miniprogramState = miniprogramState;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
