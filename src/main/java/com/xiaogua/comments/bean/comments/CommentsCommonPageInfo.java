package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 评论通用分页信息
 *
 * @author wangyc
 * @date 2020-11-19 19:53
 */
@ApiModel("评论通用分页信息")
public class CommentsCommonPageInfo extends CommonPageInfo {

    /**
     * 被评论编号
     */
    @ApiModelProperty("被评论编号")
    private String toCode;

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer userType;

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
