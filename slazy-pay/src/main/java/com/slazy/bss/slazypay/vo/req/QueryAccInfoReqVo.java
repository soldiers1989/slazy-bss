package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 账户基础信息查询Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月17日 下午5:24:50
 */
public class QueryAccInfoReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -4131365866221757662L;
	
	private String accountNo;//账户编号
	private String cerificateType;//证件类型
	private String idCardNo;//证件号码
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCerificateType() {
		return cerificateType;
	}
	public void setCerificateType(String cerificateType) {
		this.cerificateType = cerificateType;
	}
	public String getIdCardNo() {
		return idCardNo;
	}
	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}
	
}
