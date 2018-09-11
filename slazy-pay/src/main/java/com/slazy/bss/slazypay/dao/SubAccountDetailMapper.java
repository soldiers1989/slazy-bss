package com.slazy.bss.slazypay.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.vo.req.CompanyAccountReqVo;
import com.slazy.bss.slazypay.vo.req.SubAccountDetailReqVo;


public interface SubAccountDetailMapper extends BaseMapper<SubAccountDetail> {
	
	/**
	 * 根据订单号和业务类型查询交易流水
	 */
	Integer selectTradeByBizIdAndBizType(Map map);
	
	/**
	 * 根据批次号和业务类型查询交易流水
	 */
	List<SubAccountDetail> selectTradeByBatchIdAndBizType(Map map);
	/**
	 * 查询公司账户明细
	 */
	List<SubAccountDetail> selectCompanyDetail(Map map);
	/**
	 * 查询公司账户明细条数
	 */
	Integer selectCompanyDetailCount(CompanyAccountReqVo vo);
	
	List<SubAccountDetailReqVo> selectMainAccountSubDetail(Map map);
	
	Integer selectMainAccountSubDetailCount(Map map);
	
	List<SubAccountDetailReqVo> selectTradeDetailList(Map map);
	
	Integer selectTradeDetailListCount(Map map);
	
	List<SubAccountDetail> selectCompanyDetailByIds(String[] arry);
	
	List<SubAccountDetail> selectDetailByBatchIdAndBizId(Map map);

	/**
	 * 
	 * @Description:统计交易金额
	 * @param:map
	 * @author:	wangyanan
	 * @date:	2017-12-5 下午3:16:26
	 */
	BigDecimal totalTradeAmount(Map map);

	BigDecimal findAccBalance(Map map);

	void batchInsert(List<SubAccountDetail> subAccDetList);
	
	
}