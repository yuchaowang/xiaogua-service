package com.xiaogua.comments.bean.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动请求页码信息
 *
 * @author wangyc
 * @date 2021-6-2
 */
@ApiModel(value = "活动请求页码信息")
@Data
@NoArgsConstructor
public class ActivityPageCommonInfo extends ActivityPageInfo {

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    List<Integer> statusList;

    public ActivityPageCommonInfo(ActivityPageInfo pageInfo) {
        super();
        setPageNumber(pageInfo.getPageNumber());
        setPageSize(pageInfo.getPageSize());
        setName(pageInfo.getName());
        setIsFree(pageInfo.getIsFree());
    }
}
