package com.xiaogua.comments.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户类型申请审核信息
 * @author wangyc
 * @date 2020-11-28 18:07
 */
@ApiModel("用户类型申请审核信息")
public class UserTypeApplySubmit {

    /**
     * 申请编号
     */
    @ApiModelProperty("申请编号")
    private String code;

    /**
     * 审核意见
     */
    @ApiModelProperty("审核意见")
    private String opinions;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpinions() {
        return opinions;
    }

    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }
}
