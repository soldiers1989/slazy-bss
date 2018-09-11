package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.vo.req.SubAccountReqVo;
import com.slazy.bss.slazypay.vo.res.BalanceDetailResVo;
import com.slazy.bss.slazypay.vo.res.QueryAccountBalanceVo;
import com.slazy.bss.slazypay.vo.res.QueryBalanceResVo;


public interface SubAccountMapper extends BaseMapper<SubAccount> {
	
	SubAccount selectSubAccount(SubAccount subAccount);
	
	int updateByPrimaryKeyToVersion(SubAccount SubAccount) throws Exception;

	List<QueryBalanceResVo> selectBalanceByAccountNo(Map map);
	
	List<SubAccountReqVo> selectAllSubAccount(String id);
	
	Integer selectAllSubAccountCount(String id);
	
	List<SubAccountReqVo> selectMainAccountSub(Map map);
	
	Integer selectMainAccountSubCount(Map map);
	
	List<SubAccount> selectSubAccounts(SubAccount subAccount);
	
	List<SubAccount> selectSubAccountInfo(SubAccount subAccount);
	
	List<BalanceDetailResVo> selectSubAccountBalance(Map map);
	
	SubAccount selectByPrimaryKeyForUpdate(Long id);

	List<SubAccount> findByMainAccount(String accountNo);
	
	void batchInsert(List<SubAccount> list);

	List<QueryAccountBalanceVo> findAllSumAmount(Map map);
}
