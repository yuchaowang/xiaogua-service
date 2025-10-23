package com.xiaogua.comments.bean.activity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 活动提交信息
 */
@Data
public class ActivitySubmit {

    /**
     * 活动编号
     */
    @ApiModelProperty("活动编号，有编号视为更新，仅待发布活动可以更新")
    private String code;

    /**
     * 活动名称
     */
    @ApiModelProperty("活动名称")
    private String name;

    /**
     * 活动开始时间
     */
    @ApiModelProperty("活动开始时间")
    private String startTime;

    /**
     * 活动结束时间
     */
    @ApiModelProperty("活动结束时间")
    private String endTime;

    /**
     * 活动地址
     */
    @ApiModelProperty("活动地址")
    private String address;

    /**
     * 消费积分
     */
    @ApiModelProperty("消费积分")
    private Integer credit;

    /**
     * 主办单位
     */
    @ApiModelProperty("主办单位")
    private String organizer;

    /**
     * 承办单位
     */
    @ApiModelProperty("承办单位")
    private String contractor;

    /**
     * 协办单位
     */
    @ApiModelProperty("协办单位")
    private String supporter;

    /**
     * 活动封面图片编号
     */
    @ApiModelProperty("活动封面图片编号")
    private String titlePageCode;

    /**
     * 活动详情
     */
    @ApiModelProperty("活动详情")
    private String description;

    /**
     * 活动详情
     */
    @ApiModelProperty("直播地址")
    private String streamingUrl;

    @ApiModelProperty("小程序直播id")
    private String streamingId;
}