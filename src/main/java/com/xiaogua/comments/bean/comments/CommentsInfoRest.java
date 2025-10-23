package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.dal.entity.CommentsInfo;

/**
 * 主评论信息rest
 */
public class CommentsInfoRest extends CommentsInfoSimpleRest {
    /**
     * id
     */
    private Integer id;

    /**
     * 回复列表
     */
    private CommentsReplyPageRest commentsReplyPageRest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CommentsReplyPageRest getCommentsReplyPageRest() {
        return commentsReplyPageRest;
    }

    public void setCommentsReplyPageRest(CommentsReplyPageRest commentsReplyPageRest) {
        this.commentsReplyPageRest = commentsReplyPageRest;
    }

    public CommentsInfoRest() {}

    public CommentsInfoRest(CommentsInfo commentsInfo) {
        super(commentsInfo);
        this.id         = commentsInfo.getId();
    }
}