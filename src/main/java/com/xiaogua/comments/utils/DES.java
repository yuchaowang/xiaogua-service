package com.xiaogua.comments.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author wangyc
 * @date 2020-12-16 1:54
 */
public class DES {

    private static final String SECRETKEY = "yj1HtYounxh";

    /**
     * 加密
     *
     * @param plainText
     * @return
     * @throws Exception
     */
    public static String encode(String plainText) throws Exception {
        SecureRandom random = new SecureRandom();
        DESKeySpec keySpec = new DESKeySpec(SECRETKEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("des");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("des");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
        byte[] cipherData = cipher.doFinal(plainText.getBytes());

        String cipherText = new BASE64Encoder().encode(cipherData);
        return cipherText;
    }

    /**
     * 解密
     *
     * @param cipherText
     * @return
     * @throws Exception
     */
    public static String decode(String cipherText) throws Exception {
        byte[] newcipherData = new BASE64Decoder().decodeBuffer(cipherText);
        SecureRandom newrandom = new SecureRandom();
        DESKeySpec keySpec = new DESKeySpec(SECRETKEY.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("des");
        SecretKey secretKey = keyFactory.generateSecret(keySpec);

        Cipher cipher = Cipher.getInstance("des");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, newrandom);
        byte[] plainData = cipher.doFinal(newcipherData);
        return new String(plainData);
    }
}
