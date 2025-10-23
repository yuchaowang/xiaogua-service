package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 用户类型信息表
 * 表名 : t_user_type
 * </pre>
 * @author: Mybatis Generator
 */
public class UserType {
    /**
     * <pre>
     * id
     * 表字段 : t_user_type.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 状态
     * 表字段 : t_user_type.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 姓名
     * 表字段 : t_user_type.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 个人履历
     * 表字段 : t_user_type.brief
     * </pre>
     */
    private String brief;

    /**
     * <pre>
     * 开发商名称
     * 表字段 : t_user_type.company
     * </pre>
     */
    private String company;

    /**
     * <pre>
     * 职位
     * 表字段 : t_user_type.position
     * </pre>
     */
    private String position;

    /**
     * <pre>
     * 使用过的品牌
     * 表字段 : t_user_type.used_brand
     * </pre>
     */
    private String usedBrand;

    /**
     * <pre>
     * 使用过的产品
     * 表字段 : t_user_type.used_product
     * </pre>
     */
    private String usedProduct;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_user_type.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_user_type.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_user_type.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_user_type.status
     * </pre>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_user_type.status
     * </pre>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 姓名
     * 表字段 : t_user_type.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 姓名
     * 表字段 : t_user_type.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 个人履历
     * 表字段 : t_user_type.brief
     * </pre>
     */
    public String getBrief() {
        return brief;
    }

    /**
     * <pre>
     * 个人履历
     * 表字段 : t_user_type.brief
     * </pre>
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * <pre>
     * 开发商名称
     * 表字段 : t_user_type.company
     * </pre>
     */
    public String getCompany() {
        return company;
    }

    /**
     * <pre>
     * 开发商名称
     * 表字段 : t_user_type.company
     * </pre>
     */
    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    /**
     * <pre>
     * 职位
     * 表字段 : t_user_type.position
     * </pre>
     */
    public String getPosition() {
        return position;
    }

    /**
     * <pre>
     * 职位
     * 表字段 : t_user_type.position
     * </pre>
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * <pre>
     * 使用过的品牌
     * 表字段 : t_user_type.used_brand
     * </pre>
     */
    public String getUsedBrand() {
        return usedBrand;
    }

    /**
     * <pre>
     * 使用过的品牌
     * 表字段 : t_user_type.used_brand
     * </pre>
     */
    public void setUsedBrand(String usedBrand) {
        this.usedBrand = usedBrand == null ? null : usedBrand.trim();
    }

    /**
     * <pre>
     * 使用过的产品
     * 表字段 : t_user_type.used_product
     * </pre>
     */
    public String getUsedProduct() {
        return usedProduct;
    }

    /**
     * <pre>
     * 使用过的产品
     * 表字段 : t_user_type.used_product
     * </pre>
     */
    public void setUsedProduct(String usedProduct) {
        this.usedProduct = usedProduct == null ? null : usedProduct.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_user_type.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_user_type.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

}