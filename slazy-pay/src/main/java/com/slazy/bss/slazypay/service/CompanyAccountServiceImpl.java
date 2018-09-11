package com.slazy.bss.slazypay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.slazyframework.utils.Date2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.Page;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.provider.FreezeAmtProviderImpl;
import com.slazy.bss.slazypay.provider.WithdrawProviderImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.utils.SnUtil;
import com.slazy.bss.slazypay.vo.req.CompanyAccountReqVo;
import com.slazy.bss.slazypay.vo.req.FreezeAmtReqVo;
import com.slazy.bss.slazypay.vo.req.WithdrawReqVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 公司账户服务
 * @author:	xianzhiqiang
 * @date:	2017年7月13日 下午2:23:02
 */
@Service("companyAccountService")
@Transactional
public class CompanyAccountServiceImpl{

	private  static Logger log = Logger.getLogger(CompanyAccountServiceImpl.class);
	
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Autowired
	private SubAccountServiceImpl subAccountService;
	
	@Autowired
	private SubAccountDetailServiceImpl subAccountDetailService;
	
//	@Autowired
//	private WithdrawProvider withdrawProvider;
	
	@Autowired
	private FreezeAmtProviderImpl freezeAmtProvider;
	
	@Autowired
	private WithdrawProviderImpl withdrawProviderImpl;//账户提现
	
