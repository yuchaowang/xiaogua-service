package com.xiaogua.comments.bean.brand;

import com.xiaogua.comments.dal.entity.Brand;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 品牌简易信息
 * @author wangyc
 * @date 2020-11-17 17:16
 */
@ApiModel("品牌简易信息")
public class BrandSimpleRest {

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
     * 图片文件url
     */
    @ApiModelProperty("图片文件url")
    private String logoUrl;

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

    /**
     * 评论数
     */
    @ApiModelProperty("评论数")
    private Integer commentNum;

    /**
     * 点赞数
     */
    @ApiModelProperty("点赞数")
    private Integer likeNum;

    /**
     * 创建时间（业务）
     */
    @ApiModelProperty("创建时间")
    private String createDate;

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

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
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

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BrandSimpleRest() {
    }

    public BrandSimpleRest(Brand brand) {
        this.code        = brand.getCode();
        this.nameCn      = brand.getNameCn();
        this.nameEn      = brand.getNameEn();
        this.inital      = brand.getInital();
        this.logoUrl     = brand.getLogoCode();
        this.application = brand.getApplication();
        this.brief       = brand.getBrief();
        this.series      = brand.getSeries();
        this.url         = brand.getUrl();
        this.commentNum  = brand.getCommentNum();
        this.createDate  = brand.getCreateDate();
    }
}
