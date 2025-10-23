package com.xiaogua.comments.bean.threadsReply;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帖子回复提交信息
 */
@ApiModel(description = "帖子回复提交信息")
public class ThreadsReplySubmit {

    /**
     * 帖子编号
     */
    @ApiModelProperty(value = "帖子编号")
    private String threadsCode;

    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容")
    private String content;

    public String getThreadsCode() {
        return threadsCode;
    }

    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}