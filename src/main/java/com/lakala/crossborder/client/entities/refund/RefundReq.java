package com.lakala.crossborder.client.entities.refund;

import com.lakala.crossborder.client.enums.LklCurrency;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-退款接口请求参数
 * </p>
 *
 * @author jiangzhife jiangzhifei@lakala.com
 */
public class RefundReq implements Serializable {

    private static final long serialVersionUID = -4618325348304644427L;

    /**
     * 原支付提交的商户订单号
     */
    private String merOrderId;

    /**
     * 商户退款订单号
     */
    private String seqId;

    /**
     * 订单币种
     *
     * @see LklCurrency
     */
    private String currency;

    /**
     * 原订单金额,单位：元，小数点后两位
     */
    private String orderAmount;

    /**
     * 退款时间,格式yyyyMMddHHmmss
     */
    private String retTime;

    /**
     * 退款金额，单位：元，小数点后两位
     */
    private String retAmt;

    /**
     * 退款币种
     *
     * @see LklCurrency
     */
    private String retCny;

    private String ext1;

    private String ext2;


    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
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

    public String getRetTime() {
        return retTime;
    }

    public void setRetTime(String retTime) {
        this.retTime = retTime;
    }

    public String getRetAmt() {
        return retAmt;
    }

    public void setRetAmt(String retAmt) {
        this.retAmt = retAmt;
    }

    public String getRetCny() {
        return retCny;
    }

    public void setRetCny(String retCny) {
        this.retCny = retCny;
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
        final StringBuffer sb = new StringBuffer("RefundReq{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", seqId='").append(seqId).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", retTime='").append(retTime).append('\'');
        sb.append(", retAmt='").append(retAmt).append('\'');
        sb.append(", retCny='").append(retCny).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
