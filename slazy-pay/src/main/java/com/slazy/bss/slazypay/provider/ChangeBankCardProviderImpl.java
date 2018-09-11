package com.slazy.bss.slazypay.provider;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slazy.bss.slazypay.model.BankCard;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.provider.BaseAccProvider;
import com.slazy.bss.slazypay.service.BankCardServiceImpl;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.service.SubAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.ChangeBankCardReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 换绑银行卡接口实现类
 * @author: dingyaru
 * @date: 2017年7月24日 下午7:08:54
 */
@Service("changeBankCardProvider")
public class ChangeBankCardProviderImpl implements BaseAccProvider<ChangeBankCardReqVo> {

	@Autowired
	private BankCardServiceImpl bankCardService;
	@Autowired
	private SubAccountServiceImpl subAccountService;
	@Autowired
	private MainAccountServiceImpl mainAccountService;

	@Override
	public ResponseVo handle(ChangeBankCardReqVo vo) {
		log.info("开始处理！请求参数：" + vo.toString());
		ResponseVo res = new ResponseVo();
		try {
			if (!AccConst.BANK_BIND_TYPE_BIND.equals(vo.getBindType()) && !AccConst.BANK_BIND_TYPE_CHANGE.equals(vo.getBindType())) {
				res.setRetInfo("换绑卡类型：" + vo.getBindType() + "错误！");
				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
				return res;
			}
			// 1.校验
//			res = verify(vo);
			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
				return res;
			}
			// 换绑银行卡
			res = changeBankCard(vo);
			if (AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
				if (AccConst.BANK_BIND_TYPE_CHANGE.equals(vo.getBindType())) {
					res.setRetInfo("换绑银行卡成功！！");				
					
				} else if (AccConst.BANK_BIND_TYPE_BIND.equals(vo.getBindType())) {
					res.setRetInfo("绑定银行卡成功！");
				} 
			}			
						
		} catch (Exception e) {
			log.error("处理失败：", e);
			res = AccUtil.returnHandle(e);
			throw new RuntimeException("换绑银行卡失败:" + e.getMessage());
		}
		log.info("处理结束！");
		return res;
	}

	/**
	 * 
	 * @Description:校验必输字段
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年7月27日 下午7:06:36
	 */
