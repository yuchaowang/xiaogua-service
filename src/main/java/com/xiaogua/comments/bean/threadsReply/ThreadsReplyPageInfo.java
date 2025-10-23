package com.xiaogua.comments.bean.threadsReply;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帖子回复分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
@ApiModel("帖子回复分页信息")
public class ThreadsReplyPageInfo extends CommonPageInfo {

    /**
     * 帖子编号
     */
    @ApiModelProperty("帖子编号")
    private String threadsCode;

    public String getThreadsCode() {
        return threadsCode;
    }

    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode;
    }
}
