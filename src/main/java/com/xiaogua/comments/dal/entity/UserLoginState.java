package com.xiaogua.comments.dal.entity;

import java.util.Date;

/**
 * <pre>
 * 用户登录状态信息表
 * 表名 : t_user_login_state
 * </pre>
 * @author: Mybatis Generator
 */
public class UserLoginState {
    /**
     * <pre>
     * id
     * 表字段 : t_user_login_state.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user_login_state.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * token
     * 表字段 : t_user_login_state.token
     * </pre>
     */
    private String token;

    /**
     * <pre>
     * 登录方式标识码
     * 表字段 : t_user_login_state.cacheKey
     * </pre>
     */
    private String cachekey;

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_user_login_state.expir
     * </pre>
     */
    private Date expir;

    /**
     * <pre>
     * id
     * 表字段 : t_user_login_state.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_user_login_state.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user_login_state.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_user_login_state.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * token
     * 表字段 : t_user_login_state.token
     * </pre>
     */
    public String getToken() {
        return token;
    }

    /**
     * <pre>
     * token
     * 表字段 : t_user_login_state.token
     * </pre>
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * <pre>
     * 登录方式标识码
     * 表字段 : t_user_login_state.cacheKey
     * </pre>
     */
    public String getCachekey() {
        return cachekey;
    }

    /**
     * <pre>
     * 登录方式标识码
     * 表字段 : t_user_login_state.cacheKey
     * </pre>
     */
    public void setCachekey(String cachekey) {
        this.cachekey = cachekey == null ? null : cachekey.trim();
    }

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_user_login_state.expir
     * </pre>
     */
    public Date getExpir() {
        return expir;
    }

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_user_login_state.expir
     * </pre>
     */
    public void setExpir(Date expir) {
        this.expir = expir;
    }
}