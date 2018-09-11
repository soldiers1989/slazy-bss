package com.slazy.bss.slazypay.model;

/**
 * 
 * @Description:
 * @author:	wangyanan
 * @date:	2017-9-26 上午11:45:18
 */
public class UkeyBind extends BaseModel{
	
	private static final long serialVersionUID = 3877026892479158056L;
	private String accountName;//账户名
	private String userName;//用户名称
	private String serialNumber;//UKEY序列号
	
	public String getAccountName() {
		return accountName;
	}
	public String getUserName() {
		return userName;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}
