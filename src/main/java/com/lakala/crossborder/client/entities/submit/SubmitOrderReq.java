package com.lakala.crossborder.client.entities.submit;

import com.lakala.crossborder.client.enums.CertType;
import com.lakala.crossborder.client.enums.CrossBorderBizType;
import com.lakala.crossborder.client.enums.LklCurrency;

import java.io.Serializable;
import java.util.Map;

/**
 * 商户下单业务参数
 * <p/>
 * Created by jiang on 16/4/11.
 */
public class SubmitOrderReq implements Serializable {

    private static final long serialVersionUID = -8580114171972496490L;
    /**
     * 订单号
     */
    private String merOrderId;

    /**
     * 订单币种 {@link LklCurrency}
     */
    private String currency;

    /**
     * 订单金额。小数点后两位。单位：元
     */
    private String orderAmount;

    /**
     * 主收款方应收金额。小数点后两位。单位：元
     */
    private String payeeAmount;

    /**
     * 持卡人姓名
     */
    private String clientName;

    /**
     * 证件类型 {@link CertType}
     */
    private String certType;

    /**
     * 证件号
     */
    private String clientId;

    /**
     * 支付卡卡号
     */
    private String cardNo;

    /**
     * 贷记卡有效期 YYMM,贷记卡必填
     */
    private String dateOfExpire;

    /**
     * 贷记卡cvv，贷记卡必填
     */
    private String cvv;

    /**
     * 银行预留手机号码
     */
    private String mobile;

    /**
     * 订单概要
     */
    private String orderSummary;

    /**
     * 订单日期：yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 订单有效时间 yyyyMMddHHmmss
     */
    private String orderEffTime;


    private String timeZone;

    /**
     * 支付完成后跳转到商户的方的页面
     */
    private String pageUrl;

    /**
     * 商户后台通知url
     */
    private String bgUrl;

    /**
     * 跨境业务类型 {@link CrossBorderBizType}
     */
    private String busiCode;

    /**
     * 是否合并“签约+支付”
     */
    private String isMergeSign;

    private String ext1;

    /**
     * 扩展字段2
     */
    private String ext2;

    /**
     * 扩展信息
     */
    private Map<String, String> extension;

    public String getMerOrderId() {
        return merOrderId;
    }

    public void setMerOrderId(String merOrderId) {
        this.merOrderId = merOrderId;
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

    public String getPayeeAmount() {
        return payeeAmount;
    }

    public void setPayeeAmount(String payeeAmount) {
        this.payeeAmount = payeeAmount;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
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

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getIsMergeSign() {
        return isMergeSign;
    }

    public void setIsMergeSign(String isMergeSign) {
        this.isMergeSign = isMergeSign;
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

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }
}
