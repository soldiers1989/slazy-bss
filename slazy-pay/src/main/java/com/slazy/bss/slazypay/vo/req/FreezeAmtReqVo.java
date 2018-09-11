package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description: 资金冻结请求参数
 * @author:	dingyaru
 * @date:	2017年6月27日 下午4:06:45
 */
public class FreezeAmtReqVo extends BaseReqVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2807554203731384195L;
	
	private String accountNo;	//账户编号
	private String freezeId;//冻结编号；账务系统子账户编号
	private String amount;	//冻结金额：不包含红包金额
	private String freezeType;	//冻结类型 40:提现冻结 43:还款冻结 10.普通冻结、51:出借冻结
//	private String lendDocNo;	//出借服务协议编号;  冻结类型：为出借冻结时必填
//	private String bonusAmt;	//红包出借金额  ;  默认值：0 (进冻结子账户)
	private String remark;	//备注
	private String ptFreezeId;	//普通冻结编号[ 冻结类型为2:还款冻结时使用（ptFreezeId不为空时，资金从该普通冻结账户出账到还款冻结账户）]
	
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
	public String getPtFreezeId() {
		return ptFreezeId;
	}
	public void setPtFreezeId(String ptFreezeId) {
		this.ptFreezeId = ptFreezeId;
	}

}
