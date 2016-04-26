package com.lakala.crossborder.client.entities.payment;

import com.lakala.crossborder.client.enums.LklCurrency;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-确认支付接口请求参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class PaymentReq implements Serializable {

    private static final long serialVersionUID = 2432428418568613160L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 订单金额
     */
    private String orderAmount;

    /**
     * 订单币种 {@link LklCurrency}
     */
    private String currency;

    /**
     * 主收款方应收金额
     */
    private String payeeAmount;

    /**
     * 短信验证码
     */
    private String msgCode;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

    /**
     * 扩展字段1
     */
    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPayeeAmount() {
        return payeeAmount;
    }

    public void setPayeeAmount(String payeeAmount) {
        this.payeeAmount = payeeAmount;
    }

    public String getMsgCode() {
        return msgCode;
    }

    public void setMsgCode(String msgCode) {
        this.msgCode = msgCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
        final StringBuffer sb = new StringBuffer("PaymentReq{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", payeeAmount='").append(payeeAmount).append('\'');
        sb.append(", msgCode='").append(msgCode).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
