package com.xiaogua.comments.domain;

import com.xiaogua.comments.bl.CheckCodeBl;
import com.xiaogua.comments.common.constant.RegexConstant;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.UserMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * 验证码 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class CheckCodeDomain {

    @Autowired private CheckCodeBl checkCodeBl;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号
     * @param opType 验证码类型
     */
    public void sendRegisterSmsCheckCode(String mobile, int opType) {
        VerifyParamsUtil.hasText(mobile, "手机号不可为空");
        VerifyParamsUtil.isTrue(Pattern.matches(RegexConstant.REGEX_MOBILE, mobile), "手机号格式不正确");
        User user = userMapper.selectByMobile(mobile);
        VerifyParamsUtil.isTrue(user == null, "手机号已注册");
        checkCodeBl.sendSmsCheckCode(mobile, null, opType);
    }

    /**
     * 发送忘记密码验证码
     *
     * @param mobile 手机号
     * @param opType 验证码类型
     */
    public void sendForgotPasswordSmsCheckCode(String mobile, int opType) {
        VerifyParamsUtil.hasText(mobile, "手机号不可为空");
        VerifyParamsUtil.isTrue(Pattern.matches(RegexConstant.REGEX_MOBILE, mobile), "手机号格式不正确");
        User user = userMapper.selectByMobile(mobile);
        VerifyParamsUtil.isTrue(user != null, "手机号未注册");
        checkCodeBl.sendSmsCheckCode(mobile, user.getCode(), opType);
    }

    /**
     * 绑定手机号发送验证码
     *
     * @param mobile
     * @param opType
     */
    public void sendBindPhoneSmsCheckCode(String mobile, int opType) {
        VerifyParamsUtil.hasText(mobile, "手机号不可为空");
        VerifyParamsUtil.isTrue(Pattern.matches(RegexConstant.REGEX_MOBILE, mobile), "手机号格式不正确");
        User user = userMapper.selectByMobile(mobile);
        if (user != null && StringUtils.isNotEmpty(user.getWxOpenId())) {
            throw new CommentsRuntimeException(-1, "对不起，该手机号已经被绑定");
        }
        checkCodeBl.sendSmsCheckCode(mobile, null, opType);
    }
}


