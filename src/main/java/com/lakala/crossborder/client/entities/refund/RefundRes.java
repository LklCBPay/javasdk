package com.lakala.crossborder.client.entities.refund;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

/**
 * <p>
 * 拉卡拉跨境支付-退款接口响应参数
 * </p>
 *
 * @author jiangzhife jiangzhifei@lakala.com
 */
public class RefundRes extends LklCrossPaySuperRes {

    private static final long serialVersionUID = -6007239512051704490L;

    /**
     * 退款请求中的retTime,格 相同
     */
    private String orderTime;

    /**
     * 商 原订单号
     */
    private String merOrderId;

    /**
     * 退款订单号
     */
    private String seqId;

    /**
     * 卡 流水号
     */
    private String transactionId;

    /**
     * 退款状
     * <ul>
     * <li>0-退款 功</li>
     * <li>1-处理中</li>
     * <li>2-失败</li>
     * </ul>
     */
    private String retStatu;

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

    public String getSeqId() {
        return seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRetStatu() {
        return retStatu;
    }

    public void setRetStatu(String retStatu) {
        this.retStatu = retStatu;
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
        final StringBuffer sb = new StringBuffer("RefundRes{");
        sb.append("super=").append(super.toString());
        sb.append("orderTime='").append(orderTime).append('\'');
        sb.append(", merOrderId='").append(merOrderId).append('\'');
        sb.append(", seqId='").append(seqId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", retStatu='").append(retStatu).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
