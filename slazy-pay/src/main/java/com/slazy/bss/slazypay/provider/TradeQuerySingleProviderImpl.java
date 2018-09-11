package com.slazy.bss.slazypay.provider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.service.SubAccountDetailServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.ValidateUtil;
import com.slazy.bss.slazypay.vo.req.TradeQuerySingleVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;
import com.slazy.bss.slazypay.vo.res.TradeQueryResVo;
/**
 * 
 * @Description: 单笔交易查询服务
 * @author:	xianzhiqiang
 * @date:	2017年7月6日 下午4:37:32
 */
@Service("tradeQuerySingleProvider")
@Transactional(readOnly=true)
public class TradeQuerySingleProviderImpl implements BaseAccProvider<TradeQuerySingleVo> {

	@Autowired
	private SubAccountDetailServiceImpl subAccountDetailService;
	
	@Override
	public ResponseVo handle(TradeQuerySingleVo vo) {
		log.info("开始处理单笔交易查询！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			TradeQueryResVo tradeQueryResVo = new TradeQueryResVo();
			res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
			res.setRetInfo("查询成功！");
			tradeQueryResVo.setBizId(vo.getBizId());
			//校验数据
			res = verify(vo,res);
			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
				return res;
			}
			int count = subAccountDetailService.selectTradeByBizIdAndBizType(vo.getBizId(), null, null);
			if(count>0){
				tradeQueryResVo.setState(AccConst.TRADE_STATE_SUCCESS);
			}else{
				tradeQueryResVo.setState(AccConst.TRADE_STATE_FAILED);
			}
			res.setObj(tradeQueryResVo);
		} catch (Exception e) {
			log.error("单笔交易查询失败：",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		
		log.info("单笔交易查询完毕！");
		
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
	private ResponseVo verify(TradeQuerySingleVo vo,ResponseVo res) throws Exception{
		
		//校验外层必填项
		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
                "bizId"}, new String[]{
                "交易订单号"});
        if (!errMsg.isEmpty()) {
        	res.setRetCode(AccConst.RET_CODE_FAILED_A);
        	res.setRetInfo(StringUtils.join(errMsg, ",") + ";");
        	return res;
        }
		return res;
	}

}
