package com.lakala.crossborder.client.entities.webHook;

import com.lakala.crossborder.client.util.webhook.SuperWebHookResponse;

/**
 * 批量交易回调响应参数
 * Created by jiang on 2016/10/25.
 */
public class BatchTradeNotifyRes extends SuperWebHookResponse {

    private static final long serialVersionUID = 2097992584886437271L;

    /**
     * 随机数，注册回调地址时，需将拉卡拉发送的随机数原样返回
     */
    private String nonce;

    private String merchantId;

    private String bizToken;

    private String notifyType;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getBizToken() {
        return bizToken;
    }

    public void setBizToken(String bizToken) {
        this.bizToken = bizToken;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatchTradeNotifyRes{");
        sb.append("nonce='").append(nonce).append('\'');
        sb.append(",merchantId='").append(merchantId).append('\'');
        sb.append(", bizToken='").append(bizToken).append('\'');
        sb.append(", notifyType='").append(notifyType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
