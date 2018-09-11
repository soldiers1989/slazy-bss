package com.slazy.bss.slazypay.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.service.SubAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.ValidateUtil;
import com.slazy.bss.slazypay.vo.req.QueryBalanceReqVo;
import com.slazy.bss.slazypay.vo.res.QueryBalanceResVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;
/**
 * 
 * @Description: 账户余额查询服务
 * @author:	xianzhiqiang
 * @date:	2017年7月17日 下午4:14:13
 */
@Service("queryBalanceProvider")
@Transactional(readOnly=true)
public class QueryBalanceProviderImpl implements BaseAccProvider<QueryBalanceReqVo> {

	@Autowired
	private SubAccountServiceImpl subAccountService;
	
	@Override
	public ResponseVo handle(QueryBalanceReqVo vo) {
		log.info("开始处理账户余额查询！请求参数："+ JSON.toJSONString(vo));
		long startTime = System.currentTimeMillis();
		ResponseVo res = new ResponseVo();
		try {
			//校验数据
			res = verify(vo);
			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
				return res;
			}
			List<QueryBalanceResVo> list = new ArrayList<>();
			List<SubAccount> subAccounts = subAccountService.selectSubAccounts(vo.getAccountNo(),AccConst.SUB_ACC_TYPE_CASH,null,null);
			if(subAccounts!=null&&subAccounts.size()>0){
				res.setObj(subAccounts.get(0));
			}else{
				res.setRetCode(AccConst.RET_CODE_FAILED_O);
				res.setRetInfo("账户余额查询失败：账户："+ vo.getAccountNo() 
				+((vo.getSubType()!=null&&!"".equals(vo.getSubType()))?",子账户类型："+vo.getSubType():"")
				+((vo.getLendId()!=null&&!"".equals(vo.getLendId()))?",子账户编号："+vo.getLendId():"")
				+"不存在！");
			}
		} catch (Exception e) {
			log.error("账户余额查询失败：",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		
		log.info("账户余额查询完毕！耗时:"+ (System.currentTimeMillis()-startTime));
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
	private ResponseVo verify(QueryBalanceReqVo vo) throws Exception{
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"查询成功！");
		//校验外层必填项
		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
                "accountNo"}, new String[]{
                "账户编号"});
        if (!errMsg.isEmpty()) {
        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
        	return res;		
        }
        return res;		
	}

}
