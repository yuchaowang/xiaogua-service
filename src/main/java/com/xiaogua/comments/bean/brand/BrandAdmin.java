package com.xiaogua.comments.bean.brand;

import com.xiaogua.comments.dal.entity.Brand;

/**
 * @author wangyc
 * @date 2020-11-17 17:16
 */
public class BrandAdmin {

    /**
     * <pre>
     * 品牌编号
     * 表字段 : t_brand.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 品牌中文名
     * 表字段 : t_brand.name_cn
     * </pre>
     */
    private String nameCn;

    /**
     * <pre>
     * 品牌英文名
     * 表字段 : t_brand.name_en
     * </pre>
     */
    private String nameEn;

    /**
     * <pre>
     * 首字母
     * 表字段 : t_brand.inital
     * </pre>
     */
    private String inital;

    /**
     * <pre>
     * 图片文件url
     * 表字段 : t_brand.logoUrl
     * </pre>
     */
    private String logoUrl;

    /**
     * <pre>
     * 主营领域
     * 表字段 : t_brand.application
     * </pre>
     */
    private String application;

    /**
     * <pre>
     * 品牌介绍
     * 表字段 : t_brand.brief
     * </pre>
     */
    private String brief;

    /**
     * <pre>
     * 主营产品
     * 表字段 : t_brand.series
     * </pre>
     */
    private String series;

    /**
     * <pre>
     * 官网地址
     * 表字段 : t_brand.url
     * </pre>
     */
    private String url;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_brand.deleted
     * </pre>
     */
    private boolean deleted;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_brand.create_date
     * </pre>
     */
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BrandAdmin() {
    }

    public BrandAdmin(Brand brand) {
        this.code = brand.getCode();
        this.nameCn = brand.getNameCn();
        this.nameEn = brand.getNameEn();
        this.inital = brand.getInital();
        this.application = brand.getApplication();
        this.brief = brand.getBrief();
        this.series = brand.getSeries();
        this.logoUrl = brand.getLogoCode();
        this.url = brand.getUrl();
        this.commentNum = brand.getCommentNum();
        this.deleted = brand.getDeleted();
        this.createDate = brand.getCreateDate();
    }
}
