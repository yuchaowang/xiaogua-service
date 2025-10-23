package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 游客表
 * 表名 : t_visitor
 * </pre>
 * @author: Mybatis Generator
 */
public class Visitor {
    /**
     * <pre>
     * 主键
     * 表字段 : t_visitor.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 游客编号
     * 表字段 : t_visitor.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_visitor.open_id
     * </pre>
     */
    private String openId;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_visitor.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_visitor.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 主键
     * 表字段 : t_visitor.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 主键
     * 表字段 : t_visitor.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 游客编号
     * 表字段 : t_visitor.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 游客编号
     * 表字段 : t_visitor.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_visitor.open_id
     * </pre>
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * <pre>
     * 微信openId
     * 表字段 : t_visitor.open_id
     * </pre>
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_visitor.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_visitor.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_visitor.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_visitor.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }
}