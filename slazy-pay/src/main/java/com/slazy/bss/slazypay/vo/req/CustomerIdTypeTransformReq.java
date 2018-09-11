package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 客户身份转换VO
 * @author:	xianzhiqiang
 * @date:	2017年8月10日 下午7:41:05
 */
public class CustomerIdTypeTransformReq extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = 6420927760708249291L;
	
	private String accountNo;//账户编号
	
	private String customerIdType;//用户角色
	
	private String transformType;//转换类型

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getCustomerIdType() {
		return customerIdType;
	}

	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}

	public String getTransformType() {
		return transformType;
	}

	public void setTransformType(String transformType) {
		this.transformType = transformType;
	}

}
