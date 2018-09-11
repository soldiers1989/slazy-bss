package com.slazy.bss.slazypay.model;

import java.util.Date;


/**
 * 
 * @Description: 银行卡模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午3:58:37
 */
public class BankCard extends BaseModel {

	private static final long serialVersionUID = 3156495914370361848L;

	private String bizId;//订单号
	private String name;//姓名
	private String idCardNo;//身份证号码
	private String bankId;//新卡银行编码
	private String mobile;//新卡绑定手机号
	private String province;//新银行卡开户省
	private String city;//新卡开户市
	private String newBankInfo;//新卡银行支行信息
	private String bankNumber;//开户行号
	private String nBankCardNo;//新卡卡号
	private String oBankCardNo;//原卡卡号
	private String accountNo;//账户编号
	private String bindType;//绑卡类型:[1:绑卡  2:换卡]
	private String operator;//操作人
	private String status;//状态 {0：无效 1：有效}
	private String remark;//备注
	private String bankName;//银行名称
	private String payChannel;//支付通道
	private Date beginDate;
	private Date endDate;
	
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getNewBankInfo() {
		return newBankInfo;
	}
	public void setNewBankInfo(String newBankInfo) {
		this.newBankInfo = newBankInfo;
	}
	public String getBankNumber() {
		return bankNumber;
	}
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	public String getnBankCardNo() {
		return nBankCardNo;
	}
	public void setnBankCardNo(String nBankCardNo) {
		this.nBankCardNo = nBankCardNo;
	}
	public String getoBankCardNo() {
		return oBankCardNo;
	}
	public void setoBankCardNo(String oBankCardNo) {
		this.oBankCardNo = oBankCardNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}
