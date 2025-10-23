package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 评论回复信息表
 * 表名 : t_comments_reply
 * </pre>
 * @author: Mybatis Generator
 */
public class CommentsReply {
    /**
     * <pre>
     * id
     * 表字段 : t_comments_reply.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 评论回复编号
     * 表字段 : t_comments_reply.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_reply.comments_code
     * </pre>
     */
    private String commentsCode;

    /**
     * <pre>
     * 被评论回复编号
     * 表字段 : t_comments_reply.reply_code
     * </pre>
     */
    private String replyCode;

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_reply.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_reply.from_name
     * </pre>
     */
    private String fromName;

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_reply.from_avatar
     * </pre>
     */
    private String fromAvatar;

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_comments_reply.to_usercode
     * </pre>
     */
    private String toUsercode;

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_comments_reply.to_name
     * </pre>
     */
    private String toName;

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_comments_reply.to_avatar
     * </pre>
     */
    private String toAvatar;

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_reply.like_num
     * </pre>
     */
    private Integer likeNum;

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_reply.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_reply.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_reply.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * id
     * 表字段 : t_comments_reply.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_comments_reply.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 评论回复编号
     * 表字段 : t_comments_reply.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 评论回复编号
     * 表字段 : t_comments_reply.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_reply.comments_code
     * </pre>
     */
    public String getCommentsCode() {
        return commentsCode;
    }

    /**
     * <pre>
     * 主评论编号
     * 表字段 : t_comments_reply.comments_code
     * </pre>
     */
    public void setCommentsCode(String commentsCode) {
        this.commentsCode = commentsCode == null ? null : commentsCode.trim();
    }

    /**
     * <pre>
     * 被评论回复编号
     * 表字段 : t_comments_reply.reply_code
     * </pre>
     */
    public String getReplyCode() {
        return replyCode;
    }

    /**
     * <pre>
     * 被评论回复编号
     * 表字段 : t_comments_reply.reply_code
     * </pre>
     */
    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_reply.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_comments_reply.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_reply.from_name
     * </pre>
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * <pre>
     * 评论者名字
     * 表字段 : t_comments_reply.from_name
     * </pre>
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_reply.from_avatar
     * </pre>
     */
    public String getFromAvatar() {
        return fromAvatar;
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_comments_reply.from_avatar
     * </pre>
     */
    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar == null ? null : fromAvatar.trim();
    }

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_comments_reply.to_usercode
     * </pre>
     */
    public String getToUsercode() {
        return toUsercode;
    }

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_comments_reply.to_usercode
     * </pre>
     */
    public void setToUsercode(String toUsercode) {
        this.toUsercode = toUsercode == null ? null : toUsercode.trim();
    }

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_comments_reply.to_name
     * </pre>
     */
    public String getToName() {
        return toName;
    }

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_comments_reply.to_name
     * </pre>
     */
    public void setToName(String toName) {
        this.toName = toName == null ? null : toName.trim();
    }

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_comments_reply.to_avatar
     * </pre>
     */
    public String getToAvatar() {
        return toAvatar;
    }

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_comments_reply.to_avatar
     * </pre>
     */
    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar == null ? null : toAvatar.trim();
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_reply.like_num
     * </pre>
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_comments_reply.like_num
     * </pre>
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_reply.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_comments_reply.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_reply.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_comments_reply.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_reply.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_comments_reply.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}