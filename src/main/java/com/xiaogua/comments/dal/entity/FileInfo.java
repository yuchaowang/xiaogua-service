package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 文件记录表
 * 表名 : t_file
 * </pre>
 * @author: Mybatis Generator
 */
public class FileInfo {
    /**
     * <pre>
     * id
     * 表字段 : t_file.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_file.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 文件名称
     * 表字段 : t_file.name
     * </pre>
     */
    private String name;

    /**
     * <pre>
     * 文件类型
     * 表字段 : t_file.content_type
     * </pre>
     */
    private String contentType;

    /**
     * <pre>
     * 文件大小
     * 表字段 : t_file.size
     * </pre>
     */
    private Integer size;

    /**
     * <pre>
     * 文件地址
     * 表字段 : t_file.url
     * </pre>
     */
    private String url;

    /**
     * <pre>
     * id
     * 表字段 : t_file.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_file.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_file.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_file.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 文件名称
     * 表字段 : t_file.name
     * </pre>
     */
    public String getName() {
        return name;
    }

    /**
     * <pre>
     * 文件名称
     * 表字段 : t_file.name
     * </pre>
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * <pre>
     * 文件类型
     * 表字段 : t_file.content_type
     * </pre>
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * <pre>
     * 文件类型
     * 表字段 : t_file.content_type
     * </pre>
     */
    public void setContentType(String contentType) {
        this.contentType = contentType == null ? null : contentType.trim();
    }

    /**
     * <pre>
     * 文件大小
     * 表字段 : t_file.size
     * </pre>
     */
    public Integer getSize() {
        return size;
    }

    /**
     * <pre>
     * 文件大小
     * 表字段 : t_file.size
     * </pre>
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * <pre>
     * 文件地址
     * 表字段 : t_file.url
     * </pre>
     */
    public String getUrl() {
        return url;
    }

    /**
     * <pre>
     * 文件地址
     * 表字段 : t_file.url
     * </pre>
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}