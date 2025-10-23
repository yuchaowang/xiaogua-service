package com.xiaogua.comments.bean.activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动用户提交信息
 */
@Data
@ApiModel("活动用户提交信息")
public class ActivityUserSubmit {

    /**
     * 活动编号
     */
    @ApiModelProperty("活动编号")
    private String activityCode;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 公司名称
     */
    @ApiModelProperty("公司名称")
    private String company;

    /**
     * 职位
     */
    @ApiModelProperty("职位")
    private String position;

}
