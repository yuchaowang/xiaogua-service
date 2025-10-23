package com.xiaogua.comments.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

public class Wechat {

    private static final Logger LOGGER = LoggerFactory.getLogger(Wechat.class);

    /**
     * 根据encryptedData，iv，sessionKey解密获取用户信息
     * @param encryptedData
     * @param iv
     * @param sessionKey
     * @return
     *
     * 返回值 openid
     *       nickName
     *       gender
     *       avatarUrl
     */
    public static JSONObject decryptData(String encryptedData, String iv, String sessionKey) {
        byte[] aesKey = Base64.decode(sessionKey);
        byte[] aesIV = Base64.decode(iv);
        byte[] aesEncryptedData = Base64.decode(encryptedData);

        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            AlgorithmParameterSpec ivParameterSpec = new IvParameterSpec(aesIV);
            cipher.init(cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] original = cipher.doFinal(aesEncryptedData);
            if (null != original && original.length > 0) {
                String result = new String(original, "UTF-8");
                JSONObject userInfo = JSON.parseObject(result);
                LOGGER.info("解密的用户信息为" + JSON.toJSONString(userInfo));
                return userInfo;
            }

            LOGGER.info("解密的用户信息失败");
            return null;
        } catch (Exception ex){
            ex.printStackTrace();
            LOGGER.error("解密的用户信息失败");
            return null;
        }
    }
}


