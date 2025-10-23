package com.xiaogua.comments.bean.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xiaogua.comments.dal.entity.UserType;
import com.xiaogua.comments.dal.entity.UserTypeFile;
import java.util.List;

/**
 * <pre>
 * 用户类型信息表
 * 表名 : t_user_type
 * </pre>
 * @author: Mybatis Generator
 */
public class UserTypeRest {

    /**
     * 用户类型编号
     */
    private String code;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 用户类型
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
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 用户类型申请信息
     */
    private UserTypeApplyRest userTypeApplyRest;

    /**
     * 证明文件信息
     */
    private List<UserTypeFileRest> userTypeFiles;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public UserTypeApplyRest getUserTypeApplyRest() {
        return userTypeApplyRest;
    }

    public void setUserTypeApplyRest(UserTypeApplyRest userTypeApplyRest) {
        this.userTypeApplyRest = userTypeApplyRest;
    }

    public List<UserTypeFileRest> getUserTypeFiles() {
        return userTypeFiles;
    }

    public void setUserTypeFiles(List<UserTypeFileRest> userTypeFiles) {
        this.userTypeFiles = userTypeFiles;
    }

    public UserTypeRest() {}

    public UserTypeRest(UserType userType) {
        this.code = userType.getCode();
        this.userCode = userType.getUserCode();
        this.status = userType.getStatus();
        this.type = userType.getType();
        this.name = userType.getName();
        this.brief = userType.getBrief();
        this.company = userType.getCompany();
        this.position = userType.getPosition();
        this.usedBrand = userType.getUsedBrand();
        this.usedProduct = userType.getUsedProduct();
        this.createDate = userType.getCreateDate();
    }

}