package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动用户请求页码信息
 *
 * @author wangyc
 * @date 2021-5-16
 */
@Data
@ApiModel(value = "活动用户请求页码信息-管理后台")
public class ActivityUserPageInfo extends CommonPageInfo {

    @ApiModelProperty(value = "活动编号")
    String activityCode;

    @ApiModelProperty(value = "用户编号")
    String userCode;
}
