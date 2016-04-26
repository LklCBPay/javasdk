package com.lakala.crossborder.client.entities.notify;

import com.lakala.crossborder.client.util.webhook.SuperWebHookRequest;

/**
 * <p>
 * 拉卡拉跨境支付-拉卡拉发起的支付结果回调通知
 * </p>
 */
public class PayResultNotify extends SuperWebHookRequest {

    private static final long serialVersionUID = -4041692299186477990L;

    private String merOrder;

    private String orderTime;

    private String currency;

    private String orderAmount;

    private String payAmount;

    private String exchangeRate;

    private String dealTime;

    private String orderFee;

    private String transactionId;

    private String payResult;

    private String ext1;

    private String ext2;

    public String getMerOrder() {
        return merOrder;
    }

    public void setMerOrder(String merOrder) {
        this.merOrder = merOrder;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(String orderFee) {
        this.orderFee = orderFee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
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
        final StringBuffer sb = new StringBuffer("PayResultNotify{");
        sb.append("merOrder='").append(merOrder).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", payAmount='").append(payAmount).append('\'');
        sb.append(", exchangeRate='").append(exchangeRate).append('\'');
        sb.append(", dealTime='").append(dealTime).append('\'');
        sb.append(", orderFee='").append(orderFee).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", payResult='").append(payResult).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
