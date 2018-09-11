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
import com.slazy.bss.slazypay.service.RecordAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.UnFreezeAmtReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 资金解冻
 * @author:	dingyaru
 * @date:	2017年7月5日 上午11:10:28
 */
@Service("unFreezeAmtProvider")
public class UnFreezeAmtProviderImpl extends AbstractAccProvider implements BaseAccProvider<UnFreezeAmtReqVo> {

	@Autowired
	private RecordAccountServiceImpl recordAccountService;
	
	@Override
	public ResponseVo handle(UnFreezeAmtReqVo vo) {
		log.info("开始处理资金解冻！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			//1.校验
//			res= verify(vo);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			//2.资金解冻
			res = unFreezen(vo);
			
		} catch (Exception e) {
			log.error("处理资金解冻失败：",e);
			res = AccUtil.returnHandle(e);
		}
		
		log.info("处理资金解解冻束！");
		return res;
	}
	
	/**
	 * 
	 * @Description:校验必填数据
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年7月5日 上午11:42:33
	 */
//	public ResponseVo verify(UnFreezeAmtReqVo vo) throws Exception {
//		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"解冻成功！！");
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[] {
//				"bizId", "accountNo", "freezeId", "amount", "freezeType",
//				"bizSystem" }, new String[] { "订单编号", "账户编号",
//				"冻结编号", "解冻金额", "解冻类型", "业务系统" });
//        if (!errMsg.isEmpty()) {
//        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
//        	return res;
//        }
//        return res;
//	}
	
	/**
	 * 
	 * @Description:资金解冻处理
	 * @param: FreezeAmtVo
	 * @return: ResponseVo
	 * @throws Exception
	 * @author:	dingyaru
	 * @date:	2017年7月5日 上午11:46:00
	 */
	public ResponseVo unFreezen(UnFreezeAmtReqVo vo) throws Exception {
		List<AccountDetailVo> detail = new ArrayList<AccountDetailVo>();
		List<AccountVo> listVo = new ArrayList<AccountVo>();
		AccountVo accountVo = new AccountVo();
		accountVo.setBizId(vo.getBizId());
		accountVo.setBizSystem(vo.getBizSystem());
		accountVo.setBizType(getFreezeType(vo.getFreezeType()));//[42：提现解冻 44:还款解冻 22:资金解冻]
		
		AccountDetailVo accountDetailVo = new AccountDetailVo();
		accountDetailVo.setAmount(new BigDecimal(vo.getAmount()));
		accountDetailVo.setPayeeAccNo(vo.getAccountNo());
		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_IN);
		accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_CASH);
		
		accountDetailVo.setDraweeAccNo(vo.getAccountNo());
		accountDetailVo.setRivalSubType(getSubType(vo.getFreezeType()));
		/** 提现解冻时FreezeId的值必须是‘666666’  */
		if(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN.equals(vo.getFreezeType())){
			accountDetailVo.setRivalLendId(AccConst.SUB_BIZ_ID_WITHDRAW);//（取现）解冻账户
		}else {
			accountDetailVo.setRivalLendId(vo.getFreezeId());
		}
		accountDetailVo.setRemark("资金解冻！");
		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_JD_FREEZE);
		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_UNFREEZE);
		
		detail.add(accountDetailVo);
		
		accountVo.setDetail(detail);
		listVo.add(accountVo);
		return verifyRet(listVo);
	}
	/**
	 * 
	 * @Description:根据业务系统所传的解冻类型转换为账户自己的解冻类型
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年11月25日 下午2:20:01
	 */
	public String getFreezeType(String freezeType) throws Exception{
		if(AccConst.BUSI_TYPE_FUND_UNWITHDRAW.equals(freezeType)){//提现冻结
			freezeType = AccConst.BUSI_TYPE_FUND_UNWITHDRAW;
		}else if("1".equals(freezeType)){//资金解冻
			freezeType = AccConst.BUSI_TYPE_FUND_UNFROZEN;
		}else if("2".equals(freezeType)){//还款解冻
			freezeType = AccConst.BUSI_TYPE_FUND_UNREPAYMENT;
		}else if("3".equals(freezeType)){//出借解冻
			freezeType = AccConst.BUSI_TYPE_FUND_UNLEND;
		}else {
			throw new Exception("解冻类型:"+ freezeType +" 是错误的解冻类型！");
		}
		return freezeType;
	}

	/**
	 * 
	 * @Description: 根据解冻类型映射子账户类型
	 * @param: freezeType 解冻类型
	 * @return:String 子账户类型
	 * @author:	xianzhiqiang
	 * @date:	2017年7月25日 上午10:37:59
	 */
	public String getSubType(String freezeType) throws Exception{
		String subType = null;
		if(AccConst.BUSI_TYPE_FUND_UNWITHDRAW.equals(freezeType)){	//提现解冻
			subType = AccConst.SUB_ACC_TYPE_WITHDRAW;
		}else if("1".equals(freezeType)){	//资金解冻
			subType = AccConst.SUB_ACC_TYPE_FREEZE;
		}else if("2".equals(freezeType)){	//还款预处理账户
			subType = AccConst.SUB_ACC_TYPE_REPAYMENT_PRE;
		}else if("3".equals(freezeType)){	//出借解冻
			subType = AccConst.SUB_ACC_TYPE_LEND;
		}else {
			throw new Exception("解冻类型:"+ freezeType +" 是错误的解冻类型！");
		}
		return subType;
	}
	
	public String getService() {
		return "dictService";
	}
}
