package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.dal.entity.CommentsReply;

/**
 * <pre>
 * 评论回复信息表
 * 表名 : t_comments_reply
 * </pre>
 *
 * @author: Mybatis Generator
 */
public class CommentsReplyRest {

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
     * 点赞数
     */
    private Integer likeNum;

    /**
     * 是否已赞
     */
    private boolean liked;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCommentsCode() {
        return commentsCode;
    }

    public void setCommentsCode(String commentsCode) {
        this.commentsCode = commentsCode;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getToUsercode() {
        return toUsercode;
    }

    public void setToUsercode(String toUsercode) {
        this.toUsercode = toUsercode;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public String getToAvatar() {
        return toAvatar;
    }

    public void setToAvatar(String toAvatar) {
        this.toAvatar = toAvatar;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public CommentsReplyRest() {}

    public CommentsReplyRest(CommentsReply commentsReply) {
        this.code         = commentsReply.getCode();
        this.commentsCode = commentsReply.getCommentsCode();
        this.replyCode    = commentsReply.getReplyCode();
        this.userCode     = commentsReply.getUserCode();
        this.fromName     = commentsReply.getFromName();
        this.fromAvatar   = commentsReply.getFromAvatar();
        this.toUsercode   = commentsReply.getToUsercode();
        this.toName       = commentsReply.getToName();
        this.toAvatar     = commentsReply.getToAvatar();
        this.likeNum      = commentsReply.getLikeNum();
        this.content      = commentsReply.getContent();
        this.createDate   = commentsReply.getCreateDate();
    }
}