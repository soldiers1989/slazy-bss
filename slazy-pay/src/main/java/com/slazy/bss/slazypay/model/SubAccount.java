package com.slazy.bss.slazypay.model;

import java.math.BigDecimal;


/**
 * 
 * @Description: 子账户模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午4:56:57
 */
public class SubAccount extends BaseModel {

	private static final long serialVersionUID = -8387526720979145947L;

	private String accountNo;//账户编号
	private String lendId;//出借编号
	private BigDecimal amount;//账户金额
	private String subType;//子账户类型
	private String status;//状态
	private String operator;//操作人
	private String unit;//单位
	private String remark;//备注
	private String value1;//存放MD5加密值
	private BigDecimal lockAmount;//锁定金额
	
	public BigDecimal getLockAmount() {
		return lockAmount;
	}
	public void setLockAmount(BigDecimal lockAmount) {
		this.lockAmount = lockAmount;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getLendId() {
		return lendId;
	}
	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
