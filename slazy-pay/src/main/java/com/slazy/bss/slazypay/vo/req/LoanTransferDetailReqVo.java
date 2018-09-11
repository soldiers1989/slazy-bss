package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 债权转让明细Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午2:28:12
 */
public class LoanTransferDetailReqVo implements Serializable {

	private static final long serialVersionUID = -3894667219054495211L;
	private String bizId;//交易订单号
	private String alienatorName;//转让人姓名
	private String alienatorAccountNo;//转让人账号
	private String projectCode;//项目编号
	private String amount;//转让金额
	private String serviceAmt;//服务费
	private String assigneeName;//受让人姓名
	private String assigneeAccountNo;//受让人账号
	private String bonusAmt;//红包金额
	private String bonusNo;//红包编号
	private String freezeId;//冻结编号
	private String signOutAmt;//退出服务费
	private String alienatorFreezeId;//转让人冻结编号
	
	public String getAlienatorFreezeId() {
		return alienatorFreezeId;
	}
	public void setAlienatorFreezeId(String alienatorFreezeId) {
		this.alienatorFreezeId = alienatorFreezeId;
	}
	public String getSignOutAmt() {
		return signOutAmt;
	}
	public void setSignOutAmt(String signOutAmt) {
		this.signOutAmt = signOutAmt;
	}
	public String getFreezeId() {
		return freezeId;
	}
	public void setFreezeId(String freezeId) {
		this.freezeId = freezeId;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getAlienatorName() {
		return alienatorName;
	}
	public void setAlienatorName(String alienatorName) {
		this.alienatorName = alienatorName;
	}
	public String getAlienatorAccountNo() {
		return alienatorAccountNo;
	}
	public void setAlienatorAccountNo(String alienatorAccountNo) {
		this.alienatorAccountNo = alienatorAccountNo;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getServiceAmt() {
		return serviceAmt;
	}
	public void setServiceAmt(String serviceAmt) {
		this.serviceAmt = serviceAmt;
	}
	public String getAssigneeName() {
		return assigneeName;
	}
	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}
	public String getAssigneeAccountNo() {
		return assigneeAccountNo;
	}
	public void setAssigneeAccountNo(String assigneeAccountNo) {
		this.assigneeAccountNo = assigneeAccountNo;
	}
	public String getBonusAmt() {
		return bonusAmt;
	}
	public void setBonusAmt(String bonusAmt) {
		this.bonusAmt = bonusAmt;
	}
	public String getBonusNo() {
		return bonusNo;
	}
	public void setBonusNo(String bonusNo) {
		this.bonusNo = bonusNo;
	}

}
