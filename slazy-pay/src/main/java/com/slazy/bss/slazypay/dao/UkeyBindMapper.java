package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.UkeyBind;


public interface UkeyBindMapper extends BaseMapper<UkeyBind>{
	public UkeyBind getSerialNum(String accountName);

	public List<UkeyBind> selectUkeyBindList(Map map);

	public int selectUkeyBindListCount(Map  map);
}
