package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description:提现
 * @author:	dingyaru
 * @date:	2017年7月5日 下午3:23:45
 */
public class WithdrawReqVo extends BaseReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4872789059877108752L;
	
	private String tranAmount;  //交易金额
	private String accountNo;   //账户编号
	private String name;        //客户姓名
	private String withdrawType;   //提现类型
	private String bankCardNo;   //银行卡号
	private String tradeTime;   //交易时间
	private String feeAmount;   //手续费：手续费内扣
	
	public String getTranAmount() {
		return tranAmount;
	}
	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWithdrawType() {
		return withdrawType;
	}
	public void setWithdrawType(String withdrawType) {
		this.withdrawType = withdrawType;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	

}
