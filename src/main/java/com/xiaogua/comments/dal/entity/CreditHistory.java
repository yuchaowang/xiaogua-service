package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 积分记录信息表
 * 表名 : t_credit_history
 * </pre>
 * @author: Mybatis Generator
 */
public class CreditHistory {
    /**
     * <pre>
     * id
     * 表字段 : t_credit_history.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 积分记录编号
     * 表字段 : t_credit_history.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 发起用户编号
     * 表字段 : t_credit_history.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_history.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 数量
     * 表字段 : t_credit_history.num
     * </pre>
     */
    private Integer num;

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_history.event
     * </pre>
     */
    private Integer event;

    /**
     * <pre>
     * 关联编号
     * 表字段 : t_credit_history.rel_code
     * </pre>
     */
    private String relCode;

    /**
     * <pre>
     * 影响用户编号
     * 表字段 : t_credit_history.rel_user_code
     * </pre>
     */
    private String relUserCode;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_history.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_history.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_credit_history.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_credit_history.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 积分记录编号
     * 表字段 : t_credit_history.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 积分记录编号
     * 表字段 : t_credit_history.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 发起用户编号
     * 表字段 : t_credit_history.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 发起用户编号
     * 表字段 : t_credit_history.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_history.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_history.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 数量
     * 表字段 : t_credit_history.num
     * </pre>
     */
    public Integer getNum() {
        return num;
    }

    /**
     * <pre>
     * 数量
     * 表字段 : t_credit_history.num
     * </pre>
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_history.event
     * </pre>
     */
    public Integer getEvent() {
        return event;
    }

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_history.event
     * </pre>
     */
    public void setEvent(Integer event) {
        this.event = event;
    }

    /**
     * <pre>
     * 关联编号
     * 表字段 : t_credit_history.rel_code
     * </pre>
     */
    public String getRelCode() {
        return relCode;
    }

    /**
     * <pre>
     * 关联编号
     * 表字段 : t_credit_history.rel_code
     * </pre>
     */
    public void setRelCode(String relCode) {
        this.relCode = relCode == null ? null : relCode.trim();
    }

    /**
     * <pre>
     * 影响用户编号
     * 表字段 : t_credit_history.rel_user_code
     * </pre>
     */
    public String getRelUserCode() {
        return relUserCode;
    }

    /**
     * <pre>
     * 影响用户编号
     * 表字段 : t_credit_history.rel_user_code
     * </pre>
     */
    public void setRelUserCode(String relUserCode) {
        this.relUserCode = relUserCode == null ? null : relUserCode.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_history.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_history.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_history.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_history.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}