package com.slazy.bss.slazypay.model;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 
 * @Description: 交易明细模型
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:15:08
 */
public class SubAccountDetail extends BaseModel {

	private static final long serialVersionUID = 3093547040215922334L;

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
	private String lendDocNo;//出借服务协议编号
	private String borrowDocNo;//借款服务协议编号
	private Date tradeTime;//交易时间
	
	public Date getTradeTime() {
		return tradeTime;
	}
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
	public String getLendDocNo() {
		return lendDocNo;
	}
	public void setLendDocNo(String lendDocNo) {
		this.lendDocNo = lendDocNo;
	}
	public String getBorrowDocNo() {
		return borrowDocNo;
	}
	public void setBorrowDocNo(String borrowDocNo) {
		this.borrowDocNo = borrowDocNo;
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
	
}
