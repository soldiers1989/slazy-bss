package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 放款Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月3日 下午3:17:40
 */
public class BorrowLoanReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -247685410314397137L;

//	private String batchId;//批次号
	private String projectCode;//项目编号
	private String payeeAccNo;//收款人账户编号
	private String payeeAccName;//收款人名称
	private String safeAmt;//保险费
	private String serviceAmt;//服务费
	private String borrowDocNo;//借款服务协议编号
	private String totalAmount;//放款总金额
	private List<BorrowLoanDetailReqVo> detail; //放款明细
	
//	public String getBatchId() {
//		return batchId;
//	}
//	public void setBatchId(String batchId) {
//		this.batchId = batchId;
//	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public List<BorrowLoanDetailReqVo> getDetail() {
		return detail;
	}
	
	public String getPayeeAccNo() {
		return payeeAccNo;
	}
	public String getPayeeAccName() {
		return payeeAccName;
	}
	public String getSafeAmt() {
		return safeAmt;
	}
	public String getServiceAmt() {
		return serviceAmt;
	}
	public String getBorrowDocNo() {
		return borrowDocNo;
	}
	public void setPayeeAccNo(String payeeAccNo) {
		this.payeeAccNo = payeeAccNo;
	}
	public void setPayeeAccName(String payeeAccName) {
		this.payeeAccName = payeeAccName;
	}
	public void setSafeAmt(String safeAmt) {
		this.safeAmt = safeAmt;
	}
	public void setServiceAmt(String serviceAmt) {
		this.serviceAmt = serviceAmt;
	}
	public void setBorrowDocNo(String borrowDocNo) {
		this.borrowDocNo = borrowDocNo;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setDetail(List<BorrowLoanDetailReqVo> detail) {
		this.detail = detail;
	}
	
	
	
}
