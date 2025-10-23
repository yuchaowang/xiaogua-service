package com.xiaogua.comments.bean.threadsReply;

import com.xiaogua.comments.api.entity.PagingInfo;
import java.util.List;

/**
 * 帖子回复分页信息 rest
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyPageRest {
    PagingInfo pagingInfo;

    List<ThreadsReplyRest> threadsReplyRests;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReplyRest> getThreadsReplyRests() {
        return threadsReplyRests;
    }

    public void setThreadsReplyRests(List<ThreadsReplyRest> threadsReplyRests) {
        this.threadsReplyRests = threadsReplyRests;
    }
}
