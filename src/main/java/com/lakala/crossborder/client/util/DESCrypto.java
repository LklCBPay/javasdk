package com.lakala.crossborder.client.util;

import com.lakala.crossborder.client.exception.LklEncryptException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * <p>
 * des对称加密
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class DESCrypto {
    /**
     * 加密
     *
     * @param datasource 待加密数据
     * @param key
     * @return byte数组
     * @throws LklEncryptException
     */
    public static byte[] enCrypto(byte[] datasource, String key) throws LklEncryptException {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            throw new LklEncryptException(e);
        }
    }

    /**
     * 解密
     *
     * @param src
     * @param key
     * @return byte数组
     * @throws LklEncryptException
     */
    public static byte[] deCrypt(byte[] src, String key) throws LklEncryptException {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        try {
            // 创建一个DESKeySpec对象
            DESKeySpec desKey = new DESKeySpec(key.getBytes());
            // 创建一个密匙工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            // 将DESKeySpec对象转换成SecretKey对象
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, securekey, random);
            // 真正开始解密操作
            return cipher.doFinal(src);
        } catch (Exception e) {
            throw new LklEncryptException(e);
        }
    }
}
