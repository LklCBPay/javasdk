package com.lakala.crossborder.client.entities.payment;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-确认支付接口响应参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class PaymentRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = 3840923652856524634L;

    /**
     * 订单日期,格式yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 拉卡拉支付平台时间，格式yyyyMMddHHmmss
     */
    private String dealTime;
    /**
     * 手续费
     */
    private String orderFee;

    /**
     * 人民币实收金额,单位：元，小数点后两位
     */
    private String cnyAmount;

    /**
     * 购汇汇率
     */
    private String exchangeRate;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

    /**
     * <ul>
     * <li>
     * 1-支付成功
     * </li>
     * </ul>
     */
    private String payResult;

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

    public String getCnyAmount() {
        return cnyAmount;
    }

    public void setCnyAmount(String cnyAmount) {
        this.cnyAmount = cnyAmount;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
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
}
