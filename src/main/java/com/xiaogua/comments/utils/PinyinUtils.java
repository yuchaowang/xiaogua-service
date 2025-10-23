package com.xiaogua.comments.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

/**
 * 拼音处理工具
 * Created by wangyc on 2017/3/22.
 */
public class PinyinUtils {

    /**
     * 把汉字转成对应的全部拼音
     *
     * @param name
     * @return
     */
    public static String chineseToPinyin(String name) {
        String pinyinName = "";
        char[] nameChar = name.toCharArray();
        char[] error = "，。（）：；“”’‘、？！￥……~".toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    boolean isError = false;
                    for (int j = 0; j < error.length; j++) {
                        if (nameChar[i] == error[j]) {
                            isError = true;
                        }
                    }
                    if (!isError) {
                        pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaultFormat)[0];
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return pinyinName;
    }

    // 判断一个字符串是否含有中文
    public static boolean isChinese(String str) {
        if (str == null) {
            return false;
        }
        if (isChinese(str.toCharArray()[0])) {
            return true;// 有一个中文字符就返回
        }
        return false;
    }

    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;// 根据字节码判断
    }
}