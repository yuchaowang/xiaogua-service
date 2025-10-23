package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.UserTypeSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.UserTypeDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyc
 * @date 2020-11-28 23:27
 */
@RestController
@RequestMapping("/api/userType")
public class UserTyepController extends BaseController  {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTyepController.class);

    @Autowired
    private UserTypeDomain userDomain;

    /**
     * 更新用户类型
     * @param request
     * @param submit 用户类型提交信息
     * @return
     */
    @PostMapping(value = "/update", produces = "application/json")
    public String update(HttpServletRequest request, @RequestBody UserTypeSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = userDomain.update(submit, userLoginState.getCode());
            LOGGER.info("更新用户类型[UserTyepController.update]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("更新用户类型[UserTyepController.update]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("更新用户类型[UserTyepController.update]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
