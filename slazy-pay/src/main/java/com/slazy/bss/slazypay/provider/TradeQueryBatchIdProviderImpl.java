package com.slazy.bss.slazypay.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.service.SubAccountDetailServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.vo.req.TradeQueryBatchIdVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;
import com.slazy.bss.slazypay.vo.res.TradeQueryResVo;
/**
 * 
 * @Description: 批量交易查询服务
 * @author:	xianzhiqiang
 * @date:	2017年7月7日 上午11:28:58
 */
@Service("tradeQueryBatchIdProvider")
@Transactional(readOnly=true)
public class TradeQueryBatchIdProviderImpl implements BaseAccProvider<TradeQueryBatchIdVo> {

	@Autowired
	private SubAccountDetailServiceImpl subAccountDetailService;
	
	@Override
	public ResponseVo handle(TradeQueryBatchIdVo vo) {
		log.info("开始处理批量交易查询！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		res.setRetInfo("查询成功！");
		try {
			//校验数据
//			res = verify(vo,res);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			List<SubAccountDetail> list = subAccountDetailService.selectTradeByBatchIdAndBizType(vo.getBatchId(), null, vo.getBizSystem());
			if(list!=null&&list.size()>0){
				List<TradeQueryResVo> resList = new ArrayList<TradeQueryResVo>();
				for(SubAccountDetail sd:list){
					TradeQueryResVo tradeQueryResVo = new TradeQueryResVo();
					tradeQueryResVo.setBizId(sd.getBizId());
					tradeQueryResVo.setState(AccConst.TRADE_STATE_SUCCESS);
					resList.add(tradeQueryResVo);
				}
				res.setObj(resList);
			}else {
				res.setRetCode(AccConst.RET_CODE_FAILED_N);
	        	res.setRetInfo("批次号不存在");
	        	return res;
			}
		} catch (Exception e) {
			log.error("批量交易查询失败：",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		
		log.info("批量交易查询完毕！");
		
		return res;
	}

	/**
	 * 
	 * @Description: 数据校验
	 * @param: TradeQuerySingleVo
	 * @throws Exception
	 * @author:	xianzhiqiang
	 * @date:	2017年7月3日 下午4:07:08
	 */
//	private ResponseVo verify(TradeQueryBatchIdVo vo,ResponseVo res) throws Exception{
//		
//		//校验外层必填项
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
//                "batchId","bizSystem"}, new String[]{
//                "批次号","业务系统"});
//        if (!errMsg.isEmpty()) {
//        	res.setRetCode(AccConst.RET_CODE_FAILED_A);
//        	res.setRetInfo(StringUtils.join(errMsg, ",") + ";");
//        	return res;
//        }
//		return res;
//	}
}
