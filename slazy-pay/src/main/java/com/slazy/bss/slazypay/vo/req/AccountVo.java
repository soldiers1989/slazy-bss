package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 记账VO
 * @author:	xianzhiqiang
 * @date:	2017年6月26日 下午1:57:51
 */
public class AccountVo extends BaseReqVo implements Serializable{
	
	private static final long serialVersionUID = -1069387228601444183L;
	
	private String batchId; //批次号
	
	private String projectCode; //项目编号
	
	private String bizType; //业务类型
	
//	private String tradeState;//交易状态 0:失败 1:成功
	
	private String tradeTime;//交易时间
	
	private List<AccountDetailVo> detail; //记账明细

//	public String getTradeState() {
//		return tradeState;
//	}
//
//	public void setTradeState(String tradeState) {
//		this.tradeState = tradeState;
//	}

	public String getBatchId() {
		return batchId;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public List<AccountDetailVo> getDetail() {
		return detail;
	}

	public void setDetail(List<AccountDetailVo> detail) {
		this.detail = detail;
	}
	
	
	
}
