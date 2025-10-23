package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangyc
 * @date 2020-11-28 18:07
 */
@ApiModel("用户类型申请分页信息")
public class UserTypeApplyPageInfo extends CommonPageInfo {

    /**
     * 审核状态
     */
    @ApiModelProperty("审核状态")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
