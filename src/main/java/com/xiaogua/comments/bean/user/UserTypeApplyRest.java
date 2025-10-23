package com.xiaogua.comments.bean.user;

import com.xiaogua.comments.dal.entity.UserTypeApply;

/**
 * <pre>
 * 用户类型申请信息表
 * 表名 : t_user_type_apply
 * </pre>
 * @author: Mybatis Generator
 */
public class UserTypeApplyRest {
    /**
     * 用户类型申请编号
     */
    private String code;

    /**
     * 用户类型编号
     */
    private String userTypeCode;

    /**
     * 用户code
     */
    private String userCode;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 申请状态
     */
    private Integer status;

    /**
     * 审核意见
     */
    private String opinions;

    /**
     * 审核人code
     */
    private String auditUserCode;

    /**
     * 创建时间（业务）
     */
    private String createDate;

    /**
     * 更新时间（业务）
     */
    private String updateDate;

    /**
     * 用户类型
     */
    private UserTypeRest userTypeRest;

    /**
     * 用户信息
     */
    private UserRest userRest;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserTypeCode() {
        return userTypeCode;
    }

    public void setUserTypeCode(String userTypeCode) {
        this.userTypeCode = userTypeCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOpinions() {
        return opinions;
    }

    public void setOpinions(String opinions) {
        this.opinions = opinions;
    }

    public String getAuditUserCode() {
        return auditUserCode;
    }

    public void setAuditUserCode(String auditUserCode) {
        this.auditUserCode = auditUserCode;
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

    public UserTypeRest getUserTypeRest() {
        return userTypeRest;
    }

    public void setUserTypeRest(UserTypeRest userTypeRest) {
        this.userTypeRest = userTypeRest;
    }

    public UserRest getUserRest() {
        return userRest;
    }

    public void setUserRest(UserRest userRest) {
        this.userRest = userRest;
    }

    public UserTypeApplyRest() {}

    public UserTypeApplyRest(UserTypeApply apply) {
        this.code = apply.getCode();
        this.userTypeCode = apply.getUserTypeCode();
        this.userCode = apply.getUserCode();
        this.type = apply.getType();
        this.status = apply.getStatus();
        this.opinions = apply.getOpinions();
        this.auditUserCode = apply.getAuditUserCode();
        this.createDate = apply.getCreateDate();
        this.updateDate = apply.getUpdateDate();
    }
}