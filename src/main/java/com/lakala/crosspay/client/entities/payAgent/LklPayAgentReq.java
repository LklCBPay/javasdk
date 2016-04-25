package com.lakala.crosspay.client.entities.payAgent;

import java.io.Serializable;

/**
 * <p>
 * 拉卡拉跨境支付-实时代付请求业务参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklPayAgentReq implements Serializable {

    private static final long serialVersionUID = 9103610311447066042L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 支付方式:5代付
     */
    private String payTypeId = "5";

    /**
     * 订单币种，目前只支持人民币 {@link com.lakala.crosspay.client.enums.LklCurrency}
     */
    private String currency;

    /**
     * 订单金额，单元：元，小数点后两位
     */
    private String orderAmount;

    /**
     * 账户名
     */
    private String accountName;

    /**
     * 账户号
     */
    private String accountNumber;

    /**
     * 证件类型 {@link com.lakala.crosspay.client.enums.CertType}
     */
    private String certType;

    /**
     * 证件号
     */
    private String clientId;

    /**
     * 支付卡有效期：YYMM
     */
    private String dateOfExpire;
    /**
     * 信用卡cvv，信用卡必填
     */
    private String cvv;

    /**
     * 银行预留手机号
     */
    private String mobile;

    /**
     * 订单概要
     */
    private String orderSummary;

    /**
     * 银行代码
     */
    private String bankCode;

    /**
     * 账户类型：1-对私账户
     */
    private String accountType;

    /**
     * 开户行名称
     */
    private String bankName;

    /**
     * 支付行号
     */
    private String interBankCode;

    /**
     * 银行摘要
     */
    private String bankRemark;

    /**
     * 订单日期yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 订单有效时间yyyyMMddHHmmss
     */
    private String orderEffTime;

    /**
     *
     */
    private String pageUrl;

    /**
     * 商户后台应答地址
     */
    private String bgUrl;

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
    }

    public String getPayTypeId() {
        return payTypeId;
    }

    public void setPayTypeId(String payTypeId) {
        this.payTypeId = payTypeId;
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

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getDateOfExpire() {
        return dateOfExpire;
    }

    public void setDateOfExpire(String dateOfExpire) {
        this.dateOfExpire = dateOfExpire;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public void setOrderSummary(String orderSummary) {
        this.orderSummary = orderSummary;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getInterBankCode() {
        return interBankCode;
    }

    public void setInterBankCode(String interBankCode) {
        this.interBankCode = interBankCode;
    }

    public String getBankRemark() {
        return bankRemark;
    }

    public void setBankRemark(String bankRemark) {
        this.bankRemark = bankRemark;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderEffTime() {
        return orderEffTime;
    }

    public void setOrderEffTime(String orderEffTime) {
        this.orderEffTime = orderEffTime;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getBgUrl() {
        return bgUrl;
    }

    public void setBgUrl(String bgUrl) {
        this.bgUrl = bgUrl;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklPayAgentReq{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", payTypeId='").append(payTypeId).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", accountName='").append(accountName).append('\'');
        sb.append(", accountNumber='").append(accountNumber).append('\'');
        sb.append(", certType='").append(certType).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", dateOfExpire='").append(dateOfExpire).append('\'');
        sb.append(", cvv='").append(cvv).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", orderSummary='").append(orderSummary).append('\'');
        sb.append(", bankCode='").append(bankCode).append('\'');
        sb.append(", accountType='").append(accountType).append('\'');
        sb.append(", bankName='").append(bankName).append('\'');
        sb.append(", interBankCode='").append(interBankCode).append('\'');
        sb.append(", bankRemark='").append(bankRemark).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", orderEffTime='").append(orderEffTime).append('\'');
        sb.append(", pageUrl='").append(pageUrl).append('\'');
        sb.append(", bgUrl='").append(bgUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
