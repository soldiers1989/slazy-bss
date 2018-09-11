package com.slazy.bss.order.vo;

import java.util.Date;
import java.util.List;

import org.slazyframework.model.BaseModel;

public class OrderQueryBatchRespVO extends BaseModel {
	
	private List<OrderInfoVO> list;
	
	private int limit;
	
	private Date timestamp;

	public List<OrderInfoVO> getList() {
		return list;
	}

	public void setList(List<OrderInfoVO> list) {
		this.list = list;
	}
	
	

}
