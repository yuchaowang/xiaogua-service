package com.xiaogua.comments.utils;

import com.xiaogua.comments.bean.common.RespHeader;

/**
 * 响应工具类
 *
 * @author: wangcanyi
 * @date: 2018-07-31 17:57
 */
public class ResponseUtil {

    /**
     * 获取自定义响应头
     *
     * @param code 返回码
     * @param msg  返回消息
     * @return
     */
    public static RespHeader getRespHeader(int code, String msg) {
        RespHeader respHeader = new RespHeader();
        respHeader.setCode(code);
        respHeader.setMsg(msg);
        return respHeader;
    }

    /**
     * 获取成功响应头
     *
     * @return
     */
    public static RespHeader getSuccessRespHeader() {
        RespHeader respHeader = new RespHeader();
        respHeader.setCode(0);
        respHeader.setMsg("");
        return respHeader;
    }

    /**
     * 验证响应体编码
     *
     * @param RespHeader
     */
    public static void checkResponseCode(RespHeader RespHeader) {
        if (RespHeader.getCode() != 0) {
            if (RespHeader.getCode() == CommentsRuntimeException.ERROR_CODE_100) {
                throw new RuntimeException(RespHeader.getMsg());
            } else {
                throw new CommentsRuntimeException(RespHeader.getCode(), RespHeader.getMsg());
            }
        }
    }

    /**
     * 验证响应体编码
     *
     * @param code
     * @param msg
     */
    public static void checkResponseCode(int code, String msg) {
        if (code != 0) {
            if (code == CommentsRuntimeException.ERROR_CODE_100) {
                throw new RuntimeException(msg);
            } else {
                throw new CommentsRuntimeException(code, msg);
            }
        }
    }
}
