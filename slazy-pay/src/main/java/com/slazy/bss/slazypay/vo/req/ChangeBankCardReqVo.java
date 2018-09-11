package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 换绑银行卡
 * @author:	dingyaru
 * @date:	2017年7月24日 下午7:02:09
 */
public class ChangeBankCardReqVo extends BaseReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 523270239882978003L;
	
	private String name;        //客户姓名
	private String idCardNo;	//身份证号
	private String accountNo;	//账户编号
	private String mobile;		//新卡绑定手机号
	private String bankId;		//新卡银行编码
	private String province;	//新银行卡开户省
	private String city;		//新卡开户市
	private String newBankInfo;	//新卡银行支行信息
	private String bankNumber;	//开户行号
	private String nBankCardNo;	//新卡卡号
	private String oBankCardNo;	//原卡卡号
	private String bindType;	//绑卡类型
	private String bankName;	//银行名称
	private String payChannel;	//支付通道
	
	
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
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
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
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	@Override
	public String toString() {
		return "ChangeBankCardReqVo [name=" + name + ", idCardNo=" + idCardNo
				+ ", accountNo=" + accountNo + ", mobile=" + mobile
				+ ", bankId=" + bankId + ", province=" + province + ", city="
				+ city + ", newBankInfo=" + newBankInfo + ", bankNumber="
				+ bankNumber + ", nBankCardNo=" + nBankCardNo
				+ ", oBankCardNo=" + oBankCardNo + ", bindType=" + bindType
				+ ", bankName=" + bankName + ", payChannel=" + payChannel + "]";
	}
	

}
