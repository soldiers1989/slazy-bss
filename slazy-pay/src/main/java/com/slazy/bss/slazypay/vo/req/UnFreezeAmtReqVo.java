package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 资金解冻请求参数
 * @author:	dingyaru
 * @date:	2017年6月27日 下午4:23:44
 */
public class UnFreezeAmtReqVo extends BaseReqVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7839577778022660879L;
	
	private String accountNo;	//账户编号
	private String freezeId;//冻结编号； 账务系统子账户编号
	private String amount;	//解冻金额：不包含红包金额
	private String freezeType;	//冻结类型 42：提现解冻、 44:还款解冻、 22:普通解冻、  52:出借解冻
//	private String lendDocNo;	//出借服务协议编号;  冻结类型：为出借解冻时必填
//	private String bonusAmt;	//红包出借金额  ;  默认值：0 (进冻结子账户)
	private String remark;	//备注
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFreezeType() {
		return freezeType;
	}
	public void setFreezeType(String freezeType) {
		this.freezeType = freezeType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


}
