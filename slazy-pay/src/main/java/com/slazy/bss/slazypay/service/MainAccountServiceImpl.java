package com.slazy.bss.slazypay.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.slazy.bss.slazypay.dao.MainAccountMapper;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.Page;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.CompanyAccountReqVo;
import com.slazy.bss.slazypay.vo.req.MainAccountReqVo;
import com.slazy.bss.slazypay.vo.req.QueryAccInfoReqVo;

				

@Service("mainAccountService")
@Transactional
public class MainAccountServiceImpl extends BaseServiceImpl<MainAccount>{

	@Autowired
	private MainAccountMapper mainAccountMapper;
	
	public MainAccount selectAccByAccountNo(String accountNo,String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("accountNo", accountNo);
		map.put("bizSystem", bizSystem);
		return mainAccountMapper.selectAccByAccountNo(map);
	}

	public List<CompanyAccountReqVo> selectCompanyAccount(Page page,String pId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pId", pId);
		map.put("page", page);
		return mainAccountMapper.selectCompanyAccount(map);
	}

	public MainAccount selectAccByAccountNoAndIdNo(QueryAccInfoReqVo vo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("accountNo", vo.getAccountNo());
		map.put("bizSystem", vo.getBizSystem());
		map.put("cerificateType", vo.getCerificateType());
		map.put("certificateNo", vo.getIdCardNo());
		return mainAccountMapper.selectAccByAccountNoAndIdNo(map);
	}

	public Integer selectCompanyCountAccount(Page page, String pId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pId", pId);
		map.put("page", page);
		return mainAccountMapper.selectCompanyCountAccount(map);
	}

	public List<MainAccountReqVo> selectMainAccountList(MainAccountReqVo mAcc, Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mAcc", mAcc);
		map.put("page", page);
		List<MainAccountReqVo> list = mainAccountMapper.selectMainAccountList(map);
		List<MainAccountReqVo> mainAccountList = new ArrayList<MainAccountReqVo>();
		for (MainAccountReqVo mainAccount : list) {
			if (AccConst.CUSTOMER_TYPE_PERSONAL.equals(mainAccount.getCustomerType())) {
				if(!StringUtils.isEmpty(mainAccount.getCertificateNo())){
					if(mainAccount.getCertificateNo().length()>8){
						String idCardNo = AccUtil.transform(mainAccount.getCertificateNo(), 4, 4);
						mainAccount.setCertificateNo(idCardNo);
					}
				}				
			}
			mainAccountList.add(mainAccount);			
		}
		return mainAccountList;
	}

	public Integer selectMainAccountCount(MainAccountReqVo mAcc, Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mAcc", mAcc);
		map.put("page", page);
		return mainAccountMapper.selectMainAccountCount(map);
	}

	public List<MainAccountReqVo> selectMainDetailByIds(String[] array) {
		List<MainAccountReqVo> list = mainAccountMapper.selectMainDetailByIds(array);
		for (MainAccountReqVo mainAccount : list) {
			if (AccConst.CUSTOMER_TYPE_PERSONAL.equals(mainAccount.getCustomerType())) {
				if(!StringUtils.isEmpty(mainAccount.getCertificateNo())){
					if(mainAccount.getCertificateNo().length()>8){
						String idCardNo = AccUtil.transform(mainAccount.getCertificateNo(), 4, 4);
						mainAccount.setCertificateNo(idCardNo);
					}
				}
			}
		}
		return list;
	}

	public List<MainAccount> selectAccByIdNoAndCusIdType(String cerificateType,
			String certificateNo, String bizSystem, String customerIdType) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("cerificateType", cerificateType);
		map.put("certificateNo", certificateNo);
		map.put("bizSystem", bizSystem);
		map.put("customerIdType", customerIdType);
		return mainAccountMapper.selectAccByIdNoAndCusIdType(map);
	}
	
	public MainAccount selectTheMainAccount(String bizSystem, String cerificateType,String certificateNo) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bizSystem", bizSystem);
		map.put("cerificateType", cerificateType);
		map.put("certificateNo", certificateNo);
		return mainAccountMapper.selectTheMainAccount(map);
	}

	public List<CompanyAccountReqVo> selectCompanySubAccount(Page page,
			String pId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pId", pId);
		map.put("page", page);
		return mainAccountMapper.selectCompanySubAccount(map);
	}

	public int selectCompanyCountSubAccount(Page page, String pId) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pId", pId);
		map.put("page", page);
		return mainAccountMapper.selectCompanyCountSubAccount(map);
	}

	public List<MainAccount> queryUsers(MainAccountReqVo mAcc) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mAcc", mAcc);
		return mainAccountMapper.queryUsers(map);
	}

	/**
	 * 
	 * @Description: 批量插入
	 * @author:	xianzhiqiang
	 * @date:	2017年12月5日 下午4:35:23
	 */
	public void batchInsert(List<MainAccount> list){
		mainAccountMapper.batchInsert(list);
	}
	
	public List<MainAccount> getVariableAccount(String bizSystem, String beginTime,String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bizSystem", bizSystem);
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		String[] bizType = {"22","41","42","44","52","39","40","10","43","51"};
		map.put("bizType", bizType);
		return mainAccountMapper.getVariableAccount(map);
	}
}
