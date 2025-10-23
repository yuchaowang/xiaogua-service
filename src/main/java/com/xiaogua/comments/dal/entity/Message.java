package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 消息表
 * 表名 : t_message
 * </pre>
 * @author: Mybatis Generator
 */
public class Message {
    /**
     * <pre>
     * 主键
     * 表字段 : t_message.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 消息编号
     * 表字段 : t_message.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 分组ID
     * 表字段 : t_message.group_id
     * </pre>
     */
    private String groupId;

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_message.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 消息内容
     * 表字段 : t_message.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 类型 对应记录的消息类型
     * 表字段 : t_message.type
     * </pre>
     */
    private Integer type;

    /**
     * <pre>
     * 是否读取消息 0否 1是
     * 表字段 : t_message.is_read
     * </pre>
     */
    private Integer isRead;

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_message.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_message.update_date
     * </pre>
     */
    private String updateDate;

    /**
     * <pre>
     * 跳转json参数
     * 表字段 : t_message.params
     * </pre>
     */
    private String params;

    /**
     * <pre>
     * 主键
     * 表字段 : t_message.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * 主键
     * 表字段 : t_message.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 消息编号
     * 表字段 : t_message.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 消息编号
     * 表字段 : t_message.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_message.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 用户编号
     * 表字段 : t_message.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 类型 对应记录的消息类型
     * 表字段 : t_message.type
     * </pre>
     */
    public Integer getType() {
        return type;
    }

    /**
     * <pre>
     * 类型 对应记录的消息类型
     * 表字段 : t_message.type
     * </pre>
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * <pre>
     * 是否读取消息 0否 1是
     * 表字段 : t_message.is_read
     * </pre>
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * <pre>
     * 是否读取消息 0否 1是
     * 表字段 : t_message.is_read
     * </pre>
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_message.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 创建时间（业务）
     * 表字段 : t_message.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_message.update_date
     * </pre>
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * <pre>
     * 更新时间（业务）
     * 表字段 : t_message.update_date
     * </pre>
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate == null ? null : updateDate.trim();
    }

    /**
     * <pre>
     * 跳转json参数
     * 表字段 : t_message.params
     * </pre>
     */
    public String getParams() {
        return params;
    }

    /**
     * <pre>
     * 跳转json参数
     * 表字段 : t_message.params
     * </pre>
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * <pre>
     * 消息内容
     * 表字段 : t_message.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 消息内容
     * 表字段 : t_message.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 分组ID
     * 表字段 : t_message.group_id
     * </pre>
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * <pre>
     * 分组ID
     * 表字段 : t_message.group_id
     * </pre>
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}