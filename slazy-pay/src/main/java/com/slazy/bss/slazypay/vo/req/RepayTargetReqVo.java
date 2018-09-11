package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Description:标的还款
 * @author:	wangyanan
 * @date:	2017-11-20 上午10:59:37
 */
public class RepayTargetReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -8157468312858396864L;
	private String bidProjectCode;//项目编号
	private String draweeAccNo;//付款人账户编号
	private String draweeAccName;//付款人名称
	private String borrowFreezeId;//还款冻结编号
	private List<BorrowerRePayDetailReqVo> detail; //还款明细
	
	public String getBidProjectCode() {
		return bidProjectCode;
	}
	public String getDraweeAccNo() {
		return draweeAccNo;
	}
	public String getDraweeAccName() {
		return draweeAccName;
	}
	public String getBorrowFreezeId() {
		return borrowFreezeId;
	}
	public List<BorrowerRePayDetailReqVo> getDetail() {
		return detail;
	}
	public void setBidProjectCode(String bidProjectCode) {
		this.bidProjectCode = bidProjectCode;
	}
	public void setDraweeAccNo(String draweeAccNo) {
		this.draweeAccNo = draweeAccNo;
	}
	public void setDraweeAccName(String draweeAccName) {
		this.draweeAccName = draweeAccName;
	}
	public void setBorrowFreezeId(String borrowFreezeId) {
		this.borrowFreezeId = borrowFreezeId;
	}
	public void setDetail(List<BorrowerRePayDetailReqVo> detail) {
		this.detail = detail;
	}
	
	
}
