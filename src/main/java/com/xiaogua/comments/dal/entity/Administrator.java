package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 管理员信息表
 * 表名 : t_administrator
 * </pre>
 * @author: Mybatis Generator
 */
public class Administrator {
    /**
     * <pre>
     * 用户编号
     * 表字段 : t_administrator.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_administrator.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_administrator.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_administrator.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_administrator.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_administrator.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_administrator.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_administrator.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_administrator.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}