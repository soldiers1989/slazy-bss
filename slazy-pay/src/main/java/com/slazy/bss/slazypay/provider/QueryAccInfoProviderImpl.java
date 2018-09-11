package com.slazy.bss.slazypay.provider;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.vo.req.QueryAccInfoReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;
/**
 * 
 * @Description: 账户基础信息查询服务
 * @author:	xianzhiqiang
 * @date:	2017年7月17日 下午5:29:36
 */
@Service("queryAccInfoProvider")
@Transactional(readOnly=true)
public class QueryAccInfoProviderImpl implements BaseAccProvider<QueryAccInfoReqVo> {

	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Override
	public ResponseVo handle(QueryAccInfoReqVo vo) {
	
		log.info("开始处理账户基础信息查询！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		res.setRetInfo("查询成功！");
		try {
			//校验数据
//			res = verify(vo,res);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			
				MainAccount mAcc = mainAccountService.selectAccByAccountNoAndIdNo(vo);
				if(mAcc!=null){					
					res.setObj(mAcc);
				}else {
					res.setRetCode(AccConst.RET_CODE_FAILED_H);
					res.setRetInfo("主账户不存在");
					return res;
				}				
			
			
		} catch (Exception e) {
			log.error(" 账户基础信息查询失败：",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		
		log.info(" 账户基础信息查询完毕！");
		
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
//	private ResponseVo verify(QueryAccInfoReqVo vo,ResponseVo res) throws Exception{
//		
//		if(vo.getAccountNo()==null||"".equals(vo.getAccountNo())){
//			
//			if((vo.getIdCardNo()!=null&&!"".equals(vo.getIdCardNo()))
//	        		||(vo.getCerificateType()!=null&&!"".equals(vo.getCerificateType()))){
//				List<String> err_Msg = ValidateUtil.validateMust(vo, new String[]{
//	                    "cerificateType","idCardNo","bizSystem"}, new String[]{
//	                    "证件类型","证件号码","业务系统"});
//	            if (!err_Msg.isEmpty()) {
//	            	 res.setRetCode(AccConst.RET_CODE_FAILED_A);
//	             	 res.setRetInfo(StringUtils.join(err_Msg, ",") + ";");
//	             	 return res;
//	            }
//			}else{
//				//校验外层必填项
//				List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
//		                "accountNo","bizSystem"}, new String[]{
//		                "账户编号","业务系统"});
//		        if (!errMsg.isEmpty()) {
//		        	 res.setRetCode(AccConst.RET_CODE_FAILED_A);
//	             	 res.setRetInfo(StringUtils.join(errMsg, ",") + ";");
//	             	 return res;
//		        }
//			}
//		}else{
//			List<String> errMsg = new ArrayList<String>();
//			if((vo.getIdCardNo()!=null&&!"".equals(vo.getIdCardNo()))
//	        		||(vo.getCerificateType()!=null&&!"".equals(vo.getCerificateType()))){
//				//校验外层必填项
//				errMsg = ValidateUtil.validateMust(vo, new String[]{
//						 "cerificateType","idCardNo","accountNo","bizSystem"}, new String[]{
//								 "证件类型","证件号码","账户编号","业务系统"});
//			}else{
//				//校验外层必填项
//				errMsg = ValidateUtil.validateMust(vo, new String[]{
//		                "accountNo","bizSystem"}, new String[]{
//		                "账户编号","业务系统"});
//			}
//			
//	        if (!errMsg.isEmpty()) {
//	        	 res.setRetCode(AccConst.RET_CODE_FAILED_A);
//            	 res.setRetInfo(StringUtils.join(errMsg, ",") + ";");
//            	 return res;
//	        }
//		}
//		return res;
//	}
	 
}
