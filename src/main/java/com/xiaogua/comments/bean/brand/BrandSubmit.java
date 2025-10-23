package com.xiaogua.comments.bean.brand;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 品牌提交信息
 * @author wangyc
 * @date 2020-11-17 17:16
 */
@ApiModel("品牌提交信息")
public class BrandSubmit {

    /**
     * 品牌编号
     */
    @ApiModelProperty("品牌编号")
    private String code;

    /**
     * 品牌中文名
     */
    @ApiModelProperty("品牌中文名")
    private String nameCn;

    /**
     * 品牌英文名
     */
    @ApiModelProperty("品牌英文名")
    private String nameEn;

    /**
     * 首字母
     */
    @ApiModelProperty("首字母")
    private String inital;

    /**
     * 图片文件地址
     */
    @ApiModelProperty("图片文件地址")
    private String logoCode;

    /**
     * 主营领域
     */
    @ApiModelProperty("主营领域")
    private String application;

    /**
     * 品牌介绍
     */
    @ApiModelProperty("品牌介绍")
    private String brief;

    /**
     * 主营产品
     */
    @ApiModelProperty("主营产品")
    private String series;

    /**
     * 官网地址
     */
    @ApiModelProperty("官网地址")
    private String url;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getInital() {
        return inital;
    }

    public void setInital(String inital) {
        this.inital = inital;
    }

    public String getLogoCode() {
        return logoCode;
    }

    public void setLogoCode(String logoCode) {
        this.logoCode = logoCode;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BrandSubmit() {
    }
}
