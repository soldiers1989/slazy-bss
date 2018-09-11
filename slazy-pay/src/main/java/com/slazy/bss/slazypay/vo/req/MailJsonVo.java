package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;


public class MailJsonVo implements Serializable {

	private static final long serialVersionUID = 4319101684482059381L;
	private String title;//标题
	private String addr;//发送地址（邮箱地址、手机号等等，多个地址用“，”隔开）
	private String bcAddr;//密送地址（邮箱地址、手机号等等，多个地址用“，”隔开）
	private String tplKey;//模板关键字
	private String fieldJson;//字段信息
	private String bizNo;//业务编号（如合同号、投资编号等）
	private String customerName;//客户姓名
	private String bizId;//交易标识
	private String systemSign;//系统标识
	private String signInfo;//签名信息
	private String operator;//操作人

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public String getSystemSign() {
		return systemSign;
	}

	public void setSystemSign(String systemSign) {
		this.systemSign = systemSign;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public String getTplKey() {
		return tplKey;
	}

	public void setTplKey(String tplKey) {
		this.tplKey = tplKey;
	}

	public String getFieldJson() {
		return fieldJson;
	}

	public void setFieldJson(String fieldJson) {
		this.fieldJson = fieldJson;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBcAddr() {
		return bcAddr;
	}

	public void setBcAddr(String bcAddr) {
		this.bcAddr = bcAddr;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBizNo() {
		return bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

}
