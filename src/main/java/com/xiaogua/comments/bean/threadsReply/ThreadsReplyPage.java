package com.xiaogua.comments.bean.threadsReply;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.ThreadsReply;
import java.util.List;

/**
 * 帖子回复分页信息
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyPage {
    PagingInfo pagingInfo;

    List<ThreadsReply> threadsReplyList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReply> getThreadsReplyList() {
        return threadsReplyList;
    }

    public void setThreadsReplyList(List<ThreadsReply> threadsReplyList) {
        this.threadsReplyList = threadsReplyList;
    }
}
