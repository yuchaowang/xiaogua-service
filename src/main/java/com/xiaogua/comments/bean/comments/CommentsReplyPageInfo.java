package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.bean.common.CommonPageInfo;

/**
 * 评论回复分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
public class CommentsReplyPageInfo extends CommonPageInfo {

    /**
     * 评论编号
     */
    private String commentsCode;

    public String getCommentsCode() {
        return commentsCode;
    }

    public void setCommentsCode(String commentsCode) {
        this.commentsCode = commentsCode;
    }
}
