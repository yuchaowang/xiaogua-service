package com.xiaogua.comments.bean.comments;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * 主评论信息表
 * 表名 : t_comments_info
 * </pre>
 *
 * @author: Mybatis Generator
 */
@ApiModel(value = "主评论提交信息")
public class CommentsInfoSubmit {

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_info.code
     * </pre>
     */
    @ApiModelProperty(value = "主评论编号")
    private String code;

    /**
     * <pre>
     * 被评论编号
     * 表字段 : t_comments_info.to_code
     * </pre>
     */
    @ApiModelProperty(value = "被评论编号")
    private String toCode;

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_info.content
     * </pre>
     */
    @ApiModelProperty(value = "评论内容")
    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}