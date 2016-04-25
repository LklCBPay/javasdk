package com.lakala.crosspay.client.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 日期类 具
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class DateUtil {
    private static String ymdhms = "yyyyMMddHHmmssssssss";
    private static SimpleDateFormat yyyyMMddHHmmssssssss = new SimpleDateFormat(ymdhms);

    /**
     * 获得当前时间
     * 格式：yyyyMMddHHmmssssssss
     *
     * @return String
     */
    public static String getCurrentTime() {

        return yyyyMMddHHmmssssssss.format(new Date());
    }

    /**
     * 返回一个格式化的日期字符串
     *
     * @param pattern 日期格式，若为空则默认为yyyyMMdd
     * @return
     */
    public String getCurrentDate(String pattern) {
        SimpleDateFormat datePattern = null;
        if (null == pattern || "".equals(pattern)) {
            datePattern = new SimpleDateFormat("yyyyMMdd");
        } else {
            datePattern = new SimpleDateFormat(pattern);
        }
        return datePattern.format(new Date());
    }
}
