package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 公司补贴
 * @author:	dingyaru
 * @date:	2017年7月25日 下午3:50:06
 */
public class CompanySubsidiesReqVo extends BaseReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4172200042443850756L;
	
	private String accountNo;   //账户编号
	private String amount;		//补贴金额
	private String cashType;	//补贴类型
	private String remark;		//备注
	private String freezeId;//冻结编号
	private String freezeAmt;//冻结金额
	private String subType;//子账户类型
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCashType() {
		return cashType;
	}
	public void setCashType(String cashType) {
		this.cashType = cashType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	public String getFreezeAmt() {
		return freezeAmt;
	}
	public void setFreezeAmt(String freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	
}
