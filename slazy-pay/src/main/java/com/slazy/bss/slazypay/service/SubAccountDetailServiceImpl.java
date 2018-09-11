package com.slazy.bss.slazypay.service;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.slazy.bss.slazypay.dao.SubAccountDetailMapper;
import com.slazy.bss.slazypay.model.Page;
import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.vo.req.CompanyAccountReqVo;
import com.slazy.bss.slazypay.vo.req.SubAccountDetailReqVo;

@Service("subAccountDetailService")
@Transactional
public class SubAccountDetailServiceImpl extends BaseServiceImpl<SubAccountDetail>{
	@Autowired
	private SubAccountDetailMapper subAccountDetailMapper;
	
	public int selectTradeByBizIdAndBizType(String bizId, String bizType, String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bizId", bizId);
		map.put("bizType", bizType);
		map.put("bizSystem", bizSystem);
		return subAccountDetailMapper.selectTradeByBizIdAndBizType(map);
	}

	public List<SubAccountDetail> selectTradeByBatchIdAndBizType(String batchId, String bizType, String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("batchId", batchId);
		map.put("bizType", bizType);
		map.put("bizSystem", bizSystem);
		return subAccountDetailMapper.selectTradeByBatchIdAndBizType(map);
	}

	public List<SubAccountDetail> selectCompanyDetail(CompanyAccountReqVo vo,Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return subAccountDetailMapper.selectCompanyDetail(map);
	}

	public Integer selectCompanyDetailCount(CompanyAccountReqVo vo) {
		return subAccountDetailMapper.selectCompanyDetailCount(vo);
	}

	public List<SubAccountDetailReqVo> selectMainAccountSubDetail(SubAccountDetailReqVo vo, Page page) {
		if(!StringUtils.isEmpty(vo.getBeginTime())){
			vo.setBeginTime(vo.getBeginTime()+" 00:00:00");
		}
		if(!StringUtils.isEmpty(vo.getEndTime())){
			vo.setEndTime(vo.getEndTime()+" 23:59:59");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return subAccountDetailMapper.selectMainAccountSubDetail(map);
	}

	public Integer selectMainAccountSubDetailCount(SubAccountDetailReqVo vo, Page page) {
		if(!StringUtils.isEmpty(vo.getBeginTime())){
			vo.setBeginTime(vo.getBeginTime()+" 00:00:00");
		}
		if(!StringUtils.isEmpty(vo.getEndTime())){
			vo.setEndTime(vo.getEndTime()+" 23:59:59");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return subAccountDetailMapper.selectMainAccountSubDetailCount(map);
	}

	public List<SubAccountDetailReqVo> selectTradeDetailList(SubAccountDetailReqVo vo, Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return subAccountDetailMapper.selectTradeDetailList(map);
	}

	public Integer selectTradeDetailListCount(SubAccountDetailReqVo vo, Page page) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return subAccountDetailMapper.selectTradeDetailListCount(map);
	}

	public List<SubAccountDetail> selectCompanyDetailByIds(String[] arry) {
		
		return subAccountDetailMapper.selectCompanyDetailByIds(arry);
	}

	public List<SubAccountDetail> selectDetailByBatchIdAndBizId(String bizId,String batchId,String bizSystem){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bizId", bizId);
		map.put("batchId", batchId);
		map.put("bizSystem", bizSystem);
		return subAccountDetailMapper.selectDetailByBatchIdAndBizId(map);
	}

	public BigDecimal totalTradeAmount(SubAccountDetailReqVo vo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		return subAccountDetailMapper.totalTradeAmount(map);
	}

	public BigDecimal findAccBalance(SubAccountDetailReqVo vo) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		return subAccountDetailMapper.findAccBalance(map);
	}

	public void batchInsert(List<SubAccountDetail> subAccDetList) {
		subAccountDetailMapper.batchInsert(subAccDetList);
	}

}
