package com.xiaogua.comments.bean.activity;

import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.dal.entity.ActivityUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动用户信息表-用户Rest
 */
@ApiModel("活动用户信息表-用户Rest")
@Data
@NoArgsConstructor
public class ActivityUserRest {

    @ApiModelProperty("活动用户编号")
    private String code;

    @ApiModelProperty("活动编号")
    private String activityCode;

    @ApiModelProperty("活动信息")
    private ActivityRest activityRest;

    @ApiModelProperty("用户编号")
    private String userCode;

    @ApiModelProperty("参加活动姓名")
    private String name;

    @ApiModelProperty("参加活动手机号")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("公司名称")
    private String company;

    @ApiModelProperty("职位")
    private String position;

    @ApiModelProperty("报名时间")
    private String createDate;

    public ActivityUserRest(ActivityUser user) {
        super();
        this.code = user.getCode();
        this.activityCode = user.getActivityCode();
        this.userCode = user.getUserCode();
        this.name = user.getName();
        this.mobile = user.getMobile();
        this.email = user.getEmail();
        this.company = user.getCompany();
        this.position = user.getPosition();
        this.createDate = user.getCreateDate();
    }
}