package com.slazy.bss.order.vo;

import java.util.Date;

import org.slazyframework.model.BaseModel;

public class CommodityInfoVO extends BaseModel {

	private String commodityId;

    private String orderId;

    private String status;

    private Date createDate;

    private String createOpId;

    private Date doneDate;

    private String opId;

    private String commodityType;

    private String commodityName;

    private String commodityLocus;

    private String expressName;

    private String commodityDes;

    private String commoditySize;

    private Date arrivalTime;

    private Date leaveTime;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateOpId() {
		return createOpId;
	}

	public void setCreateOpId(String createOpId) {
		this.createOpId = createOpId;
	}

	public Date getDoneDate() {
		return doneDate;
	}

	public void setDoneDate(Date doneDate) {
		this.doneDate = doneDate;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getCommodityType() {
		return commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityLocus() {
		return commodityLocus;
	}

	public void setCommodityLocus(String commodityLocus) {
		this.commodityLocus = commodityLocus;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getCommodityDes() {
		return commodityDes;
	}

	public void setCommodityDes(String commodityDes) {
		this.commodityDes = commodityDes;
	}

	public String getCommoditySize() {
		return commoditySize;
	}

	public void setCommoditySize(String commoditySize) {
		this.commoditySize = commoditySize;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
    
    
}
