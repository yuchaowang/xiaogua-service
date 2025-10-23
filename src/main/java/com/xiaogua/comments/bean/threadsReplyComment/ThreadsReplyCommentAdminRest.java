package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.dal.entity.ThreadsReplyComment;

/**
 * 帖子回复评论信息 admin rest
 *
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyCommentAdminRest extends ThreadsReplyCommentRest {

    /**
     * 是否删除
     */
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ThreadsReplyCommentAdminRest() {
        super();
    }

    public ThreadsReplyCommentAdminRest(ThreadsReplyComment comment) {
        super();
        this.code             = comment.getCode();
        this.threadsCode      = comment.getThreadsCode();
        this.replyCode        = comment.getReplyCode();
        this.replyCommentCode = comment.getReplyCommentCode();
        this.userCode         = comment.getUserCode();
        this.fromName         = comment.getFromName();
        this.fromAvatar       = comment.getFromAvatar();
        this.toUsercode       = comment.getToUsercode();
        this.toName           = comment.getToName();
        this.toAvatar         = comment.getToAvatar();
        this.likeNum          = comment.getLikeNum();
        this.content          = comment.getContent();
        this.createDate       = comment.getCreateDate();
        this.deleted          = comment.getDeleted();
    }
}