//	public ResponseVo verify(ChangeBankCardReqVo vo) throws Exception {
//		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,
//				"换绑银行卡成功！！");
//		List<String> errMsg = ValidateUtil.validateMust(vo, new String[] {
//				"bizId", "name", "idCardNo", "accountNo", "mobile", "bankId",
//				"nBankCardNo", "bindType", "bizSystem", "payChannel" }, new String[] {
//				"订单编号", "客户姓名", "身份证号", "账户编号", "新卡绑定手机号", "新卡银行编码", "新卡卡号",
//				"绑卡类型", "业务系统","支付通道" });
//		/** 绑卡类型为‘换卡’时‘原卡卡号’为必填字段 */
//		if (AccConst.BANK_BIND_TYPE_CHANGE.equals(vo.getBindType())) {
//			errMsg.addAll(ValidateUtil.validateMust(vo,
//					new String[] { "oBankCardNo" }, new String[] { "原卡卡号" }));
//		}
//		if (!errMsg.isEmpty()) {
//			res = new ResponseVo(AccConst.RET_CODE_FAILED_A, StringUtils.join(
//					errMsg, ";"));
//			return res;
//		}
//		return res;
//	}

	/**
	 * 
	 * @Description:换绑卡
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年7月27日 下午7:07:05
	 */
	@Transactional
	public ResponseVo changeBankCard(ChangeBankCardReqVo vo) throws Exception {
		ResponseVo res = new ResponseVo(AccConst.RET_CODE_SUCCESS_ZERO,"换绑银行卡成功！！");
		AccountVo accountVo = new AccountVo();
		accountVo.setBizSystem(vo.getBizSystem());
		accountVo.setBizType(vo.getBindType()); // 换卡
		accountVo.setBizId(vo.getBizId());
		
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
		
		/** 绑卡类型为‘换卡’时校验原卡信息并解绑 */
		if (AccConst.BANK_BIND_TYPE_CHANGE.equals(vo.getBindType())) {
			// 被换卡的银行卡账户信息
			BankCard bank = getOldBankCardNo(vo);
			if (bank == null) {
				res = new ResponseVo(AccConst.RET_CODE_FAILED_I, "账户编号:" + vo.getAccountNo() + "未绑卡，无法换卡");
				return res;
			}
			//被换掉的卡是否是已绑定的卡
			if(!vo.getoBankCardNo().equals(bank.getnBankCardNo())){
				res = new ResponseVo(AccConst.RET_CODE_FAILED_K, "账户编号:" + vo.getAccountNo() + "要解绑的卡与已绑定的卡不一致,无法换卡");
				return res;
			}
//			if(vo.getnBankCardNo().equals(bank.getnBankCardNo())){
//				res = new ResponseVo(AccConst.RET_CODE_FAILED_M, "账户编号:" + vo.getAccountNo() + "新卡卡号与原来被换卡卡号一致,不需要换卡");
//				return res;
//			}
			//记录中的客户姓名及身份证号和新传入的客户信息是否一致
			if(!vo.getIdCardNo().equals(bank.getIdCardNo()) || !vo.getName().equals(bank.getName())){
				res.setRetCode(AccConst.RET_CODE_FAILED_A);
				res.setRetInfo("账户编号："+ vo.getAccountNo() +"的客户姓名或身份证号输入有误");
				return res;
			}
			
			List<SubAccount> subList = null;
			//被换卡的银行卡账户信息是否存在
			subList = subAccountService.selectSubAccountInfo(vo.getAccountNo(), AccConst.SUB_ACC_TYPE_BANK, vo.getoBankCardNo(), null, vo.getBizSystem());
			if (subList != null && subList.size() > 0) {
				SubAccount subAccount = subList.get(0);
				if(AccConst.STATE_INVALID.equals(subAccount.getStatus())){
					res = new ResponseVo(AccConst.RET_CODE_FAILED_E,"账户编号:"+vo.getAccountNo()+"子账户"+ vo.getoBankCardNo() +"已停用 ！");
					return res;
				}
				// 更改被换卡的绑卡状态为已换卡
				bank.setBindType(AccConst.BANK_BIND_TYPE_CHANGE); // 换卡
				bank.setStatus(AccConst.STATE_VALID); // 有效
				bank.setModifyTime(new Date());
				bank.setRemark("换绑银行卡");
				bankCardService.updateByPrimaryKey(bank);

				// 更改被换卡的银行卡账户状态为禁用
				subAccount.setStatus(AccConst.STATE_INVALID);
				subAccountService.updateByPrimaryKey(subAccount);
				
				
				// 新卡是否是已换卡或已解绑的卡
				List<BankCard> bankCard = getNoBindBankInfo(vo);
				if(bankCard!=null && bankCard.size()>0){					
					List<SubAccount> subAccountList = null;
					// 新卡是否开过户
					subAccountList = subAccountService.selectSubAccounts( vo.getAccountNo(), AccConst.SUB_ACC_TYPE_BANK, vo.getnBankCardNo(), vo.getBizSystem());
					if (subAccountList != null && subAccountList.size() > 0) {	
						// 更改绑卡状态为已绑卡
						bankCard.get(0).setBindType(AccConst.BANK_BIND_TYPE_BIND); //绑卡
						bankCard.get(0).setStatus(AccConst.STATE_VALID); //有效
						bankCard.get(0).setModifyTime(new Date());
						bankCard.get(0).setRemark("银行卡绑定");
						bankCard.get(0).setBizSystem(vo.getBizSystem());
						bankCard.get(0).setIdCardNo(vo.getIdCardNo());
						bankCard.get(0).setMobile(vo.getMobile());
						bankCard.get(0).setnBankCardNo(vo.getnBankCardNo());
						bankCard.get(0).setNewBankInfo(vo.getNewBankInfo() == null ? "" : vo.getNewBankInfo());
						bankCard.get(0).setBankId(vo.getBankId());
						bankCard.get(0).setBankNumber(vo.getBankNumber() == null ? "" : vo.getBankNumber());
						bankCard.get(0).setCreateTime(new Date());
						bankCard.get(0).setPayChannel(vo.getPayChannel());
						bankCardService.updateByPrimaryKey(bankCard.get(0));
						
						SubAccount subAcc = subAccountList.get(0);
						subAcc.setStatus(AccConst.STATE_VALID);// 0:无效 1:有效
						// 更新子账户状态
						subAccountService.updateByPrimaryKey(subAcc);
					} else {
						res = new ResponseVo(AccConst.RET_CODE_FAILED_I, "账户编号:" + vo.getAccountNo() + ",新绑卡是已解绑或已换绑的卡，而银行卡子账户" + vo.getoBankCardNo() + "不存在");
						return res;
					}
				} else {
					// 保存银行卡信息
					openBankInfo(vo);
					// 开银行卡子账户
					openSubAccount(vo, AccConst.SUB_ACC_TYPE_BANK);
				}

			} else {
				res = new ResponseVo(AccConst.RET_CODE_FAILED_I, "账户编号:" + vo.getAccountNo() + ",被换卡的银行卡子账户" + vo.getoBankCardNo() + "不存在");
				return res;
			}

			/** 绑卡类型为‘绑卡’时校验当前账号是否已绑卡 */
		} else if (AccConst.BANK_BIND_TYPE_BIND.equals(vo.getBindType())) {
			//查询被绑卡账户是否已绑卡
			BankCard bankCard = getOldBankCardNo(vo);
			if (bankCard == null) {
				//新卡是否在银行卡表有记录
				List<BankCard> bank = getNoBindBankInfo(vo);
				if(bank!=null && bank.size()>0){
					//记录中的客户姓名及身份证号和新传入的客户信息是否一致
					if(!vo.getIdCardNo().equals(bank.get(0).getIdCardNo()) || !vo.getName().equals(bank.get(0).getName())){
						res.setRetCode(AccConst.RET_CODE_FAILED_A);
						res.setRetInfo("账户编号："+ vo.getAccountNo() +"的客户姓名或身份证号输入有误");
						return res;
					}
					List<SubAccount> subAccountList = null;
					subAccountList = subAccountService.selectSubAccountInfo(vo.getAccountNo(), AccConst.SUB_ACC_TYPE_BANK, vo.getnBankCardNo(), null, vo.getBizSystem());
					if (subAccountList != null && subAccountList.size() > 0) {
						// 更改新绑卡的绑卡状态为绑卡
						bank.get(0).setBindType(AccConst.BANK_BIND_TYPE_BIND); //绑卡
						bank.get(0).setStatus(AccConst.STATE_VALID); //有效
						bank.get(0).setModifyTime(new Date());
						bank.get(0).setRemark("银行卡绑定");
						bank.get(0).setBizSystem(vo.getBizSystem());
						bank.get(0).setIdCardNo(vo.getIdCardNo());
						bank.get(0).setMobile(vo.getMobile());
						bank.get(0).setnBankCardNo(vo.getnBankCardNo());
						bank.get(0).setNewBankInfo(vo.getNewBankInfo() == null ? "" : vo.getNewBankInfo());
						bank.get(0).setBankId(vo.getBankId());
						bank.get(0).setBankNumber(vo.getBankNumber() == null ? "" : vo.getBankNumber());
						bank.get(0).setCreateTime(new Date());
						bank.get(0).setPayChannel(vo.getPayChannel());
						bankCardService.updateByPrimaryKey(bank.get(0));
						
						SubAccount subAcc = subAccountList.get(0);
						subAcc.setStatus(AccConst.STATE_VALID);// 0:无效 1:有效
						// 更新子账户状态
						subAccountService.updateByPrimaryKey(subAcc);
						
					}else {
						res = new ResponseVo(AccConst.RET_CODE_FAILED_I, "账户编号:" + vo.getAccountNo() + ",新绑卡是已解绑或已换绑的卡，而银行卡子账户" + vo.getoBankCardNo() + "不存在");
						return res;
					}
				} else {
					// 保存银行卡信息
					openBankInfo(vo);
					// 开银行卡子账户
					openSubAccount(vo, AccConst.SUB_ACC_TYPE_BANK);
				} 
			
			} else {
				res = new ResponseVo(AccConst.RET_CODE_FAILED_M, "账户编号:" + vo.getAccountNo() + "已绑过卡，无需再绑卡！");
				return res;
			}
			
			
		}
		return res;

	}

	/**
	 * 
	 * @Description:保存银行卡信息
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年11月14日 上午9:55:38
	 */
	public int openBankInfo(ChangeBankCardReqVo vo) throws Exception {
		BankCard newBank = new BankCard();
		newBank.setAccountNo(vo.getAccountNo());
		newBank.setBindType(AccConst.BANK_BIND_TYPE_BIND); // 绑卡
		newBank.setBizId(vo.getBizId());
		newBank.setBizSystem(vo.getBizSystem());
		newBank.setIdCardNo(vo.getIdCardNo());
		newBank.setMobile(vo.getMobile());
		newBank.setnBankCardNo(vo.getnBankCardNo());
		newBank.setNewBankInfo(vo.getNewBankInfo() == null ? "" : vo
				.getNewBankInfo());
		newBank.setName(vo.getName());
		newBank.setStatus(AccConst.STATE_VALID);
		newBank.setRemark("银行卡绑定");
		newBank.setBankId(vo.getBankId());
		newBank.setBankNumber(vo.getBankNumber() == null ? "" : vo
				.getBankNumber());
		newBank.setCreateTime(new Date());
		newBank.setModifyTime(new Date());
		newBank.setPayChannel(vo.getPayChannel());
		
		int newCount = bankCardService.insert(newBank);
		if (newCount <= 0) {
			throw new Exception("账户编号:" + vo.getAccountNo() + "的绑卡信息保存失败！");
		}
		return newCount;
	}

	/**
	 * 
	 * @Description: 开子账户
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年8月8日 下午5:57:05
	 */
	public void openSubAccount(ChangeBankCardReqVo vo, String subType)
			throws Exception {
		SubAccount sub = new SubAccount();
		sub.setAmount(BigDecimal.ZERO);
		sub.setBizSystem(vo.getBizSystem());
		sub.setStatus(AccConst.STATE_VALID); // 0:禁用 1:可用
		sub.setAccountNo(vo.getAccountNo());
		sub.setSubType(subType);
		if (AccConst.SUB_ACC_TYPE_BANK.equals(subType)) {
			sub.setLendId(vo.getnBankCardNo());
		}
		sub.setUnit(AccConst.CURRENCY_UNIT_RMB);
		// 加密原始数据拼装
		String tempStr = vo.getBizSystem() + vo.getAccountNo()
				+ AccConst.AMOUNT_ZERO + subType + sub.getLendId();
		String value1 = AccUtil.string2MD5(tempStr);
		sub.setValue1(value1);
		subAccountService.insert(sub);

	}

	/**
	 * 
	 * @Description:被换卡信息是否存在
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年11月29日 下午6:50:54
	 */
	public BankCard getOldBankCardNo(ChangeBankCardReqVo vo) {
		BankCard newBank = new BankCard();
		newBank.setAccountNo(vo.getAccountNo());
		newBank.setBizSystem(vo.getBizSystem());
		newBank.setBindType(AccConst.BANK_BIND_TYPE_BIND);
		// 被绑卡账户是否在银行卡表有记录
		BankCard bankCard = bankCardService.selectBankByAccountNo(newBank);
		return bankCard;
	}

	/**
	 * 
	 * @Description:新换卡是否在银行卡表有记录（新卡是否是已换卡或者已解绑的卡）
	 * @param:
	 * @return:
	 * @throws
	 * @author: dingyaru
	 * @date: 2017年11月29日 下午6:56:46
	 */
	public List<BankCard> getNoBindBankInfo(ChangeBankCardReqVo vo) {
		BankCard newBank = new BankCard();
		newBank.setAccountNo(vo.getAccountNo());
		newBank.setnBankCardNo(vo.getnBankCardNo());
		newBank.setBizSystem(vo.getBizSystem());
		// 新换卡是否在银行卡表有记录
		List<BankCard> bankCard = bankCardService.selectBankByAccNo(newBank);
		return bankCard;
	}


}
