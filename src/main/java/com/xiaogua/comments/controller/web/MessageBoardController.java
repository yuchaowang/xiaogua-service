package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPageInfo;
import com.xiaogua.comments.bean.messageBoard.MessageBoardSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.MessageBoardDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 留言板Web Controller
 *
 * @author wangyc
 * @date 2021-2-18 00:06:43
 */
@RestController
@Api(tags = "留言板Web Controller")
@RequestMapping("/api/messageboard")
public class MessageBoardController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    MessageBoardDomain messageBoardDomain;

    /**
     * 分页获取我的留言信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取我的留言信息")
    @PostMapping(value = "/page/my", produces = "application/json")
    public String getMyPage(HttpServletRequest request, @RequestBody MessageBoardPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = messageBoardDomain.getMyPage(pageInfo, userLoginState.getCode());
            LOGGER.info("分页获取我的留言信息[MessageBoardController.getMyPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的留言信息[MessageBoardController.getMyPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的留言信息[MessageBoardController.getMyPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取我的留言信息详情
     *
     * @param request  request
     * @param code 留言编号
     * @return
     */
    @ApiOperation(value = "获取我的留言信息详情")
    @GetMapping(value = "/get", produces = "application/json")
        public String get(HttpServletRequest request, @RequestParam String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = messageBoardDomain.get(code, userLoginState.getCode());
            LOGGER.info("获取我的留言信息详情[MessageBoardController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取我的留言信息详情[MessageBoardController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取我的留言信息详情[MessageBoardController.get]异常，参数：{}", code, e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 保存留言
     *
     * @param request      request
     * @param submit 留言信息
     * @return
     */
    @ApiOperation(value = "保存留言")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody MessageBoardSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = messageBoardDomain.save(submit, userLoginState.getCode());
            LOGGER.info("保存留言[MessageBoardController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存留言[MessageBoardController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存留言[MessageBoardController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
