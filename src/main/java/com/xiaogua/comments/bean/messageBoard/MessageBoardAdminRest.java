package com.xiaogua.comments.bean.messageBoard;

import com.xiaogua.comments.dal.entity.MessageBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 留言信息-管理后台
 * @author wangyc
 * @date 2021-02-18 17:28
 */
@ApiModel("留言信息-管理后台")
public class MessageBoardAdminRest extends MessageBoardRest {

    /**
     * 状态
     */
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 是否已阅
     */
    @ApiModelProperty("是否已阅")
    private Boolean isRead;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public MessageBoardAdminRest() {};

    public MessageBoardAdminRest(MessageBoard messageBoard) {
        super(messageBoard);
        this.status = messageBoard.getStatus();
        this.isRead = messageBoard.getIsRead();
    }
}
