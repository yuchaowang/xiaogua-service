package com.xiaogua.comments.bean.messageBoard;

import com.xiaogua.comments.bean.user.UserRest;
import com.xiaogua.comments.dal.entity.MessageBoard;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 留言信息
 * @author wangyc
 * @date 2021-02-18 17:23
 */
@ApiModel("留言信息")
public class MessageBoardRest {

    /**
     * 留言编号
     */
    @ApiModelProperty("留言编号")
    private String code;

    /**
     * 留言用户code
     */
    @ApiModelProperty("留言用户code")
    private String userCode;

    /**
     * 留言用户信息
     */
    @ApiModelProperty("留言用户信息")
    private UserRest user;

    /**
     * 内容
     */
    @ApiModelProperty("内容")
    private String content;

    /**
     * 回复
     */
    @ApiModelProperty("回复")
    private String replyContent;

    /**
     * 回复用户code
     */
    @ApiModelProperty("回复用户code")
    private String replyCode;

    /**
     * 回复用户信息
     */
    @ApiModelProperty("回复用户信息")
    private UserRest replyer;

    /**
     * 留言时间
     */
    @ApiModelProperty("留言时间")
    private String createDate;

    /**
     * 回复时间
     */
    @ApiModelProperty("回复时间")
    private String replyDate;

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

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public UserRest getReplyer() {
        return replyer;
    }

    public void setReplyer(UserRest replyer) {
        this.replyer = replyer;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(String replyDate) {
        this.replyDate = replyDate;
    }

    public MessageBoardRest() {
    }

    public MessageBoardRest(MessageBoard messageBoard) {
        this.code = messageBoard.getCode();
        this.userCode = messageBoard.getUserCode();
        this.content = messageBoard.getContent();
        this.replyContent = messageBoard.getReplyContent();
        this.replyCode = messageBoard.getReplyCode();
        this.createDate = messageBoard.getCreateDate();
        this.replyDate = messageBoard.getReplyDate();
    }
}
