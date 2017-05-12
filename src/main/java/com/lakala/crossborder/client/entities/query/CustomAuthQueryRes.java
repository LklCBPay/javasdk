package com.lakala.crossborder.client.entities.query;

import com.lakala.crossborder.client.entities.LklCrossPaySuperRes;

public class CustomAuthQueryRes extends LklCrossPaySuperRes{

	private static final long serialVersionUID = 1627663082608774833L;
	private String orderNo;
	private String status;
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
