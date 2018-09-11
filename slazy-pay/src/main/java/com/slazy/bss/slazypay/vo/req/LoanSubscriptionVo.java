package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 债权认购Vo
 * @author:	xianzhiqiang
 * @date:	2017年8月9日 下午4:03:24
 */
public class LoanSubscriptionVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = 7388379478285390968L;

	private String loanId;//债权认购编号
	private String accountNo;//账户编号
	private String amount;//出借金额
	private String freezeId;//冻结编号
	
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
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
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	
}
