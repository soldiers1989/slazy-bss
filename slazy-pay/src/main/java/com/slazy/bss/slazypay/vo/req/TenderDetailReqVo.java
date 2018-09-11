package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description:投标详情Vo/撤销Vo
 * @author:	wangyanan
 * @date:	2017-11-20 上午10:01:38
 */
public class TenderDetailReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = -6201894233211550161L;
	private String lendId;//投标编号
	private String accountNo;//账户编号
	private String amount;//出借金额
	private String bonusAmt;//红包出借金额
//	private String bonusNo;//红包编号
	private String freezeId;//冻结编号
	
	public String getLendId() {
		return lendId;
	}
	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	public String getBonusAmt() {
		return bonusAmt;
	}
	public void setBonusAmt(String bonusAmt) {
		this.bonusAmt = bonusAmt;
	}
//	public String getBonusNo() {
//		return bonusNo;
//	}
//	public void setBonusNo(String bonusNo) {
//		this.bonusNo = bonusNo;
//	}
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

	
}
