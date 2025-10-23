package com.xiaogua.comments.controller.admin;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.message.MessageAdminDto;
import com.xiaogua.comments.bean.message.MessageCommonPageInfo;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.domain.MessageDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "消息中心Admin Controller")
@RestController(value = "admin.MessageController")
@RequestMapping("/api/admin/message")
public class MessageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageDomain messageDomain;

    /**
     * admin-分页获取消息信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "管理平台-分页获取消息信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getPage(HttpServletRequest request, @RequestBody MessageCommonPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = messageDomain.getAdminPage(pageInfo);
            LOGGER.info("分页获取消息信息[Admin.MessageController.getPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取消息信息[Admin.MessageController.getPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取消息信息[Admin.MessageController.getPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                    CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-发送消息
     *
     * @param request  request
     * @param messageAdminDto 消息对象
     * @return
     */
    @ApiOperation(value = "管理平台-发送消息")
    @PostMapping(value = "/sendMessage", produces = "application/json")
    public String sendMessage(HttpServletRequest request, @RequestBody MessageAdminDto messageAdminDto) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = messageDomain.sendMessage(messageAdminDto);
            LOGGER.info("发送消息[Admin.MessageController.sendMessage]正常，参数：{}", JSON.toJSONString(messageAdminDto));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("发送消息[Admin.MessageController.sendMessage]告警，参数：{}", JSON.toJSONString(messageAdminDto), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("发送消息[Admin.MessageController.sendMessage]异常，参数：{}", JSON.toJSONString(messageAdminDto), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                    CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-撤销消息
     *
     * @param request  request
     * @param jsonObject 分页信息
     * @return
     */
    @ApiOperation(value = "管理平台-撤销消息")
    @PostMapping(value = "/withdrawMessage", produces = "application/json")
    public String withdrawMessage(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            String groupId = jsonObject.getString("groupId");
            isAdmin(request);
            builder = messageDomain.withdrawMessage(groupId);
            LOGGER.info("撤销消息[Admin.MessageController.withdrawMessage]正常，参数：{}", JSON.toJSONString(jsonObject));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("撤销消息[Admin.MessageController.withdrawMessage]告警，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("撤销消息[Admin.MessageController.withdrawMessage]异常，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                    CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
