package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 
 * 表名 : create_user
 * </pre>
 * @author: Mybatis Generator
 */
public class CreateUser {
    /**
     * <pre>
     * 
     * 表字段 : create_user.mobile
     * </pre>
     */
    private String mobile;

    /**
     * <pre>
     * 
     * 表字段 : create_user.password
     * </pre>
     */
    private String password;

    /**
     * <pre>
     * 
     * 表字段 : create_user.mobile
     * </pre>
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * <pre>
     * 
     * 表字段 : create_user.mobile
     * </pre>
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * <pre>
     * 
     * 表字段 : create_user.password
     * </pre>
     */
    public String getPassword() {
        return password;
    }

    /**
     * <pre>
     * 
     * 表字段 : create_user.password
     * </pre>
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}