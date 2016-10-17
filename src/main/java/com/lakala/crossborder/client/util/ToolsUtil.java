package com.lakala.crossborder.client.util;

import java.util.Random;

/**
 * <p>
 * 工具类
 * </p>
 */
public class ToolsUtil {

    private static final ThreadLocal<String> LOCAL_MERKEY = new ThreadLocal<String>();

    /**
     * 随机生成字符串(自定义长度)
     *
     * @param length 自定义长度
     * @return
     */
    private static String getRandomString(int length) {
//        String radStr = "w0eA*1l&Bht3oCz^2p-D=n45%Ev8F6+Gg$9jHxdI7Ev8F6+Gg$9jHxdI\7Jc8KkLrMyaNq@mOsP#QR/!STbUVuWXfYiZ~w0eA*1l&Bht3oCz^2Jc8KkLrMyaNq@mOsP#QR!STbUVuWXfYiZ~w0eA*1l&Bht3oCz^2p-D=n45%ÍÍp-D=n45%Ev8F6+Gg$9jHxdI7Jc8KkLrMyaNq@mOsP#QR!STbUVuWXfYiZ~";
//        StringBuffer generateRandStr = new StringBuffer();
//        Random rand = new Random();
//        for (int i = 0; i < length; i++) {
//            int randNum = rand.nextInt(radStr.length());
//            generateRandStr.append(radStr.substring(randNum, randNum + 1));
//        }
//        return generateRandStr.toString();
        String radStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int randNum = rand.nextInt(36);
            generateRandStr.append(radStr.substring(randNum, randNum + 1));
        }
        return generateRandStr + "";
    }

    /**
     * 获取当前线程的商户对称密钥
     *
     * @return
     */
    public static String getMerKey() {
        if (null == LOCAL_MERKEY.get()) {
            String merKey = getRandomString(32);
            LOCAL_MERKEY.set(merKey);

            return merKey;
        } else {
            return LOCAL_MERKEY.get();
        }
    }

    /**
     * 设置对称密钥
     *
     * @param key
     */
    public static void setMerKey(String key) {
        LOCAL_MERKEY.set(key);
    }

    public static void remove() {
        LOCAL_MERKEY.remove();
    }


}

