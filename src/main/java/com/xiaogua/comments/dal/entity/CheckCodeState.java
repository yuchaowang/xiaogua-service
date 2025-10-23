package com.xiaogua.comments.dal.entity;

import java.util.Date;

/**
 * <pre>
 * 用户登录状态信息表
 * 表名 : t_checkcode_state
 * </pre>
 * @author: Mybatis Generator
 */
public class CheckCodeState {
    /**
     * <pre>
     * id
     * 表字段 : t_checkcode_state.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_checkcode_state.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 验证码
     * 表字段 : t_checkcode_state.checkcode
     * </pre>
     */
    private String checkcode;

    /**
     * <pre>
     * 验证码标识码
     * 表字段 : t_checkcode_state.cacheKey
     * </pre>
     */
    private String cachekey;

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_checkcode_state.expir
     * </pre>
     */
    private Date expir;

    /**
     * <pre>
     * id
     * 表字段 : t_checkcode_state.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_checkcode_state.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_checkcode_state.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_checkcode_state.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 验证码
     * 表字段 : t_checkcode_state.checkcode
     * </pre>
     */
    public String getCheckcode() {
        return checkcode;
    }

    /**
     * <pre>
     * 验证码
     * 表字段 : t_checkcode_state.checkcode
     * </pre>
     */
    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode == null ? null : checkcode.trim();
    }

    /**
     * <pre>
     * 验证码标识码
     * 表字段 : t_checkcode_state.cacheKey
     * </pre>
     */
    public String getCachekey() {
        return cachekey;
    }

    /**
     * <pre>
     * 验证码标识码
     * 表字段 : t_checkcode_state.cacheKey
     * </pre>
     */
    public void setCachekey(String cachekey) {
        this.cachekey = cachekey == null ? null : cachekey.trim();
    }

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_checkcode_state.expir
     * </pre>
     */
    public Date getExpir() {
        return expir;
    }

    /**
     * <pre>
     * 有效截止时间
     * 表字段 : t_checkcode_state.expir
     * </pre>
     */
    public void setExpir(Date expir) {
        this.expir = expir;
    }
}