	public ResponseVo createCompanyMainAcc(CompanyAccountReqVo vo,String pId) {
		log.info("开始处理新建/修改公司账户！");
		ResponseVo res = new ResponseVo();
		try{
			MainAccount oldMAcc = null;
			
			if(vo.getId()!=null&&!"".equals(vo.getId())){
				oldMAcc = mainAccountService.selectByPrimaryKey(Long.parseLong(vo.getId()));
			}
			
			if(oldMAcc==null){//执行新增
				log.info("准备处理新建公司账户！");
				MainAccount mAcc = new MainAccount();
				mAcc.setBizId(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));
				mAcc.setAccountNo(SnUtil.getSn(AccConst.ACCOUNT_NO_SN, AccConst.ACCOUNT_NO_SN_PREFIX, AccConst.ACCOUNT_NO_SN_LEN));
				mAcc.setBizSystem(vo.getBizSystem());
				mAcc.setCerificateType("");//证件类型
				mAcc.setCertificateNo(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));//证件号码
				mAcc.setCustomerIdType("");//客户身份类型
				mAcc.setCustomerType(AccConst.CUSTOMER_TYPE_COMPANY);//客户类别[0:个人,1:公司]
				mAcc.setName(vo.getName());
				mAcc.setMobile("");
				mAcc.setStatus(AccConst.STATE_VALID);//可用
				mAcc.setOpenAccountTime(new Date());
				mAcc.setpId(vo.getpId());
				mAcc.setRemark(vo.getRemark());
				mAcc.setOperator(vo.getOperator());
				if(pId!=null&&!"".equals(pId)){//存在pId则为新增公司子账户
					mAcc.setpId(pId);
				}
				mAcc.setActivationType(AccConst.ACTIVAT_VALID);
				mainAccountService.insert(mAcc);
				if(pId!=null&&!"".equals(pId)){//存在pId则为新增公司子账户
					openSubAccount(mAcc);
				}
				res.setRetInfo("创建成功！");
			}else{//执行修改
				log.info("准备处理修改公司账户！");
				oldMAcc.setBizSystem(vo.getBizSystem());
				oldMAcc.setRemark(vo.getRemark());
				oldMAcc.setName(vo.getName());
				mainAccountService.updateByPrimaryKey(oldMAcc);
				res.setRetInfo("修改成功！");
			}
			res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		}catch(Exception e){
			log.error("新建/修改公司账户失败:",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		log.info("新建/修改公司账户完毕！");
		return res;
	}

	/**
	 * 
	 * @Description: 开子账户
	 * @param: MainAccount
	 * @throws Exception
	 * @author:	xianzhiqiang
	 * @date:	2017年7月13日 下午5:34:07
	 */
	public void openSubAccount(MainAccount mAcc) throws Exception{
	      SubAccount sub = new SubAccount();
	      sub.setAmount(BigDecimal.ZERO);
	      sub.setBizSystem(mAcc.getBizSystem());
	      sub.setStatus(AccConst.STATE_VALID); //0:禁用 1:可用
	      sub.setAccountNo(mAcc.getAccountNo());
	      sub.setSubType(AccConst.SUB_ACC_TYPE_CASH);
	      sub.setUnit(AccConst.CURRENCY_UNIT_RMB);
	      //加密原始数据拼装
	      String tempStr = mAcc.getBizSystem()+mAcc.getAccountNo()+AccConst.AMOUNT_ZERO+AccConst.SUB_ACC_TYPE_CASH+"null";
	      String value1 = AccUtil.string2MD5(tempStr);
	      sub.setValue1(value1);
	      subAccountService.insert(sub);
	     
	   }

	public ResponseVo updateCompanyAcc(CompanyAccountReqVo vo) {
		log.info("开始处理修改公司账户！");
		ResponseVo res = new ResponseVo();
		try{
			MainAccount mAcc = mainAccountService.selectAccByAccountNo(vo.getAccountNo(), vo.getBizSystem());
			mAcc.setBizSystem(vo.getNewBizSystem());
			mAcc.setName(vo.getName());
			res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
			res.setRetInfo("修改成功！");
		}catch(Exception e){
			log.error("修改公司账户失败:",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
		log.info("修改公司账户完毕！");	
		return res;
	}

	public Page selectCompanyList(Page page,String pId)  {
		log.info("开始获取公司主账户列表！");
		List<CompanyAccountReqVo> list = mainAccountService.selectCompanyAccount(page,pId);
		int count = mainAccountService.selectCompanyCountAccount(page, pId);
		page.setResult(list);
		page.setTotalCount(count); 
		log.info("获取公司主账户列表结束！");
		return page;
	}

	public Page selectCompanyDetail(CompanyAccountReqVo vo,Page page,boolean isExport) {
		List<SubAccountDetail> list = new ArrayList<SubAccountDetail>();
		//isExport为真则为导出文件所用
		if(isExport){
			list = subAccountDetailService.selectCompanyDetail(vo,null);
		}else{
			list = subAccountDetailService.selectCompanyDetail(vo,page);
			int count = subAccountDetailService.selectCompanyDetailCount(vo);
			page.setTotalCount(count); 
		}
		page.setResult(list);
		return page;
	}

	public List<SubAccountDetail> selectCompanyDetailByIds(String ids) {
		String[] array = ids.split(",");
		return subAccountDetailService.selectCompanyDetailByIds(array);
	}

	public ResponseVo withdraw(CompanyAccountReqVo vo) {
		ResponseVo res = new ResponseVo();
		res.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		res.setRetInfo("执行完毕！");
//		BaseGatewayResVO b_Res = null ;
//		ReqWithdrawVO request = new ReqWithdrawVO();
		try {
			//公司主账户信息
//			MainAccount mainAcc = mainAccountService.selectByPrimaryKey(Long.parseLong(vo.getpId()));
			
//			if(!AccConst.BANK_BIND_TYPE_BIND.equals(mainAcc.getBindType())){
//				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
//				res.setRetInfo("公司主账户未绑卡！");
//				return res;
//			}
			//账务提现冻结参数组装
			FreezeAmtReqVo freezeVo = new FreezeAmtReqVo();
			freezeVo.setBizId(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));
			freezeVo.setBizSystem(vo.getBizSystem());
			freezeVo.setAccountNo(vo.getAccountNo());
			freezeVo.setFreezeId(AccConst.SUB_BIZ_ID_WITHDRAW);
			freezeVo.setAmount(AccUtil.bigDecimal2String(vo.getAmount()));
			freezeVo.setFreezeType(AccConst.BUSI_TYPE_FUND_WITHDRAW_FROZEN);
			//执行提现冻结
			ResponseVo accRes = freezeAmtProvider.handle(freezeVo);
			//冻结异常
			if(!AccConst.RET_CODE_SUCCESS_ZERO.equals(accRes.getRetCode())){
				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
				res.setRetInfo(accRes.getRetInfo());
				return res;
			}
			//发送存管提现参数组装
//			request.setBizId(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));
//			request.setBizSystem(vo.getBizSystem());
//			request.setBizAccountNo(vo.getAccountNo());
//			request.setCustomerId(mainAcc.getAccountNo());//企业账户编号(对应银行的)
//			request.setAmount(AccUtil.bigDecimal2String(vo.getAmount()));
//			request.setFeeAmount("0");
//			request.setRedirectUrl(AccConst.TX_URL);
//			request.setWithdrawType(AccConst.WITHDRAW_TYPE);
//			request.setWithdrawForm(AccConst.WITHDRAW_FORM);
//			request.setCustodyAmount("0");
//			log.info("执行公司账户提现！请求参数："+ JSON.toJSONString(request));
			//执行提现
//			b_Res = withdrawProvider.handle(request);
			//返回结果非成功
//			if(!AccConst.RET_CODE_SUCCESS_ZERO.equals(b_Res.getRetCode())){
//				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
//				res.setRetInfo(b_Res.getRetInfo());
//			}
//			res.setObj(AccUtil.getGatewayUrl(b_Res));
			
			
			//存管返回的是同步还是异步的，根据实际情况，看是否需要下面代码
			//账务提现参数组装
			MainAccount mainAccount = mainAccountService.selectAccByAccountNo(vo.getAccountNo(), vo.getBizSystem());
			WithdrawReqVo withdrawReqVo = new WithdrawReqVo();
			withdrawReqVo.setBizId(SnUtil.getSn(AccConst.BIZ_ID_SN, AccConst.BIZ_ID_SN_PREFIX, AccConst.BIZ_ID_SN_LEN));
			withdrawReqVo.setBizSystem(vo.getBizSystem());
			withdrawReqVo.setTranAmount(AccUtil.bigDecimal2String(vo.getAmount()));
			withdrawReqVo.setAccountNo(vo.getAccountNo());
			withdrawReqVo.setName(mainAccount.getName());
			withdrawReqVo.setWithdrawType(AccConst.BUSI_TYPE_WITHDRAW);
			withdrawReqVo.setBankCardNo(AccConst.SUB_BIZ_ID_VIRTUAL);
			withdrawReqVo.setTradeTime(Date2Utils.thisDateTime());
			withdrawReqVo.setFeeAmount("0");
			ResponseVo witResVo = withdrawProviderImpl.handle(withdrawReqVo);
			//返回非成功
			if(!AccConst.RET_CODE_SUCCESS_ZERO.equals(witResVo.getRetCode())){
				res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
				res.setRetInfo(witResVo.getRetInfo());
				return res;
			}
		} catch (Exception e) {
			log.error("公司账户提现异常：",e);
			res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			res.setRetInfo(e.getMessage());
		}
//		log.info("bizId:"+request.getBizId()+" code:"+b_Res.getRetCode()+" info:"+b_Res.getRetInfo());
		return res;
	}
	
	public Page selectCompanySubList(Page page,String pId)  {
		log.info("开始获取公司子账户列表！");
		List<CompanyAccountReqVo> list = mainAccountService.selectCompanySubAccount(page,pId);
		int count = mainAccountService.selectCompanyCountSubAccount(page, pId);
		page.setResult(list);
		page.setTotalCount(count); 
		log.info("获取公司子账户列表结束！");
		return page;
	}
}
