package com.xiaogua.comments.common.constant;

/**
 * 积分操作类型
 * @author wangyc
 * @date 2020-11-21 21:36
 */
public enum CreditType {

    /**
     * 加
     */
    ADD(400101),

    /**
     * 审核不通过
     */
    SUBTRACT(400102);

    private int code;

    CreditType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
