package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.ThreadsReplyComment;
import java.util.List;

/**
 * 帖子回复评论分页信息
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyCommentPage {
    PagingInfo pagingInfo;

    List<ThreadsReplyComment> replyComments;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsReplyComment> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<ThreadsReplyComment> replyComments) {
        this.replyComments = replyComments;
    }
}
