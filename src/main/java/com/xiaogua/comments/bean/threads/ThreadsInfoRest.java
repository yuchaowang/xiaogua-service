package com.xiaogua.comments.bean.threads;

import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageRest;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyRest;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import java.util.List;

/**
 * 帖子信息 Rest
 * @author wangyc
 * @date 2021-01-24 12:51
 */
public class ThreadsInfoRest {
    /**
     * 帖子编号
     */
    private String code;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 用户类型
     */
    private Integer usertType;

    /**
     * 点赞的数量
     */
    private Integer likeNum;

    /**
     * 是否已赞
     */
    private boolean liked;

    /**
     * 回复数量
     */
    private Integer replyNum;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面
     */
    private String cover;

    /**
     * 帖子分类
     */
    private Integer type;

    /**
     * 是否加精
     */
    private Boolean isEssence;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 内容
     */
    private String content;

    /**
     * 内容富文本
     */
    private String contentHtml;

    /**
     * 帖子回复
     */
    private ThreadsReplyPageRest replyPageRest;

    public String getCode() {
        return code;
    }

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

    public ThreadsReplyPageRest getReplyPageRest() {
        return replyPageRest;
    }

    public void setReplyPageRest(ThreadsReplyPageRest replyPageRest) {
        this.replyPageRest = replyPageRest;
    }

    public ThreadsInfoRest() {}

    public ThreadsInfoRest(ThreadsInfoWithBLOBs threadsInfo) {
        this.code = threadsInfo.getCode();
        this.userCode = threadsInfo.getUserCode();
        this.userName = threadsInfo.getUserName();
        this.userAvatar = threadsInfo.getUserAvatar();
        this.usertType = threadsInfo.getUsertType();
        this.likeNum = threadsInfo.getLikeNum();
        this.title = threadsInfo.getTitle();
        this.cover = threadsInfo.getCover();
        this.type = threadsInfo.getType();
        this.isEssence = threadsInfo.getIsEssence();
        this.createDate = threadsInfo.getCreateDate();
        this.content = threadsInfo.getContent();
        this.contentHtml = threadsInfo.getContentHtml();
    }
}
