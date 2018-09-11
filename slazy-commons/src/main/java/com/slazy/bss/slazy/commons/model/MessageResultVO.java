package com.slazy.bss.slazy.commons.model;

public class MessageResultVO extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2127568301505604351L;
	
	private String state;//提交状态，0表示提交成功，其他值为失败
	
	private String msgId;//信息批次号
	
	private String msgState;//返回状态描述
	
	private String reserve;//扩展字段

	public MessageResultVO() {
		super();
	}

	public MessageResultVO(String state, String msgId, String msgState,
			String reserve) {
		super();
		this.state = state;
		this.msgId = msgId;
		this.msgState = msgState;
		this.reserve = reserve;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgState() {
		return msgState;
	}

	public void setMsgState(String msgState) {
		this.msgState = msgState;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
	

}
