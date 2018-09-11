package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 余额查询Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月17日 下午3:47:17
 */
public class QueryBalanceReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = 1971666251288503030L;

	private String accountNo;//账户编号
	private String subType;//子账户类型
	private String lendId;//子账户编号
	
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

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	
	
}
