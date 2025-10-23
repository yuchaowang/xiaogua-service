package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 用户类型申请信息表
 * 表名 : t_user_type_apply
 * </pre>
 * @author: Mybatis Generator
 */
public class UserTypeApply {
    /**
     * <pre>
     * id
     * 表字段 : t_user_type_apply.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 用户类型申请编号
     * 表字段 : t_user_type_apply.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type_apply.user_type_code
     * </pre>
     */
    private String userTypeCode;

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_apply.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_apply.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 申请状态
     * 表字段 : t_user_type_apply.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 审核意见
     * 表字段 : t_user_type_apply.opinions
     * </pre>
     */
    private String opinions;

    /**
     * <pre>
     * 审核人code
     * 表字段 : t_user_type_apply.audit_user_code
     * </pre>
     */
    private String auditUserCode;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_apply.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user_type_apply.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * id
     * 表字段 : t_user_type_apply.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_user_type_apply.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 用户类型申请编号
     * 表字段 : t_user_type_apply.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 用户类型申请编号
     * 表字段 : t_user_type_apply.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type_apply.user_type_code
     * </pre>
     */
    public String getUserTypeCode() {
        return userTypeCode;
    }

    /**
     * <pre>
     * 用户类型编号
     * 表字段 : t_user_type_apply.user_type_code
     * </pre>
     */
    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode == null ? null : userTypeCode.trim();
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_apply.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户code
     * 表字段 : t_user_type_apply.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_apply.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 用户类型
     * 表字段 : t_user_type_apply.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 申请状态
     * 表字段 : t_user_type_apply.status
     * </pre>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 申请状态
     * 表字段 : t_user_type_apply.status
     * </pre>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 审核意见
     * 表字段 : t_user_type_apply.opinions
     * </pre>
     */
    public String getOpinions() {
        return opinions;
    }

    /**
     * <pre>
     * 审核意见
     * 表字段 : t_user_type_apply.opinions
     * </pre>
     */
    public void setOpinions(String opinions) {
        this.opinions = opinions == null ? null : opinions.trim();
    }

    /**
     * <pre>
     * 审核人code
     * 表字段 : t_user_type_apply.audit_user_code
     * </pre>
     */
    public String getAuditUserCode() {
        return auditUserCode;
    }

    /**
     * <pre>
     * 审核人code
     * 表字段 : t_user_type_apply.audit_user_code
     * </pre>
     */
    public void setAuditUserCode(String auditUserCode) {
        this.auditUserCode = auditUserCode == null ? null : auditUserCode.trim();
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_apply.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_user_type_apply.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user_type_apply.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_user_type_apply.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }
}