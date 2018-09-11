package com.slazy.bss.slazypay.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @Description: 交易记录模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:17:32
 */
public class Trade extends BaseModel {

	private static final long serialVersionUID = -5856980543125953588L;

	private String batchId;//批次号
	private String bizId;//订单号
	private String remark;//备注
	private String status;//入账状态
	private String bizType;//业务类型
	private BigDecimal amount;//交易总金额
	private Date tradeTime;//交易时间
	private String beginTime;//开始时间
	private String endTime;//结束时间
	private String borrowDocNo;//借款服务协议编号
	private String lendDocNo;//出借服务协议编号
	private String tradeBeginTime;//交易开始时间
	private String tradeEndTime;//交易结束时间
	
	public String getTradeBeginTime() {
		return tradeBeginTime;
	}
	public void setTradeBeginTime(String tradeBeginTime) {
		this.tradeBeginTime = tradeBeginTime;
	}
	public String getTradeEndTime() {
		return tradeEndTime;
	}
	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}
	public String getBorrowDocNo() {
		return borrowDocNo;
	}
	public void setBorrowDocNo(String borrowDocNo) {
		this.borrowDocNo = borrowDocNo;
	}
	public String getLendDocNo() {
		return lendDocNo;
	}
	public void setLendDocNo(String lendDocNo) {
		this.lendDocNo = lendDocNo;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	
}
