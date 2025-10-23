package com.xiaogua.comments.bean.threadsReply;

import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentPageRest;
import com.xiaogua.comments.dal.entity.ThreadsReply;

/**
 * 帖子回复信息 rest
 * @author wangyc
 * @date 2021-01-24 13:04
 */
public class ThreadsReplyRest {
    /**
     * 帖子回复编号
     */
    private String code;

    /**
     * 帖子编号
     */
    private String threadsCode;

    /**
     * 回复者用户code
     */
    private String userCode;

    /**
     * 回复者名称
     */
    private String fromName;

    /**
     * 回复者头像
     */
    private String fromAvatar;

    /**
     * 点赞的数量
     */
    private Integer likeNum;

    /**
     * 回复内容
     */
    private String content;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 是否点赞
     */
    private Boolean liked;

    /**
     * 帖子回复评论分页信息
     */
    private ThreadsReplyCommentPageRest replyCommentPageRest;

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

    public ThreadsReplyCommentPageRest getReplyCommentPageRest() {
        return replyCommentPageRest;
    }

    public void setReplyCommentPageRest(
        ThreadsReplyCommentPageRest replyCommentPageRest) {
        this.replyCommentPageRest = replyCommentPageRest;
    }

    public ThreadsReplyRest() {}

    public ThreadsReplyRest(ThreadsReply reply) {
        this.code = reply.getCode();
        this.threadsCode = reply.getThreadsCode();
        this.userCode = reply.getUserCode();
        this.fromName = reply.getFromName();
        this.fromAvatar = reply.getFromAvatar();
        this.likeNum = reply.getLikeNum();
        this.content = reply.getContent();
        this.createDate = reply.getCreateDate();
    }
}
