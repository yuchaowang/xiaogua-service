package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.UserSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 用戶controller
 *
 * @author wangyc
 * @date 2020-11-22 21:46
 */
@Api(tags = "用户Web Controller")
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserDomain userDomain;

    /**
     * 更新用户
     *
     * @param request
     * @param userSubmit 用户提交信息
     * @return
     */
    @ApiOperation(value = "更新用户")
    @PostMapping(value = "/update", produces = "application/json")
    public String update(HttpServletRequest request, @RequestBody UserSubmit userSubmit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = userDomain.update(userSubmit, userLoginState.getCode());
            LOGGER.info("更新用户[UserController.update]正常，参数：{}", JSON.toJSONString(userSubmit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("更新用户[UserController.update]告警，参数：{}", JSON.toJSONString(userSubmit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("更新用户[UserController.update]异常，参数：{}", JSON.toJSONString(userSubmit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取当前用户信息
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping(value = "/get/my", produces = "application/json")
    public String update(HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = userDomain.getMyUser(userLoginState.getCode());
            LOGGER.info("获取当前用户信息[UserController.getMyUser]正常，参数：{}", JSON.toJSONString(userLoginState));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取当前用户信息[UserController.getMyUser]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取当前用户信息[UserController.getMyUser]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取邀请新用户链接
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取邀请新用户链接")
    @GetMapping(value = "/get/inviteurl", produces = "application/json")
    public String getInviteUrl(HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = userDomain.getInviteUrl(userLoginState.getCode());
            LOGGER.info("获取邀请新用户链接[UserController.getInviteUrl]正常，参数：{}", JSON.toJSONString(userLoginState));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取邀请新用户链接[UserController.getInviteUrl]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取邀请新用户链接[UserController.getInviteUrl]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
