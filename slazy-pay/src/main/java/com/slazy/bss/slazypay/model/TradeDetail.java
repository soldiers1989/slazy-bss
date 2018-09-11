package com.slazy.bss.slazypay.model;

import java.math.BigDecimal;


/**
 * 
 * @Description: 交易记录明细
 * @author:	xianzhiqiang
 * @date:	2017年6月19日 下午5:27:43
 */
public class TradeDetail extends BaseModel {

	private static final long serialVersionUID = 6873204062557568326L;

	private String bizId;//订单号
	private String accountNo;//账户编号
	private String remark;//备注
	private String name;//客户姓名
	private String subType;//子账户类型
	private String capitalType;//资金类型
	private String capitalName;//资金名称
	private String lendId;//子账户编号
	private String accountDir;//资金方向
	private BigDecimal amount;//交易金额
	private String rivalAccountNo;//对手方账户编号
	private String rivalName;//对手方客户姓名
	private String rivalSubType;//对手方子账户类型
	private String rivalLendld;//对手方子账户编号
	private Long tradeId;//外键
	
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRivalAccountNo() {
		return rivalAccountNo;
	}
	public void setRivalAccountNo(String rivalAccountNo) {
		this.rivalAccountNo = rivalAccountNo;
	}
	public String getRivalName() {
		return rivalName;
	}
	public void setRivalName(String rivalName) {
		this.rivalName = rivalName;
	}
	public String getRivalSubType() {
		return rivalSubType;
	}
	public void setRivalSubType(String rivalSubType) {
		this.rivalSubType = rivalSubType;
	}
	public String getRivalLendld() {
		return rivalLendld;
	}
	public void setRivalLendld(String rivalLendld) {
		this.rivalLendld = rivalLendld;
	}
	public Long getTradeId() {
		return tradeId;
	}
	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}
	
	

}
