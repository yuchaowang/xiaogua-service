package com.xiaogua.comments.bean.messageBoard;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 留言回复信息
 * @author wangyc
 * @date 2021-02-18 23:49
 */
@ApiModel("留言回复信息")
public class MessageBoardReply {

    /**
     * 留言编号
     */
    @ApiModelProperty("留言编号")
    private String code;

    /**
     * 回复内容
     */
    @ApiModelProperty("回复内容")
    private String replyContent;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
