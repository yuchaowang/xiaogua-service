package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 品牌信息表
 * 表名 : t_brand
 * </pre>
 * @author: Mybatis Generator
 */
public class Brand {
    /**
     * <pre>
     * id
     * 表字段 : t_brand.id
     * </pre>
     */
    private Integer id;

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
     * 图片文件编号
     * 表字段 : t_brand.logo_code
     * </pre>
     */
    private String logoCode;

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
     * <pre>
     * 评论数
     * 表字段 : t_brand.comment_num
     * </pre>
     */
    private Integer commentNum;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_brand.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_brand.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * id
     * 表字段 : t_brand.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_brand.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 品牌编号
     * 表字段 : t_brand.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 品牌编号
     * 表字段 : t_brand.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 品牌中文名
     * 表字段 : t_brand.name_cn
     * </pre>
     */
    public String getNameCn() {
        return nameCn;
    }

    /**
     * <pre>
     * 品牌中文名
     * 表字段 : t_brand.name_cn
     * </pre>
     */
    public void setNameCn(String nameCn) {
        this.nameCn = nameCn == null ? null : nameCn.trim();
    }

    /**
     * <pre>
     * 品牌英文名
     * 表字段 : t_brand.name_en
     * </pre>
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * <pre>
     * 品牌英文名
     * 表字段 : t_brand.name_en
     * </pre>
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * <pre>
     * 首字母
     * 表字段 : t_brand.inital
     * </pre>
     */
    public String getInital() {
        return inital;
    }

    /**
     * <pre>
     * 首字母
     * 表字段 : t_brand.inital
     * </pre>
     */
    public void setInital(String inital) {
        this.inital = inital == null ? null : inital.trim();
    }

    /**
     * <pre>
     * 图片文件编号
     * 表字段 : t_brand.logo_code
     * </pre>
     */
    public String getLogoCode() {
        return logoCode;
    }

    /**
     * <pre>
     * 图片文件编号
     * 表字段 : t_brand.logo_code
     * </pre>
     */
    public void setLogoCode(String logoCode) {
        this.logoCode = logoCode == null ? null : logoCode.trim();
    }

    /**
     * <pre>
     * 主营领域
     * 表字段 : t_brand.application
     * </pre>
     */
    public String getApplication() {
        return application;
    }

    /**
     * <pre>
     * 主营领域
     * 表字段 : t_brand.application
     * </pre>
     */
    public void setApplication(String application) {
        this.application = application == null ? null : application.trim();
    }

    /**
     * <pre>
     * 品牌介绍
     * 表字段 : t_brand.brief
     * </pre>
     */
    public String getBrief() {
        return brief;
    }

    /**
     * <pre>
     * 品牌介绍
     * 表字段 : t_brand.brief
     * </pre>
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * <pre>
     * 主营产品
     * 表字段 : t_brand.series
     * </pre>
     */
    public String getSeries() {
        return series;
    }

    /**
     * <pre>
     * 主营产品
     * 表字段 : t_brand.series
     * </pre>
     */
    public void setSeries(String series) {
        this.series = series == null ? null : series.trim();
    }

    /**
     * <pre>
     * 官网地址
     * 表字段 : t_brand.url
     * </pre>
     */
    public String getUrl() {
        return url;
    }

    /**
     * <pre>
     * 官网地址
     * 表字段 : t_brand.url
     * </pre>
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * <pre>
     * 评论数
     * 表字段 : t_brand.comment_num
     * </pre>
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * <pre>
     * 评论数
     * 表字段 : t_brand.comment_num
     * </pre>
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_brand.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_brand.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_brand.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_brand.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}