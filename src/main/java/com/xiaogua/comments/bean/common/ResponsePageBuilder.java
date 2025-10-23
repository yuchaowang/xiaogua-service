package com.xiaogua.comments.bean.common;

import com.xiaogua.comments.api.entity.PagingInfo;

/**
 * 返回体信息
 *
 * @author wangyc
 * @date 2020-11-16 20:26
 */
public class ResponsePageBuilder {

    RespHeader respHeader;

    PagingInfo pageInfo;

    Object data;

    public ResponsePageBuilder() {
    }

    public ResponsePageBuilder(RespHeader respHeader, PagingInfo pageInfo, Object data) {
        this.respHeader = respHeader;
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public RespHeader getRespHeader() {
        return respHeader;
    }

    public void setRespHeader(RespHeader respHeader) {
        this.respHeader = respHeader;
    }

    public PagingInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PagingInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
