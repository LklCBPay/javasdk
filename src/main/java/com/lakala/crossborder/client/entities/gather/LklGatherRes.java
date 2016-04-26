package com.lakala.crossborder.client.entities.gather;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;
import com.lakala.crossborder.client.enums.LklCurrency;

/**
 * <p>
 * 拉卡拉跨境支付-单笔实时收款响应参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklGatherRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -3562644471870585474L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 商户提交订单时间yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 订单金额，单元：元，小数点后两位
     */
    private String orderAmount;

    /**
     * 订单币种 {@link LklCurrency}
     */
    private String currency;

    /**
     * 人民币实付金额，单位：元，小数点后两位
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
     * 支付平台处理时间yyyyMMddHHmmss
     */
    private String dealTime;

    /**
     * 手续费，单位：元，小数点后两位
     */
    private String orderFee;

    /**
     * 支付状态
     * <ul>
     * <li>1.支付成功</li>
     * </ul>
     */
    private String payResult;

    private String ext1;

    private String ext2;

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
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
        final StringBuffer sb = new StringBuffer("LklGatherRes{");
        sb.append("super='").append(merOrderId).append('\'');
        sb.append(super.toString());
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", cnyAmount='").append(cnyAmount).append('\'');
        sb.append(", exchangeRate='").append(exchangeRate).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", dealTime='").append(dealTime).append('\'');
        sb.append(", orderFee='").append(orderFee).append('\'');
        sb.append(", payResult='").append(payResult).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
