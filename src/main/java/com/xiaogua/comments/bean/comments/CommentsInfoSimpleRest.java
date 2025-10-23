package com.xiaogua.comments.bean.comments;

import com.xiaogua.comments.bean.brand.BrandSimpleRest;
import com.xiaogua.comments.dal.entity.CommentsInfo;

/**
 * 主评论简易信息
 */
public class CommentsInfoSimpleRest {

    /**
     * 主评论编号
     */
    private String code;

    /**
     * 评论者用户code
     */
    private String userCode;

    /**
     * 被评论编号
     */
    private String toCode;

    /**
     * 品牌信息
     */
    private BrandSimpleRest brandRest;

    /**
     * 评论者名字
     */
    private String fromName;

    /**
     * 评论者头像
     */
    private String fromAvatar;

    /**
     * 点赞的数量
     */
    private Integer likeNum;

    /**
     * 是否已赞
     */
    private boolean liked;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 用户类型
     */
    private Integer userType;

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

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public BrandSimpleRest getBrandRest() {
        return brandRest;
    }

    public void setBrandRest(BrandSimpleRest brandRest) {
        this.brandRest = brandRest;
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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public CommentsInfoSimpleRest() {
        super();
    }

    public CommentsInfoSimpleRest(CommentsInfo commentsInfo) {
        this.code       = commentsInfo.getCode();
        this.userCode   = commentsInfo.getUserCode();
        this.toCode     = commentsInfo.getToCode();
        this.fromName   = commentsInfo.getFromName();
        this.fromAvatar = commentsInfo.getFromAvatar();
        this.likeNum    = commentsInfo.getLikeNum();
        this.content    = commentsInfo.getContent();
        this.userType   = commentsInfo.getUserType();
        this.createDate = commentsInfo.getCreateDate();
    }
}