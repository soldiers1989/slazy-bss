package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 绑卡信息查询Vo
 * @author:	dingyaru
 * @date:	2017年9月29日 下午5:53:50
 */
public class QueryBindBankReqVo extends BaseReqVo  implements Serializable {

	private static final long serialVersionUID = -267113587318079740L;

	private String accountNo;//账户编号
	
	private String nBankCardNo;//银行卡号
	

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getnBankCardNo() {
		return nBankCardNo;
	}

	public void setnBankCardNo(String nBankCardNo) {
		this.nBankCardNo = nBankCardNo;
	}


}
