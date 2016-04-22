package com.lakala.crosspay.client.entities.otp;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-支付短信验证码重新获取响应业务参数
 * </p>
 * Created by jiang on 16/4/21.
 */
public class LklOtpRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = 5356543978557058165L;

    /**
     * 订单提交日期，格式yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

    /**
     * 短信发送状态
     * <ul>
     * <li>
     * 1.已发送
     * </li>
     * <li>
     * 0.未发送
     * </li>
     * <p/>
     * </ul>
     */
    private String smsStatus;

    private String ext1;

    private String ext2;

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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

    public String getSmsStatus() {
        return smsStatus;
    }

    public void setSmsStatus(String smsStatus) {
        this.smsStatus = smsStatus;
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
        final StringBuffer sb = new StringBuffer("LklOtpRes{");
        sb.append("orderTime='").append(orderTime).append('\'');
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", smsStatus='").append(smsStatus).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
