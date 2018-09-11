package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.math.BigDecimal;

import com.slazy.bss.slazypay.model.BaseModel;

/**
 * 
 * @Description: 子账户明细Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月26日 下午3:58:29
 */
public class SubAccountDetailReqVo extends BaseModel implements Serializable {

	private static final long serialVersionUID = -8407647163138421729L;

	private String bizId;//订单号
	private String unit;//单位
	private String remark;//备注
	private String projectCode;//项目编号
	private String payeeAccNo;//收款人账户编号
	private String payeeAccName;//收款人名称
	private BigDecimal beginAmount;//交易前金额
	private BigDecimal amount;//交易金额
	private BigDecimal endAmount;//交易后金额
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
	private String subType;//子账户类型
	private String lendId;//本手方子账户编号
	private String accountDir;//账务方向:[1:入账,0:出账]
	private String rivalLendId;//对手方子账户编号
	private String rivalSubType;//对手方子账户类型
	private String bizType;//交易类型
	private String batchId;//批次号
	private String messageId;//报文标识号
	private String capitalType;//资金类型
	private String capitalName;//资金名称
	private Long subAccountId;//外键
	private String beginTime;
	private String endTime;
	private String tradeBeginTime;//交易开始时间
	private String tradeEndTime;//交易结束时间
	private String accountNo;//调账使用：本手方账户编号
	private String lendDocNo;//出借服务协议编号
	private String borrowDocNo;//借款服务协议编号
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
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
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
	public BigDecimal getBeginAmount() {
		return beginAmount;
	}
	public void setBeginAmount(BigDecimal beginAmount) {
		this.beginAmount = beginAmount;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public BigDecimal getEndAmount() {
		return endAmount;
	}
	public void setEndAmount(BigDecimal endAmount) {
		this.endAmount = endAmount;
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
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getLendId() {
		return lendId;
	}
	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	public String getAccountDir() {
		return accountDir;
	}
	public void setAccountDir(String accountDir) {
		this.accountDir = accountDir;
	}
	public String getRivalLendId() {
		return rivalLendId;
	}
	public void setRivalLendId(String rivalLendId) {
		this.rivalLendId = rivalLendId;
	}
	public String getRivalSubType() {
		return rivalSubType;
	}
	public void setRivalSubType(String rivalSubType) {
		this.rivalSubType = rivalSubType;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getCapitalType() {
		return capitalType;
	}
	public void setCapitalType(String capitalType) {
		this.capitalType = capitalType;
	}
	public String getCapitalName() {
		return capitalName;
	}
	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}
	public Long getSubAccountId() {
		return subAccountId;
	}
	public void setSubAccountId(Long subAccountId) {
		this.subAccountId = subAccountId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getLendDocNo() {
		return lendDocNo;
	}
	public String getBorrowDocNo() {
		return borrowDocNo;
	}
	public void setLendDocNo(String lendDocNo) {
		this.lendDocNo = lendDocNo;
	}
	public void setBorrowDocNo(String borrowDocNo) {
		this.borrowDocNo = borrowDocNo;
	}
	
}
