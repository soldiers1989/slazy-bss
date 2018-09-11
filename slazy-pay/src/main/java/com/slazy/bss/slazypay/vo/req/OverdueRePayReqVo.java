package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description: 逾期还款Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月5日 下午3:09:40
 */
public class OverdueRePayReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -561616553246632661L;

	private String batchId;//批次号
	private List<OverdueRePayDetailReqVo> detail; //逾期明细
	
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public List<OverdueRePayDetailReqVo> getDetail() {
		return detail;
	}
	public void setDetail(List<OverdueRePayDetailReqVo> detail) {
		this.detail = detail;
	}
	
}
