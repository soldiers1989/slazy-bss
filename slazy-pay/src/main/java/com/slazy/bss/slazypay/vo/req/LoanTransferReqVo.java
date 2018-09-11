package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @Description: 债权转让Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午2:27:43
 */
public class LoanTransferReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = -1047665642146955264L;
	private String batchId;//批次号
	private List<LoanTransferDetailReqVo> detail; //债权转让明细
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public List<LoanTransferDetailReqVo> getDetail() {
		return detail;
	}
	public void setDetail(List<LoanTransferDetailReqVo> detail) {
		this.detail = detail;
	}
	
}
