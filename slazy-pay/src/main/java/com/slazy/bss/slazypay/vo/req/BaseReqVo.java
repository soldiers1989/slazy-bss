package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;



public class BaseReqVo implements Serializable {

	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * requst id 接口序号
	 */
	private String id;
	
	
	/**
	 * 版本号  目前默认传1.0
	 */
	private String version = "1";
	
	
	/**
	 * 交易时间戳   YYYYMMDD
	 */
	private String time;
	
	private String bizId; //业务流水号
	
	private String bizSystem;//业务系统
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getBizSystem() {
		return bizSystem;
	}
	public void setBizSystem(String bizSystem) {
		this.bizSystem = bizSystem;
	}
	
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}
