package org.slazyframework.model;

public class MessageResultVO extends BaseModel {

	private static final long serialVersionUID = -2127568301505604351L;
	
	private String state;
	
	private String msgId;
	
	private String msgState;
	
	private String reserve;

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
