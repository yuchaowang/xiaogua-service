package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.api.entity.PagingInfo;
import java.util.List;

/**
 * 分页评论回复信息Rest
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class CommentsReplyPageRest {

    PagingInfo pagingInfo;

    List<CommentsReplyRest> commentsReplyList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<CommentsReplyRest> getCommentsReplyList() {
        return commentsReplyList;
    }

    public void setCommentsReplyList(List<CommentsReplyRest> commentsReplyList) {
        this.commentsReplyList = commentsReplyList;
    }
}
