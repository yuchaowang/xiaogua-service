package com.xiaogua.comments.bean.message;

public class MessageAdminDto {
    //用户编码
    private String userCodes;
    //发送内容
    private String content;

    public String getUserCodes() {
        return userCodes;
    }

    public void setUserCodes(String userCodes) {
        this.userCodes = userCodes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
