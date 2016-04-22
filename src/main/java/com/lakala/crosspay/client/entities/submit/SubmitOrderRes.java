package com.lakala.crosspay.client.entities.submit;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * 商户下单业务响应参数
 * <p/>
 * Created by jiang on 16/4/11.
 */
public class SubmitOrderRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -1447455521273385588L;


    /**
     * 商户订单号
     */
    private String merOrderId;
    /**
     * 订单日期 yyyyMMDDHHMMSS
     */
    private String orderTime;

    /**
     * 订单币种
     */
    private String currency;

    /**
     * 人民币实付金额 ，小数点后两位，单位：元
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
     * 短信送达状态：1-已送达；0-未送达
     */
    private String smsStatus;

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
        final StringBuffer sb = new StringBuffer("SubmitOrderRes{");
        sb.append("super=[").append(super.toString()).append("]");
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", cnyAmount='").append(cnyAmount).append('\'');
        sb.append(", exchangeRate='").append(exchangeRate).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", smsStatus='").append(smsStatus).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
