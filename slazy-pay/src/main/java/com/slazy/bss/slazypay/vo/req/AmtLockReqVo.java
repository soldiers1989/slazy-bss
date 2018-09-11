package com.slazy.bss.slazypay.vo.req;

import java.io.Serializable;
/**
 * 
 * @Description: 金额锁定/解锁vo
 * @author:	xianzhiqiang
 * @date:	2017年11月25日 下午3:08:40
 */
public class AmtLockReqVo extends BaseReqVo implements Serializable {

	private static final long serialVersionUID = -9047958311217505734L;

	private String idTepe;//编号类型 1：交易订单号 2：批次号
	
	private String lockType;//锁定类型
	
	public String getIdTepe() {
		return idTepe;
	}
	public void setIdTepe(String idTepe) {
		this.idTepe = idTepe;
	}
	public String getLockType() {
		return lockType;
	}
	public void setLockType(String lockType) {
		this.lockType = lockType;
	}
}
