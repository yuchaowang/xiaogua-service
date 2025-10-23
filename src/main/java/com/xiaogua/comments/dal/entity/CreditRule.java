package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 积分规则信息表
 * 表名 : t_credit_rule
 * </pre>
 * @author: Mybatis Generator
 */
public class CreditRule {
    /**
     * <pre>
     * id
     * 表字段 : t_credit_rule.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 积分规则编号
     * 表字段 : t_credit_rule.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 积分规则名称
     * 表字段 : t_credit_rule.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 业务领域
     * 表字段 : t_credit_rule.domain
     * </pre>
     */
    private Integer domain;

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_rule.event
     * </pre>
     */
    private Integer event;

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_rule.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 单次数量
     * 表字段 : t_credit_rule.num
     * </pre>
     */
    private Integer num;

    /**
     * <pre>
     * 最大数量
     * 表字段 : t_credit_rule.most
     * </pre>
     */
    private Integer most;

    /**
     * <pre>
     * 周期
     * 表字段 : t_credit_rule.cycle
     * </pre>
     */
    private String cycle;

    /**
     * <pre>
     * 最大次数
     * 表字段 : t_credit_rule.times
     * </pre>
     */
    private Integer times;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_rule.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_rule.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_credit_rule.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_credit_rule.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 积分规则编号
     * 表字段 : t_credit_rule.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 积分规则编号
     * 表字段 : t_credit_rule.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 积分规则名称
     * 表字段 : t_credit_rule.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 积分规则名称
     * 表字段 : t_credit_rule.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 业务领域
     * 表字段 : t_credit_rule.domain
     * </pre>
     */
    public Integer getDomain() {
        return domain;
    }

    /**
     * <pre>
     * 业务领域
     * 表字段 : t_credit_rule.domain
     * </pre>
     */
    public void setDomain(Integer domain) {
        this.domain = domain;
    }

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_rule.event
     * </pre>
     */
    public Integer getEvent() {
        return event;
    }

    /**
     * <pre>
     * 事件
     * 表字段 : t_credit_rule.event
     * </pre>
     */
    public void setEvent(Integer event) {
        this.event = event;
    }

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_rule.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 类型
     * 表字段 : t_credit_rule.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 单次数量
     * 表字段 : t_credit_rule.num
     * </pre>
     */
    public Integer getNum() {
        return num;
    }

    /**
     * <pre>
     * 单次数量
     * 表字段 : t_credit_rule.num
     * </pre>
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * <pre>
     * 最大数量
     * 表字段 : t_credit_rule.most
     * </pre>
     */
    public Integer getMost() {
        return most;
    }

    /**
     * <pre>
     * 最大数量
     * 表字段 : t_credit_rule.most
     * </pre>
     */
    public void setMost(Integer most) {
        this.most = most;
    }

    /**
     * <pre>
     * 周期
     * 表字段 : t_credit_rule.cycle
     * </pre>
     */
    public String getCycle() {
        return cycle;
    }

    /**
     * <pre>
     * 周期
     * 表字段 : t_credit_rule.cycle
     * </pre>
     */
    public void setCycle(String cycle) {
        this.cycle = cycle == null ? null : cycle.trim();
    }

    /**
     * <pre>
     * 最大次数
     * 表字段 : t_credit_rule.times
     * </pre>
     */
    public Integer getTimes() {
        return times;
    }

    /**
     * <pre>
     * 最大次数
     * 表字段 : t_credit_rule.times
     * </pre>
     */
    public void setTimes(Integer times) {
        this.times = times;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_rule.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_credit_rule.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_rule.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_credit_rule.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}