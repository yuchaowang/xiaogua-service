package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.LoginUser;
import com.xiaogua.comments.bl.MessageBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.config.WXConfig;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.dal.mapper.VisitorMapper;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.Wechat;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.WxUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 登录登出
 * @author wangyc
 * @date 2020-11-21 17:46
 */
@RestController
public class LoginController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserDomain userDomain;

    @Autowired
    WXConfig wxConfig;

    @Autowired
    private UserBl userBl;

    @Autowired
    WxUtil wxUtil;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private VisitorMapper visitorMapper;

    /**
     * 登录
     * @param request request
     * @param response response
     * @param loginUser 登录用户信息
     * @return
     */
    @PostMapping(value = "/api/login")
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestBody LoginUser loginUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = userDomain.login(loginUser, request, response);
            LOGGER.info("登录[LoginController.login]正常，参数：{}", JSON.toJSONString(loginUser));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("登录[LoginController.login]告警，参数：{}", JSON.toJSONString(loginUser), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("登录[LoginController.login]异常，参数：{}", JSON.toJSONString(loginUser), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 微信扫码登录
     *
     * @param request   request
     * @param response  response
     * @param code 微信code
     * @param url 跳转url
     * @return
     */
    @GetMapping(value = "/api/wechat/login")
    public void wechatLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code, @RequestParam("url") String url) {
        try {
            userDomain.wechatLogin(code, url, request, response);
            LOGGER.info("登录[LoginController.wechatLogin]正常，参数：{}", String.format("code: %s, url: %s,", code, url));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("登录[LoginController.wechatLogin]告警，参数：{}", String.format("code: %s, url: %s,", code, url), e);
        } catch (Exception e) {
            LOGGER.error("登录[LoginController.wechatLogin]异常，参数：{}", String.format("code: %s, url: %s,", code, url), e);
        }
    }

    /**
     * 登出
     * @param request request
     * @param response response
     * @return
     */
    @PostMapping(value = "/api/loginout")
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = userDomain.loginout(userLoginState.getCode(), request, response);
            LOGGER.info("登出[LoginController.loginout]正常，参数：{}", JSON.toJSONString(userLoginState));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("登出[LoginController.loginout]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("登出[LoginController.loginout]异常，参数：{}", e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    @GetMapping(value = "/api/getOpenId")
    public String getOpenId(HttpServletRequest request, @RequestParam(name = "code") String code) throws Exception {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            JSONObject jo = wxUtil.getWxInfo(code);
            String openId = jo.getString("openid");
            //更新用户openId
            UserLoginState loginInfo = getLoginInfo(request);
            User user = new User();
            user.setCode(loginInfo.getCode());
            user.setOpenId(openId);
            userBl.updateByCodeSelective(user);
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        } catch (Exception e) {
            LOGGER.error("登录[LoginController.getOpenId]异常，参数：{}", e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }


    /**
     * 小程序一键登录
     *
     * @param request  request
     * @param response response
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/api/mini/program/login")
    public String miniProgramLogin(@RequestBody JSONObject jsonObject,
                        HttpServletRequest request,
                        HttpServletResponse response) {

        String encrypted = jsonObject.getString("encrypted");
        String iv = jsonObject.getString("iv");
        String code = jsonObject.getString("code");
        if (StringUtils.isEmpty(encrypted) || StringUtils.isEmpty(iv) || StringUtils.isEmpty(code)) {
            throw new RuntimeException("参数错误");
        }
        ResponseBuilder builder = new ResponseBuilder();
        try {
            JSONObject wxInfo = wxUtil.getWxInfo(code);
            String openid = wxInfo.getString("openid");
            String session_key = wxInfo.getString("session_key");
            if (StringUtils.isEmpty(session_key)) {
                throw new RuntimeException("获取用户信息失败");
            }
            JSONObject userInfo = Wechat.decryptData(encrypted, iv, session_key);
            if (StringUtils.isEmpty(userInfo) || StringUtils.isEmpty(userInfo.getString("phoneNumber"))) {
                throw new RuntimeException("获取用户信息失败");
            }
            String phone = userInfo.getString("phoneNumber");
            builder = userDomain.miniProgramLogin(phone, openid, request, response);
            LOGGER.info("小程序一键登录[LoginController.miniProgramLogin]正常，参数：{}", JSON.toJSONString(request.getParameterMap()));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("小程序一键登录[LoginController.miniProgramLogin]告警，参数：{}", JSON.toJSONString(request.getParameterMap()), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("小程序一键登录[LoginController.miniProgramLogin]异常，参数：{}", JSON.toJSONString(request.getParameterMap()), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
