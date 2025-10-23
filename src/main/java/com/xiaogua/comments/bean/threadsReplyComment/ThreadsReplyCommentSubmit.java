package com.xiaogua.comments.bean.threadsReplyComment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帖子回复评论提交信息
 */
@ApiModel(description = "帖子回复评论提交信息")
public class ThreadsReplyCommentSubmit {

    /**
     * 帖子回复的编号
     */
    @ApiModelProperty(value = "帖子回复的编号")
    private String replyCode;

    /**
     * 帖子回复评论的编号
     */
    @ApiModelProperty(value = "帖子回复评论的编号")
    private String replyCommentCode;

    /**
     * 评论内容
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyCommentCode() {
        return replyCommentCode;
    }

    public void setReplyCommentCode(String replyCommentCode) {
        this.replyCommentCode = replyCommentCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}