package com.lakala.crossborder.client.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 摘要算法
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class DigestUtil {


    public DigestUtil() {
    }

    /**
     * 摘要算法
     *
     * @param strSrc  原文
     * @param encName 算法(MD5,SHA-1,SHA-256)dafault to "MD5" default charset is GBK
     * @return
     */
    public static String Encrypt(String strSrc, String encName) {
        return Encrypt(strSrc, encName, "GBK");
    }

    /**
     * 摘要算法
     *
     * @param strSrc  原文
     * @param encName 算法(MD5,SHA-1,SHA-256)dafault to "MD5"
     * @param charset 原文字符集
     * @return
     */
    public static String Encrypt(String strSrc, String encName, String charset) {
        MessageDigest md = null;
        String strDes = null;

        try {
            byte[] bt = strSrc.getBytes(charset);
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Invalid algorithm.");
            return null;
        } catch (UnsupportedEncodingException e) {
            System.out.println("UnsupportedEncoding.");
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    /**
     * 使用盐码对文件做md5摘要
     *
     * @param salt     盐码
     * @param fileName 文件名（全路径）
     * @return md5 hex
     */
    public static String fileDigestWithSalt(String salt, String fileName) {
        try {
            byte[] bytes = ArrayUtils.addAll(salt.getBytes("UTF-8"), FileUtils.readFileToByteArray(new File(fileName)));
            return DigestUtils.md5Hex(bytes);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
