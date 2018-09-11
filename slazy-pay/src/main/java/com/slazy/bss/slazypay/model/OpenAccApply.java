package com.slazy.bss.slazypay.model;



/**
 * 
 * @Description: 开户申请模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午3:39:26
 */
public class OpenAccApply extends BaseModel {

	private static final long serialVersionUID = 7722455827334064192L;
	
	private String accountNo;//账户编号
	private String bizSystem;//业务系统 
	private String status;//状态
	private String bizId;//订单号
	private String name;//姓名
	private String cerificateType;//证件类型:[01-身份证,02-护照,03-军人证,04-回乡证,05-台胞证]
	private String certificateNo;//证件号码
	private String customerType;//客户类别:[普通个人：0]
	private String mobile;//手机号码:[11位数字]
	private String customerIdType;//客户身份类型:[1:出借人、0:借款人、2:在职员工、3：离职员工]
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBizSystem() {
		return bizSystem;
	}
	public void setBizSystem(String bizSystem) {
		this.bizSystem = bizSystem;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCerificateType() {
		return cerificateType;
	}
	public void setCerificateType(String cerificateType) {
		this.cerificateType = cerificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}
