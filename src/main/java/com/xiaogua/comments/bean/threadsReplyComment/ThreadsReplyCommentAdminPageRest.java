package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.api.entity.PagingInfo;

import java.util.List;

/**
 * 帖子回复评论分页信息 admin rest
 *
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyCommentAdminPageRest {
    PagingInfo pagingInfo;

    List<ThreadsReplyCommentAdminRest> replyCommentRests;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReplyCommentAdminRest> getReplyCommentRests() {
        return replyCommentRests;
    }

    public void setReplyCommentRests(List<ThreadsReplyCommentAdminRest> replyCommentRests) {
        this.replyCommentRests = replyCommentRests;
    }
}
