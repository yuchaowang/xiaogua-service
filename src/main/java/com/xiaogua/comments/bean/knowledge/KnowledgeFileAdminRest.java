package com.xiaogua.comments.bean.knowledge;

import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.dal.entity.KnowledgeFile;

/**
 * 干货文件信息 admin-rest
 */
public class KnowledgeFileAdminRest {

    /**
     * <pre>
     * 干货文件信息编号
     * 表字段 : t_knowledge_file.code
     * </pre>
     */
    private String code;

    /**
     * 干货信息code
     */
    private String knowledgeCode;

    /**
     * 文件code
     */
    private FileRest file;

    /**
     * 序号
     */
    private Integer detno;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode;
    }

    public FileRest getFile() {
        return file;
    }

    public void setFile(FileRest file) {
        this.file = file;
    }

    public Integer getDetno() {
        return detno;
    }

    public void setDetno(Integer detno) {
        this.detno = detno;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public KnowledgeFileAdminRest() {
    }

    public KnowledgeFileAdminRest(KnowledgeFile file) {
        this.code = file.getCode();
        this.knowledgeCode = file.getKnowledgeCode();
        this.detno = file.getDetno();
        this.createDate = file.getCreateDate();
    }
}