package com.lakala.crosspay.client.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *     日期类工具
 * </p>
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class DateUtil {
    private static String ymdhms = "yyyyMMddHHmmssssssss";
    public static SimpleDateFormat yyyyMMddHHmmssssssss = new SimpleDateFormat(ymdhms);

    /**
     * 获得当前时间
     * 格式：yyyyMMddHHmmssssssss
     *
     * @return String
     */
    public static String getCurrentTime() {

        return yyyyMMddHHmmssssssss.format(new Date());
    }
}
