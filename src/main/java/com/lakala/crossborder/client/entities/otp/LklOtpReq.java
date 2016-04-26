package com.lakala.crossborder.client.entities.otp;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-支付短信验证码重新获取请求业务参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklOtpReq implements Serializable {

    private static final long serialVersionUID = 5493020637048469522L;

    /**
     * 订单日期,格式yyyyMMddHHmmss
     */
    private String oderTime;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

    private String ip;

    private String ext1;

    private String ext2;

    public String getOderTime() {
        return oderTime;
    }

    public void setOderTime(String oderTime) {
        this.oderTime = oderTime;
    }

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklOtpReq{");
        sb.append("oderTime='").append(oderTime).append('\'');
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", ip='").append(ip).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
