package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.vo.req.CompanyAccountReqVo;
import com.slazy.bss.slazypay.vo.req.MainAccountReqVo;


public interface MainAccountMapper extends BaseMapper<MainAccount>{
	
	MainAccount selectAccByAccountNo(Map map);
	
	List<CompanyAccountReqVo> selectCompanyAccount(Map map);
	
	MainAccount selectAccByAccountNoAndIdNo(Map map);
	
	Integer selectCompanyCountAccount(Map map);
	
	List<MainAccountReqVo> selectMainAccountList(Map map);
	
	Integer selectMainAccountCount(Map map);
	
	List<MainAccountReqVo> selectMainDetailByIds(String[] array);

	List<MainAccount> selectAccByIdNoAndCusIdType(Map map);

	List<CompanyAccountReqVo> selectCompanySubAccount(Map<String, Object> map);

	int selectCompanyCountSubAccount(Map<String, Object> map);

	List<MainAccount> queryUsers(Map map);
	
	void batchInsert(List<MainAccount> list);

	MainAccount selectTheMainAccount(Map<String, String> map);
	
	List<MainAccount> getVariableAccount(Map map);
}