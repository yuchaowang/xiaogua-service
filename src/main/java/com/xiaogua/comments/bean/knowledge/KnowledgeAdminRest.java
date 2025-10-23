package com.xiaogua.comments.bean.knowledge;

import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.dal.entity.Knowledge;
import java.util.List;

/**
 * 干货信息 admin-rest
 */
public class KnowledgeAdminRest {

    /**
     * <pre>
     * 干货信息编号
     * 表字段 : t_knowledge.code
     * </pre>
     */
    private String code;

    /**
     * 上传用户信息
     */
    private UserRest userRest;

    /**
     * 上传用户code
     */
    private String userCode;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String brief;

    /**
     * 审核用户code
     */
    private String auditUserCode;

    /**
     * 积分
     */
    private Integer credit;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 下载数
     */
    private Integer downloadNum;

    /**
     * 干货分类
     */
    private Integer categoryType;

    /**
     * 排序权重
     */
    private Integer priority;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 更新时间（业务）
     */
    private String updateDate;

    /**
     * 干货文件信息 admin-rest
     */
    private List<KnowledgeFileAdminRest> files;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UserRest getUserRest() {
        return userRest;
    }

    public void setUserRest(UserRest userRest) {
        this.userRest = userRest;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getAuditUserCode() {
        return auditUserCode;
    }

    public void setAuditUserCode(String auditUserCode) {
        this.auditUserCode = auditUserCode;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getDownloadNum() {
        return downloadNum;
    }

    public void setDownloadNum(Integer downloadNum) {
        this.downloadNum = downloadNum;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<KnowledgeFileAdminRest> getFiles() {
        return files;
    }

    public void setFiles(List<KnowledgeFileAdminRest> files) {
        this.files = files;
    }

    public KnowledgeAdminRest() {}

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public KnowledgeAdminRest(Knowledge knowledge) {
        this.code = knowledge.getCode();
        this.userCode = knowledge.getUserCode();
        this.title = knowledge.getTitle();
        this.brief = knowledge.getBrief();
        this.auditUserCode = knowledge.getAuditUserCode();
        this.credit = knowledge.getCredit();
        this.status = knowledge.getStatus();
        this.downloadNum = knowledge.getDownloadNum();
        this.categoryType = knowledge.getCategoryType();
        this.priority = knowledge.getPriority();
        this.createDate = knowledge.getCreateDate();
        this.updateDate = knowledge.getUpdateDate();
    }
}