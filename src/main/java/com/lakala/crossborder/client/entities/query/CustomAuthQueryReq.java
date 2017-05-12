package com.lakala.crossborder.client.entities.query;

import java.io.Serializable;

public class CustomAuthQueryReq implements Serializable{
	
	private static final long serialVersionUID = -3395900389964247218L;
	private String orderNo;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
}
