package com.xiaogua.comments.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by wangyc on 2018/10/10.
 *
 * @version 2018/10/10 14:16 wangyc
 */
@Component
@ConfigurationProperties(prefix = "comments")
public class CommentsConfig {

    private String inviteUrl;

    public String getInviteUrl() {
        return inviteUrl;
    }

    public void setInviteUrl(String inviteUrl) {
        this.inviteUrl = inviteUrl;
    }
}
