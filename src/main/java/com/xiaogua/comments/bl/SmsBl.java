package com.xiaogua.comments.bl;

import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.sms.v20190711.SmsClient;
import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;
import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;
import com.xiaogua.comments.config.TencentSmsConfig;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 短信 业务领域
 *
 * @author wangyc
 * @date 2020-12-20 15:07:37
 */
@Service
public class SmsBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmsBl.class);

    @Autowired
    SmsClient smsClient;

    @Autowired
    TencentSmsConfig smsConfig;

    public void sendSms(String templateID, String[] phoneNumbers, String[] templateParams) {
        SendSmsRequest req = new SendSmsRequest();
        req.setSmsSdkAppid(smsConfig.getAppid());
        req.setSign(smsConfig.getSign());
        req.setTemplateID(templateID);
        req.setPhoneNumberSet(phoneNumbers);
        req.setTemplateParamSet(templateParams);
        try {
            SendSmsResponse res = smsClient.SendSms(req);
            LOGGER.info(SendSmsResponse.toJsonString(res));
            if (res.getSendStatusSet().length > 0) {
                if (res.getSendStatusSet()[0].getCode().equals("LimitExceeded.PhoneNumberDailyLimit")) {
                    throw new CommentsRuntimeException(-1, "本手机号发送短信达到当日上限，请明日重试");
                }
                if (!res.getSendStatusSet()[0].getCode().equals("Ok")) {
                    throw new CommentsRuntimeException(-1, "短信发送失败，请稍后重试");
                }
            } else {
                throw new CommentsRuntimeException(-1, "短信发送失败，请稍后重试");
            }
        } catch (TencentCloudSDKException e) {
            e.printStackTrace();
            throw new CommentsRuntimeException(-1, "短信发送失败，请稍后重试");
        }
    }

}
