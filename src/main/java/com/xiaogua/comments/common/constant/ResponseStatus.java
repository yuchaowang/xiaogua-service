package com.xiaogua.comments.common.constant;

/**
 * http响应状态
 *
 * @author wangyc
 * @create 2020-11-22 08:22:04
 **/
public enum ResponseStatus {
    /**
     * 未登录
     */
    NO_LOGIN(-201),

    /**
     * 登录token过期
     */
    TOKEN_EXPIRED(-202),

    /**
     * 非管理员
     */
    ISNOT_ADMINISTRATOR(-203);

    private int code;

    ResponseStatus(int code) {
        this.code = code;
    }
    public int value() {
        return code;
    }
}
