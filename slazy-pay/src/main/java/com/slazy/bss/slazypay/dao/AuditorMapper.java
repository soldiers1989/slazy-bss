package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.Auditor;


public interface AuditorMapper extends BaseMapper<Auditor> {

	List<Auditor> selectAuditorList(Map map);
	
	Integer selectAuditorListCount(Map map);
	
	List<Auditor> selectAuditorByIds(String[] arry);
	
	Auditor selectByBizId(String bizId);
}
