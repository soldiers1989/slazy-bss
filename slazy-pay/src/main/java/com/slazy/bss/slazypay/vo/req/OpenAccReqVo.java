package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 开户Vo
 * @author:	xianzhiqiang
 * @date:	2017年6月29日 下午5:19:33
 */
public class OpenAccReqVo extends BaseReqVo implements Serializable{
	
	private static final long serialVersionUID = -8350726910692861447L;
	private String customerType;//客户类别:[普通个人：0]
	private String customerIdType;//客户身份类型:[1:出借人、0:借款人、2:在职员工、3：离职员工]
	private String name;//姓名
	private String cerificateType;//证件类型:[01-身份证,02-护照,03-军人证,04-回乡证,05-台胞证]
	private String certificateNo;//证件号码
	private String mobile;//手机号码:[11位数字]
	private String openAccountTime;//开户时间
	private String bankName;//银行名称
	private String bankCode;//银行代码
	private String accountNo;//账户编号
	private String cardNo;//卡号
	private String bankMobile;//银行预留手机号
	private String bankInfo;//银行支行信息
//	private String resultStatus;//状态 1.成功  2.失败 3.处理中
//	private String failReason;//失败原因
	private String reservedMobile;//预留手机号
	private String payChannel;//支付通道
	
	public String getCustomerType() {
		return customerType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getCerificateType() {
		return cerificateType;
	}
	public void setCerificateType(String cerificateType) {
		this.cerificateType = cerificateType;
	}
	public String getOpenAccountTime() {
		return openAccountTime;
	}
	public void setOpenAccountTime(String openAccountTime) {
		this.openAccountTime = openAccountTime;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankMobile() {
		return bankMobile;
	}
	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}
	public String getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(String bankInfo) {
		this.bankInfo = bankInfo;
	}
//	public String getResultStatus() {
//		return resultStatus;
//	}
//	public void setResultStatus(String resultStatus) {
//		this.resultStatus = resultStatus;
//	}
//	public String getFailReason() {
//		return failReason;
//	}
//	public void setFailReason(String failReason) {
//		this.failReason = failReason;
//	}
	public String getReservedMobile() {
		return reservedMobile;
	}
	public void setReservedMobile(String reservedMobile) {
		this.reservedMobile = reservedMobile;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}


	

}
