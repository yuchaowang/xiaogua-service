package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 用户类型文件信息表
 * 表名 : t_user_type_file
 * </pre>
 * @author: Mybatis Generator
 */
public class UserTypeFile {
    /**
     * <pre>
     * id
     * 表字段 : t_user_type_file.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_file.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_file.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_user_type_file.file_code
     * </pre>
     */
    private String fileCode;

    /**
     * <pre>
     * 文件序号
     * 表字段 : t_user_type_file.file_detno
     * </pre>
     */
    private Integer fileDetno;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_file.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_user_type_file.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_user_type_file.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_file.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_file.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_file.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_file.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_user_type_file.file_code
     * </pre>
     */
    public String getFileCode() {
        return fileCode;
    }

    /**
     * <pre>
     * 文件编号
     * 表字段 : t_user_type_file.file_code
     * </pre>
     */
    public void setFileCode(String fileCode) {
        this.fileCode = fileCode == null ? null : fileCode.trim();
    }

    /**
     * <pre>
     * 文件序号
     * 表字段 : t_user_type_file.file_detno
     * </pre>
     */
    public Integer getFileDetno() {
        return fileDetno;
    }

    /**
     * <pre>
     * 文件序号
     * 表字段 : t_user_type_file.file_detno
     * </pre>
     */
    public void setFileDetno(Integer fileDetno) {
        this.fileDetno = fileDetno;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_file.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_file.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}