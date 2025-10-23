package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户查询分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
@ApiModel(value = "帖子查询分页信息")
public class UserPageInfo extends CommonPageInfo {

    /**
     * 关键字
     */
    @ApiModelProperty("关键字，目前支持用户名、手机号、邮箱模糊匹配")
    private String keyword;

    /**
     * 性别
     */
    @ApiModelProperty("性别")
    private String sex;

    /**
     * 省
     */
    @ApiModelProperty("省")
    private String province;

    /**
     * 市
     */
    @ApiModelProperty("市")
    private String city;

    /**
     * 注册起始时间
     */
    @ApiModelProperty("注册起始时间")
    private String registerStartDate;

    /**
     * 注册截止时间
     */
    @ApiModelProperty("注册截止时间")
    private String registerEndDate;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegisterStartDate() {
        return registerStartDate;
    }

    public void setRegisterStartDate(String registerStartDate) {
        this.registerStartDate = registerStartDate;
    }

    public String getRegisterEndDate() {
        return registerEndDate;
    }

    public void setRegisterEndDate(String registerEndDate) {
        this.registerEndDate = registerEndDate;
    }
}
