package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.RegisterUser;
import com.xiaogua.comments.common.constant.SmsCodeConstant;
import com.xiaogua.comments.domain.CheckCodeDomain;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.DES;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;

/**
 * 注册
 *
 * @author wangyc
 * @date 2020-11-21 17:46
 */
@RestController
@Api(tags = "注册Web Controller")
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    UserDomain userDomain;

    @Autowired
    CheckCodeDomain checkCodeDomain;

    /**
     * 个人账号密码注册
     *
     * @param request      request
     * @param registerUser 注册用户信息
     * @return
     */
    @PostMapping(value = "/api/register/common")
    @ApiOperation(value = "账号密码注册")
    public String userRegister(HttpServletRequest request, @RequestBody RegisterUser registerUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = userDomain.userRegister(registerUser);
            LOGGER.info("个人账号密码注册[RegisterController.userRegister]正常，参数：{}", JSON.toJSONString(registerUser));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("个人账号密码注册[RegisterController.userRegister]告警，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("个人账号密码注册[RegisterController.userRegister]异常，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 受邀新用户注册
     *
     * @param request      request
     * @param registerUser 注册用户信息
     * @return
     */
    @PostMapping(value = "/api/register/invited")
    @ApiOperation(value = "受邀新用户注册")
    public String invitedRegister(HttpServletRequest request, @RequestBody RegisterUser registerUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = userDomain.invitedRegister(registerUser);
            LOGGER.info("受邀新用户注册[RegisterController.invitedRegister]正常，参数：{}", JSON.toJSONString(registerUser));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("受邀新用户注册[RegisterController.invitedRegister]告警，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("受邀新用户注册[RegisterController.invitedRegister]异常，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 用户注册短信验证码
     *
     * @param request      request
     * @param registerUser 注册用户信息
     * @return
     */
    @PostMapping(value = "/api/register/checkCode/sms/send")
    @ApiOperation(value = "用户注册短信验证码")
    public String sendRegisterSmsCheckCode(HttpServletRequest request, @RequestBody RegisterUser registerUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            checkCodeDomain.sendRegisterSmsCheckCode(registerUser.getMobile(), SmsCodeConstant.REGISTER_CHECKCODE);
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
            LOGGER.info("用户注册短信验证码[RegisterController.sendRegisterSmsCheckCode]正常，参数：{}",
                        JSON.toJSONString(registerUser));
        } catch (CommentsRuntimeException e) {
            LOGGER
                .warn("用户注册短信验证码[RegisterController.sendRegisterSmsCheckCode]告警，参数：{}", JSON.toJSONString(registerUser),
                      e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("用户注册短信验证码[RegisterController.sendRegisterSmsCheckCode]异常，参数：{}",
                         JSON.toJSONString(registerUser), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 忘记密码短信验证码
     *
     * @param request      request
     * @param registerUser 用户信息
     * @return
     */
    @PostMapping(value = "/api/forgotpassword/checkCode/sms/send")
    @ApiOperation(value = "忘记密码短信验证码")
    public String sendForgotPasswordSmsCheckCode(HttpServletRequest request, @RequestBody RegisterUser registerUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            checkCodeDomain
                .sendForgotPasswordSmsCheckCode(registerUser.getMobile(), SmsCodeConstant.FORGOT_PASSWORD_CHECKCODE);
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
            LOGGER.info("忘记密码短信验证码[RegisterController.sendForgotPasswordSmsCheckCode]正常，参数：{}",
                        JSON.toJSONString(registerUser));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("忘记密码短信验证码[RegisterController.sendForgotPasswordSmsCheckCode]告警，参数：{}",
                        JSON.toJSONString(registerUser), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("忘记密码短信验证码[RegisterController.sendForgotPasswordSmsCheckCode]异常，参数：{}",
                         JSON.toJSONString(registerUser), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 绑定手机号发送验证码
     *
     * @param request      request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/api/bindPhone/checkCode/sms/send")
    @ApiOperation(value = "绑定手机号发送验证码")
    public String sendBindPhoneSmsCheckCode(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            String mobile = jsonObject.getString("mobile");
            VerifyParamsUtil.hasText(mobile, "手机号不可为空");
            checkCodeDomain.sendBindPhoneSmsCheckCode(jsonObject.getString("mobile"), SmsCodeConstant.BIND_PHONE_CHECKCODE);
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
            LOGGER.info("绑定手机号发送验证码[RegisterController.sendBindPhoneSmsCheckCode]正常，参数：{}",
                    JSON.toJSONString(jsonObject));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("绑定手机号发送验证码[RegisterController.sendBindPhoneSmsCheckCode]告警，参数：{}",
                    JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("绑定手机号发送验证码[RegisterController.sendBindPhoneSmsCheckCode]异常，参数：{}",
                    JSON.toJSONString(jsonObject), e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 绑定手机号
     *
     * @param request      request
     * @param registerUser 用户信息
     * @return
     */
    @PostMapping(value = "/api/register/password/reset")
    @ApiOperation(value = "重置密码")
    public String resetPassword(HttpServletRequest request, @RequestBody RegisterUser registerUser) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = userDomain.resetPassword(registerUser);
            LOGGER.info("重置密码[RegisterController.resetPassword]正常，参数：{}", JSON.toJSONString(registerUser));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("重置密码[RegisterController.resetPassword]告警，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("重置密码[RegisterController.resetPassword]异常，参数：{}", JSON.toJSONString(registerUser), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 微信扫码绑定手机号码
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping(value = "/api/bindPhone")
    @ApiOperation(value = "微信扫码绑定手机号码")
    public String bindPhone(@RequestBody JSONObject jsonObject, HttpServletRequest request, HttpServletResponse response) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            String openid = jsonObject.getString("openid");
            String mobile = jsonObject.getString("mobile");
            String smsCheckCode = jsonObject.getString("smsCheckCode");
            VerifyParamsUtil.hasText(mobile, "手机号不可为空");
            VerifyParamsUtil.hasText(mobile, "openid不可为空");
            VerifyParamsUtil.hasText(mobile, "验证码不能为空");
            openid = URLDecoder.decode(openid, "utf-8");
            openid = DES.decode(openid);
            builder = userDomain.bindPhone(openid, mobile, smsCheckCode, request, response);
            LOGGER.info("微信扫码绑定手机号码[RegisterController.resetPassword]正常，参数：{}", JSON.toJSONString(jsonObject));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("微信扫码绑定手机号码[RegisterController.resetPassword]告警，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("微信扫码绑定手机号码[RegisterController.resetPassword]异常，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
