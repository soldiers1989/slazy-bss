package com.slazy.bss.slazypay.dao;

import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.Trade;


public interface TradeMapper extends BaseMapper<Trade>{
    
	List<Trade> selectTradeByBizId(Map map);
	
	List<Trade> selectTradeByBizIdAndBizType(Map map);
	
	List<Trade> selectTradeByBatchIdAndBizType(Map map);
	
	List<Trade> selectTradeList(Map map);
	
	Integer selectTradeListCount(Map map);
	
	List<Trade> selectTradeByIds(String[] array);

	Long findStatisticsCount(Map<String, Object> map);
}