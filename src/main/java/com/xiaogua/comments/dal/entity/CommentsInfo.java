package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 主评论信息表
 * 表名 : t_comments_info
 * </pre>
 * @author: Mybatis Generator
 */
public class CommentsInfo {
    /**
     * <pre>
     * id
     * 表字段 : t_comments_info.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_info.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 被评论编号
     * 表字段 : t_comments_info.to_code
     * </pre>
     */
    private String toCode;

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_info.from_name
     * </pre>
     */
    private String fromName;

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_info.from_avatar
     * </pre>
     */
    private String fromAvatar;

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_info.like_num
     * </pre>
     */
    private Integer likeNum;

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_info.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 用户发布评论类型
     * 表字段 : t_comments_info.user_type
     * </pre>
     */
    private Integer userType;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_info.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * id
     * 表字段 : t_comments_info.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_comments_info.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_info.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_info.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_info.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 被评论编号
     * 表字段 : t_comments_info.to_code
     * </pre>
     */
    public String getToCode() {
        return toCode;
    }

    /**
     * <pre>
     * 被评论编号
     * 表字段 : t_comments_info.to_code
     * </pre>
     */
    public void setToCode(String toCode) {
        this.toCode = toCode == null ? null : toCode.trim();
    }

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_info.from_name
     * </pre>
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_info.from_name
     * </pre>
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_info.from_avatar
     * </pre>
     */
    public String getFromAvatar() {
        return fromAvatar;
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_info.from_avatar
     * </pre>
     */
    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar == null ? null : fromAvatar.trim();
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_info.like_num
     * </pre>
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_info.like_num
     * </pre>
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_info.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_info.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_info.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 用户发布评论类型
     * 表字段 : t_comments_info.user_type
     * </pre>
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * <pre>
     * 用户发布评论类型
     * 表字段 : t_comments_info.user_type
     * </pre>
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_info.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_info.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}