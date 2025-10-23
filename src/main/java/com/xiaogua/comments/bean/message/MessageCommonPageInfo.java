package com.xiaogua.comments.bean.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 消息通用分页信息
 *
 */
@ApiModel("消息通用分页信息")
public class MessageCommonPageInfo extends CommonPageInfo {

    /**
     * 类型 对应记录的消息类型
     */
    @ApiModelProperty("类型 对应记录的消息类型")
    private Integer type;

    /**
     * 是否分组查询 0否 1是
     */
    @ApiModelProperty("是否分组查询 0否 1是")
    private Integer isGroupId;

    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    private String keyword;

    /**
     * 是否已读 0未读 1已读
     */
    @ApiModelProperty("是否已读")
    private Integer isRead;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsGroupId() {
        return isGroupId;
    }

    public void setIsGroupId(Integer isGroupId) {
        this.isGroupId = isGroupId;
    }
}
