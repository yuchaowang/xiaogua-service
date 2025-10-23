package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 留言信息表
 * 表名 : t_message_board
 * </pre>
 * @author: Mybatis Generator
 */
public class MessageBoard {
    /**
     * <pre>
     * id
     * 表字段 : t_message_board.id
     * </pre>
     */
    private Integer id;

    /**
     * <pre>
     * 留言编号
     * 表字段 : t_message_board.code
     * </pre>
     */
    private String code;

    /**
     * <pre>
     * 留言用户code
     * 表字段 : t_message_board.user_code
     * </pre>
     */
    private String userCode;

    /**
     * <pre>
     * 内容
     * 表字段 : t_message_board.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 状态
     * 表字段 : t_message_board.status
     * </pre>
     */
    private Integer status;

    /**
     * <pre>
     * 是否已阅
     * 表字段 : t_message_board.is_read
     * </pre>
     */
    private Boolean isRead;

    /**
     * <pre>
     * 回复
     * 表字段 : t_message_board.reply_content
     * </pre>
     */
    private String replyContent;

    /**
     * <pre>
     * 回复用户code
     * 表字段 : t_message_board.reply_code
     * </pre>
     */
    private String replyCode;

    /**
     * <pre>
     * 留言时间
     * 表字段 : t_message_board.create_date
     * </pre>
     */
    private String createDate;

    /**
     * <pre>
     * 回复时间
     * 表字段 : t_message_board.reply_date
     * </pre>
     */
    private String replyDate;

    /**
     * <pre>
     * id
     * 表字段 : t_message_board.id
     * </pre>
     */
    public Integer getId() {
        return id;
    }

    /**
     * <pre>
     * id
     * 表字段 : t_message_board.id
     * </pre>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * <pre>
     * 留言编号
     * 表字段 : t_message_board.code
     * </pre>
     */
    public String getCode() {
        return code;
    }

    /**
     * <pre>
     * 留言编号
     * 表字段 : t_message_board.code
     * </pre>
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * <pre>
     * 留言用户code
     * 表字段 : t_message_board.user_code
     * </pre>
     */
    public String getUserCode() {
        return userCode;
    }

    /**
     * <pre>
     * 留言用户code
     * 表字段 : t_message_board.user_code
     * </pre>
     */
    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    /**
     * <pre>
     * 内容
     * 表字段 : t_message_board.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 内容
     * 表字段 : t_message_board.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_message_board.status
     * </pre>
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * <pre>
     * 状态
     * 表字段 : t_message_board.status
     * </pre>
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * <pre>
     * 是否已阅
     * 表字段 : t_message_board.is_read
     * </pre>
     */
    public Boolean getIsRead() {
        return isRead;
    }

    /**
     * <pre>
     * 是否已阅
     * 表字段 : t_message_board.is_read
     * </pre>
     */
    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    /**
     * <pre>
     * 回复
     * 表字段 : t_message_board.reply_content
     * </pre>
     */
    public String getReplyContent() {
        return replyContent;
    }

    /**
     * <pre>
     * 回复
     * 表字段 : t_message_board.reply_content
     * </pre>
     */
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    /**
     * <pre>
     * 回复用户code
     * 表字段 : t_message_board.reply_code
     * </pre>
     */
    public String getReplyCode() {
        return replyCode;
    }

    /**
     * <pre>
     * 回复用户code
     * 表字段 : t_message_board.reply_code
     * </pre>
     */
    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode == null ? null : replyCode.trim();
    }

    /**
     * <pre>
     * 留言时间
     * 表字段 : t_message_board.create_date
     * </pre>
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * <pre>
     * 留言时间
     * 表字段 : t_message_board.create_date
     * </pre>
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * <pre>
     * 回复时间
     * 表字段 : t_message_board.reply_date
     * </pre>
     */
    public String getReplyDate() {
        return replyDate;
    }

    /**
     * <pre>
     * 回复时间
     * 表字段 : t_message_board.reply_date
     * </pre>
     */
    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate == null ? null : replyDate.trim();
    }
}