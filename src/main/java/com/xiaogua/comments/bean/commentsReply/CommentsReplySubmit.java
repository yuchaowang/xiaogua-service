package com.xiaogua.comments.bean.commentsReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * 评论回复提交信息
 * </pre>
 */
@ApiModel(description = "评论回复提交信息")
public class CommentsReplySubmit {

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_reply.comments_code
     * </pre>
     */
    @ApiModelProperty(value = "主评论编号")
    private String commentsCode;

    /**
     * 被评论回复编号
     */
    @ApiModelProperty(value = "被评论回复编号")
    private String replyCode;

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_reply.content
     * </pre>
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    public String getCommentsCode() {
        return commentsCode;
    }

    public void setCommentsCode(String commentsCode) {
        this.commentsCode = commentsCode;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}