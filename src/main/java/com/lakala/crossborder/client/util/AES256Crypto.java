package com.lakala.crossborder.client.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * Created by jiang on 2017/2/8.
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class AES256Crypto {

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * 加密
     *
     * @param content  需要加密的内容
     * @param password 加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes("utf-8"));
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");// 创建密码器
            byte[] byteContent = content.getBytes("GBK");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes("utf-8"));

            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS7Padding", "BC");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchProviderException {
        String secKey = "BDLLQ2J821P40UNVSSNFSEDUVROW0GDA";
//        String secKey = "123F";
        System.out.println(secKey);


        String bizJson = "test123";

        String encData = ByteArrayUtil.byteArray2HexString(AES256Crypto.encrypt(bizJson, secKey));

        String str = new String(AES256Crypto.decrypt(ByteArrayUtil.hexString2ByteArray(encData), "BDLLQ2J821P40UNVSSNFSEDUVROW0GDA"), "GBK");

        System.out.println(encData);

        System.out.println(str);

    }

}
