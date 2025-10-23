package com.xiaogua.comments.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;

/**
 * MD5 工具类
 *
 * @author liusw
 * @date 2019-01-03 16:48
 */
public class MD5 {
    private static final Logger logger = Logger.getLogger(MD5.class.getName());
    public static final String ALGORITHM = "MD5";
    private static final String SSO_ENCODING = "UTF-8";
    /**
     * 密码加密方式
     */
    public static final String ENCRY_FORMAT = "$password{$salt}";

    /**
     * 密码
     */
    public static final String ENCRY_PARAM_PASSWORD = "$password";

    /**
     * 盐值
     */
    public static final String ENCRY_PARAM_SALT = "$salt";

    public MD5() {
    }

    public static String toMD5(String plainText) {
        StringBuffer rlt = new StringBuffer();

        try {
            rlt.append(md5String(plainText.getBytes(SSO_ENCODING)));
        } catch (UnsupportedEncodingException var3) {
            logger.severe(" CipherHelper toMD5 exception.");
            var3.printStackTrace();
        }

        return rlt.toString();
    }

    public static String getSignature(HashMap<String, String> params, String secret) {
        Map<String, String> sortedParams = new TreeMap(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        Iterator var5 = entrys.iterator();

        while(var5.hasNext()) {
            Map.Entry<String, String> param = (Map.Entry)var5.next();
            basestring.append((String)param.getKey()).append("=").append((String)param.getValue());
        }

        return getSignature(basestring.toString(), secret);
    }

    public static String getSignature(String sigstr, String secret) {
        StringBuilder basestring = new StringBuilder(sigstr);
        basestring.append("#");
        basestring.append(toMD5(secret));
        return toMD5(basestring.toString());
    }

    public static byte[] md5Raw(byte[] data) {
        Object var1 = null;

        byte[] md5buf;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5buf = md5.digest(data);
        } catch (Exception var3) {
            md5buf = null;
            logger.severe("md5Raw error.");
            var3.printStackTrace();
        }

        return md5buf;
    }

    public static String md5String(byte[] data) {
        String md5Str = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buf = md5.digest(data);

            for(int i = 0; i < buf.length; ++i) {
                md5Str = md5Str + byte2Hex(buf[i]);
            }
        } catch (Exception var5) {
            md5Str = null;
            logger.severe("md5String error.");
            var5.printStackTrace();
        }

        return md5Str;
    }

    public static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }

        while(hex.length() < 2) {
            hex = "0" + hex;
        }

        return hex;
    }

    /**
     * 获取加密后密码
     * @param noEncryPwd 密码
     * @param salt 颜值（UU号）
     * @return
     */
    public static String getEncryPassword(String noEncryPwd, String salt) {
        // 超过32认为是已加密过的密文
        if (noEncryPwd.length() >= 32) {
            /// 之后添加日志时恢复
            throw new CommentsRuntimeException("invalid password");
        }
        // $password{$salt}
        String password = ENCRY_FORMAT.replace(ENCRY_PARAM_PASSWORD, noEncryPwd);
        password = password.replace(ENCRY_PARAM_SALT, salt == null ? "" : salt);
        return MD5.toMD5(password);
    }
}