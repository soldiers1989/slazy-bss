package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description:客户信息变更
 * @author:	dingyaru
 * @date:	2017年11月28日 下午4:32:06
 */
public class UpdateAccInfoVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -8799010381175992339L;
	
	private String accountNo;//账户编号
	private String mobile;//手机号码:[11位数字]
	
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

}
