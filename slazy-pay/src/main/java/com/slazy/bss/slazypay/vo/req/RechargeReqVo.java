package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

/**
 * 
 * @Description:充值
 * @author:	dingyaru
 * @date:	2017年7月5日 下午3:03:05
 */
public class RechargeReqVo extends BaseReqVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1495514196200662893L;
	
	private String tranAmount;  //交易金额
	private String accountNo;   //账户编号
	private String name;        //客户姓名
	private String payChannel;  //支付类型 ：16.微信支付  17.支付宝支付
	private String bankCardNo;   //银行卡号
	private String tradeTime;   //交易时间
	private String feeAmount;   //手续费：手续费内扣
	private String serviceFee;  //服务费
	
	public String getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}
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
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
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
