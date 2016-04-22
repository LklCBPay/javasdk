package com.lakala.crosspay.client.util;

/**
 * Created by jiang on 16/4/19.
 */
public class ByteArrayUtil {

    /**
     * 填充byte数组
     *
     * @param buf
     * @param b
     */
    public static void fill(byte[] buf, byte b) {
        for (int i = 0; i < buf.length; i++) {
            buf[i] = b;
        }
    }

    /**
     * 两个byte数组进行异或运算
     *
     * @param data1
     * @param data2
     * @return
     */
    public static byte[] or(byte data1[], byte data2[]) {
        int len = (data1.length <= data2.length) ? data1.length : data2.length;
        byte[] res = new byte[len];
        for (int i = 0; i < len; i++) {
            res[i] = (byte) (data1[i] ^ data2[i]);
        }
        return res;
    }

    public static String hexChars = "0123456789ABCDEF";

    /**
     * byte数组转换成Hex字符串
     *
     * @param data
     * @return
     */
    public static String byteArray2HexString(byte[] data) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            byte lo = (byte) (0x0f & data[i]);
            byte hi = (byte) ((0xf0 & data[i]) >>> 4);
            sb.append(hexChars.charAt(hi)).append(hexChars.charAt(lo));
        }
        return sb.toString();
    }

    /**
     * Hex字符串转换成byte数组
     *
     * @param hexStr
     * @return
     */
    public static byte[] hexString2ByteArray(String hexStr) {
        if (hexStr.length() % 2 != 0) {
            return null;
        }
        byte[] data = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            char hc = hexStr.charAt(2 * i);
            char lc = hexStr.charAt(2 * i + 1);
            byte hb = hexChar2Byte(hc);
            byte lb = hexChar2Byte(lc);
            if (hb < 0 || lb < 0) {
                return null;
            }
            int n = hb << 4;
            data[i] = (byte) (n + lb);
        }
        return data;
    }

    /**
     * 整形转换成Hex字符串
     *
     * @param n
     * @return
     */
    public static String int2HexString(int n) {
        return Integer.toHexString(n).toUpperCase();
    }

    /**
     * Hex字符串转换成整形
     *
     * @param s
     * @return
     */
    public static int hexString2Int(String s) {
        return Integer.parseInt(s, 16);
    }

    /**
     * 格式化Hex字符串的宽度，不足左补'0'
     *
     * @param width
     * @param hexStr
     * @return
     */
    public static String formatHexStr(int width, String hexStr) {
        if (hexStr.length() >= width) {
            return hexStr.substring(hexStr.length() - width);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < width - hexStr.length(); i++) {
            sb.append("0");
        }
        sb.append(hexStr);
        return sb.toString();
    }

    /**
     * 将byte数组转换成打印格式字符串，方便输出调试信息
     *
     * @param data
     * @param dataLen
     * @return
     */
    public static String toPrintString(byte[] data, int dataLen) {
        if (data == null) return "";
        if (dataLen < 0) return "";
        int printLen = 0;
        if (dataLen > data.length) printLen = data.length;
        else printLen = dataLen;
        StringBuffer sb = new StringBuffer();
        String lenStr = int2HexString(data.length);
        int width = lenStr.length();
        String printStr = "";
        int loopLen = 0;
        loopLen = (printLen / 16 + 1) * 16;
        for (int i = 0; i < loopLen; i++) {
            if (i % 16 == 0) {
                sb.append("0x").append(formatHexStr(width, int2HexString(i))).append(": ");
                printStr = "";
            }
            if (i % 16 == 8) {
                sb.append(" ");
            }
            if (i < printLen) {
                sb.append(" ").append(formatHexStr(2, int2HexString(data[i])));
                if (data[i] > 31 && data[i] < 127)
                    printStr += (char) data[i];
                else
                    printStr += '.';
            } else {
                sb.append("   ");
            }
            if (i % 16 == 15) {
                sb.append(" ").append(printStr).append("\r\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String merId = "0x34,0x31,0x30,0x32,0x39,0x30,0x30,0x34,0x38,0x39,0x39,0x30,0x30,0x30,0x31,";
        String[] s = merId.split(",");
        for (int i = 0; i < s.length; i++) {
            System.out.print((char) hexString2Int(s[i].substring(2)));
        }
    }

    public static String fomatTackDate(int num, String tackDate) {
        if (null == tackDate) return null;
        if (tackDate.length() % num == 0) return tackDate;
        int len = num - tackDate.length() % num;
        StringBuffer sb = new StringBuffer();
        sb.append(tackDate);
        for (int i = 0; i < len; i++) {
            sb.append("F");
        }
        return sb.toString();
    }

    /**
     * 16进制转换成字符串
     *
     * @param s
     * @return
     */
    public static String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(
                        s.substring(i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static byte[] hexStr2Bytes(String src) {
        int m = 0, n = 0;
        if ((src.length() % 2) != 0)
            src = "0" + src;
        int l = src.length() / 2;
        //System.out.println(l);
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = Integer.decode(
                    "0x" + src.substring(i * 2, m) + src.substring(m, n))
                    .byteValue();
        }
        return ret;
    }

    public static byte hexChar2Byte(char c) {
        if (c >= '0' && c <= '9')
            return (byte) (c - '0');
        if (c >= 'a' && c <= 'f')
            return (byte) (c - 'a' + 10);
        if (c >= 'A' && c <= 'F')
            return (byte) (c - 'A' + 10);
        return -1;
    }
}
