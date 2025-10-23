package com.xiaogua.comments.bean.messageBoard;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 留言分页信息
 *
 * @author wangyc
 * @date 2020-11-16 19:17:39
 */
@ApiModel(value = "留言分页信息")
public class MessageBoardPageInfo extends CommonPageInfo {

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 是否阅读
     */
    @ApiModelProperty(value = "是否阅读")
    private Boolean readed;

    /**
     * 留言人code
     */
    @ApiModelProperty(value = "留言人code")
    private String userCode;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
