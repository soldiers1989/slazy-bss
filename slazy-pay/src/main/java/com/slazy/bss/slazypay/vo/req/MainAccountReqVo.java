package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * @Description: 用户账户主账户Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月25日 下午5:46:09
 */
public class MainAccountReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = 7676428200807200742L;
	
	private String accountNo;//账户编号
	
	private String status;//状态
	private String bizId;//订单号
	private String name;//姓名
	private String cerificateType;//证件类型:[01-身份证,02-护照,03-军人证,04-回乡证,05-台胞证]
	private String certificateNo;//证件号码
	private String customerType;//客户类别:[普通个人：0]
	private String mobile;//手机号码:[11位数字]
	private String customerIdType;//客户身份类型:[1:出借人、0:借款人、2:在职员工、3：离职员工]
	private String pId;//公司账户关联使用
	private Date openAccountTime;//开户时间
	private String operator;//操作人
	private String remark;//备注
	private String beginTime;//起始时间
	private String endTime;//结束时间
	private Date createTime;//创建时间
	private Date modifyTime;//修改时间
	private Long sortidx;//排序号
	private String activationType;//激活状态[ 0:未激活  1:已激活]
	private String reservedMobile;//预留手机号
	
	public String getActivationType() {
		return activationType;
	}
	public void setActivationType(String activationType) {
		this.activationType = activationType;
	}
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCerificateType() {
		return cerificateType;
	}
	public void setCerificateType(String cerificateType) {
		this.cerificateType = cerificateType;
	}
	public String getCertificateNo() {
		return certificateNo;
	}
	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}
	public String getCustomerType() {
		return customerType;
	}
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public Date getOpenAccountTime() {
		return openAccountTime;
	}
	public void setOpenAccountTime(Date openAccountTime) {
		this.openAccountTime = openAccountTime;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getReservedMobile() {
		return reservedMobile;
	}
	public void setReservedMobile(String reservedMobile) {
		this.reservedMobile = reservedMobile;
	}
	
}
