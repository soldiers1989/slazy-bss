package com.slazy.bss.slazypay.vo.res;

import java.io.Serializable;
/**
 * 
 * @Description:交易查询返回结果Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午5:54:44
 */
public class TradeQueryResVo implements Serializable {

	private static final long serialVersionUID = -6628592597255240705L;

	private String bizId;//交易流水号
	private String state;//状态（0：成功，1：失败）
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
