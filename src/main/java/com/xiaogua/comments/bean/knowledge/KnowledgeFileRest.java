package com.xiaogua.comments.bean.knowledge;

import com.xiaogua.comments.bean.file.FileNoUrlRest;
import com.xiaogua.comments.dal.entity.KnowledgeFile;

/**
 * 干货文件信息 rest
 */
public class KnowledgeFileRest {

    /**
     * 干货文件信息编号
     */
    private String code;

    /**
     * 干货信息code
     */
    private String knowledgeCode;

    /**
     * 文件code
     */
    private FileNoUrlRest file;

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

    public FileNoUrlRest getFile() {
        return file;
    }

    public void setFile(FileNoUrlRest file) {
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

    public KnowledgeFileRest() {
    }

    public KnowledgeFileRest(KnowledgeFile file) {
        this.code = file.getCode();
        this.knowledgeCode = file.getKnowledgeCode();
        this.detno = file.getDetno();
        this.createDate = file.getCreateDate();
    }
}