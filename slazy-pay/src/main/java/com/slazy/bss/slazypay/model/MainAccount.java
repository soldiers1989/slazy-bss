package com.slazy.bss.slazypay.model;

import java.util.Date;


/**
 * 
 * @Description: 主账户模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午3:39:26
 */
public class MainAccount extends BaseModel {

	private static final long serialVersionUID = 9138676717310576543L;

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
	private String pId;//公司账户关联使用
	private Date openAccountTime;//开户时间
	private String operator;//操作人
	private String remark;//备注
	private String activationType;//激活状态[ 0:未激活  1:已激活]
	private String sex;//性别[0：男，1：女]
	
	/**************************临时字段****************************/
	private String bankCardNo;
	private String bankCode;
	private String reservedMobile;//预留手机号
	/******************************************************/
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getReservedMobile() {
		return reservedMobile;
	}
	public void setReservedMobile(String reservedMobile) {
		this.reservedMobile = reservedMobile;
	}
	
	public String getActivationType() {
		return activationType;
	}
	public void setActivationType(String activationType) {
		this.activationType = activationType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getOpenAccountTime() {
		return openAccountTime;
	}
	public void setOpenAccountTime(Date openAccountTime) {
		this.openAccountTime = openAccountTime;
	}
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
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
//	public String getReservedMobile() {
//		return reservedMobile;
//	}
//	public void setReservedMobile(String reservedMobile) {
//		this.reservedMobile = reservedMobile;
//	}
	
}
