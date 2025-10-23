package com.xiaogua.comments.bean.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 干货审核信息
 */
@ApiModel("干货审核信息")
public class KnowledgeAudit {

    /**
     * 干货信息编号
     */
    @ApiModelProperty("干货信息编号")
    private String code;

    /**
     * 分数
     */
    @ApiModelProperty("分数")
    private Integer credit;

    /**
     * 干货分类
     */
    @ApiModelProperty("干货分类")
    private Integer categoryType;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }
}