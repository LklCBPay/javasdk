package com.lakala.crosspay.client.util;

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
        String radStr = "0eA1lB3oC2pD4e5E8rF6Gg9HI7Jc8KkLrMaNmOsPQRSTbUVuWXYiZ";
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int randNum = rand.nextInt(radStr.length());
            generateRandStr.append(radStr.substring(randNum, randNum + 1));
        }
        return generateRandStr.toString();
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

    public static void remove() {
        LOCAL_MERKEY.remove();
    }
}

