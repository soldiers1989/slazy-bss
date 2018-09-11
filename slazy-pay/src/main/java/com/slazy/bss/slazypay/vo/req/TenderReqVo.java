package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @Description:批量投标Vo
 * @author:	wangyanan
 * @date:	2017-11-20 上午10:09:28
 */
public class TenderReqVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = 533388125441661905L;
    private String batchId;
    private List<TenderDetailReqVo> detail;//投标明细
	public String getBatchId() {
		return batchId;
	}
	public List<TenderDetailReqVo> getDetail() {
		return detail;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public void setDetail(List<TenderDetailReqVo> detail) {
		this.detail = detail;
	}

	
}
