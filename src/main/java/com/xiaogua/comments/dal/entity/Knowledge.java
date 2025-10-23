package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 干货信息表
 * 表名 : t_knowledge
 * </pre>
 * @author: Mybatis Generator
 */
public class Knowledge {
    /**
     * <pre>
     * id
     * 表字段 : t_knowledge.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 干货信息编号
     * 表字段 : t_knowledge.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 上传用户code
     * 表字段 : t_knowledge.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 标题
     * 表字段 : t_knowledge.title
     * </pre>
     */
    private String title;

    /**
     * <pre>
     * 简介
     * 表字段 : t_knowledge.brief
     * </pre>
     */
    private String brief;

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_knowledge.deleted
     * </pre>
     */
    private Boolean deleted;

    /**
     * <pre>
     * 审核用户code
     * 表字段 : t_knowledge.audit_user_code
     * </pre>
     */
    private String auditUserCode;

    /**
     * <pre>
     * 积分
     * 表字段 : t_knowledge.credit
     * </pre>
     */
    private Integer credit;

    /**
     * <pre>
     * 状态
     * 表字段 : t_knowledge.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 下载数
     * 表字段 : t_knowledge.download_num
     * </pre>
     */
    private Integer downloadNum;

    /**
     * <pre>
     * 干货分类
     * 表字段 : t_knowledge.category_type
     * </pre>
     */
    private Integer categoryType;

    /**
     * <pre>
     * 排序权重
     * 表字段 : t_knowledge.priority
     * </pre>
     */
    private Integer priority;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_knowledge.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_knowledge.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 干货信息编号
     * 表字段 : t_knowledge.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 干货信息编号
     * 表字段 : t_knowledge.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 上传用户code
     * 表字段 : t_knowledge.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 上传用户code
     * 表字段 : t_knowledge.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 标题
     * 表字段 : t_knowledge.title
     * </pre>
     */
    public String getTitle() {
        return title;
    }

    /**
     * <pre>
     * 标题
     * 表字段 : t_knowledge.title
     * </pre>
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * <pre>
     * 简介
     * 表字段 : t_knowledge.brief
     * </pre>
     */
    public String getBrief() {
        return brief;
    }

    /**
     * <pre>
     * 简介
     * 表字段 : t_knowledge.brief
     * </pre>
     */
    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_knowledge.deleted
     * </pre>
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * <pre>
     * 是否删除
     * 表字段 : t_knowledge.deleted
     * </pre>
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * <pre>
     * 审核用户code
     * 表字段 : t_knowledge.audit_user_code
     * </pre>
     */
    public String getAuditUserCode() {
        return auditUserCode;
    }

    /**
     * <pre>
     * 审核用户code
     * 表字段 : t_knowledge.audit_user_code
     * </pre>
     */
    public void setAuditUserCode(String auditUserCode) {
        this.auditUserCode = auditUserCode == null ? null : auditUserCode.trim();
    }

    /**
     * <pre>
     * 积分
     * 表字段 : t_knowledge.credit
     * </pre>
     */
    public Integer getCredit() {
        return credit;
    }

    /**
     * <pre>
     * 积分
     * 表字段 : t_knowledge.credit
     * </pre>
     */
    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_knowledge.status
     * </pre>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_knowledge.status
     * </pre>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 下载数
     * 表字段 : t_knowledge.download_num
     * </pre>
     */
    public Integer getDownloadNum() {
        return downloadNum;
    }

    /**
     * <pre>
     * 下载数
     * 表字段 : t_knowledge.download_num
     * </pre>
     */
    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    /**
     * <pre>
     * 干货分类
     * 表字段 : t_knowledge.category_type
     * </pre>
     */
    public Integer getCategoryType() {
        return categoryType;
    }

    /**
     * <pre>
     * 干货分类
     * 表字段 : t_knowledge.category_type
     * </pre>
     */
    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * <pre>
     * 排序权重
     * 表字段 : t_knowledge.priority
     * </pre>
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * <pre>
     * 排序权重
     * 表字段 : t_knowledge.priority
     * </pre>
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_knowledge.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_knowledge.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_knowledge.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }
}