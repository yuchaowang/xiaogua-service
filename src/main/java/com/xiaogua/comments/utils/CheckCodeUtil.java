package com.xiaogua.comments.utils;

import java.util.Random;

/**
 * 验证码工具类
 * @author wangyc
 * @date 2020-12-20 6:22
 */
public  class CheckCodeUtil {

    /**
     * 验证码生成
     *
     * @param len
     * @return
     */
    public static Integer getRandomNumber(int len) {
        int max = (int) Math.pow(10, len) - 1;
        int min = (int) Math.pow(10, len - 1);
        return new Random().nextInt(max) % (max - min + 1) + min;
    }
}
