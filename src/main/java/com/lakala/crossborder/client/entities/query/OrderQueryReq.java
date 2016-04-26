package com.lakala.crossborder.client.entities.query;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-订单查询请求参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class OrderQueryReq implements Serializable {


    private static final long serialVersionUID = 3058765353836604073L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 拉卡拉流水号
     */
    private String transactionId;

    private String ext1;

    private String ext2;

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
        final StringBuffer sb = new StringBuffer("OrderQueryReq{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
