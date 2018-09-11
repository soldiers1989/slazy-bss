package com.slazy.bss.slazypay.service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.slazy.bss.slazypay.dao.BankCardMapper;
import com.slazy.bss.slazypay.model.BankCard;
import com.slazy.bss.slazypay.model.Page;
import com.slazy.bss.slazypay.utils.AccUtil;


@Service("bankCardService")
@Transactional
public class BankCardServiceImpl extends BaseServiceImpl<BankCard>{

	@Autowired
	private BankCardMapper bankCardMapper;
	
	public BankCard selectBankByCardNo(String accountNo, String nBankCardNo, String bizSystem) {
		Map<String, Object> map = new HashMap<>();
		map.put("accountNo", accountNo);
		map.put("nBankCardNo", nBankCardNo);
		map.put("bizSystem", bizSystem);
		return bankCardMapper.selectBankByCardNo(map);
	}

	public List<BankCard> selectBankList(BankCard bank, Page page) {
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("bank", bank);
		map.put("page", page);
		List<BankCard> list = bankCardMapper.selectBankList(map);
		List<BankCard> bankList = new ArrayList<BankCard>();
		for (BankCard bankCard : list) {
			if(!StringUtils.isEmpty(bankCard.getnBankCardNo())){
				if(bankCard.getnBankCardNo().length()>8){
					String bankCardNo = AccUtil.transform(bankCard.getnBankCardNo(), 4, 4);
					bankCard.setnBankCardNo(bankCardNo);
				}
			}
			if(!StringUtils.isEmpty(bankCard.getMobile())){
				if(bankCard.getMobile().length()>7){
					String mobile = AccUtil.transform(bankCard.getMobile(), 3, 4);
					bankCard.setMobile(mobile);
				}
			}
			bankList.add(bankCard);
		}
		return bankList;
	}
	
	public Integer selectBankListCount(BankCard bank, Page page) {
		Map<String,Object> map  = new HashMap<String,Object>();
		map.put("bank", bank);
		map.put("page", page);
		return bankCardMapper.selectBankListCount(map);
	}
	
	/**
	 * 根据账户编号、老卡的卡号、绑卡状态、绑卡类型、业务系统来查询银行卡信息
	 */
	public BankCard selectBankByAccountNo(BankCard bank) {
		
		return bankCardMapper.selectBankByAccountNo(bank);
	}

	public List<BankCard> selectBankByIds(String[] arry) {
		List<BankCard> list = bankCardMapper.selectBankByIds(arry);
		for (BankCard bankCard : list) {
			if(!StringUtils.isEmpty(bankCard.getnBankCardNo())){
				if(bankCard.getnBankCardNo().length()>8){
					String bankCardNo = AccUtil.transform(bankCard.getnBankCardNo(), 4, 4);
					bankCard.setnBankCardNo(bankCardNo);
				}
			}
			if(!StringUtils.isEmpty(bankCard.getMobile())){
				if(bankCard.getMobile().length()>7){
					String mobile = AccUtil.transform(bankCard.getMobile(), 3, 4);
					bankCard.setMobile(mobile);
				}
			}
		}
		return list;
	}


	/**
	 * 根据账户编号、业务系统、（新卡的卡号或状态为已绑卡）来查询银行卡信息
	 */
	public List<BankCard> selectBankByAccNo(BankCard bank) {
		
		return bankCardMapper.selectBankByAccNo(bank);
	}

	public List<BankCard> findBankInfo(BankCard bankCard) {
		return bankCardMapper.findBankInfo(bankCard);
	}

	/**
	 * 
	 * @Description: 批量插入
	 * @author:	xianzhiqiang
	 * @date:	2017年12月5日 下午4:35:23
	 */
	public void batchInsert(List<BankCard> list){
		bankCardMapper.batchInsert(list);
	}
}
