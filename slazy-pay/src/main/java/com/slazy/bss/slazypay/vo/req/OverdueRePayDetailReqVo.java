package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 逾期还款明细Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月5日 下午3:11:46
 */
public class OverdueRePayDetailReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -218534647621555526L;
	
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
	private String borrowFreezeId;//借款编号
	private String amount;//自有资金申请金额
	private String wyAmount;//违约金
	private String fxAmount;//罚息金
	private String serviceAmt;//催收服务费
	private String bidProjectCode;//项目编号
	private String offsettingAmt;//冲正金额
	/* 新增字段 */
	private String cancelAmt;  //结清核销金额
	private String noCancelAmt;  //非结清核销金额
	private String tOutAmt;    //表外资金

	
	public String getOffsettingAmt() {
		return offsettingAmt;
	}
	public void setOffsettingAmt(String offsettingAmt) {
		this.offsettingAmt = offsettingAmt;
	}
	public String getBorrowFreezeId() {
		return borrowFreezeId;
	}
	public void setBorrowFreezeId(String borrowFreezeId) {
		this.borrowFreezeId = borrowFreezeId;
	}
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
	public String getDraweeAccName() {
		return draweeAccName;
	}
	public void setDraweeAccName(String draweeAccName) {
		this.draweeAccName = draweeAccName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getWyAmount() {
		return wyAmount;
	}
	public void setWyAmount(String wyAmount) {
		this.wyAmount = wyAmount;
	}
	public String getFxAmount() {
		return fxAmount;
	}
	public void setFxAmount(String fxAmount) {
		this.fxAmount = fxAmount;
	}
	public String getServiceAmt() {
		return serviceAmt;
	}
	public void setServiceAmt(String serviceAmt) {
		this.serviceAmt = serviceAmt;
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
	public String gettOutAmt() {
		return tOutAmt;
	}
	public void settOutAmt(String tOutAmt) {
		this.tOutAmt = tOutAmt;
	}

}
