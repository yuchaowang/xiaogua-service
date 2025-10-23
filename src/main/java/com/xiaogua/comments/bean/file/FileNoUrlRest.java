package com.xiaogua.comments.bean.file;

import com.xiaogua.comments.dal.entity.FileInfo;

/**
 * 文件记录表Rest
 */
public class FileNoUrlRest {

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件类型
     */
    private String contentType;

    /**
     * 文件大小
     */
    private Integer size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public FileNoUrlRest() {}

    public FileNoUrlRest(FileInfo fileInfo) {
        this.name = fileInfo.getName();
        this.contentType = fileInfo.getContentType();
        this.size = fileInfo.getSize();
    }
}