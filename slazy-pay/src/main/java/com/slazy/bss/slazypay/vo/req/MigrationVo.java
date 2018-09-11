package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 数据迁移vo
 * @author:	xianzhiqiang
 * @date:	2017年12月5日 下午3:02:16
 */
public class MigrationVo implements Serializable {

	private static final long serialVersionUID = -2227131260951396078L;

	private String accountNo;//账户编号
	private String name;//姓名
	private String cerificateType;//证件类型:[01-身份证,02-护照,03-军人证,04-回乡证,05-台胞证]
	private String certificateNo;//证件号码
	private String customerIdType;//用户角色:[1:出借人、2:借款人]
	private String mobile;//注册手机号码:[11位数字]
	private String bankMobile;//银行预留手机号
	private String bankId;//银行编码
	private String bankName;//银行名称
	private String bankCardNo;//银行卡号
	private String sex;//性别
	private String amount;//冻结金额
	private String freezeId;//冻结
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
	public String getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBankMobile() {
		return bankMobile;
	}
	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	
}
