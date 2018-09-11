package com.slazy.bss.slazypay.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.service.BankCardServiceImpl;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.vo.req.QueryAccInfoReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;
/**
 * 
 * @Description:客户信息查询
 * @author:	dingyaru
 * @date:	2017年12月25日 上午11:34:08
 */
@Service("queryAccountInfoProvider")
@Transactional(readOnly=true)
public class QueryAccountInfoProviderImpl implements BaseAccProvider<QueryAccInfoReqVo> {

	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Autowired
	private BankCardServiceImpl bankCardService;
	
	@Override
	public ResponseVo handle(QueryAccInfoReqVo vo) {
	
		log.info("开始处 账户基础信息查询！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		res.setRetInfo("查询成功！");
		try {
			//校验数据
//			res = verify(vo,res);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			if(vo.getAccountNo()==null||"".equals(vo.getAccountNo())){
				//检查账务系统是否已开过户(同一个系统来源同一个身份证号只能开一个类型的户)
				List<MainAccount> mainAccount = mainAccountService.selectAccByIdNoAndCusIdType(vo.getCerificateType(), vo.getIdCardNo(), vo.getBizSystem(), null);
				if(mainAccount!=null && mainAccount.size() >0 ){
					for (MainAccount mAcc : mainAccount) {
						res.setObj(mAcc);						
					}
				}else {
					res.setRetCode(AccConst.RET_CODE_FAILED_H);
					res.setRetInfo("主账户不存在");
					return res;
				}				
				
			} else {
				MainAccount mAcc = mainAccountService.selectAccByAccountNoAndIdNo(vo);
				if(mAcc!=null){					
					res.setObj(mAcc);
				}else {
					res.setRetCode(AccConst.RET_CODE_FAILED_H);
					res.setRetInfo("主账户不存在");
					return res;
				}				
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
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年12月25日 上午11:34:38
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
