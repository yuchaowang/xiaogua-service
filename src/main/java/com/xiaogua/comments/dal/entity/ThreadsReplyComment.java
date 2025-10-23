package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 帖子回复评论信息表
 * 表名 : t_threads_reply_comment
 * </pre>
 * @author: Mybatis Generator
 */
public class ThreadsReplyComment {
    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply_comment.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 帖子回复评论编号
     * 表字段 : t_threads_reply_comment.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply_comment.threads_code
     * </pre>
     */
    private String threadsCode;

    /**
     * <pre>
     * 被评论帖子回复编号
     * 表字段 : t_threads_reply_comment.reply_code
     * </pre>
     */
    private String replyCode;

    /**
     * <pre>
     * 被评论回复评论编号
     * 表字段 : t_threads_reply_comment.reply_comment_code
     * </pre>
     */
    private String replyCommentCode;

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_threads_reply_comment.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 评论者名称
     * 表字段 : t_threads_reply_comment.from_name
     * </pre>
     */
    private String fromName;

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_threads_reply_comment.from_avatar
     * </pre>
     */
    private String fromAvatar;

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_threads_reply_comment.to_usercode
     * </pre>
     */
    private String toUsercode;

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_threads_reply_comment.to_name
     * </pre>
     */
    private String toName;

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_threads_reply_comment.to_avatar
     * </pre>
     */
    private String toAvatar;

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply_comment.like_num
     * </pre>
     */
    private Integer likeNum;

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_threads_reply_comment.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply_comment.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply_comment.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply_comment.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_threads_reply_comment.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 帖子回复评论编号
     * 表字段 : t_threads_reply_comment.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 帖子回复评论编号
     * 表字段 : t_threads_reply_comment.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply_comment.threads_code
     * </pre>
     */
    public String getThreadsCode() {
        return threadsCode;
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads_reply_comment.threads_code
     * </pre>
     */
    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode == null ? null : threadsCode.trim();
    }

    /**
     * <pre>
     * 被评论帖子回复编号
     * 表字段 : t_threads_reply_comment.reply_code
     * </pre>
     */
    public String getReplyCode() {
        return replyCode;
    }

    /**
     * <pre>
     * 被评论帖子回复编号
     * 表字段 : t_threads_reply_comment.reply_code
     * </pre>
     */
    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    /**
     * <pre>
     * 被评论回复评论编号
     * 表字段 : t_threads_reply_comment.reply_comment_code
     * </pre>
     */
    public String getReplyCommentCode() {
        return replyCommentCode;
    }

    /**
     * <pre>
     * 被评论回复评论编号
     * 表字段 : t_threads_reply_comment.reply_comment_code
     * </pre>
     */
    public void setReplyCommentCode(String replyCommentCode) {
        this.replyCommentCode = replyCommentCode == null ? null : replyCommentCode.trim();
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_threads_reply_comment.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 评论者用户code
     * 表字段 : t_threads_reply_comment.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 评论者名称
     * 表字段 : t_threads_reply_comment.from_name
     * </pre>
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * <pre>
     * 评论者名称
     * 表字段 : t_threads_reply_comment.from_name
     * </pre>
     */
    public void setFromName(String fromName) {
        this.fromName = fromName == null ? null : fromName.trim();
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_threads_reply_comment.from_avatar
     * </pre>
     */
    public String getFromAvatar() {
        return fromAvatar;
    }

    /**
     * <pre>
     * 评论者头像
     * 表字段 : t_threads_reply_comment.from_avatar
     * </pre>
     */
    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar == null ? null : fromAvatar.trim();
    }

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_threads_reply_comment.to_usercode
     * </pre>
     */
    public String getToUsercode() {
        return toUsercode;
    }

    /**
     * <pre>
     * 被评论者code
     * 表字段 : t_threads_reply_comment.to_usercode
     * </pre>
     */
    public void setToUsercode(String toUsercode) {
        this.toUsercode = toUsercode == null ? null : toUsercode.trim();
    }

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_threads_reply_comment.to_name
     * </pre>
     */
    public String getToName() {
        return toName;
    }

    /**
     * <pre>
     * 被评论者名字
     * 表字段 : t_threads_reply_comment.to_name
     * </pre>
     */
    public void setToName(String toName) {
        this.toName = toName == null ? null : toName.trim();
    }

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_threads_reply_comment.to_avatar
     * </pre>
     */
    public String getToAvatar() {
        return toAvatar;
    }

    /**
     * <pre>
     * 被评论者头像
     * 表字段 : t_threads_reply_comment.to_avatar
     * </pre>
     */
    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar == null ? null : toAvatar.trim();
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply_comment.like_num
     * </pre>
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads_reply_comment.like_num
     * </pre>
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_threads_reply_comment.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 评论内容
     * 表字段 : t_threads_reply_comment.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply_comment.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads_reply_comment.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply_comment.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads_reply_comment.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}