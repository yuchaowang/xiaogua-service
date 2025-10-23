package com.xiaogua.comments.bean.message;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.CommentsInfo;
import com.xiaogua.comments.dal.entity.Message;

import java.util.List;

/**
 * 消息评论信息
 *
 */
public class MessagePage {

    PagingInfo pagingInfo;

    List<Message> messageList;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }
}
