package com.lakala.crosspay.client.entities.query;

import com.lakala.crosspay.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-单笔实时代付状态查询响应参数
 * </p>
 */
public class AgentPayStatusQueryRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = 6766471016678687429L;

    /**
     * 订单日期,格式yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * <p>
     * 订单状态
     * <ul>
     * <li>
     * 00-未支付
     * </li>
     * <li>01-已支付</li>
     * <li>02-失效</li>
     * <li>03-状态不明</li>
     * <li>04-已撤销</li>
     * <li>99-关闭</li>
     * </ul>
     * </p>
     */
    private String payResult;

    /**
     * 实付金额,单位：元，小数点后两位
     */
    private String amount;

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

    public String getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult = payResult;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AgentPayStatusQueryRes{");
        sb.append("super=").append(super.toString());
        sb.append("orderTime='").append(orderTime).append('\'');
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", payResult='").append(payResult).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
