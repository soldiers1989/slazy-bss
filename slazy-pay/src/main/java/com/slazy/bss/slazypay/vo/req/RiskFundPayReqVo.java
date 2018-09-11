package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Description: 风险金垫付Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 上午11:24:11
 */
public class RiskFundPayReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -6069927061920488386L;
//	private String batchId;//批次号
	private String bidProjectCode;//项目编号
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
	private String borrowFreezeId;//借款编号
	private String offsettingAmt;//冲正金额
	private String totalAmount;//还款总金额
	private List<RiskFundPayDetailReqVo> detail; //风险金垫付明细
	private String amount;//垫付金额
	
//	public String getBatchId() {
//		return batchId;
//	}
//	public void setBatchId(String batchId) {
//		this.batchId = batchId;
//	}
	public List<RiskFundPayDetailReqVo> getDetail() {
		return detail;
	}
	public void setDetail(List<RiskFundPayDetailReqVo> detail) {
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
	public String getBorrowFreezeId() {
		return borrowFreezeId;
	}
	public String getOffsettingAmt() {
		return offsettingAmt;
	}
	public String getTotalAmount() {
		return totalAmount;
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
	public void setBorrowFreezeId(String borrowFreezeId) {
		this.borrowFreezeId = borrowFreezeId;
	}
	public void setOffsettingAmt(String offsettingAmt) {
		this.offsettingAmt = offsettingAmt;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
