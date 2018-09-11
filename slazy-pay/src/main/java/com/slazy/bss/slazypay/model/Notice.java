package com.slazy.bss.slazypay.model;


/**
 * 
 * @Description: 通知模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午6:15:31
 */
public class Notice extends BaseModel {

	private static final long serialVersionUID = -8127953697834653164L;

	private String bizId;//订单号
	private String bizType;//交易类型
	private String status;//交易状态
	private String json;//交易报文
	
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	
	
}
