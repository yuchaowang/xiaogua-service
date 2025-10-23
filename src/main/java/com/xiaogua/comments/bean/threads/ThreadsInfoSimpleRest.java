package com.xiaogua.comments.bean.threads;

import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;

/**
 * <pre>
 * 帖子简要信息
 * </pre>
 */
public class ThreadsInfoSimpleRest {

    /**
     * 帖子编号
     */
    public String code;

    /**
     * 用户code
     */
    public String userCode;

    /**
     * 用户名称
     */
    public String userName;

    /**
     * 用户头像
     */
    public String userAvatar;

    /**
     * 用户类型
     */
    public Integer usertType;

    /**
     * 是否已赞
     */
    public boolean liked;

    /**
     * 点赞的数量
     */
    public Integer likeNum;

    /**
     * 评论数量
     */
    public Integer replyNum;

    /**
     * 标题
     */
    public String title;

    /**
     * 封面
     */
    public String cover;

    /**
     * 内容
     */
    public String content;

    /**
     * 内容富文本
     */
    public String contentHtml;

    /**
     * 帖子分类
     */
    public Integer type;

    /**
     * 是否加精
     */
    public Boolean isEssence;

    /**
     * 创建时间（业务）
     */
    public String createDate;

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getUsertType() {
        return usertType;
    }

    public void setUsertType(Integer usertType) {
        this.usertType = usertType;
    }

    public String getCode() {
        return code;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Boolean getEssence() {
        return isEssence;
    }

    public void setEssence(Boolean essence) {
        isEssence = essence;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public ThreadsInfoSimpleRest() {
    }

    public ThreadsInfoSimpleRest(ThreadsInfoWithBLOBs threadsInfo) {
        this.code        = threadsInfo.getCode();
        this.userCode    = threadsInfo.getUserCode();
        this.userName    = threadsInfo.getUserName();
        this.userAvatar  = threadsInfo.getUserAvatar();
        this.usertType   = threadsInfo.getUsertType();
        this.likeNum     = threadsInfo.getLikeNum();
        this.title       = threadsInfo.getTitle();
        this.cover       = threadsInfo.getCover();
        this.content     = threadsInfo.getContent();
        this.contentHtml = threadsInfo.getContentHtml();
        this.type        = threadsInfo.getType();
        this.isEssence   = threadsInfo.getIsEssence();
        this.createDate  = threadsInfo.getCreateDate();
    }
}