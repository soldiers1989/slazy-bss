package com.slazy.bss.slazypay.vo.res;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @Description: 记账返回结果VO
 * @author:	xianzhiqiang
 * @date:	2017年6月27日 下午4:12:40
 */
public class AccountResVo implements Serializable {
	
	private static final long serialVersionUID = -6353158911417624919L;
	
	private String bizId;//业务流水号
    protected String retCode;//响应码
    protected String retInfo;//响应信息

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
}
