package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 批量交易查询Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午4:35:55
 */
public class TradeQueryBatchIdVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = 2553659604815800551L;

	private String bizType;//业务类型

	private String batchId;//批次号
	
	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
