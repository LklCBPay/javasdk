package com.lakala.crossborder.client.entities.webHook;

import com.lakala.crossborder.client.util.webhook.SuperWebHookRequest;

/**
 * 拉卡拉批量交易回调报文对象
 * <p/>
 * Created by jiang on 2016/10/25.
 */
public class BatchTradeNotify extends SuperWebHookRequest {

    private static final long serialVersionUID = -7469334290334195484L;

    /**
     * 注册毁掉地址时，拉卡拉发送的随机数，商户需将该随机数返回拉卡拉，已完成回调地址注册
     */
    private String nonce;

    /**
     * 批量交易令牌(必填)
     */
    private String bizToken;

    /**
     * 商户号（必填）
     */
    private String merchantId;

    /**
     * 回调通知类型
     * <ul>
     * <li>01-交易结果回调</li>
     * <li>02-下载回盘文件通知</li>
     * <li>03-下载补回盘文件通知</li>
     * </ul>
     */
    private String notifyType;
    /**
     * notifyType为2、3时，该字段有值
     */
    private String downLoadToken;

    /**
     * 当notifyType为1时，该字段有值
     * <ui>
     * <li>00-商户送盘文件名错误；</li>
     * <li>01-商户送盘文件头格式错误；</li>
     * <li>02-商户送盘文件体格式错误；</li>
     * <li>03-重复的交易；</li>
     * <li>04-文件内容有误-交易总金额不正确；</li>
     * <li>05-文件内容有误-交易总笔数不正确； </li>
     * <li>99-系统异常</li>
     * </ui>
     */
    private String bizResult;

    /**
     * 商户送盘日期：yyyymmdd
     * notifyType为2、3时，该字段有值
     */
    private String receiveDate;

    /**
     * 交易类型，默认为DS
     * notifyType为2、3时，该字段有值
     */
    private String bizType;

    /**
     * 商户（补）回盘文件名
     * notifyType为2、3时，该字段有值
     */
    private String merchantResFileName;

    /**
     * 商户送盘文件名
     * notifyType为2时，该字段有值
     */
    private String merchantFileName;

    /**
     * zip包内文件数量，notifyType为2时，该字段有值
     */
    private String fileCount;

    /**
     * 包内总交易笔数，notifyType为2时，该字段有值
     */
    private String totalBizCount;

    /**
     * 包内总交易金额，单位：分,notifyType为2时，该字段有值
     */
    private String totalBizAmount;

    /**
     * 成功交易笔数，notifyType为2时，该字段有值
     */
    private String successBizCount;

    /**
     * 成功金额，单位：分，notifyType为2时，该字段有值
     */
    private String successBizAmount;

    /**
     * 文件摘要盐值，YYYYMMDDHHMISS+6位随机数，notifyType为2、3时，该字段有值
     */
    private String secCode;

    /**
     * 文件摘要，MD5（secCode+文件内容），notifyType为2、3时，该字段有值
     */
    private String digest;

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getBizToken() {
        return bizToken;
    }

    public void setBizToken(String bizToken) {
        this.bizToken = bizToken;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getDownLoadToken() {
        return downLoadToken;
    }

    public void setDownLoadToken(String downLoadToken) {
        this.downLoadToken = downLoadToken;
    }

    public String getBizResult() {
        return bizResult;
    }

    public void setBizResult(String bizResult) {
        this.bizResult = bizResult;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getMerchantResFileName() {
        return merchantResFileName;
    }

    public void setMerchantResFileName(String merchantResFileName) {
        this.merchantResFileName = merchantResFileName;
    }

    public String getMerchantFileName() {
        return merchantFileName;
    }

    public void setMerchantFileName(String merchantFileName) {
        this.merchantFileName = merchantFileName;
    }

    public String getFileCount() {
        return fileCount;
    }

    public void setFileCount(String fileCount) {
        this.fileCount = fileCount;
    }

    public String getTotalBizCount() {
        return totalBizCount;
    }

    public void setTotalBizCount(String totalBizCount) {
        this.totalBizCount = totalBizCount;
    }

    public String getTotalBizAmount() {
        return totalBizAmount;
    }

    public void setTotalBizAmount(String totalBizAmount) {
        this.totalBizAmount = totalBizAmount;
    }

    public String getSuccessBizCount() {
        return successBizCount;
    }

    public void setSuccessBizCount(String successBizCount) {
        this.successBizCount = successBizCount;
    }

    public String getSuccessBizAmount() {
        return successBizAmount;
    }

    public void setSuccessBizAmount(String successBizAmount) {
        this.successBizAmount = successBizAmount;
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
        final StringBuffer sb = new StringBuffer("BatchTradeNotify{");
        sb.append("nonce='").append(nonce).append('\'');
        sb.append(",bizToken='").append(bizToken).append('\'');
        sb.append(", merchantId='").append(merchantId).append('\'');
        sb.append(", notifyType='").append(notifyType).append('\'');
        sb.append(", downLoadToken='").append(downLoadToken).append('\'');
        sb.append(", bizResult='").append(bizResult).append('\'');
        sb.append(", receiveDate='").append(receiveDate).append('\'');
        sb.append(", bizType='").append(bizType).append('\'');
        sb.append(", merchantResFileName='").append(merchantResFileName).append('\'');
        sb.append(", merchantFileName='").append(merchantFileName).append('\'');
        sb.append(", fileCount='").append(fileCount).append('\'');
        sb.append(", totalBizCount='").append(totalBizCount).append('\'');
        sb.append(", totalBizAmount='").append(totalBizAmount).append('\'');
        sb.append(", successBizCount='").append(successBizCount).append('\'');
        sb.append(", successBizAmount='").append(successBizAmount).append('\'');
        sb.append(", secCode='").append(secCode).append('\'');
        sb.append(", digest='").append(digest).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
