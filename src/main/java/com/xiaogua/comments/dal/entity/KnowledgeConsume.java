package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 干货用户消费信息表
 * 表名 : t_knowledge_consume
 * </pre>
 * @author: Mybatis Generator
 */
public class KnowledgeConsume {
    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_consume.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 干货用户消费信息编号
     * 表字段 : t_knowledge_consume.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_consume.knowledge_code
     * </pre>
     */
    private String knowledgeCode;

    /**
     * <pre>
     * 用户code
     * 表字段 : t_knowledge_consume.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_consume.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_consume.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_consume.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 干货用户消费信息编号
     * 表字段 : t_knowledge_consume.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 干货用户消费信息编号
     * 表字段 : t_knowledge_consume.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_consume.knowledge_code
     * </pre>
     */
    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_consume.knowledge_code
     * </pre>
     */
    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode == null ? null : knowledgeCode.trim();
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_knowledge_consume.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_knowledge_consume.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_consume.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_consume.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}