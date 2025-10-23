package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 主评论点赞表
 * 表名 : t_comments_info_like
 * </pre>
 * @author: Mybatis Generator
 */
public class CommentsInfoLike {
    /**
     * <pre>
     * id
     * 表字段 : t_comments_info_like.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 评论点赞编号
     * 表字段 : t_comments_info_like.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info_like.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 评论code
     * 表字段 : t_comments_info_like.comments_code
     * </pre>
     */
    private String commentsCode;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info_like.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_comments_info_like.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_comments_info_like.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 评论点赞编号
     * 表字段 : t_comments_info_like.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 评论点赞编号
     * 表字段 : t_comments_info_like.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info_like.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info_like.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 评论code
     * 表字段 : t_comments_info_like.comments_code
     * </pre>
     */
    public String getCommentsCode() {
        return commentsCode;
    }

    /**
     * <pre>
     * 评论code
     * 表字段 : t_comments_info_like.comments_code
     * </pre>
     */
    public void setCommentsCode(String commentsCode) {
        this.commentsCode = commentsCode == null ? null : commentsCode.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info_like.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info_like.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}