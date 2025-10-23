package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 帖子信息表
 * 表名 : t_threads
 * </pre>
 * @author: Mybatis Generator
 */
public class ThreadsInfo {
    /**
     * <pre>
     * id
     * 表字段 : t_threads.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 用户code
     * 表字段 : t_threads.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 用户名称
     * 表字段 : t_threads.user_name
     * </pre>
     */
    private String userName;

    /**
     * <pre>
     * 用户头像
     * 表字段 : t_threads.user_avatar
     * </pre>
     */
    private String userAvatar;

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_threads.usert_type
     * </pre>
     */
    private Integer usertType;

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads.like_num
     * </pre>
     */
    private Integer likeNum;

    /**
     * <pre>
     * 标题
     * 表字段 : t_threads.title
     * </pre>
     */
    private String title;

    /**
     * <pre>
     * 封面
     * 表字段 : t_threads.cover
     * </pre>
     */
    private String cover;

    /**
     * <pre>
     * 帖子分类
     * 表字段 : t_threads.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 是否加精
     * 表字段 : t_threads.is_essence
     * </pre>
     */
    private Boolean isEssence;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * id
     * 表字段 : t_threads.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_threads.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 帖子编号
     * 表字段 : t_threads.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_threads.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_threads.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 用户名称
     * 表字段 : t_threads.user_name
     * </pre>
     */
    public String getUserName() {
        return userName;
    }

    /**
     * <pre>
     * 用户名称
     * 表字段 : t_threads.user_name
     * </pre>
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * <pre>
     * 用户头像
     * 表字段 : t_threads.user_avatar
     * </pre>
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * <pre>
     * 用户头像
     * 表字段 : t_threads.user_avatar
     * </pre>
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_threads.usert_type
     * </pre>
     */
    public Integer getUsertType() {
        return usertType;
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_threads.usert_type
     * </pre>
     */
    public void setUsertType(Integer usertType) {
        this.usertType = usertType;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads.like_num
     * </pre>
     */
    public Integer getLikeNum() {
        return likeNum;
    }

    /**
     * <pre>
     * 点赞的数量
     * 表字段 : t_threads.like_num
     * </pre>
     */
    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    /**
     * <pre>
     * 标题
     * 表字段 : t_threads.title
     * </pre>
     */
    public String getTitle() {
        return title;
    }

    /**
     * <pre>
     * 标题
     * 表字段 : t_threads.title
     * </pre>
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * <pre>
     * 封面
     * 表字段 : t_threads.cover
     * </pre>
     */
    public String getCover() {
        return cover;
    }

    /**
     * <pre>
     * 封面
     * 表字段 : t_threads.cover
     * </pre>
     */
    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    /**
     * <pre>
     * 帖子分类
     * 表字段 : t_threads.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 帖子分类
     * 表字段 : t_threads.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 是否加精
     * 表字段 : t_threads.is_essence
     * </pre>
     */
    public Boolean getIsEssence() {
        return isEssence;
    }

    /**
     * <pre>
     * 是否加精
     * 表字段 : t_threads.is_essence
     * </pre>
     */
    public void setIsEssence(Boolean isEssence) {
        this.isEssence = isEssence;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_threads.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_threads.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }
}