package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.Activity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动信息表-用户Rest
 */
@ApiModel("活动信息表-用户Rest")
@Data
@NoArgsConstructor
public class ActivityRest {

    /**
     * 活动编号
     */
    @ApiModelProperty("活动编号")
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
     * 状态
     */
    @ApiModelProperty("状态，140100: 活动待发布,140101:活动待开始,140102:活动进行中,140103:活动结束,140104:活动取消")
    private Integer status;

    /**
     * 活动封面图片地址
     */
    @ApiModelProperty("活动封面图片地址")
    private String titlePageUrl;

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

    public ActivityRest(Activity activity) {
        super();
        this.address = activity.getAddress();
        this.code = activity.getCode();
        this.credit = activity.getCredit();
        this.organizer = activity.getOrganizer();
        this.contractor = activity.getContractor();
        this.supporter = activity.getSupporter();
        this.description = activity.getDescription();
        this.endTime = activity.getEndTime();
        this.status = activity.getStatus();
        this.startTime = activity.getStartTime();
        this.name = activity.getName();
        // 活动待开始、进行中期间可查看直播地址
        if (StatusCode.ACTIVITY_RUNNING.getCode() == activity.getStatus() || StatusCode.ACTIVITY_READY.getCode() == activity.getStatus()) {
            this.streamingUrl = activity.getStreamingUrl();
            this.streamingId = activity.getStreamingId();
        }
    }

}