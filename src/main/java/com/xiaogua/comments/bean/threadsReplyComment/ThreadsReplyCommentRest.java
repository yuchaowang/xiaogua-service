package com.xiaogua.comments.bean.threadsReplyComment;

import com.xiaogua.comments.dal.entity.ThreadsReplyComment;

/**
 * 帖子回复评论信息 rest
 *
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyCommentRest {
    /**
     * 帖子回复评论编号
     */
    public String code;

    /**
     * 帖子编号
     */
    public String threadsCode;

    /**
     * 被评论帖子回复编号
     */
    public String replyCode;

    /**
     * 被评论回复评论编号
     */
    public String replyCommentCode;

    /**
     * 评论者用户code
     */
    public String userCode;

    /**
     * 评论者名称
     */
    public String fromName;

    /**
     * 评论者头像
     */
    public String fromAvatar;

    /**
     * 被评论者code
     */
    public String toUsercode;

    /**
     * 被评论者名字
     */
    public String toName;

    /**
     * 被评论者头像
     */
    public String toAvatar;

    /**
     * 点赞的数量
     */
    public Integer likeNum;

    /**
     * 评论内容
     */
    public String content;

    /**
     * 创建时间（业务）
     */
    public String createDate;

    /**
     * 是否点赞
     */
    public Boolean liked;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getThreadsCode() {
        return threadsCode;
    }

    public void setThreadsCode(String threadsCode) {
        this.threadsCode = threadsCode;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getReplyCommentCode() {
        return replyCommentCode;
    }

    public void setReplyCommentCode(String replyCommentCode) {
        this.replyCommentCode = replyCommentCode;
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

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public ThreadsReplyCommentRest() {}

    public ThreadsReplyCommentRest(ThreadsReplyComment comment) {
        this.code             = comment.getCode();
        this.threadsCode      = comment.getThreadsCode();
        this.replyCode        = comment.getReplyCode();
        this.replyCommentCode = comment.getReplyCommentCode();
        this.userCode         = comment.getUserCode();
        this.fromName         = comment.getFromName();
        this.fromAvatar       = comment.getFromAvatar();
        this.toUsercode       = comment.getToUsercode();
        this.toName           = comment.getToName();
        this.toAvatar         = comment.getToAvatar();
        this.likeNum          = comment.getLikeNum();
        this.content          = comment.getContent();
        this.createDate       = comment.getCreateDate();
    }
}
