package com.xiaogua.comments.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.comments.CommentsCommonPageInfo;
import com.xiaogua.comments.bean.comments.CommentsInfoSubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.message.MessageCommonPageInfo;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CommentsDomain;
import com.xiaogua.comments.domain.MessageDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 消息Web Controller
 *
 */
@RestController
@Api(tags = "消息Web Controller")
@RequestMapping("/api/message")
public class MessageController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageDomain messageDomain;

    /**
     * 分页获取我的消息列表
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取我的消息列表")
    @PostMapping(value = "/page/my", produces = "application/json")
    public String getMyMessagePage(HttpServletRequest request, @RequestBody MessageCommonPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = messageDomain.getMyMessagePage(pageInfo, userLoginState);
            LOGGER.info("分页获取我的消息列表[MessageController.getMyMessagePage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的消息列表[MessageController.getMyMessagePage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的消息列表[MessageController.getMyMessagePage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 将消息置为已读
     *
     * @param request  request
     * @return
     */
    @ApiOperation(value = "将消息置为已读")
    @PostMapping(value = "/updateMessage", produces = "application/json")
    public String updateMessage(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            String messageCode = jsonObject.getString("code");

            UserLoginState userLoginState = isLogin(request);
            messageDomain.updateMessage(messageCode, userLoginState);
            LOGGER.info("将消息置为已读[MessageController.updateMessage]正常，参数：{}", JSON.toJSONString(jsonObject));
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("将消息置为已读[MessageController.updateMessage]告警，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("将消息置为已读[MessageController.updateMessage]异常，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 查询消息数量
     *
     * @param request  request
     * @return
     */
    @ApiOperation(value = "查询消息数量")
    @GetMapping(value = "/count", produces = "application/json")
    public String count(HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = messageDomain.count(userLoginState);
            LOGGER.info("查询消息数量[MessageController.count]正常");
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("查询消息数量[MessageController.count]告警", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("查询消息数量[MessageController.count]异常", e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
