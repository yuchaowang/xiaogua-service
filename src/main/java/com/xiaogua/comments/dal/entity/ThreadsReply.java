package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 帖子回复信息表
 * 表名 : t_threads_reply
 * </pre>
 * @author: Mybatis Generator
 */
public class ThreadsReply {
    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 帖子回复编号
     * 表字段 : t_threads_reply.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply.threads_code
     * </pre>
     */
    private String threadsCode;

    /**
     * <pre>
     * 回复者用户code
     * 表字段 : t_threads_reply.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 回复者名称
     * 表字段 : t_threads_reply.from_name
     * </pre>
     */
    private String fromName;

    /**
     * <pre>
     * 回复者头像
     * 表字段 : t_threads_reply.from_avatar
     * </pre>
     */
    private String fromAvatar;

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply.like_num
     * </pre>
     */
    private Integer likeNum;

    /**
     * <pre>
     * 回复内容
     * 表字段 : t_threads_reply.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 帖子回复编号
     * 表字段 : t_threads_reply.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 帖子回复编号
     * 表字段 : t_threads_reply.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply.threads_code
     * </pre>
     */
    public String getThreadsCode() {
        return threadsCode;
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply.threads_code
     * </pre>
     */
    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode == null ? null : threadsCode.trim();
    }

    /**
     * <pre>
     * 回复者用户code
     * 表字段 : t_threads_reply.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 回复者用户code
     * 表字段 : t_threads_reply.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 回复者名称
     * 表字段 : t_threads_reply.from_name
     * </pre>
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * <pre>
     * 回复者名称
     * 表字段 : t_threads_reply.from_name
     * </pre>
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * <pre>
     * 回复者头像
     * 表字段 : t_threads_reply.from_avatar
     * </pre>
     */
    public String getFromAvatar() {
        return fromAvatar;
    }

    /**
     * <pre>
     * 回复者头像
     * 表字段 : t_threads_reply.from_avatar
     * </pre>
     */
    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar == null ? null : fromAvatar.trim();
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply.like_num
     * </pre>
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply.like_num
     * </pre>
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * <pre>
     * 回复内容
     * 表字段 : t_threads_reply.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 回复内容
     * 表字段 : t_threads_reply.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}