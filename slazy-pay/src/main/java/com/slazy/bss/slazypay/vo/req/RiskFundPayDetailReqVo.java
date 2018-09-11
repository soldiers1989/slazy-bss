package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 风险金垫付明细Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 上午11:27:15
 */
public class RiskFundPayDetailReqVo implements Serializable {

	private static final long serialVersionUID = 8698561878744786520L;

//	private String bizId;//交易订单号
//	private String bidProjectCode;//项目编号
//	private String draweeAccNo;//付款人账户编号
//	private String draweeAccName;//付款人名称
//	private String borrowFreezeId;//借款编号
//	private String offsettingAmt;//冲正金额
	
	private String payeeAccNo;//收款人账户编号
	private String payeeAccName;//收款人名称
	private String amount;//补偿金额
//	private String reAmount;//出借复投金额
	private String takeAmount;//出借可取现金额
//	private String serviceAmt;//服务费
	private String lendFreezeId;//出借编号

	public String getLendFreezeId() {
		return lendFreezeId;
	}
	public void setLendFreezeId(String lendFreezeId) {
		this.lendFreezeId = lendFreezeId;
	}
//	public String getOffsettingAmt() {
//		return offsettingAmt;
//	}
//	public void setOffsettingAmt(String offsettingAmt) {
//		this.offsettingAmt = offsettingAmt;
//	}
//	public String getBorrowFreezeId() {
//		return borrowFreezeId;
//	}
//	public void setBorrowFreezeId(String borrowFreezeId) {
//		this.borrowFreezeId = borrowFreezeId;
//	}
	public String getPayeeAccNo() {
		return payeeAccNo;
	}
	public void setPayeeAccNo(String payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}
	public String getPayeeAccName() {
		return payeeAccName;
	}
	public void setPayeeAccName(String payeeAccName) {
		this.payeeAccName = payeeAccName;
	}
//	public String getBizId() {
//		return bizId;
//	}
//	public void setBizId(String bizId) {
//		this.bizId = bizId;
//	}
//	public String getDraweeAccNo() {
//		return draweeAccNo;
//	}
//	public void setDraweeAccNo(String draweeAccNo) {
//		this.draweeAccNo = draweeAccNo;
//	}
//	public String getDraweeAccName() {
//		return draweeAccName;
//	}
//	public void setDraweeAccName(String draweeAccName) {
//		this.draweeAccName = draweeAccName;
//	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
//	public String getReAmount() {
//		return reAmount;
//	}
//	public void setReAmount(String reAmount) {
//		this.reAmount = reAmount;
//	}
	public String getTakeAmount() {
		return takeAmount;
	}
	public void setTakeAmount(String takeAmount) {
		this.takeAmount = takeAmount;
	}
//	public String getBidProjectCode() {
//		return bidProjectCode;
//	}
//	public void setBidProjectCode(String bidProjectCode) {
//		this.bidProjectCode = bidProjectCode;
//	}
//	public String getServiceAmt() {
//		return serviceAmt;
//	}
//	public void setServiceAmt(String serviceAmt) {
//		this.serviceAmt = serviceAmt;
//	}
	
}
