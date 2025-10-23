package com.xiaogua.comments.bean.common;

/**
 * 返回体信息
 *
 * @author wangyc
 * @date 2020-11-16 20:26
 */
public class ResponseBuilder {

    RespHeader respHeader;

    Object data;

    public ResponseBuilder() {
    }

    public ResponseBuilder(RespHeader respHeader, Object data) {
        this.respHeader = respHeader;
        this.data = data;
    }

    public RespHeader getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(RespHeader respHeader) {
        this.respHeader = respHeader;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
