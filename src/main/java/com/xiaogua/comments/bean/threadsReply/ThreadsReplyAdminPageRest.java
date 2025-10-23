package com.xiaogua.comments.bean.threadsReply;

import com.xiaogua.comments.api.entity.PagingInfo;

import java.util.List;

/**
 * 帖子回复分页信息 admin rest
 *
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyAdminPageRest {
    PagingInfo pagingInfo;

    List<ThreadsReplyAdminRest> threadsReplyRests;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReplyAdminRest> getThreadsReplyRests() {
        return threadsReplyRests;
    }

    public void setThreadsReplyRests(List<ThreadsReplyAdminRest> threadsReplyRests) {
        this.threadsReplyRests = threadsReplyRests;
    }
}
