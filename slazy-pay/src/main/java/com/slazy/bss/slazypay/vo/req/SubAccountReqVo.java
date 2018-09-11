package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 
 * @Description: 子账户Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月26日 上午10:48:10
 */
public class SubAccountReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = -4608292772991760324L;

	private String accountNo;//账户编号
	private String lendId;//出借编号
	private BigDecimal amount;//账户金额
	private String subType;//子账户类型
	private String status;//状态
	private String operator;//操作人
	private String unit;//单位
	private String remark;//备注
	private String value1;//存放MD5加密值
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private Long sortidx;//排序号
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Long getSortidx() {
		return sortidx;
	}
	public void setSortidx(Long sortidx) {
		this.sortidx = sortidx;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getLendId() {
		return lendId;
	}
	public void setLendId(String lendId) {
		this.lendId = lendId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
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
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
}
