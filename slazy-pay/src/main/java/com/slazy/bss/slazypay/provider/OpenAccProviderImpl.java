package com.slazy.bss.slazypay.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.service.BankCardServiceImpl;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.service.SubAccountServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.ValidateUtil;
import com.slazy.bss.slazypay.vo.req.OpenAccReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description:开户服务
 * @author:	wangyanan
 * @date:	2017-9-13 下午3:41:43
 */
@Service
@Transactional
public class OpenAccProviderImpl implements BaseAccProvider<OpenAccReqVo> {
	
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Autowired
	private SubAccountServiceImpl subAccountService;
	
	@Override
	public ResponseVo handle(OpenAccReqVo vo) {
		
		log.info("开始处理开户！请求参数："+ JSON.toJSONString(vo));
		ResponseVo res = new ResponseVo();
		res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		res.setRetInfo("开户成功！");
		try {
			//校验
			res = verify(vo,res);
			if (!AccConst.RET_CODE_SUCCESS_ZERO.equals(res.getRetCode())) {
				return res;
			}
			saveMainAccount(vo);
			saveBank(vo);
			
		} catch (Exception e) {
			log.error("开户失败:",e);
			if(e.getClass().getName().contains("DuplicateKeyException")){
				throw new RuntimeException("开户失败:重复开户！");
			}else{
				throw new RuntimeException("开户失败:" + e.getMessage());
			}
			
			
		}
		log.info("开户完毕！");
		return res;
	}
	
	/**
	 * 
	 * @Description: 校验
	 * @param:  OpenAccReqVo
	 * @throws  Exception
	 * @author:	wangyanan
	 * @date:	2017-9-13 下午3:50:34
	 */
	private ResponseVo verify(OpenAccReqVo vo,ResponseVo res) throws Exception{
		//校验必填数据
        List<String> errMsg = ValidateUtil.validateMust(vo, new String[]{
                "mobile",
                "customerType"}, new String[]{
                "电话号码",
                "客户类别"
        });
        if (!errMsg.isEmpty()) {
        	res.setRetCode(AccConst.RET_CODE_FAILED_A);
        	res.setRetInfo(StringUtils.join(errMsg, ";"));
        	return res;
        }

		/*一个证件号只能开一个户*/
//		MainAccount cMainAccount = mainAccountService.selectTheMainAccount(null,vo.getCerificateType(),vo.getCertificateNo());
//		if(cMainAccount!=null){
//			res.setRetCode(AccConst.RET_CODE_FAILED_G);
//			res.setRetInfo("证件类型"+vo.getCerificateType()+",证件号码"+vo.getCertificateNo()+"的账户已存在！");
//		}
		return res;
	}
	
	/**
	 * 
	 * @Description: 保存主账户信息
	 * @param: 	OpenAccReqVo
	 * @throws  Exception
	 * @author:	wangyanan
	 * @date:	2017-9-13 下午3:50:56
	 */
	public void saveMainAccount(OpenAccReqVo vo) throws Exception{
		MainAccount mAcc = new MainAccount();
		mAcc.setAccountNo(vo.getMobile());
		mAcc.setCustomerIdType("L");
		mAcc.setCustomerType(vo.getCustomerType());
		mAcc.setName(vo.getMobile());
		mAcc.setMobile(vo.getMobile());
		mAcc.setStatus(AccConst.STATE_VALID);//可用
		mAcc.setActivationType(AccConst.ACTIVAT_VALID);//激活状态[ 0:未激活  1:已激活]
		mainAccountService.insert(mAcc);
		//开现金子账户
		openSubAccount(vo,AccConst.SUB_ACC_TYPE_CASH);
	}
	
	/**
	 * 
	 * @Description: 开户初始化子账户
	 * @param: OpenAccReqVo
	 * @throws Exception
	 * @author:	wangyanan
	 * @date:	2017-9-14 上午11:20:47
	 */
	public void openSubAccount(OpenAccReqVo vo,String subType) throws Exception{
	      SubAccount sub = new SubAccount();
	      sub.setAmount(BigDecimal.ZERO);
	      sub.setStatus(AccConst.STATE_VALID); //0:禁用 1:可用
	      sub.setAccountNo(vo.getMobile());
	      sub.setSubType(subType);
	      if(AccConst.SUB_ACC_TYPE_BANK.equals(subType)){
	    	  sub.setLendId(vo.getCardNo());
	      }
	      sub.setUnit(AccConst.CURRENCY_UNIT_RMB);
	      //加密原始数据拼装
	      /*String tempStr = vo.getBizSystem()+accountNo+AccConst.AMOUNT_ZERO+subType+sub.getLendId();
	      String value1 = AccUtil.string2MD5(tempStr);
	      sub.setValue1(value1);*/
	      subAccountService.insert(sub);
	     
	   }
	
	/**
	 * 
	 * @Description: 保存银行绑卡信息
	 * @param:
	 * @return:
	 * @throws Exception 
	 * @author:	wangyanan
	 * @date:	2017-9-14 上午11:21:08
	 */
	public void saveBank(OpenAccReqVo vo) throws Exception{
	/*	BankCard bank = new BankCard();
		bank.setAccountNo(accountNo);
		bank.setBindType(AccConst.BANK_BIND_TYPE_BIND);
		bank.setBizId(vo.getBizId());
		bank.setBizSystem(vo.getBizSystem());
		bank.setIdCardNo(vo.getCertificateNo());
		bank.setMobile(vo.getReservedMobile());
		bank.setnBankCardNo(vo.getCardNo());
		bank.setName(vo.getName());
		bank.setStatus(AccConst.STATE_VALID);
		bank.setRemark("银行卡绑定");
		bank.setBankId(vo.getBankCode());
		bank.setPayChannel(vo.getPayChannel());
		bankCardService.insert(bank);*/
		//开支付账户
		List<String> list = new ArrayList<String>();
		list.add(AccConst.SUB_ACC_TYPE_WX);
		list.add(AccConst.SUB_ACC_TYPE_ZFB);
		for(String subType:list) {
			openSubAccount(vo,subType);
		}
		
	}

}
