package com.xiaogua.comments.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 参数校验
 *
 * @author: wangyc
 * @date: 2020-11-17 20:27:23
 */
public class VerifyParamsUtil {

    /**
     * 对象是否为空
     *
     * @param object  对象
     * @param message 错误消息
     * @throws CommentsRuntimeException
     */
    public static void notNull(Object object, String message) throws CommentsRuntimeException {
        if (object == null) {
            throw new CommentsRuntimeException(-1, message);
        }
    }

    /**
     * 是否有值
     *
     * @param text    文本
     * @param message 错误消息
     * @throws CommentsRuntimeException CommentsRuntimeException
     */
    public static void hasText(String text, String message) throws CommentsRuntimeException {
        if (StringUtils.isBlank(text)) {
            throw new CommentsRuntimeException(-1, message);
        }
        if (StringUtils.isBlank(text.trim())) {
            throw new CommentsRuntimeException(-1, message);
        }
    }

    /**
     * 表达式是否正确
     *
     * @param expression 表达式
     * @param message    错误消息
     * @throws CommentsRuntimeException CommentsRuntimeException
     */
    public static void isTrue(boolean expression, String message) throws CommentsRuntimeException {
        if (!expression) {
            throw new CommentsRuntimeException(-1, message);
        }
    }

    /**
     * 位运算 计算存在哪些为true
     *
     * @param bls
     * @return
     */
    public static int bitOperation(boolean... bls) {
        if (bls.length <= 0) {
            return 0;
        }
        int result = 0;
        for (int i = 0; i < bls.length; i++) {
            int b = bls.clone()[i] ? 1 : 0;
            result = result | (b << (bls.length - i - 1));
        }
        return result;
    }
}
