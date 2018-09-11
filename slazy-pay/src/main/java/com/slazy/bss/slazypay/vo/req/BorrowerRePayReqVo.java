package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Description: 正常/提前还款Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月4日 下午7:12:57
 */
public class BorrowerRePayReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -8157468312858396864L;
//	private String batchId;//批次号
	private String bidProjectCode;//项目编号
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
	private String backServiceAmt;//退还服务费
	private String takeServiceAmt;//收取服务费
	private String offsettingAmt;//冲正金额
	private String earlyRepayFlag;//6:提前还款 4：正常还款
	private String borrowFreezeId;//借款编号
	private String totalAmount;//还款总金额
	private String cancelAmt;//结清核销金额
	private String noCancelAmt;//非结清核销金额
	private List<BorrowerRePayDetailReqVo> detail; //还款明细
	
//	public String getBatchId() {
//		return batchId;
//	}
//	public void setBatchId(String batchId) {
//		this.batchId = batchId;
//	}
	public List<BorrowerRePayDetailReqVo> getDetail() {
		return detail;
	}
	public void setDetail(List<BorrowerRePayDetailReqVo> detail) {
		this.detail = detail;
	}
	public String getBidProjectCode() {
		return bidProjectCode;
	}
	public String getDraweeAccNo() {
		return draweeAccNo;
	}
	public String getDraweeAccName() {
		return draweeAccName;
	}
	public String getBackServiceAmt() {
		return backServiceAmt;
	}
	public String getTakeServiceAmt() {
		return takeServiceAmt;
	}
	public String getOffsettingAmt() {
		return offsettingAmt;
	}
	public String getEarlyRepayFlag() {
		return earlyRepayFlag;
	}
	public String getBorrowFreezeId() {
		return borrowFreezeId;
	}
	public void setBidProjectCode(String bidProjectCode) {
		this.bidProjectCode = bidProjectCode;
	}
	public void setDraweeAccNo(String draweeAccNo) {
		this.draweeAccNo = draweeAccNo;
	}
	public void setDraweeAccName(String draweeAccName) {
		this.draweeAccName = draweeAccName;
	}
	public void setBackServiceAmt(String backServiceAmt) {
		this.backServiceAmt = backServiceAmt;
	}
	public void setTakeServiceAmt(String takeServiceAmt) {
		this.takeServiceAmt = takeServiceAmt;
	}
	public void setOffsettingAmt(String offsettingAmt) {
		this.offsettingAmt = offsettingAmt;
	}
	public void setEarlyRepayFlag(String earlyRepayFlag) {
		this.earlyRepayFlag = earlyRepayFlag;
	}
	public void setBorrowFreezeId(String borrowFreezeId) {
		this.borrowFreezeId = borrowFreezeId;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getCancelAmt() {
		return cancelAmt;
	}
	public String getNoCancelAmt() {
		return noCancelAmt;
	}
	public void setCancelAmt(String cancelAmt) {
		this.cancelAmt = cancelAmt;
	}
	public void setNoCancelAmt(String noCancelAmt) {
		this.noCancelAmt = noCancelAmt;
	}
	
}
