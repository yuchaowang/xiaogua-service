package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动请求页码信息
 *
 * @author wangyc
 * @date 2021-6-2
 */
@ApiModel(value = "活动请求页码信息,默认按活动开始时间排序，sort=status时 优先按状态排序(未开始>进行中>已完成>已取消)")
@Data
public class ActivityPageInfo extends CommonPageInfo {

    /**
     * 活动名称
     */
    @ApiModelProperty(value = "活动名称")
    String name;

    /**
     * 是否免费
     */
    @ApiModelProperty(value = "是否免费")
    Boolean isFree;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    Integer status;
}
