package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.messageBoard.MessageBoardAdminRest;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPage;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPageInfo;
import com.xiaogua.comments.bean.messageBoard.MessageBoardReply;
import com.xiaogua.comments.bean.messageBoard.MessageBoardRest;
import com.xiaogua.comments.bean.messageBoard.MessageBoardSubmit;
import com.xiaogua.comments.bl.MessageBl;
import com.xiaogua.comments.bl.MessageBoardBl;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.MessageBoard;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 留言板 domain
 *
 * @author wangyc
 * @date 2021-2-18 00:11:11
 **/

@Service
public class MessageBoardDomain {

    @Autowired
    MessageBoardBl messageBoardBl;

    @Autowired
    UserDomain userDomain;

    @Autowired
    private MessageBl messageBl;

    /**
     * 保存留言
     * @param submit 留言信息
     * @param userCode 留言人code
     * @return
     */
    public ResponseBuilder save(MessageBoardSubmit submit, String userCode) {
        VerifyParamsUtil.notNull(submit, "留言提交信息不可为空");
        VerifyParamsUtil.hasText(submit.getContent(), "留言内容不可为空");
        VerifyParamsUtil.isTrue(submit.getContent().length() <= 200, "留言内容不可超过200字");

        String code = messageBoardBl.save(submit, userCode);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(code);
        //发送消息
        MessageType messageBoard = MessageType.SAVE_MESSAGE_BOARD;
        messageBl.createMessage(userCode, messageBoard.getMessage(), messageBoard.getCode(), null);
        return builder;
    }

    /**
     * 分页获取我的留言信息
     * @param pageInfo 分页信息
     * @param userCode 留言用户code
     * @return
     */
    public ResponsePageBuilder getMyPage(MessageBoardPageInfo pageInfo, String userCode) {
        // 用户不允许筛选是否已阅、状态
        pageInfo.setReaded(null);
        pageInfo.setStatus(null);
        pageInfo.setUserCode(userCode);

        MessageBoardPage page = messageBoardBl.getPage(pageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();
        builder.setData(convertToRests(page.getMessageBoards()));
        builder.setPageInfo(page.getPagingInfo());

        return builder;
    }

    /**
     * 获取我的留言详情
     * @param code 留言编号
     * @return
     */
    public ResponseBuilder get(String code, String userCode) {
        VerifyParamsUtil.hasText(code, "留言编号不可为空");

        MessageBoard messageBoard = messageBoardBl.get(code);
        VerifyParamsUtil.notNull(messageBoard, "留言不存在");
        VerifyParamsUtil.isTrue(userCode.equals(messageBoard.getUserCode()), "留言不属于你，不可查看");

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(convertToRest(messageBoard));
        return builder;
    }

    /**
     * 管理后台-分页获取留言信息
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getAdminPage(MessageBoardPageInfo pageInfo) {
        MessageBoardPage page = messageBoardBl.getPage(pageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();
        builder.setData(convertToAdminRests(page.getMessageBoards()));
        builder.setPageInfo(page.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        return builder;
    }

    /**
     * 管理后台-获取单条留言详情
     * @param code 留言编号
     * @return
     */
    public ResponseBuilder getAdmin(String code) {
        VerifyParamsUtil.hasText(code, "留言编号不可为空");

        MessageBoard messageBoard = messageBoardBl.get(code);
        VerifyParamsUtil.notNull(messageBoard, "留言不存在");

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(convertToAdminRest(messageBoard));
        return builder;
    }

    /**
     * 批量转换留言信息
     * @param messageBoards 留言信息
     * @return
     */
    public List<MessageBoardRest> convertToRests(List<MessageBoard> messageBoards) {
        List<MessageBoardRest> rests = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageBoards)) {
            for (MessageBoard messageBoard : messageBoards) {
                rests.add(convertToRest(messageBoard));
            }
        }
        return rests;
    }

    /**
     * 转换留言信息-admin
     * @param messageBoard 留言信息
     * @return
     */
    public MessageBoardRest convertToRest(MessageBoard messageBoard) {
        MessageBoardRest rest = new MessageBoardRest(messageBoard);
        if (StringUtils.isNotEmpty(messageBoard.getUserCode())) {
            rest.setUser(userDomain.getUserRest(messageBoard.getUserCode()));
        }

        if (StringUtils.isNotEmpty(messageBoard.getReplyCode())) {
            rest.setReplyer(userDomain.getUserRest(messageBoard.getReplyCode()));
        }

        return rest;
    }

    /**
     * 批量转换留言信息-admin
     * @param messageBoards 留言信息
     * @return
     */
    public List<MessageBoardAdminRest> convertToAdminRests(List<MessageBoard> messageBoards) {
        List<MessageBoardAdminRest> rests = new ArrayList<>();
        if (!CollectionUtils.isEmpty(messageBoards)) {
            for (MessageBoard messageBoard : messageBoards) {
                rests.add(convertToAdminRest(messageBoard));
            }
        }
        return rests;
    }

    /**
     * 转换留言信息-admin
     * @param messageBoard 留言信息
     * @return
     */
    public MessageBoardAdminRest convertToAdminRest(MessageBoard messageBoard) {
        MessageBoardAdminRest rest = new MessageBoardAdminRest(messageBoard);
        if (StringUtils.isNotEmpty(messageBoard.getUserCode())) {
            rest.setUser(userDomain.getUserRest(messageBoard.getUserCode()));
        }

        if (StringUtils.isNotEmpty(messageBoard.getReplyCode())) {
            rest.setReplyer(userDomain.getUserRest(messageBoard.getReplyCode()));
        }

        return rest;
    }

    /**
     * 阅读单条留言
     * @param code 留言编号
     * @return
     */
    public ResponseBuilder read(String code) {
        VerifyParamsUtil.hasText(code, "留言编号不可为空");

        MessageBoard messageBoard = messageBoardBl.get(code);
        VerifyParamsUtil.notNull(messageBoard, "留言不存在");
        VerifyParamsUtil.isTrue(!messageBoard.getIsRead(), "留言已阅，请勿重复操作");

        messageBoardBl.read(code);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量阅读留言
     * @param codes 留言编号列表
     * @return
     */
    public ResponseBuilder batchRead(List<String> codes) {
        VerifyParamsUtil.isTrue(!CollectionUtils.isEmpty(codes), "留言编号不可为空");
        int count = 0;
        for (String code : codes) {
            try {
                messageBoardBl.read(code);
                count++;
            } catch (CommentsRuntimeException e) {
                continue;
            }
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(count);
        return builder;
    }

    /**
     * 回复留言
     * @param reply 回复内容
     * @param replyUserCode 回复用户code
     * @return
     */
    public ResponseBuilder reply(MessageBoardReply reply, String replyUserCode) {
        VerifyParamsUtil.notNull(reply, "回复信息不可为空");
        VerifyParamsUtil.hasText(reply.getCode(), "留言编号不可为空");
        VerifyParamsUtil.hasText(reply.getReplyContent(), "留言内容不可为空");

        MessageBoard messageBoard = messageBoardBl.get(reply.getCode());
        VerifyParamsUtil.notNull(messageBoard, "留言不存在");
        VerifyParamsUtil.isTrue(StatusCode.MESSAGE_BOARD_UNREPLY.getCode() == messageBoard.getStatus().intValue(), "留言已回复，请勿重复回复");

        messageBoardBl.reply(reply.getCode(), reply.getReplyContent(), replyUserCode);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }
}


