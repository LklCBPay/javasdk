package com.lakala.crossborder.client.entities.notify;

import com.lakala.crossborder.client.util.webhook.SuperWebHookResponse;

/**
 * <p>
 * 商户响应拉卡拉支付通知
 * </p>
 */
public class PayResultNotifyResponse extends SuperWebHookResponse {

    private static final long serialVersionUID = -1765519655733204701L;

    private String merOrderId;

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
        final StringBuffer sb = new StringBuffer("PayResultNotifyResponse{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", transactionId='").append(transactionId).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
