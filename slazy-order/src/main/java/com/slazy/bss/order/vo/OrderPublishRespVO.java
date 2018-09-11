package com.slazy.bss.order.vo;

import java.util.Date;

import org.slazyframework.model.BaseModel;

public class OrderPublishRespVO extends BaseModel {

	// 取件人信息
	private String orderId;
	private String amount;
	private String commodityId;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	
}
