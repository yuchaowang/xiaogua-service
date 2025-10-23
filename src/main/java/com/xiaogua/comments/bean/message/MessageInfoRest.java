package com.xiaogua.comments.bean.message;

import com.xiaogua.comments.bean.comments.CommentsInfoSimpleRest;
import com.xiaogua.comments.bean.comments.CommentsReplyPageRest;
import com.xiaogua.comments.dal.entity.CommentsInfo;

/**
 * 消息信息rest
 */
public class MessageInfoRest {
    /**
     * id
     */
    private Integer id;

    /**
     * 消息编号
     */
    private String code;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 类型 对应记录的消息类型
     */
    private Integer type;

    /**
     * 是否读取消息 0否 1是
     */
    private Integer isRead;

    /**
     * 跳转json参数
     */
    private String params;

    /**
     * 分组ID
     */
    private String groupId;

    /**
     * 创建时间
     */
    private String createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}