package com.slazy.bss.slazypay.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.slazy.bss.slazypay.dao.TradeMapper;
import com.slazy.bss.slazypay.model.Page;
import com.slazy.bss.slazypay.model.Trade;
import com.slazy.bss.slazypay.utils.AccConst;

@Service("tradeService")
@Transactional
public class TradeServiceImpl extends BaseServiceImpl<Trade>{

	@Autowired
	private TradeMapper tradeMapper;
	
	public Trade selectTradeByBizId(String bizId,String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bizId", bizId);
		map.put("bizSystem", bizSystem);
		List<Trade> list = tradeMapper.selectTradeByBizId(map);
		if(list!=null&&list.size()>0){
			//交易失败和提现允许重发
			if(AccConst.TRADE_STATE_FAILED.equals(list.get(0).getStatus())||
					(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN).equals(list.get(0).getBizType())){
				return null;
			}else{
				return list.get(0);
			}
			
		}else{
			return null;
		}
	}

	public Trade selectTradeByBizIdAndBizType(String bizId, String bizType,String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("bizId", bizId);
		map.put("bizSystem", bizSystem);
		map.put("bizType", bizType);
		List<Trade> list = tradeMapper.selectTradeByBizIdAndBizType(map);
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

	public List<Trade> selectTradeByBatchIdAndBizType(String batchId, String bizType, String bizSystem) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("batchId", batchId);
		map.put("bizSystem", bizSystem);
		map.put("bizType", bizType);
		return tradeMapper.selectTradeByBatchIdAndBizType(map);
	}

	public List<Trade> selectTradeList(Trade vo, Page page) {
		if(!StringUtils.isEmpty(vo.getBeginTime())){
			vo.setBeginTime(vo.getBeginTime()+" 00:00:00");
		}
		if(!StringUtils.isEmpty(vo.getEndTime())){
			vo.setEndTime(vo.getEndTime()+" 23:59:59");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return tradeMapper.selectTradeList(map);
	}

	public Integer selectTradeListCount(Trade vo, Page page) {
		if(!StringUtils.isEmpty(vo.getBeginTime())){
			vo.setBeginTime(vo.getBeginTime()+" 00:00:00");
		}
		if(!StringUtils.isEmpty(vo.getEndTime())){
			vo.setEndTime(vo.getEndTime()+" 23:59:59");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("vo", vo);
		map.put("page", page);
		return tradeMapper.selectTradeListCount(map);
	}

	public List<Trade> selectTradeByIds(String[] array) {
		
		return tradeMapper.selectTradeByIds(array);
	}

	public Long findStatisticsCount(Date bDate, Date eDate, String bizSystem,String[] bizTypes) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bDate", bDate);
		map.put("eDate", eDate);
		map.put("bizSystem", bizSystem);
		map.put("bizTypes", bizTypes);
		return tradeMapper.findStatisticsCount(map);
	}


}
