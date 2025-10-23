package com.xiaogua.comments.bean.messageBoard;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.MessageBoard;

import java.util.List;

/**
 * 分页留言信息
 *
 * @author wangyc
 * @date 2021-02-18 16:47:23
 */
public class MessageBoardPage {

    PagingInfo pagingInfo;

    List<MessageBoard> messageBoards;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<MessageBoard> getMessageBoards() {
        return messageBoards;
    }

    public void setMessageBoards(List<MessageBoard> messageBoards) {
        this.messageBoards = messageBoards;
    }
}
