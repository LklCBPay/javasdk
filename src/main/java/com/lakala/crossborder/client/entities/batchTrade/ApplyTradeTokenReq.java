package com.lakala.crossborder.client.entities.batchTrade;

import java.io.Serializable;

/**
 * 批量代收申请交易令牌请求参数
 * Created by jiang on 2016/10/25.
 */
public class ApplyTradeTokenReq implements Serializable {

    private static final long serialVersionUID = 1775222560235233781L;

    /**
     * 交易类型，默认为代收-DS
     */
    private String bizType = "DS";

    /**
     * zip包内文件数量,默认为1
     */
    private String fileCount = "1";

    /**
     * 包内包含的交易总笔数，不超过5000
     */
    private String bizCount;

    /**
     * 包内包含的交易总金额，单位：分
     */
    private String bizAmount;

    /**
     * YYYYMMDDHHMISS+6位随机字符串，保证永远唯一。用于Zip文件摘要盐值
     */
    private String secCode;

    /**
     * zip文件摘要，md5(secCode+文件内容)
     */
    private String digest;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public String getBizCount() {
        return bizCount;
    }

    public void setBizCount(String bizCount) {
        this.bizCount = bizCount;
    }

    public String getBizAmount() {
        return bizAmount;
    }

    public void setBizAmount(String bizAmount) {
        this.bizAmount = bizAmount;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApplyTradeTokenReq{");
        sb.append("bizType='").append(bizType).append('\'');
        sb.append(", fileCount='").append(fileCount).append('\'');
        sb.append(", bizCount='").append(bizCount).append('\'');
        sb.append(", bizAmount='").append(bizAmount).append('\'');
        sb.append(", secCode='").append(secCode).append('\'');
        sb.append(", digest='").append(digest).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
