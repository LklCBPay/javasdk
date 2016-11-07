package com.lakala.crossborder.client.entities.batchTrade;

import java.io.Serializable;

/**
 * 上传批量文件请求参数
 * Created by jiang on 2016/10/25.
 */
public class BatUploadFileReq implements Serializable {

    private static final long serialVersionUID = -1455489080843597093L;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 批量交易令牌
     */
    private String bizToken;

    /**
     * 批量文件名
     */
    private String fileName;

    /**
     * 文件本地全路径
     */
    private String filePath;

    /**
     * 安全码,此secCode为申请批量交易令牌时所用
     */
    private String secCode;

    /**
     * Md5(secCode+bizToken+filename)
     */
    private String sign;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatUploadFileReq{");
        sb.append("merchantId='").append(merchantId).append('\'');
        sb.append("bizToken='").append(bizToken).append('\'');
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", filePath='").append(filePath).append('\'');
        sb.append(", sign='").append(sign).append('\'');
        sb.append(", secCode='").append(secCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
