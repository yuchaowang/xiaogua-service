package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帖子回复评论分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
@ApiModel("帖子回复评论分页信息")
public class ThreadsReplyCommentPageInfo extends CommonPageInfo {

    /**
     * 帖子回复编号
     */
    @ApiModelProperty("帖子回复编号")
    private String replyCode;

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }
}
