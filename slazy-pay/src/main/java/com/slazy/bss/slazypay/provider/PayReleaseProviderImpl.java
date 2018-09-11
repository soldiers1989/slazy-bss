package com.slazy.bss.slazypay.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.BankCard;
import com.slazy.bss.slazypay.model.Dict;
import com.slazy.bss.slazypay.service.BankCardServiceImpl;
import com.slazy.bss.slazypay.service.RecordAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.utils.ValidateUtil;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.RechargeReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 订单发布支付记账接口实现类
 */
@Service("payReleaseProvider")
public class PayReleaseProviderImpl extends AbstractAccProvider implements BaseAccProvider<RechargeReqVo> {
	
//	@Autowired
//	private ISysService dictService;

	@Override
	public ResponseVo handle(RechargeReqVo vo) {
		log.info("开始处理订单发布支付记账！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			//1.校验
			res = verify(vo);
			if(!res.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
				return res;
			}
			//2.记账
			res = recharge(vo);
			
		} catch (Exception e) {
			log.error("处理订单发布支付记账失败：",e);
			res = AccUtil.returnHandle(e);
		}
		
		log.info("处理订单发布支付记账结束！");
		return res;
	}

	/**
	 * 
	 * @Description: 充值记账
	 * @param: RechargeReqVo
	 * @return:
	 * @throws Exception
	 * @author:	dingyaru
	 * @date:	2017年7月6日 下午5:56:52
	 */
	public ResponseVo verify(RechargeReqVo vo) throws Exception {
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"充值成功！");
		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
        		"bizId","tranAmount","accountNo","payChannel","tradeTime","feeAmount"
        }, new String[]{"订单编号","交易金额","账户编号","充值渠道","交易时间","手续费"});
        
        if (!errMsg.isEmpty()) {
        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
        	return res;
        }
		return res;
	}
	
	/**
	 * 
	 * @Description: 充值记账
	 * @param: RechargeReqVo
	 * @return:
	 * @throws Exception
	 * @author:	dingyaru
	 * @date:	2017年7月6日 下午5:57:32
	 */
	public ResponseVo recharge(RechargeReqVo vo) throws Exception {
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"处理完毕！");
		List<AccountDetailVo> detail = new ArrayList<AccountDetailVo>();
		List<AccountVo> listVo = new ArrayList<AccountVo>();
		AccountVo accountVo = new AccountVo();
		accountVo.setBizType(vo.getPayChannel());  //支付类型 ：16.微信支付  17.支付宝支付
		accountVo.setBizId(vo.getBizId());
		accountVo.setTradeTime(vo.getTradeTime()); 
		
		AccountDetailVo accountDetailVo = new AccountDetailVo();
		/** 支付账户----------------> 备付金账户 **/
		BigDecimal tranAmount = AccUtil.StrToBigDecimal(vo.getTranAmount());  //支付金额
//		tranAmount = tranAmount.subtract(AccUtil.StrToBigDecimal(vo.getFeeAmount())); //减去服务费金额
		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_IN);
		/** ---------备付金账户--------- **/
		accountDetailVo.setPayeeAccNo(AccConst.COMPANY_ACCOUNT_PROVISION_ACC);
		accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_CASH);
		accountDetailVo.setAmount(tranAmount);
		/** --------- 支付账户--------- **/
		accountDetailVo.setDraweeAccNo(vo.getAccountNo());
		accountDetailVo.setRivalSubType(vo.getPayChannel());		
		accountDetailVo.setRemark("发布订单支付记账！");
		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_PAY_RELEASE);
		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_PAY);
		detail.add(accountDetailVo);
		
		//使用第三方支付的手续费
//		accountDetailVo = new AccountDetailVo();
//		/** 公司手续费账户----------------> 收取手续费账户 **/
//		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_OUT);
//		/** ---------手续费账户--------- **/
//		accountDetailVo.setPayeeAccNo(AccConst.COMPANY_ACCOUNT_POUNDAGE_OUT_ACC);
//		accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_CASH);
//		accountDetailVo.setAmount(AccUtil.StrToBigDecimal(vo.getFeeAmount()));
//		/** --------- 收取手续费账户--------- **/
//		accountDetailVo.setDraweeAccNo(AccConst.COMPANY_ACCOUNT_POUNDAGE_IN_ACC);
//		accountDetailVo.setRivalSubType(AccConst.SUB_ACC_TYPE_CASH);		
//		accountDetailVo.setRemark("发布订单支付手续费记账！");
//		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_SXF_FREEZE);
//		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_PAY);
//		detail.add(accountDetailVo);
		
		accountVo.setDetail(detail);
		listVo.add(accountVo);
		return verifyRet(listVo);
	}
	public String getService() {
		return "dictService";
	}
}
