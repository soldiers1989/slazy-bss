package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 单笔交易查询Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午4:35:55
 */
public class TradeQuerySingleVo extends BaseReqVo implements Serializable{

	private static final long serialVersionUID = -5193377697710505354L;

	private String bizType;//业务类型

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
}
