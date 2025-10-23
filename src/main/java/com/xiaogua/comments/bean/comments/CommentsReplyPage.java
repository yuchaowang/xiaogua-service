package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.CommentsReply;

import java.util.List;

/**
 * 分页评论回复信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class CommentsReplyPage {

    PagingInfo pagingInfo;

    List<CommentsReply> commentsReplyList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<CommentsReply> getCommentsReplyList() {
        return commentsReplyList;
    }

    public void setCommentsReplyList(List<CommentsReply> commentsReplyList) {
        this.commentsReplyList = commentsReplyList;
    }
}
