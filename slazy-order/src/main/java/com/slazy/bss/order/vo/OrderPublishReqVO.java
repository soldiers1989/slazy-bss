package com.slazy.bss.order.vo;

import java.util.Date;

import org.slazyframework.model.BaseModel;

public class OrderPublishReqVO extends BaseModel {

	// 取件人信息
	private String deliveryId;
	private String deliveryName;
	private String deliveryPhone;
	private String deliveryAddress;
	private String deliverySchoolId;
	private Date deliveryStartime;
	private Date deliveryEndtime;
	// 收件 人 信息
	private String reveiverId;
	private String reveiverName;
	private String reveiverPhone;
	private String reveiverAddress;
	private String reveiverSchoolId;
	private Date reveiverStarttime;
	private Date reveiverEndtime;
	// 快递信息
	private String expressMessage;
	// 快递名称
	private String expressName;
	// 获取大小
	private String commoditySize;
	// 支付金额，也就是价格
	private String amount;
	// 备注
	private String remark;
	// 优惠券
	private String coupon;

	public String getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(String deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getDeliveryName() {
		return deliveryName;
	}

	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	public String getDeliveryPhone() {
		return deliveryPhone;
	}

	public void setDeliveryPhone(String deliveryPhone) {
		this.deliveryPhone = deliveryPhone;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getDeliverySchoolId() {
		return deliverySchoolId;
	}

	public void setDeliverySchoolId(String deliverySchoolId) {
		this.deliverySchoolId = deliverySchoolId;
	}

	public Date getDeliveryStartime() {
		return deliveryStartime;
	}

	public void setDeliveryStartime(Date deliveryStartime) {
		this.deliveryStartime = deliveryStartime;
	}

	public Date getDeliveryEndtime() {
		return deliveryEndtime;
	}

	public void setDeliveryEndtime(Date deliveryEndtime) {
		this.deliveryEndtime = deliveryEndtime;
	}

	public String getReveiverId() {
		return reveiverId;
	}

	public void setReveiverId(String reveiverId) {
		this.reveiverId = reveiverId;
	}

	public String getReveiverName() {
		return reveiverName;
	}

	public void setReveiverName(String reveiverName) {
		this.reveiverName = reveiverName;
	}

	public String getReveiverPhone() {
		return reveiverPhone;
	}

	public void setReveiverPhone(String reveiverPhone) {
		this.reveiverPhone = reveiverPhone;
	}

	public String getReveiverAddress() {
		return reveiverAddress;
	}

	public void setReveiverAddress(String reveiverAddress) {
		this.reveiverAddress = reveiverAddress;
	}

	public String getReveiverSchoolId() {
		return reveiverSchoolId;
	}

	public void setReveiverSchoolId(String reveiverSchoolId) {
		this.reveiverSchoolId = reveiverSchoolId;
	}

	public Date getReveiverStarttime() {
		return reveiverStarttime;
	}

	public void setReveiverStarttime(Date reveiverStarttime) {
		this.reveiverStarttime = reveiverStarttime;
	}

	public Date getReveiverEndtime() {
		return reveiverEndtime;
	}

	public void setReveiverEndtime(Date reveiverEndtime) {
		this.reveiverEndtime = reveiverEndtime;
	}

	public String getExpressMessage() {
		return expressMessage;
	}

	public void setExpressMessage(String expressMessage) {
		this.expressMessage = expressMessage;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getCommoditySize() {
		return commoditySize;
	}

	public void setCommoditySize(String commoditySize) {
		this.commoditySize = commoditySize;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

}
