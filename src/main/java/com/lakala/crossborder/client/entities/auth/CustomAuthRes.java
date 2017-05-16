package com.lakala.crossborder.client.entities.auth;

import java.io.Serializable;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

public class CustomAuthRes extends LklCrossPaySuperRes{
	private static final long serialVersionUID = 4088720655938566139L;

	private String orderNo; //商户订单号
	
	private String payOrderId;//支付订单号
	
	private String name;//姓名
	
	private String mobile;//手机号码
	
	private String amount;//金额
	
	private String cuId;//海关
	
	private String customcomCode;//海关备案号
	
	private String cbpName;//海关备案号名称
	
	private String clientId;//身份证号
	
	private String clientType;//证件类型
	
	private String payerMail;//邮箱
	
	private String bgUrl;//后台通知地址
	
	private String goodsFee;//商品货款金额
	
	private String taxFee;//税款金额
	
	private String bizTypeCode;//业务类型
	
	private String orderNote;//订单备注

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCuId() {
		return cuId;
	}

	public void setCuId(String cuId) {
		this.cuId = cuId;
	}

	public String getCustomcomCode() {
		return customcomCode;
	}

	public void setCustomcomCode(String customcomCode) {
		this.customcomCode = customcomCode;
	}

	public String getCbpName() {
		return cbpName;
	}

	public void setCbpName(String cbpName) {
		this.cbpName = cbpName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getPayerMail() {
		return payerMail;
	}

	public void setPayerMail(String payerMail) {
		this.payerMail = payerMail;
	}

	public String getBgUrl() {
		return bgUrl;
	}

	public void setBgUrl(String bgUrl) {
		this.bgUrl = bgUrl;
	}

	public String getGoodsFee() {
		return goodsFee;
	}

	public void setGoodsFee(String goodsFee) {
		this.goodsFee = goodsFee;
	}

	public String getTaxFee() {
		return taxFee;
	}

	public void setTaxFee(String taxFee) {
		this.taxFee = taxFee;
	}

	public String getBizTypeCode() {
		return bizTypeCode;
	}

	public void setBizTypeCode(String bizTypeCode) {
		this.bizTypeCode = bizTypeCode;
	}

	public String getOrderNote() {
		return orderNote;
	}

	public void setOrderNote(String orderNote) {
		this.orderNote = orderNote;
	}

	
	
}
