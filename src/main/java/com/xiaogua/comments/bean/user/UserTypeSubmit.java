package com.xiaogua.comments.bean.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 用户类型信息表
 */
@ApiModel("用户类型信息表")
public class UserTypeSubmit {

    /**
     * 用户类型
     */
    @ApiModelProperty("用户类型")
    private Integer type;

    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;

    /**
     * 个人履历
     */
    @ApiModelProperty("个人履历")
    private String brief;

    /**
     * 开发商名称
     */
    @ApiModelProperty("开发商名称")
    private String company;

    /**
     * 职位
     */
    @ApiModelProperty("职位")
    private String position;

    /**
     * 使用过的品牌
     */
    @ApiModelProperty("使用过的品牌")
    private String usedBrand;

    /**
     * 使用过的产品
     */
    @ApiModelProperty("使用过的产品")
    private String usedProduct;

    /**
     * 证明文件信息
     */
    @ApiModelProperty("证明文件信息")
    private List<UserTypeFileSubmit> userTypeFileSubmits;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getUsedBrand() {
        return usedBrand;
    }

    public void setUsedBrand(String usedBrand) {
        this.usedBrand = usedBrand;
    }

    public String getUsedProduct() {
        return usedProduct;
    }

    public void setUsedProduct(String usedProduct) {
        this.usedProduct = usedProduct;
    }

    public List<UserTypeFileSubmit> getUserTypeFileSubmits() {
        return userTypeFileSubmits;
    }

    public void setUserTypeFileSubmits(List<UserTypeFileSubmit> userTypeFileSubmits) {
        this.userTypeFileSubmits = userTypeFileSubmits;
    }
}