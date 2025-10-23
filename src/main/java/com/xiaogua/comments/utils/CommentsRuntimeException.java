package com.xiaogua.comments.utils;

import com.xiaogua.exception.utils.BaseRuntimeException;

/**
 * 自定义异常
 *
 * @author: wangyc
 * @date: 2020-11-16 19:40:23
 */
public class CommentsRuntimeException extends BaseRuntimeException {

    /**
     * 权限异常
     */
    public static final int ERROR_CODE_110 = 110;
    public static final String ERROR_CODE_110_MSG = "该用户没有权限！";

    // 文件转换异常码
    public static final int IO_ERROR_CODE = 101;

    // 文件转换异常提示
    public static final String IO_ERROR_STRING = "文件格式错误，请重试";

    /**
     * Example公用异常
     *
     * @param errorMessage 错误信息
     */
    public CommentsRuntimeException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Example公用异常
     *
     * @param errorCode    错误号
     * @param errorMessage 错误信息
     */
    public CommentsRuntimeException(int errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }

}
