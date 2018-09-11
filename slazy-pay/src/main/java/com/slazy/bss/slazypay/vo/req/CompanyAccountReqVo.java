package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @Description: 公司账户Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月13日 下午2:20:54
 */
public class CompanyAccountReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = 3048764083812574663L;

	private String name;//公司账户名称
	private String pId;//关联ID
	private String status;//账户状态 [0:禁用，1：启用]
	private String accountNo;//账户编号
	private String newBizSystem;//需修改的业务系统标识
	private BigDecimal amount;//余额
	private String remark;//备注
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private String operator;//操作人
	private String beginTime;//起始时间
	private String endTime;//结束时间
	private String bizType;//交易类型
	private String batchId;//批次号
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
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
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNewBizSystem() {
		return newBizSystem;
	}
	public void setNewBizSystem(String newBizSystem) {
		this.newBizSystem = newBizSystem;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
