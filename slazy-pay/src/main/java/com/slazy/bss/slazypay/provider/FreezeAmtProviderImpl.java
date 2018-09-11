package com.slazy.bss.slazypay.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.provider.BaseAccProvider;
import com.slazy.bss.slazypay.service.RecordAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.FreezeAmtReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 资金冻结
 * @author:	dingyaru
 * @date:	2017年7月5日 上午10:10:28
 */
@Service("freezeAmtProvider")
public class FreezeAmtProviderImpl extends AbstractAccProvider implements BaseAccProvider<FreezeAmtReqVo> {

	@Autowired
	private RecordAccountServiceImpl recordAccountService;
	
	@Override
	public ResponseVo handle(FreezeAmtReqVo vo) {
		log.info("开始处理资金冻结！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			//1.校验
//			res = verify(vo);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			//2.资金冻结
			res = freezen(vo);
			
		} catch (Exception e) {
			log.error("处理资金冻结失败：",e);
			res = AccUtil.returnHandle(e);
		}
		
		log.info("处理资金冻结结束！");
		return res;
	}
	
	/**
	 * 
	 * @Description:校验必填数据
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年7月5日 上午10:41:33
	 */
//	public ResponseVo verify(FreezeAmtReqVo vo) throws Exception {
//		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"冻结成功！！");
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[] {
//				"bizId", "accountNo", "freezeId", "amount", "freezeType",
//				"bizSystem" }, new String[] { "订单编号", "账户编号",
//				"冻结编号", "冻结金额", "冻结类型", "业务系统" });
//		/**冻结类型为出借冻结 */
//        if (!errMsg.isEmpty()) {
//        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
//        	return res;
//        }
//        return res;
//	}
	
	/**
	 * 
	 * @Description:资金冻结处理
	 * @param: FreezeAmtVo
	 * @return: ResponseVo
	 * @throws Exception
	 * @author:	dingyaru
	 * @date:	2017年7月5日 上午11:02:50
	 */
	public ResponseVo freezen(FreezeAmtReqVo vo) throws Exception {
		List<AccountDetailVo> detail = new ArrayList<AccountDetailVo>();
		List<AccountVo> listVo = new ArrayList<AccountVo>();
		AccountVo accountVo = new AccountVo();
		accountVo.setBizId(vo.getBizId());
		accountVo.setBizSystem(vo.getBizSystem());
		accountVo.setBizType(getFreezeType(vo.getFreezeType())); //[40:提现冻结  43:还款冻结 10:资金冻结 51:出借冻结]
		
		AccountDetailVo accountDetailVo = new AccountDetailVo();
		/** 可用资金账户-----------> 冻结账户 */
		accountDetailVo.setAmount(new BigDecimal(vo.getAmount()));
		accountDetailVo.setPayeeAccNo(vo.getAccountNo());  //本手方账户编号
		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_OUT);
		accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_CASH); //本手方账户类型
		if("2".equals(vo.getFreezeType())){ //还款冻结
			if(!StringUtils.isEmpty(vo.getPtFreezeId())){
				accountDetailVo.setLendId(vo.getPtFreezeId());				
				accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_FREEZE); //本手方账户类型
			}
		}
		
		accountDetailVo.setDraweeAccNo(vo.getAccountNo());
		accountDetailVo.setRivalSubType(getSubType(vo.getFreezeType()));
		/** 提现冻结时FreezeId的值必须是‘666666’  */
		if(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN.equals(vo.getFreezeType())){
			accountDetailVo.setRivalLendId(AccConst.SUB_BIZ_ID_WITHDRAW);//（取现）冻结账户
		}else {
			accountDetailVo.setRivalLendId(vo.getFreezeId());
		}
		accountDetailVo.setRemark("资金冻结！");
		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_DJ_FREEZE);
		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_FREEZE);
		
		detail.add(accountDetailVo);
		
		accountVo.setDetail(detail);
		listVo.add(accountVo);
		return verifyRet(listVo);
	}

	/**
	 * 
	 * @Description:根据业务系统所传的冻结类型转换为账户自己的冻结类型
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年11月25日 下午2:17:47
	 */
	public String getFreezeType(String freezeType) throws Exception{
		if(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN.equals(freezeType)){//提现冻结
			freezeType = AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN;
		}else if("1".equals(freezeType)){//资金冻结
			freezeType = AccConst.BUSI_TYPE_FUND_GENERAL_FROZEN;
		}else if("2".equals(freezeType)){//还款冻结
			freezeType = AccConst.BUSI_TYPE_FUND_REPAYMENT_FROZEN;
		}else if("3".equals(freezeType)){//出借冻结
			freezeType = AccConst.BUSI_TYPE_FUND_LEND_FROZEN;
		}else {
			throw new Exception("冻结类型:"+ freezeType +" 是错误的冻结类型！");
		}
		return freezeType;
	}
	/**
	 * 
	 * @Description: 根据冻结类型映射子账户类型
	 * @param: freezeType 冻结类型
	 * @return:String 子账户类型
	 * @author:	xianzhiqiang
	 * @date:	2017年7月25日 上午10:37:59
	 */
	public String getSubType(String freezeType) throws Exception{
		String subType = null;
		if(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN.equals(freezeType)){//提现冻结
			subType = AccConst.SUB_ACC_TYPE_WITHDRAW;
		}else if("1".equals(freezeType)){//资金冻结
			subType = AccConst.SUB_ACC_TYPE_FREEZE;
		}else if("2".equals(freezeType)){//还款预处理账户
			subType = AccConst.SUB_ACC_TYPE_REPAYMENT_PRE;
		}else if("3".equals(freezeType)){//出借冻结
			subType = AccConst.SUB_ACC_TYPE_LEND;
		}else {
			throw new Exception("冻结类型:"+ freezeType +" 是错误的冻结类型！");
		}
		return subType;
	}
	
	public String getService() {
		return "dictService";
	}
}
