package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description:还款预处理明细vo
 * @author:	xianzhiqiang
 * @date:	2017年11月17日 下午6:05:47
 */
public class RePayDetailPreReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -1277782325389575814L;

	private String bidProjectCode;//项目编号
	private String draweeAccNo;//付款人账户编号
	private String backServiceAmt;//退还服务费
	private String takeServiceAmt;//收取服务费
	private String offsettingAmt;//冲正金额
	private String earlyRepayFlag;//6:提前还款 4：正常还款
	private String borrowFreezeId;//还款冻结编号
	private String repayAmt;//还款金额
	private String cancelAmt;//结清核销金额
	private String noCancelAmt;//非结清核销金额
	
	public String getBidProjectCode() {
		return bidProjectCode;
	}
	public void setBidProjectCode(String bidProjectCode) {
		this.bidProjectCode = bidProjectCode;
	}
	public String getDraweeAccNo() {
		return draweeAccNo;
	}
	public void setDraweeAccNo(String draweeAccNo) {
		this.draweeAccNo = draweeAccNo;
	}
	public String getBackServiceAmt() {
		return backServiceAmt;
	}
	public void setBackServiceAmt(String backServiceAmt) {
		this.backServiceAmt = backServiceAmt;
	}
	public String getTakeServiceAmt() {
		return takeServiceAmt;
	}
	public void setTakeServiceAmt(String takeServiceAmt) {
		this.takeServiceAmt = takeServiceAmt;
	}
	public String getOffsettingAmt() {
		return offsettingAmt;
	}
	public void setOffsettingAmt(String offsettingAmt) {
		this.offsettingAmt = offsettingAmt;
	}
	public String getEarlyRepayFlag() {
		return earlyRepayFlag;
	}
	public void setEarlyRepayFlag(String earlyRepayFlag) {
		this.earlyRepayFlag = earlyRepayFlag;
	}
	public String getBorrowFreezeId() {
		return borrowFreezeId;
	}
	public void setBorrowFreezeId(String borrowFreezeId) {
		this.borrowFreezeId = borrowFreezeId;
	}
	public String getRepayAmt() {
		return repayAmt;
	}
	public void setRepayAmt(String repayAmt) {
		this.repayAmt = repayAmt;
	}
	public String getCancelAmt() {
		return cancelAmt;
	}
	public void setCancelAmt(String cancelAmt) {
		this.cancelAmt = cancelAmt;
	}
	public String getNoCancelAmt() {
		return noCancelAmt;
	}
	public void setNoCancelAmt(String noCancelAmt) {
		this.noCancelAmt = noCancelAmt;
	}
	
}
