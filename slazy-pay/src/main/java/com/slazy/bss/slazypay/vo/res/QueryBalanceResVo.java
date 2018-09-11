package com.slazy.bss.slazypay.vo.res;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @Description: 余额查询返回结果Vo
 * @author:	xianzhiqiang
 * @date:	2017年7月17日 下午3:49:44
 */
public class QueryBalanceResVo implements Serializable {

	private static final long serialVersionUID = 411658918415708312L;

	private String subType;//子账户类型
	private String amount;//余额
	private List<BalanceDetailResVo> detailList=new ArrayList<BalanceDetailResVo>();
	
	public List<BalanceDetailResVo> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<BalanceDetailResVo> detailList) {
		this.detailList = detailList;
	}
	public String getSubType() {
		return subType;
	}
	public void setSubType(String subType) {
		this.subType = subType;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
}
