package com.delta.report.kylintask.commons;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * kylin的時間轉換為utc
 */
@Slf4j
public class KylinTimeUtil {
    public static String getTimeStamp(String formatTimeString) {
        if ("0".equals(formatTimeString)) {
            return formatTimeString;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            Date date = sdf.parse(formatTimeString);
            //+8小時才符合kylin的時間,Kylin代碼是uct,顯示是GMT8
            return String.valueOf(date.getTime() + 8 * 60 *60 * 1000);
        } catch (ParseException e) {
            log.error("", e);
        }
        return null;
    }

    public static String getTimeString(String longtime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = new Date(Long.parseLong(longtime) - 8 * 60 *60 * 1000);
        return sdf.format(date);
    }
}
