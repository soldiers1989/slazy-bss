package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Description: 正常/提前还款预处理vo
 * @author:	xianzhiqiang
 * @date:	2017年11月17日 下午6:03:02
 */
public class RePayPreReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -3288089680879688445L;

	private String batchId;//批次号
	
	private List<RePayDetailPreReqVo> detail; //还款预处理明细
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public List<RePayDetailPreReqVo> getDetail() {
		return detail;
	}
	public void setDetail(List<RePayDetailPreReqVo> detail) {
		this.detail = detail;
	}
	
}
