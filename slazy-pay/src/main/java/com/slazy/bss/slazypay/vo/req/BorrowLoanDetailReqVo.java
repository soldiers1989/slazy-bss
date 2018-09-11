package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 放款明细Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月3日 下午3:26:15
 */
public class BorrowLoanDetailReqVo implements Serializable {

	private static final long serialVersionUID = -6258475965554026274L;

//	private String bizId;//订单号
//	private String payeeAccNo;//收款人账户编号
//	private String payeeAccName;//收款人名称
//	private String safeAmt;//保险费
//	private String serviceAmt;//服务费
//	private String borrowDocNo;//借款服务协议编号
	
	private String amount;//交易金额
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
//	private String cashAmount;//现金出借金额
	private String lendId;//投标编号
//	private String bonusAmt;//红包出借金额
//	private String bonusNo;//红包编号
	
	
	
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
//	public String getCashAmount() {
//		return cashAmount;
//	}
//	public void setCashAmount(String cashAmount) {
//		this.cashAmount = cashAmount;
//	}
//	public String getBonusAmt() {
//		return bonusAmt;
//	}
//	public void setBonusAmt(String bonusAmt) {
//		this.bonusAmt = bonusAmt;
//	}
//	public String getBonusNo() {
//		return bonusNo;
//	}
//	public void setBonusNo(String bonusNo) {
//		this.bonusNo = bonusNo;
//	}
	
	

}
