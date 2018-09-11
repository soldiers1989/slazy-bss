package com.slazy.bss.user.vo;

import java.util.List;

import org.slazyframework.model.BaseModel;

import com.slazy.bss.user.model.SysActivity;

public class SysActivityVO extends BaseModel{
   
	List<SysActivity> list;

	public List<SysActivity> getList() {
		return list;
	}

	public void setList(List<SysActivity> list) {
		this.list = list;
	}
	
	
}