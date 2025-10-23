package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.api.entity.PagingInfo;
import java.util.List;

/**
 * 帖子回复评论分页信息 rest
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyCommentPageRest {
    PagingInfo pagingInfo;

    List<ThreadsReplyCommentRest> replyCommentRests;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReplyCommentRest> getReplyCommentRests() {
        return replyCommentRests;
    }

    public void setReplyCommentRests(
        List<ThreadsReplyCommentRest> replyCommentRests) {
        this.replyCommentRests = replyCommentRests;
    }
}
