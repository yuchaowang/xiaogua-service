package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 用户积分信息表
 * 表名 : t_credit_user
 * </pre>
 * @author: Mybatis Generator
 */
public class CreditUser {
    /**
     * <pre>
     * id
     * 表字段 : t_credit_user.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户积分编号
     * 表字段 : t_credit_user.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_credit_user.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 当前可用积分
     * 表字段 : t_credit_user.available
     * </pre>
     */
    private Integer available;

    /**
     * <pre>
     * 总计
     * 表字段 : t_credit_user.total
     * </pre>
     */
    private Integer total;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_user.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_user.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_credit_user.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_credit_user.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户积分编号
     * 表字段 : t_credit_user.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户积分编号
     * 表字段 : t_credit_user.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_credit_user.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_credit_user.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 当前可用积分
     * 表字段 : t_credit_user.available
     * </pre>
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * <pre>
     * 当前可用积分
     * 表字段 : t_credit_user.available
     * </pre>
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * <pre>
     * 总计
     * 表字段 : t_credit_user.total
     * </pre>
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * <pre>
     * 总计
     * 表字段 : t_credit_user.total
     * </pre>
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_user.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_user.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_user.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_user.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}