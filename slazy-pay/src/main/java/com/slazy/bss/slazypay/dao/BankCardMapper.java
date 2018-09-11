package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.BankCard;


public interface BankCardMapper extends BaseMapper<BankCard>{
	
	BankCard selectBankByCardNo(Map map);
	
	List<BankCard> selectBankList(Map map);
	
	Integer selectBankListCount(Map map);
	/**
	 * 
	 * @Description:根据账户编号、老卡的卡号、绑卡状态、业务系统来查询银行卡信息
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年8月1日 下午5:01:38
	 */
	BankCard selectBankByAccountNo(BankCard bank);
	
	List<BankCard> selectBankByIds(String[] arry);
	
	/**
	 * 
	 * @Description:根据账户编号、业务系统、（新卡的卡号或状态为已绑卡）来查询银行卡信息
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年9月30日 上午10:51:19
	 */
	List<BankCard> selectBankByAccNo(BankCard bank);

	List<BankCard> findBankInfo(BankCard bankCard);
	
	void batchInsert(List<BankCard> list);
}