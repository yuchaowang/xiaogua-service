package com.xiaogua.comments.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author wangyc
 * @date 2020-12-16 2:32
 */
public class DateUtil {
    public static Date getEndOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());;
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    // 获得某天最小时间
    public static Date getStartOfDay(Date date) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    //得到今天剩余秒数
    public static int getLastSeconds(){
        Calendar calendar=Calendar.getInstance();
        // 得到今天 晚上的最后一刻 最后时间
        String last=getTime()+" 23:59:59";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 转换为今天
            Date latDate=sdf.parse(last);
            // 得到的毫秒 除以1000转换 为秒
            return (int)(latDate.getTime()-System.currentTimeMillis())/1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
