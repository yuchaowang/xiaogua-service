package com.xiaogua.comments.bean.messageBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 留言提交信息
 *
 */
@ApiModel(value = "留言提交信息")
public class MessageBoardSubmit {

    /**
     * 留言内容
     */
    @ApiModelProperty(value = "留言内容")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}