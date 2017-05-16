package com.lakala.crossborder.client.entities.query;

import java.io.Serializable;

public class CustomAuthQueryReq implements Serializable{
	
	private static final long serialVersionUID = -3395900389964247218L;
	private String orderNo;
    private String payOrderId;
    
	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
}
