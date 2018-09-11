package com.slazy.bss.slazypay.vo.res;

import java.io.Serializable;

public class BalanceDetailResVo implements Serializable {

	private static final long serialVersionUID = 7459942644589669466L;

	private String lendId;//子账户编号
	private String amount;//余额
	
	public String getLendId() {
		return lendId;
	}
	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
