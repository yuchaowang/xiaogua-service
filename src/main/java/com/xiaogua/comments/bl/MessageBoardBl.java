package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPage;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPageInfo;
import com.xiaogua.comments.bean.messageBoard.MessageBoardSubmit;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.MessageBoard;
import com.xiaogua.comments.dal.mapper.MessageBoardMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 留言板bl
 * @author wangyc
 * @date 2021-02-18 0:14
 */
@Service
public class MessageBoardBl {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MessageBoardMapper messageBoardMapper;
    @Autowired
    private MessageBl messageBl;

    /**
     * 保存留言
     * @param submit 留言信息
     * @param userCode 提交留言用户code
     * @return
     */
    public String save(MessageBoardSubmit submit, String userCode) {
        MessageBoard messageBoard = new MessageBoard();
        messageBoard.setCode(BizCodeUtil.genLongBizCode(TableCode.T_MESSAGE_BOARD.getCode()));
        messageBoard.setContent(submit.getContent());
        messageBoard.setUserCode(userCode);
        messageBoard.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        messageBoard.setIsRead(false);
        messageBoard.setStatus(StatusCode.MESSAGE_BOARD_UNREPLY.getCode());

        messageBoardMapper.insertSelective(messageBoard);

        return messageBoard.getCode();
    }

    /**
     * 分页获取留言信息
     * @param pageInfo 分页信息
     * @return
     */
    public MessageBoardPage getPage(MessageBoardPageInfo pageInfo) {
        int count = messageBoardMapper.count(pageInfo.getReaded(),
                (pageInfo.getStatus() == null || pageInfo.getStatus().intValue() == 0) ? null : pageInfo.getStatus(),
                StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode());

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<MessageBoard> messageBoards = messageBoardMapper
                .findPage(pagingInfo.getIndex(),
                        pagingInfo.getPageSize(),
                        pageInfo.getReaded(),
                        (pageInfo.getStatus() == null || pageInfo.getStatus() == 0) ? null : pageInfo.getStatus(),
                        StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode());

        MessageBoardPage messageBoardPage = new MessageBoardPage();

        if (!CollectionUtils.isEmpty(messageBoards)) {
            messageBoardPage.setMessageBoards(messageBoards);
        }
        messageBoardPage.setPagingInfo(pagingInfo);
        return messageBoardPage;
    }

    /**
     * 获取单条留言信息
     * @param code 留言编号
     * @return
     */
    public MessageBoard get(String code) {
        return messageBoardMapper.selectByCode(code);
    }

    /**
     * 阅读留言信息
     * @param code 留言编号
     */
    public void read(String code) {
        MessageBoard messageBoard = messageBoardMapper.selectByCode(code);
        if (messageBoard == null) {
            throw new CommentsRuntimeException(-1, "留言不存在");
        }

        if (messageBoard.getIsRead()) {
            throw new CommentsRuntimeException(-1, "留言已阅，请勿重复操作");
        }

        messageBoard.setIsRead(true);
        messageBoardMapper.updateByPrimaryKeySelective(messageBoard);
    }

    /**
     * 回复留言
     * @param code 留言编号
     * @param replyContent 回复内容
     * @param userCode 回复用户code
     */
    public void reply(String code, String replyContent, String userCode) {
        MessageBoard messageBoard = messageBoardMapper.selectByCode(code);
        if (messageBoard == null) {
            throw new CommentsRuntimeException(-1, "留言不存在");
        }

        if (StatusCode.MESSAGE_BOARD_UNREPLY.getCode() != messageBoard.getStatus().intValue()) {
            throw new CommentsRuntimeException(-1, "留言已回复，请勿重复回复");
        }

        messageBoard.setIsRead(true);
        messageBoard.setReplyContent(replyContent);
        messageBoard.setStatus(StatusCode.MESSAGE_BOARD_REPLYED.getCode());
        messageBoard.setReplyCode(userCode);
        messageBoard.setReplyDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        //发送消息
        MessageType replyMessage = MessageType.REPLY_MESSAGE_BOARD;
        messageBl.createMessage(messageBoard.getUserCode(),
                String.format(replyMessage.getMessage(), messageBoard.getContent(), replyContent), replyMessage.getCode(), null);

        messageBoardMapper.updateByPrimaryKeySelective(messageBoard);
    }
}
