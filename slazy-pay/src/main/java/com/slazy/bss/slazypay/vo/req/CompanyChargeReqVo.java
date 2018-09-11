package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description:公司收费
 * @author:	dingyaru
 * @date:	2017年12月12日 下午2:30:44
 */
public class CompanyChargeReqVo extends BaseReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5458588395135464988L;
	
	private String accountNo;	//账户编号
	private String subType;	//子账户类型[0:现金子账户    8:普通冻结子账户 13：出借冻结子账户]
	private String lendId;	//子账户编号[子账户类型为现金账户时不需要填写]
	private String amount;	//收费金额
	
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
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
