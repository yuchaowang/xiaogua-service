package com.xiaogua.comments.bl;

import com.xiaogua.comments.common.constant.CacheKeyConstant;
import com.xiaogua.comments.common.constant.SmsCodeConstant;
import com.xiaogua.comments.dal.entity.CheckCodeState;
import com.xiaogua.comments.dal.mapper.CheckCodeStateMapper;
import com.xiaogua.comments.utils.CheckCodeUtil;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 验证码 业务领域
 *
 * @author wangyc
 * @date 2018-10-10 19:21
 */
@Service
public class CheckCodeBl {

    @Autowired
    SmsBl smsBl;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    CheckCodeStateMapper checkCodeStateMapper;

    /**
     * 发送手机验证码
     *
     * @param mobile   手机号
     * @param userCode 用户编号
     * @param opType   验证码类型
     * @return
     */
    public void sendSmsCheckCode(String mobile, String userCode, int opType) {
        // 参数校验
        VerifyParamsUtil.hasText(mobile, "手机号不能为空");
        VerifyParamsUtil.isTrue(opType != 0, "操作类型不能为空");

        // 发送手机短信
        String[] phoneList = {"+86" + mobile};

        String checkCode = CheckCodeUtil.getRandomNumber(6).toString();
        String[] params = {checkCode, "10"};
        smsBl.sendSms(String.valueOf(opType), phoneList, params);

        String cacheKey = "";
        if (opType == SmsCodeConstant.REGISTER_CHECKCODE) {
            cacheKey = CacheKeyConstant.VERIFY_REGISTER_CHECKCODE;
            userCode = mobile;
        } else if (opType == SmsCodeConstant.FORGOT_PASSWORD_CHECKCODE) {
            cacheKey = CacheKeyConstant.VERIFY_FORGOT_PASSWORD_CHECKCODE;
        } else if (opType == SmsCodeConstant.BIND_PHONE_CHECKCODE) {
            cacheKey = CacheKeyConstant.VERIFY_BIND_PHONE_CHECKCODE;
            userCode = mobile;
        }
        saveCheckCodeToken(userCode, cacheKey, checkCode);
    }

    /**
     * 保存验证码状态信息
     *
     * @param account
     * @param cacheKey
     * @param checkCode
     */
    private void saveCheckCodeToken(String account, String cacheKey, String checkCode) {

        CheckCodeState checkCodeState = checkCodeStateMapper.selectMaxByCodeAndCacheKey(account, cacheKey);

        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        // 10分钟
        long expir = now + (600000);

        if (checkCodeState == null || checkCodeState.getExpir().before(nowDate)) {
            if (checkCodeState != null) {
                checkCodeStateMapper.deleteByPrimaryKey(checkCodeState.getId());
            }
            CheckCodeState newCheckCodeState = new CheckCodeState();
            newCheckCodeState.setCachekey(cacheKey);
            newCheckCodeState.setCheckcode(checkCode);
            newCheckCodeState.setCode(account);
            newCheckCodeState.setExpir(new Date(expir));

            checkCodeStateMapper.insertSelective(newCheckCodeState);
        } else {
            checkCodeState.setCheckcode(checkCode);
            checkCodeState.setExpir(new Date(expir));

            checkCodeStateMapper.updateByPrimaryKeySelective(checkCodeState);
        }

    }

    /**
     * 验证短信验证码
     *
     * @param mobile
     * @param checkCode
     */
    public void checkCheckCode(String mobile, String checkCode, String cacheKeyConstant) {
        CheckCodeState checkCodeState = checkCodeStateMapper.selectMaxByCodeAndCacheKey(mobile, cacheKeyConstant);

        if (checkCodeState == null) {
            throw new CommentsRuntimeException(-1, "验证码不存在，请重新获取验证码");
        }

        long now = System.currentTimeMillis();
        Date nowDate = new Date(now);
        if (checkCodeState.getExpir().before(nowDate)) {
            throw new CommentsRuntimeException(-1, "验证码已过期");
        }

        if (!checkCodeState.getCheckcode().equals(checkCode)) {
            throw new CommentsRuntimeException(-1, "验证码错误");
        }

    }

    /**
     * 验证码失效
     *
     * @param code
     * @param cacheKey
     */
    public void unavailableCheckCode(String code, String cacheKey) {
        CheckCodeState checkCodeState = checkCodeStateMapper.selectMaxByCodeAndCacheKey(code, cacheKey);

        if (checkCodeState == null) {
            throw new CommentsRuntimeException(-1, "验证码不存在，请重新获取验证码");
        }

        checkCodeStateMapper.deleteByPrimaryKey(checkCodeState.getId());
    }
}
