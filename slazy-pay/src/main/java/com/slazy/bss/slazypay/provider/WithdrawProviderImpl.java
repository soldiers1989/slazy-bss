package com.slazy.bss.slazypay.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.BankCard;
import com.slazy.bss.slazypay.model.Dict;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.service.BankCardServiceImpl;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.service.RecordAccountServiceImpl;
import com.slazy.bss.slazypay.service.SubAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.WithdrawReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 提现
 * @author:	dingyaru
 * @date:	2017年7月5日 下午3:43:31
 */
@Service("withdrawProvider")
public class WithdrawProviderImpl extends AbstractAccProvider implements BaseAccProvider<WithdrawReqVo>{
	private static final Logger log = LoggerFactory.getLogger(WithdrawProviderImpl.class);
	
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	
	public ResponseVo handle(WithdrawReqVo vo) {
		log.info("开始处理提现！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		try {
			//1.校验
//			res = verify(vo);
//			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
//				return res;
//			}
			//2.提现记账
			res = withdraw(vo);
			
		} catch (Exception e) {
			log.error("处理提现失败：",e);
			res = AccUtil.returnHandle(e);
		}
		
		log.info("处理提现结束！");
		return res;
	}
	
	/**
	 * 
	 * @Description: 数据校验
	 * @param: WithdrawReqVo
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年7月6日 下午3:28:54
	 */
//	public ResponseVo verify(WithdrawReqVo vo) throws Exception {
//		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"提现成功！！");
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
//        		"bizId","tranAmount","accountNo","name","bankCardNo","bizSystem","tradeTime","feeAmount","withdrawType"
//        }, new String[]{"订单编号","交易金额","账户编号","客户姓名","银行卡号","业务系统","交易时间","手续费","提现类型"});
//        
//        if (!errMsg.isEmpty()) {
//        	res = new ResponseVo(AccConst.RET_CODE_FAILED_A,StringUtils.join(errMsg, ";"));
//        	return res;
//        }
//        return res;
//	}
	
	/**
	 * 
	 * @Description:提现记账
	 * @param:
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年7月6日 下午3:29:26
	 */
	public ResponseVo withdraw(WithdrawReqVo vo) throws Exception {
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"提现成功！！");
		List<AccountDetailVo> detail = new ArrayList<AccountDetailVo>();
		List<AccountVo> listVo = new ArrayList<AccountVo>();
		AccountVo accountVo = new AccountVo();
		accountVo.setBizSystem(vo.getBizSystem());
		accountVo.setBizType(vo.getWithdrawType()); //提现类型 ： 1.微信提现  50.支付宝
		accountVo.setBizId(vo.getBizId());
		accountVo.setTradeTime(vo.getTradeTime()); 
		
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
		//个人账户需绑卡做验证，公司账户不需绑卡不做验证
		/*if (AccConst.CUSTOMER_TYPE_PERSONAL.equals(mainAccount.getCustomerType())) {
			BankCard oldBank = new BankCard();
			oldBank.setAccountNo(vo.getAccountNo());
			oldBank.setBizSystem(vo.getBizSystem());
			oldBank.setBindType(AccConst.BANK_BIND_TYPE_BIND);
			
			BankCard bank = bankCardService.selectBankByAccountNo(oldBank);
			
			if(bank == null){
				res = new ResponseVo(AccConst.RET_CODE_FAILED_J,"账户编号:"+vo.getAccountNo()+"未绑定银行卡！");
				return res;
			}
			
			List<SubAccount> subList = null;
			List<SubAccount> subAccountList = null;			
			subList = subAccountService.selectSubAccountInfo(vo.getAccountNo(), AccConst.SUB_ACC_TYPE_BANK, bank.getnBankCardNo(), null, vo.getBizSystem());
			if (subList == null || subList.size() <= 0) {
				res = new ResponseVo(AccConst.RET_CODE_FAILED_I,"银行卡账户:"+vo.getAccountNo()+"不存在 ！");
				return res;
			}
			subAccountList = subAccountService.selectSubAccountInfo(vo.getAccountNo(), AccConst.SUB_ACC_TYPE_BANK, bank.getnBankCardNo(), AccConst.STATE_VALID, vo.getBizSystem());
			if (subAccountList == null || subAccountList.size() <= 0) {
				res = new ResponseVo(AccConst.RET_CODE_FAILED_E,"银行卡账户:"+vo.getAccountNo()+"已停用 ！");
				return res;
			}
		}*/
		AccountDetailVo accountDetailVo = new AccountDetailVo();
		BigDecimal tranAmount = AccUtil.StrToBigDecimal(vo.getTranAmount());
		tranAmount = tranAmount.subtract(AccUtil.StrToBigDecimal(vo.getFeeAmount())); //减去提现手续费金额
		/* -------- （取现）冻结账户 ----------> 收款卡户---------*/
		/* -------（取现）冻结账户 -------- */
		accountDetailVo.setAmount(tranAmount);
		accountDetailVo.setDraweeAccNo(vo.getAccountNo());
		accountDetailVo.setRivalSubType(AccConst.SUB_ACC_TYPE_WITHDRAW);
		accountDetailVo.setRivalLendId(AccConst.SUB_BIZ_ID_WITHDRAW);//（取现）冻结账户
		/* ------- 收款卡户 -------- */
		accountDetailVo.setPayeeAccNo(vo.getAccountNo());
		accountDetailVo.setSubType(getSubType(vo.getWithdrawType()));
		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_IN);
		accountDetailVo.setRemark("实际提现金额");
		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_TRANSFER);
		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_WITHDRAW_FREEZE);
	
		detail.add(accountDetailVo);
	
		
		//使用第三方支付的手续费
		accountDetailVo = new AccountDetailVo();
		/** 公司手续费账户----------------> 公司收取手续费账户 **/
		accountDetailVo.setAccountDir(AccConst.ACC_DIR_TYPE_OUT);
		/** ---------手续费账户--------- **/
		accountDetailVo.setPayeeAccNo(AccConst.COMPANY_ACCOUNT_POUNDAGE_OUT_ACC);
		accountDetailVo.setSubType(AccConst.SUB_ACC_TYPE_CASH);
		accountDetailVo.setAmount(AccUtil.StrToBigDecimal(vo.getFeeAmount()));
		/** --------- 收取手续费账户--------- **/
		accountDetailVo.setDraweeAccNo(AccConst.COMPANY_ACCOUNT_POUNDAGE_IN_ACC);
		accountDetailVo.setRivalSubType(AccConst.SUB_ACC_TYPE_CASH);		
		accountDetailVo.setRemark("发布订单支付手续费记账！");
		accountDetailVo.setCapitalName(AccConst.CAPITAL_NAME_SXF_FREEZE);
		accountDetailVo.setCapitalType(AccConst.CAPITAL_TYPE_PAY);
		detail.add(accountDetailVo);
		
		accountVo.setDetail(detail);
		listVo.add(accountVo);
		return verifyRet(listVo);
	}
	public String getService() {
		return "dictService";
	}

	public static String getSubType(String type) {
		
		if("1".equals(type)) {
			return AccConst.SUB_ACC_TYPE_WX;
		}else {
			return AccConst.SUB_ACC_TYPE_ZFB;
		}
		
	}
}
