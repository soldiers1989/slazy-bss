package com.slazy.bss.slazypay.provider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.UpdateAccInfoVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description:客户信息变更接口
 * @author:	dingyaru
 * @date:	2017年11月28日 下午4:32:30
 */
@Service("updateAccInfoProvider")
public class UpdateAccInfoProviderImpl implements BaseAccProvider<UpdateAccInfoVo> {
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Override
	public ResponseVo handle(UpdateAccInfoVo vo) {
		log.info("开始处理客户信息变更！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			//1.校验
//			res = verify(vo);
			
			//2.客户信息变更
			res = updateAccInfo(vo);
			
		} catch (Exception e) {
			log.error("处理客户信息变更失败：",e);
			res = AccUtil.returnHandle(e);
			throw new RuntimeException("客户信息变更失败:" + e.getMessage());
		}
		log.info("处理客户信息变更结束！");
		return res;
	}
	
	/**
	 * 
	 * @Description:必填字段校验
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年11月28日 下午4:47:48
	 */
//	public ResponseVo verify(UpdateAccInfoVo vo) throws Exception {
//		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"客户信息变更成功！");
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
//        		"accountNo","mobile","bizSystem"
//        }, new String[]{"账户编号","手机号","业务系统"});
//        if (!errMsg.isEmpty()) {
//        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
//        }
//        return res;
//	}
	/**
	 * 
	 * @Description:客户信息变更方法
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年11月28日 下午5:12:43
	 */
	public ResponseVo updateAccInfo(UpdateAccInfoVo vo)  throws Exception {
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"客户信息变更成功！");
		MainAccount mainAccount = mainAccountService.selectAccByAccountNo(vo.getAccountNo(), vo.getBizSystem());
		if(mainAccount == null ){
			res = new ResponseVo(AccConst.RET_CODE_FAILED_H,"主账户:"+vo.getAccountNo()+"不存在 ！");
			return res;
		}
		//主账户是否是启用状态
		if(AccConst.STATE_INVALID.equals(mainAccount.getStatus())){
			res = new ResponseVo(AccConst.RET_CODE_FAILED_D,"主账户:"+vo.getAccountNo()+"已停用 ！");
			return res;
		}		
		if(vo.getMobile().equals(mainAccount.getMobile())){
			res = new ResponseVo(AccConst.RET_CODE_FAILED_P,"主账户:"+vo.getAccountNo()+"新换手机号与已绑手机号一致，无需更换！");
			return res;
		}
		
		mainAccount.setMobile(vo.getMobile());
		mainAccountService.updateByPrimaryKey(mainAccount);
		return res;
	}


}
