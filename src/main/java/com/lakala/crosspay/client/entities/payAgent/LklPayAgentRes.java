package com.lakala.crosspay.client.entities.payAgent;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-实时代付响应参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklPayAgentRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -327453260441905785L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 订单日期yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 订单币种，目前只支持人民币
     */
    private String currency;

    /**
     * 实付金额，单位：元，小数点后两位
     */
    private String amount;

    /**
     * 手续费，单位：元，小数点后两位
     */
    private String fee;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklPayAgentRes{");
        sb.append("super=").append(super.toString());
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", fee='").append(fee).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
