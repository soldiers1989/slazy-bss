package com.slazy.bss.slazypay.model;

import java.util.Date;

/**
 * 
 * @Description: 审核Vo
 * @author:	xianzhiqiang
 * @date:	2017年9月14日 上午1:19:00
 */
public class Auditor extends BaseModel {

	private static final long serialVersionUID = 5006387955574212304L;

    private String unit;//单位

    private String reasonRemark;//调账原因

    private String bizId;//订单号

    private String payeeAccNo;//本手方账户编号

    private String payeeAccName;//本手方用户名称

    private String lendId;//本手方子账户编号

    private String amount;//金额

    private String accountDir;//资金方向

    private String draweeAccNo;//对手方账户编号

    private String draweeAccName;//对手方用户名称

    private String rivalLendId;//对手方子账户编号

    private String applicant;//申请操作人

    private Date auditerCreateTime;//审核时间

    private String auditerOperator;//审核操作人

    private String auditStatus;//审核状态

    private String auditerRemark;//审批意见

    private String remark;//备注

    private String subType;//本手方子账户类型
    
    private String rivalSubType;//对手方子账户类型
    
    private String reasonBeginTime;//调账申请开始时间
    
    private String reasonEndTime;//调账申请结束时间
    
    private String auditerBeginTime;//审核开始时间
    
    private String auditerEndTime;//审核结束时间

	public String getReasonBeginTime() {
		return reasonBeginTime;
	}

	public void setReasonBeginTime(String reasonBeginTime) {
		this.reasonBeginTime = reasonBeginTime;
	}

	public String getReasonEndTime() {
		return reasonEndTime;
	}

	public void setReasonEndTime(String reasonEndTime) {
		this.reasonEndTime = reasonEndTime;
	}

	public String getAuditerBeginTime() {
		return auditerBeginTime;
	}

	public void setAuditerBeginTime(String auditerBeginTime) {
		this.auditerBeginTime = auditerBeginTime;
	}

	public String getAuditerEndTime() {
		return auditerEndTime;
	}

	public void setAuditerEndTime(String auditerEndTime) {
		this.auditerEndTime = auditerEndTime;
	}

	public String getRivalSubType() {
		return rivalSubType;
	}

	public void setRivalSubType(String rivalSubType) {
		this.rivalSubType = rivalSubType;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getReasonRemark() {
		return reasonRemark;
	}

	public void setReasonRemark(String reasonRemark) {
		this.reasonRemark = reasonRemark;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
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

	public String getLendId() {
		return lendId;
	}

	public void setLendId(String lendId) {
		this.lendId = lendId;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAccountDir() {
		return accountDir;
	}

	public void setAccountDir(String accountDir) {
		this.accountDir = accountDir;
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

	public String getRivalLendId() {
		return rivalLendId;
	}

	public void setRivalLendId(String rivalLendId) {
		this.rivalLendId = rivalLendId;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public Date getAuditerCreateTime() {
		return auditerCreateTime;
	}

	public void setAuditerCreateTime(Date auditerCreateTime) {
		this.auditerCreateTime = auditerCreateTime;
	}

	public String getAuditerOperator() {
		return auditerOperator;
	}

	public void setAuditerOperator(String auditerOperator) {
		this.auditerOperator = auditerOperator;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditerRemark() {
		return auditerRemark;
	}

	public void setAuditerRemark(String auditerRemark) {
		this.auditerRemark = auditerRemark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

}
