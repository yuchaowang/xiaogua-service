package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.comments.*;
import com.xiaogua.comments.bean.common.CommonPageInfo;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.message.*;
import com.xiaogua.comments.bl.*;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.UserMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 消息 domain
 *
 **/

@Service
public class MessageDomain {

    @Autowired
    private MessageBl messageBl;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    /**
     * 分页获取消息信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getMyMessagePage(MessageCommonPageInfo pageInfo, UserLoginState userLoginState) {
        MessagePageInfoDal pageInfoDal = new MessagePageInfoDal(pageInfo);
        pageInfoDal.setUserCode(userLoginState.getCode());

        MessagePage commentsPage = messageBl.getMessagePage(pageInfoDal);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换消息信息
        if (!CollectionUtils.isEmpty(commentsPage.getMessageList())) {
            builder.setData(convertToMessageInfoRest(commentsPage.getMessageList()));
        }

        builder.setPageInfo(commentsPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 后台分页获取消息信息
     * @param pageInfo
     * @return
     */
    public ResponsePageBuilder getAdminPage(MessageCommonPageInfo pageInfo) {
        //默认搜索所有的系统消息
        MessagePageInfoDal pageInfoDal = new MessagePageInfoDal(pageInfo);
        pageInfoDal.setType(MessageType.SYSTEM_MESSAGE.getCode());
        pageInfoDal.setIsGroupId(1);

        MessagePage commentsPage = messageBl.getMessagePage(pageInfoDal);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换消息信息
        if (!CollectionUtils.isEmpty(commentsPage.getMessageList())) {
            builder.setData(convertToMessageInfoRest(commentsPage.getMessageList()));
        }

        builder.setPageInfo(commentsPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    private List<MessageInfoRest> convertToMessageInfoRest(List<Message> messageList) {
        List<MessageInfoRest> restList = new ArrayList<>();
        for (Message message : messageList) {
            MessageInfoRest messageInfoRest = new MessageInfoRest();
            BeanUtils.copyProperties(message, messageInfoRest);
            restList.add(messageInfoRest);
        }
        return restList;
    }

    /**
     * 将消息置为已读
     *
     * @param messageCode
     * @param userLoginState
     */
    public void updateMessage(String messageCode, UserLoginState userLoginState) {
        String userCode = userLoginState.getCode();
        messageBl.updateMessage(messageCode, userCode);
    }

    /**
     * 查询消息数量
     * @param userLoginState
     * @return
     */
    public ResponseBuilder count(UserLoginState userLoginState) {
        ResponseBuilder builder = new ResponseBuilder();
        Map<String, Object> map = messageBl.count(userLoginState.getCode());
        builder.setData(map);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * admin-发送消息
     * @param messageAdminDto
     * @return
     */
    public ResponseBuilder sendMessage(MessageAdminDto messageAdminDto) {
        ResponseBuilder builder = new ResponseBuilder();
        VerifyParamsUtil.hasText(messageAdminDto.getContent(), "发送内容不能为空");
        messageBl.createSystemMessage(messageAdminDto);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * admin-撤销消息
     * @param groupId
     * @return
     */
    public ResponseBuilder withdrawMessage(String groupId) {
        ResponseBuilder builder = new ResponseBuilder();
        messageBl.withdrawMessage(groupId);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }
}


