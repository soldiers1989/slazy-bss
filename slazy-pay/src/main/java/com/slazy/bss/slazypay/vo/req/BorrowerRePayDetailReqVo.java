package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;

public class BorrowerRePayDetailReqVo implements Serializable {

	private static final long serialVersionUID = 6384317144715428906L;

//	private String bizId;//订单号
//	private String bidProjectCode;//项目编号
//	private String draweeAccNo;//付款人账户编号
//	private String draweeAccName;//付款人名称
//	private String backServiceAmt;//退还服务费
//	private String takeServiceAmt;//收取服务费
//	private String offsettingAmt;//冲正金额
//	private String earlyRepayFlag;//6:提前还款 4：正常还款
//	private String borrowFreezeId;//借款编号
	
	
	private String payeeAccNo;//收款人账户编号
	private String payeeAccName;//收款人名称
	private String amount;//出借金额    = 复投+可取现
	private String takeAmount;//可取现金额
	private String lendFreezeId;//出借编号
//	private String reAmount;//复投金额
//	private String compensationAmt;//补偿金
	
	
//	public String getOffsettingAmt() {
//		return offsettingAmt;
//	}
//	public void setOffsettingAmt(String offsettingAmt) {
//		this.offsettingAmt = offsettingAmt;
//	}
	public String getLendFreezeId() {
		return lendFreezeId;
	}
	public void setLendFreezeId(String lendFreezeId) {
		this.lendFreezeId = lendFreezeId;
	}
//	public String getBorrowFreezeId() {
//		return borrowFreezeId;
//	}
//	public void setBorrowFreezeId(String borrowFreezeId) {
//		this.borrowFreezeId = borrowFreezeId;
//	}
//	public String getCompensationAmt() {
//		return compensationAmt;
//	}
//	public void setCompensationAmt(String compensationAmt) {
//		this.compensationAmt = compensationAmt;
//	}
//	public String getBizId() {
//		return bizId;
//	}
//	public void setBizId(String bizId) {
//		this.bizId = bizId;
//	}
//	public String getBidProjectCode() {
//		return bidProjectCode;
//	}
//	public void setBidProjectCode(String bidProjectCode) {
//		this.bidProjectCode = bidProjectCode;
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
//	public String getBackServiceAmt() {
//		return backServiceAmt;
//	}
//	public void setBackServiceAmt(String backServiceAmt) {
//		this.backServiceAmt = backServiceAmt;
//	}
//	public String getTakeServiceAmt() {
//		return takeServiceAmt;
//	}
//	public void setTakeServiceAmt(String takeServiceAmt) {
//		this.takeServiceAmt = takeServiceAmt;
//	}
//	public String getEarlyRepayFlag() {
//		return earlyRepayFlag;
//	}
//	public void setEarlyRepayFlag(String earlyRepayFlag) {
//		this.earlyRepayFlag = earlyRepayFlag;
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
}
