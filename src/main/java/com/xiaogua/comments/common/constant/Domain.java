package com.xiaogua.comments.common.constant;

/**
 * 业务领域
 *
 * @author wangyc
 * @date 2020-11-21 21:36
 */
public enum Domain {

    /**
     * 注册
     */
    REGISTER(300101),

    /**
     * 干货
     */
    KNOWLEDGE(300102),

    /**
     * 品牌点评
     */
    BRAND_COMMENT(300103),

    /**
     * 帖子
     */
    THREADS_INFO(300104);

    private int code;

    Domain(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
