package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.CommentsInfo;

import java.util.List;

/**
 * 分页评论信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class CommentsPage {

    PagingInfo pagingInfo;

    List<CommentsInfo> commentsInfos;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<CommentsInfo> getCommentsInfos() {
        return commentsInfos;
    }

    public void setCommentsInfos(List<CommentsInfo> commentsInfos) {
        this.commentsInfos = commentsInfos;
    }

}
