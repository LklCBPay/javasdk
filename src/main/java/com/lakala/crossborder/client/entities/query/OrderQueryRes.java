package com.lakala.crossborder.client.entities.query;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-订单查询响应参数
 * </p>
 */
public class OrderQueryRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -4649406729726135794L;

    /**
     * 订单时间,格式yyyyMMddHHmmss
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
     * 支付结果
     * <ul>
     * 支付
     * <li>0-未支付</li>
     * <li>1-已支付</li>
     * <li>2-失效</li>
     * <li>3-已冲正</li>
     * <li>4-已撤销</li>
     * <li>99-关闭</li>
     * </ul>
     * <p/>
     * <ul>
     * 退款
     * <li>0-商户确认（待处理）</li>
     * <li>1-退款成功</li>
     * <li>2-退款失败</li>
     * <li>9-退款申请</li>
     * <li>10-无效退款(拒绝退款)</li>
     * </ul>
     */
    private String payResult;

    /**
     * 人民币实付金额,单位：元，小数点后两位
     */
    private String cnyAmount;

    /**
     * 购汇汇率
     */
    private String exchangeRate;

    /**
     * 支付成功时间,格式yyyyMMddHHmmss
     */
    private String dealTime;

    /**
     *  订单金额,单位：元，小数点后两位
     */
    private String orderAmount;

    /**
     * 手续费,单位：元，小数点后两位
     */
    private String orderFee;

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

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
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

    public String getDealTime() {
        return dealTime;
    }

    public void setDealTime(String dealTime) {
        this.dealTime = dealTime;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderFee() {
        return orderFee;
    }

    public void setOrderFee(String orderFee) {
        this.orderFee = orderFee;
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
        final StringBuffer sb = new StringBuffer("OrderQueryRes{");
        sb.append("orderTime='").append(orderTime).append('\'');
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", payResult='").append(payResult).append('\'');
        sb.append(", cnyAmount='").append(cnyAmount).append('\'');
        sb.append(", exchangeRate='").append(exchangeRate).append('\'');
        sb.append(", dealTime='").append(dealTime).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", orderFee='").append(orderFee).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
