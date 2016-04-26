package com.lakala.crossborder.client.entities.gather;

import com.lakala.crossborder.client.enums.CertType;
import com.lakala.crossborder.client.enums.CrossBorderBizType;
import com.lakala.crossborder.client.enums.LklCurrency;

import java.io.Serializable;
import java.util.Map;

/**
 * <p>
 * 拉卡拉跨境支付-单笔实时收款请求业务参数
 * </p>
 *
 * @author jiangzhifei jiangzhifei@lakala.com
 */
public class LklGatherReq implements Serializable {

    private static final long serialVersionUID = 7098772610763637561L;

    /**
     * 商户订单号
     */
    private String merOrderId;

    /**
     * 订单币种 {@link LklCurrency}
     */
    private String currency;

    /**
     * 订单金额。单位：元，小数点后两位
     */
    private String orderAmount;

    /**
     * 主收款方应收金额。单位：元，小数点后两位
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
     * 支付卡有效期，YYMM，信用卡必填
     */
    private String dateOfExpire;
    /**
     * cvv,信用卡必填
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
     * 订单日期,yyyyMMddHHmmss
     */
    private String orderTime;

    /**
     * 订单有效时间yyyyMMddHHmmss
     */
    private String orderEffTime;

    /**
     * 商户服务器所在时区
     */
    private String timeZone;
    /**
     * 杭虎后台应答地址，需是完整的http或https的url
     */
    private String bgUrl;
    /**
     * 跨境业务类型 {@link CrossBorderBizType}
     */
    private String busiCode;

    /**
     * 是否启用白名单
     */
    private String isDirectPay;
    /**
     * 商户会员ID
     */
    private String merClientId;

    /**
     * 白名单签约协议号，若启用白名单则商户会员ID以及协议号必填
     */
    private String signDealNumber;

    private String ext1;

    private String ext2;

    /**
     * 扩展信息，字段参见接口文档对应章节
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

    public String getIsDirectPay() {
        return isDirectPay;
    }

    public void setIsDirectPay(String isDirectPay) {
        this.isDirectPay = isDirectPay;
    }

    public String getMerClientId() {
        return merClientId;
    }

    public void setMerClientId(String merClientId) {
        this.merClientId = merClientId;
    }

    public String getSignDealNumber() {
        return signDealNumber;
    }

    public void setSignDealNumber(String signDealNumber) {
        this.signDealNumber = signDealNumber;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LklGatherReq{");
        sb.append("merOrderId='").append(merOrderId).append('\'');
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", orderAmount='").append(orderAmount).append('\'');
        sb.append(", payeeAmount='").append(payeeAmount).append('\'');
        sb.append(", clientName='").append(clientName).append('\'');
        sb.append(", certType='").append(certType).append('\'');
        sb.append(", clientId='").append(clientId).append('\'');
        sb.append(", cardNo='").append(cardNo).append('\'');
        sb.append(", dateOfExpire='").append(dateOfExpire).append('\'');
        sb.append(", cvv='").append(cvv).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append(", orderSummary='").append(orderSummary).append('\'');
        sb.append(", orderTime='").append(orderTime).append('\'');
        sb.append(", orderEffTime='").append(orderEffTime).append('\'');
        sb.append(", timeZone='").append(timeZone).append('\'');
        sb.append(", bgUrl='").append(bgUrl).append('\'');
        sb.append(", busiCode='").append(busiCode).append('\'');
        sb.append(", isDirectPay='").append(isDirectPay).append('\'');
        sb.append(", merClientId='").append(merClientId).append('\'');
        sb.append(", signDealNumber='").append(signDealNumber).append('\'');
        sb.append(", ext1='").append(ext1).append('\'');
        sb.append(", ext2='").append(ext2).append('\'');
        sb.append(", extension=").append(extension);
        sb.append('}');
        return sb.toString();
    }
}
