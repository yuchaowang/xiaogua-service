package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CreditDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分controller
 *
 * @author wangyc
 * @date 2020-11-22 21:46
 */
@RestController
@RequestMapping("/api/credit")
public class CreditController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreditController.class);

    @Autowired
    private CreditDomain creditDomain;

    /**
     * 获取当前用户积分
     * @param request
     * @return
     */
    @GetMapping(value = "/myCredit", produces = "application/json")
    public String myCredit(HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = creditDomain.getMyCredit(userLoginState.getCode());
            LOGGER.info("获取当前用户积分[CreditController.myCredit]正常，参数：{}", JSON.toJSONString(userLoginState));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取当前用户积分[CreditController.myCredit]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取当前用户积分[CreditController.myCredit]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
