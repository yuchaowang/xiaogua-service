package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 干货文件信息表
 * 表名 : t_knowledge_file
 * </pre>
 * @author: Mybatis Generator
 */
public class KnowledgeFile {
    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_file.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 干货文件信息编号
     * 表字段 : t_knowledge_file.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_file.knowledge_code
     * </pre>
     */
    private String knowledgeCode;

    /**
     * <pre>
     * 文件code
     * 表字段 : t_knowledge_file.file_code
     * </pre>
     */
    private String fileCode;

    /**
     * <pre>
     * 序号
     * 表字段 : t_knowledge_file.detno
     * </pre>
     */
    private Integer detno;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_file.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_file.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge_file.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 干货文件信息编号
     * 表字段 : t_knowledge_file.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 干货文件信息编号
     * 表字段 : t_knowledge_file.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_file.knowledge_code
     * </pre>
     */
    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    /**
     * <pre>
     * 干货信息code
     * 表字段 : t_knowledge_file.knowledge_code
     * </pre>
     */
    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode == null ? null : knowledgeCode.trim();
    }

    /**
     * <pre>
     * 文件code
     * 表字段 : t_knowledge_file.file_code
     * </pre>
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * <pre>
     * 文件code
     * 表字段 : t_knowledge_file.file_code
     * </pre>
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode == null ? null : fileCode.trim();
    }

    /**
     * <pre>
     * 序号
     * 表字段 : t_knowledge_file.detno
     * </pre>
     */
    public Integer getDetno() {
        return detno;
    }

    /**
     * <pre>
     * 序号
     * 表字段 : t_knowledge_file.detno
     * </pre>
     */
    public void setDetno(Integer detno) {
        this.detno = detno;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_file.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge_file.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}