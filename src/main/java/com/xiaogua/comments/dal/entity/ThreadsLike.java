package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 帖子点赞表
 * 表名 : t_threads_like
 * </pre>
 * @author: Mybatis Generator
 */
public class ThreadsLike {
    /**
     * <pre>
     * id
     * 表字段 : t_threads_like.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 帖子点赞编号
     * 表字段 : t_threads_like.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 点赞用户code
     * 表字段 : t_threads_like.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 帖子code
     * 表字段 : t_threads_like.threads_code
     * </pre>
     */
    private String threadsCode;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_like.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_threads_like.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_threads_like.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 帖子点赞编号
     * 表字段 : t_threads_like.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 帖子点赞编号
     * 表字段 : t_threads_like.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 点赞用户code
     * 表字段 : t_threads_like.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 点赞用户code
     * 表字段 : t_threads_like.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 帖子code
     * 表字段 : t_threads_like.threads_code
     * </pre>
     */
    public String getThreadsCode() {
        return threadsCode;
    }

    /**
     * <pre>
     * 帖子code
     * 表字段 : t_threads_like.threads_code
     * </pre>
     */
    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode == null ? null : threadsCode.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_like.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_like.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}