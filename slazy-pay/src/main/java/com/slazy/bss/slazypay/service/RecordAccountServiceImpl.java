package com.slazy.bss.slazypay.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.slazyframework.utils.Date2Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.exception.BalanceException;
import com.slazy.bss.slazypay.exception.FalsifyException;
import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.model.Trade;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.utils.ModelUtil;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.res.AccountResVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;

/**
 * 
 * @Description: 记账服务
 * @author:	xianzhiqiang
 * @date:	2017年6月26日 下午2:00:31
 */
@Service
@Transactional(isolation=Isolation.READ_COMMITTED)
public class RecordAccountServiceImpl{
    private	final static Logger log = Logger.getLogger(RecordAccountServiceImpl.class);
	@Autowired
	private SubAccountServiceImpl subAccountService;
	
	@Autowired
	private TradeServiceImpl tradeService;
	
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Autowired
	private SubAccountDetailServiceImpl subAccountDetailService;
	
	
	public ResponseVo handle(List<AccountVo> listVo,TreeSet<Long> ids){
		Long startTime = System.currentTimeMillis();
		log.info("开始处理记账！请求参数："+ JSON.toJSONString(listVo));
		ResponseVo responseVo = new ResponseVo();
		List<AccountResVo> retList = new ArrayList<AccountResVo>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		boolean rollbackFlag = false;
		
		try {
			//2.执行获取锁操作
			getLock(ids);
		} catch (Exception e) {
			log.error("执行记账时,锁异常！",e);
			responseVo.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			responseVo.setRetInfo(e.getMessage());
			return responseVo;
		}
		//3.记账,保存交易记录
		retList = saveSubAcc(listVo);
		//4.检查记账结果是否需要执行回滚
		for(AccountResVo res:retList){
			if(!res.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
				//需要执行回滚
				rollbackFlag = true;
			}
		}
		if(rollbackFlag){
			//执行回滚
			throw new RuntimeException(JSON.toJSONString(getRetVo(retList,listVo)));
		}
		responseVo.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		responseVo.setRetInfo("全部记账成功！");
		responseVo.setObj(retList);
		
		log.info("记账完毕！"+"---耗时："+(System.currentTimeMillis()-startTime)+"ms");
		return responseVo;
	}
	
	public ResponseVo handle2(List<AccountVo> listVo){
		Long startTime = System.currentTimeMillis();
		log.info("开始处理记账！请求参数："+ JSON.toJSONString(listVo));
		ResponseVo responseVo = new ResponseVo();
		List<AccountResVo> retList = new ArrayList<AccountResVo>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		//1.校验数据
		retMap = verify(listVo,retList);
		retList = (List<AccountResVo>) retMap.get("retList");
		boolean rollbackFlag = false;
		for(AccountResVo res:retList){
			if(!res.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
				//需要执行返回
				rollbackFlag = true;
			}
		}
		if(rollbackFlag){
			//执行回滚
			throw new RuntimeException(JSON.toJSONString(getRetVo(retList,listVo)));
		}
		
		try {
			//2.执行获取锁操作
			getLock((TreeSet<Long>)retMap.get("idSet"));
		} catch (Exception e) {
			log.error("执行记账时,锁异常！",e);
			responseVo.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			responseVo.setRetInfo(e.getMessage());
			return responseVo;
		}
		//3.记账,保存交易记录
		retList = saveSubAcc(listVo);
		//4.检查记账结果是否需要执行回滚
		for(AccountResVo res:retList){
			if(!res.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
				//需要执行回滚
				rollbackFlag = true;
			}
		}
		if(rollbackFlag){
			//执行回滚
			throw new RuntimeException(JSON.toJSONString(getRetVo(retList,listVo)));
		}
		responseVo.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
		responseVo.setRetInfo("全部记账成功！");
		responseVo.setObj(retList);
		
		log.info("记账完毕！"+"---耗时："+(System.currentTimeMillis()-startTime)+"ms");
		return responseVo;
	}
	/**
	 * 
	 * @Description: 处理返回结果
	 * @author:	xianzhiqiang
	 * @date:	2017年11月28日 下午6:25:48
	 */
	public ResponseVo getRetVo(List<AccountResVo> retList,List<AccountVo> listVo){
		ResponseVo responseVo = new ResponseVo();
		//单笔交易
		if(retList.size()==1&&(listVo.get(0).getBatchId()==null||"".equals(listVo.get(0).getBatchId()))){
			responseVo.setRetCode(retList.get(0).getRetCode());
			responseVo.setRetInfo(retList.get(0).getRetInfo());
		}else{//批量交易
			responseVo.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			responseVo.setRetInfo("全部记账失败！");
			for(AccountResVo res:retList){
				if(res.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
					res.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
					res.setRetInfo("记账失败已回滚！");
				}
			}
			responseVo.setObj(retList);
		}
		return responseVo;
	}
	
	/**
	 * 
	 * @Description: 保存交易记录
	 * @param:AccountVo
	 * @throws Exception
	 * @author:	xianzhiqiang
	 * @date:	2017年6月27日 下午2:43:15
	 */
	public void saveTrade(AccountVo vo){
		
		try{
			BigDecimal sumTradeAmount = new BigDecimal("0");//交易总金额
			//每笔交易（同一订单号）只记录一次交易记录
			Trade trade  = new Trade();
			for(AccountDetailVo accountDetailVo:vo.getDetail()){
				
				/*TradeDetail tradeDetail = new TradeDetail();
				tradeDetail.setAccountDir(accountDetailVo.getAccountDir());
				tradeDetail.setAccountNo(accountDetailVo.getPayeeAccNo());
				tradeDetail.setAmount(accountDetailVo.getAmount());
				tradeDetail.setBizId(vo.getBizId());
				tradeDetail.setBizSystem(vo.getBizSystem());
				tradeDetail.setCapitalName(accountDetailVo.getCapitalName());
				tradeDetail.setCapitalType(accountDetailVo.getCapitalType());
				tradeDetail.setLendId(accountDetailVo.getLendId());
				tradeDetail.setRemark(accountDetailVo.getRemark());
				tradeDetail.setRivalAccountNo(accountDetailVo.getDraweeAccNo());
				tradeDetail.setRivalLendld(accountDetailVo.getRivalLendId());
				tradeDetail.setRivalSubType(accountDetailVo.getRivalSubType());
				tradeDetail.setRivalName(accountDetailVo.getDraweeAccName());
				tradeDetail.setSubType(accountDetailVo.getSubType());
				tradeDetail.setCreateTime(new Date());
				tradeDetail.setModifyTime(new Date());*/
				
				sumTradeAmount = sumTradeAmount.add(accountDetailVo.getAmount());
				trade.setBorrowDocNo(accountDetailVo.getBorrowDocNo());
				trade.setLendDocNo(accountDetailVo.getLendDocNo());
//				tradeDetailService.insert(tradeDetail);
			}
			
			trade.setBatchId(vo.getBatchId());
			trade.setAmount(sumTradeAmount);
			trade.setBizId(vo.getBizId());
			trade.setBizSystem(vo.getBizSystem());
			trade.setBizType(vo.getBizType());
			trade.setStatus(AccConst.TRADE_STATE_SUCCESS);
			trade.setTradeTime((vo.getTradeTime()!=null&&!"".equals(vo.getTradeTime()))?Date2Utils.str2DateTime(vo.getTradeTime(), null):new Date());
			tradeService.insert(trade);
			
		}catch(Exception e){
			log.error("记账保存交易记录时异常：",e);
		}
		
	}
	
	
	/**
	 * 
	 * @Description: 校验数据
	 * @param: AccountVo
	 * @param: List<AccountResVo>
	 * @return:List<AccountResVo>
	 * @throws Exception
	 * @author:	xianzhiqiang
	 * @date:	2017年6月26日 下午4:11:27
	 */
	private Map<String,Object> verify(List<AccountVo> list,List<AccountResVo> retList){
//		List<String> bizIdList = new ArrayList<String>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		TreeSet<Long> idSet = new TreeSet<Long>();
    	for(AccountVo vo:list){
    		AccountResVo accountResVo = new AccountResVo();
    		accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
			accountResVo.setRetInfo("校验通过！");
    		try{
    			//校验参数中是否存在重复交易订单号
//    			if(bizIdList.size()>0&&bizIdList.contains(vo.getBizId())){
//        			throw new Exception("参数中交易订单号："+vo.getBizId()+"重复！");
//        		}
//        		bizIdList.add(vo.getBizId());
    			
    			//根据订单号查询交易记录
//        		Trade trade = tradeService.selectTradeByBizId(vo.getBizId(),vo.getBizSystem());
//            	if(trade!=null){
////            		throw new Exception("交易订单号："+vo.getBizId()+"已存在！");
//            		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_FIVE);
//            		accountResVo.setRetInfo("交易订单号："+vo.getBizId()+"已存在！");
//            		retList.add(accountResVo);
//            		continue;
//            	}
	    		for(AccountDetailVo accountDetailVo:vo.getDetail()){
        		
            		//查询本手方主账户记录
                	MainAccount mainAccount = mainAccountService.selectAccByAccountNo(
                			accountDetailVo.getPayeeAccNo(),vo.getBizSystem());
                	
                	if(mainAccount == null){
//                		throw new Exception("账户编号："+accountDetailVo.getPayeeAccNo()+"不存在！");
                		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_H);
                		accountResVo.setRetInfo("账户编号："+accountDetailVo.getPayeeAccNo()+"不存在！");
                		break;
                	}
                	
                	//检查是否已激活
//                	if(AccConst.ACTIVAT_INVALID.equals(mainAccount.getActivationType())){
//                		throw new Exception("账户编号："+accountDetailVo.getPayeeAccNo()+"未激活！");
//                	}
                	
                	//检查是否已禁用
                	if(AccConst.STATE_INVALID.equals(mainAccount.getStatus())){
//                		throw new Exception("账户编号："+accountDetailVo.getPayeeAccNo()+"已禁用！");
                		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_D);
                		accountResVo.setRetInfo("账户编号："+accountDetailVo.getPayeeAccNo()+"已禁用！");
                		break;
                	}
                	
                	//校验本手方传入参数客户名称与开户时是否一致
//                	if(accountDetailVo.getPayeeAccName()!=null&&!"".equals(accountDetailVo.getPayeeAccName())
//                			&&!accountDetailVo.getPayeeAccName().equals(mainAccount.getName())){
//                		
//            			throw new Exception("订单号："+vo.getBizId()+" 账户编号："
//                			+accountDetailVo.getPayeeAccNo()+"参数中的客户姓名："+accountDetailVo.getPayeeAccName()+"与开户时填写的客户姓名不同!");
//            		}
                	
            		//查询本手方子账户信息
            		SubAccount subAccount = subAccountService.selectSubAccount(accountDetailVo.getPayeeAccNo(),
            				accountDetailVo.getSubType(), accountDetailVo.getLendId(),vo.getBizSystem());
            		//if(不存在) 
            		if(subAccount == null){
            			
                        if(AccConst.SUB_BIZ_ID_VIRTUAL.equals(accountDetailVo.getLendId())
                        		||!(AccConst.SUB_ACC_TYPE_BANK).equals(accountDetailVo.getSubType())){
                        	//开子账户
                        	subAccount =openSubAccount(accountDetailVo,vo.getBizSystem(),null);
                        }
                        if (subAccount==null) {
//                    		throw new Exception("客户编号:"+accountDetailVo.getPayeeAccNo()
//                    		+" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "不存在！");
                    		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_I);
                    		accountResVo.setRetInfo("客户编号:"+accountDetailVo.getPayeeAccNo()
                    		+" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "不存在！");
                    		break;
                    	}
            		}
            		 idSet.add(subAccount.getId());
            		//检查是否已禁用
            		if(AccConst.STATE_INVALID.equals(subAccount.getStatus())){
//                		throw new Exception("客户编号:"+accountDetailVo.getPayeeAccNo()
//                		+" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "已禁用！");
                		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_E);
                		accountResVo.setRetInfo("客户编号:"+accountDetailVo.getPayeeAccNo()
                		+" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "已禁用！");
                		break;
                	}
            		//调账存在没有对手方的情况
					if(!(AccConst.BUSI_TYPE_CHECKBALANCE.equals(vo.getBizType()) && ("".equals(accountDetailVo.getDraweeAccNo())||accountDetailVo.getDraweeAccNo()==null))){
	            		SubAccount rivalSubAccount = null;
	//            		if(!"".equals(accountDetailVo.getDraweeAccNo())&&accountDetailVo.getDraweeAccNo()!=null){
	            			//查询对手方主账户记录
	            			MainAccount rivalMainAccount = mainAccountService.selectAccByAccountNo(
	            					accountDetailVo.getDraweeAccNo(),vo.getBizSystem());
	                    
	            			if(rivalMainAccount == null){
	//                    		throw new Exception("账户编号："+accountDetailVo.getDraweeAccNo()+"不存在！");
	                    		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_H);
	                    		accountResVo.setRetInfo("账户编号："+accountDetailVo.getDraweeAccNo()+"不存在！");
	                    		break;
	                    	}
	            			
	            			//检查是否已激活
	//                    	if(AccConst.ACTIVAT_INVALID.equals(rivalMainAccount.getActivationType())){
	//                    		throw new Exception("账户编号："+accountDetailVo.getDraweeAccNo()+"未激活！");
	//                    	}
	            			
	            			//检查是否已禁用
	            			if(AccConst.STATE_INVALID.equals(rivalMainAccount.getStatus())){
	//                    		throw new Exception("账户编号："+accountDetailVo.getDraweeAccNo()+"已禁用！");
	                    		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_D);
	                    		accountResVo.setRetInfo("账户编号："+accountDetailVo.getDraweeAccNo()+"已禁用！");
	                    		break;
	                    	}
	            			
	            		   	//校验对手方传入参数客户名称与开户时是否一致
	//                    	if(accountDetailVo.getDraweeAccName()!=null&&!"".equals(accountDetailVo.getDraweeAccName())
	//                    			&&!accountDetailVo.getDraweeAccName().equals(rivalMainAccount.getName())){
	//                    		
	//                			throw new Exception("订单号："+vo.getBizId()+" 账户编号："
	//                    			+accountDetailVo.getDraweeAccNo()+"参数中的客户姓名："+accountDetailVo.getDraweeAccName()+"与开户时填写的客户姓名不同!");
	//                		}
	                    	
            			
    						//查询对手方子账户信息
                			rivalSubAccount = subAccountService.selectSubAccount(
                					accountDetailVo.getDraweeAccNo(),accountDetailVo.getRivalSubType(), accountDetailVo.getRivalLendId(),vo.getBizSystem());
                    		//if(不存在) 
                    		if(rivalSubAccount == null){
                    			
                            	if(AccConst.SUB_BIZ_ID_VIRTUAL.equals(accountDetailVo.getRivalLendId())
                            			||!(AccConst.SUB_ACC_TYPE_BANK).equals(accountDetailVo.getRivalSubType())){
                            	  	//开子账户
                            		rivalSubAccount = openSubAccount(accountDetailVo, vo.getBizSystem(), "RIVAL");
                            	}
                            	
                            	if (rivalSubAccount==null) {
//                                    throw new Exception("客户编号:"+accountDetailVo.getDraweeAccNo()
//                                    +" 子账户：" + accountDetailVo.getRivalLendId()+" 子账户类型："+accountDetailVo.getRivalSubType() + "不存在！");
                                    accountResVo.setRetCode(AccConst.RET_CODE_FAILED_I);
                            		accountResVo.setRetInfo("客户编号:"+accountDetailVo.getDraweeAccNo()
                                    +" 子账户：" + accountDetailVo.getRivalLendId()+" 子账户类型："+accountDetailVo.getRivalSubType() + "不存在！");
                            		break;
                        		}
                    		}
                    		idSet.add(rivalSubAccount.getId());
                    		//检查是否已禁用
                    		if(AccConst.STATE_INVALID.equals(rivalSubAccount.getStatus())){
//                        		throw new Exception("客户编号:"+accountDetailVo.getDraweeAccNo()
//                                +" 子账户：" + accountDetailVo.getRivalLendId()+" 子账户类型："+accountDetailVo.getRivalSubType() + "已禁用！");
                        		 accountResVo.setRetCode(AccConst.RET_CODE_FAILED_E);
                         		accountResVo.setRetInfo("客户编号:"+accountDetailVo.getDraweeAccNo()
                                +" 子账户：" + accountDetailVo.getRivalLendId()+" 子账户类型："+accountDetailVo.getRivalSubType() + "已禁用！");
                         		break;
                        	}
    					}
            				
//            		}
            		
	    		}
	    		
	    		retList.add(accountResVo);
	    		
        		}catch(Exception e){
        			log.error("记账校验时异常：",e);
        			accountResVo.setBizId(vo.getBizId());
        			accountResVo.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
        			accountResVo.setRetInfo(e.getMessage());
        			retList.add(accountResVo);
        		}
    	}
    	retMap.put("retList", retList);
    	retMap.put("idSet", idSet);
    	return retMap;
    }
    
    
    /**
     * 
     * @Description: 开子账户
     * @author:	xianzhiqiang
     * @date:	2017年6月26日 下午5:31:41
     */
    public SubAccount openSubAccount(AccountDetailVo accountDetailVo,String bizSystem,String type){
    	
      SubAccount sub = new SubAccount();
      sub.setAmount(BigDecimal.ZERO);
      sub.setBizSystem(bizSystem);
      sub.setStatus(AccConst.STATE_VALID); //0:禁用 1:可用
      if(type !=null&&"RIVAL".equals(type)){ //对手方
    	  sub.setAccountNo(accountDetailVo.getDraweeAccNo());
          sub.setSubType(accountDetailVo.getRivalSubType());
          sub.setLendId(accountDetailVo.getRivalLendId());
      }else{
    	  sub.setAccountNo(accountDetailVo.getPayeeAccNo());
          sub.setSubType(accountDetailVo.getSubType());
          sub.setLendId(accountDetailVo.getLendId());
      }
      sub.setUnit(AccConst.CURRENCY_UNIT_RMB);//单位暂时写死为人民币
      //加密原始数据拼装
      /*String tempStr = bizSystem+sub.getAccountNo()+AccConst.AMOUNT_ZERO+sub.getSubType()+sub.getLendId();
      String value1 = AccUtil.string2MD5(tempStr);
      sub.setValue1(value1);*/
      subAccountService.insert(sub);
      return sub;
   }
    
    /**
     * 
     * @Description: 保存交易记录
     * @author:	xianzhiqiang
     * @date:	2017年6月27日 上午11:43:09
     */
	public List<AccountResVo> saveSubAcc(List<AccountVo> listVo) {
		List<AccountResVo> retList = new ArrayList<AccountResVo>();
		for(AccountVo vo:listVo){
			AccountResVo accountResVo = new AccountResVo();
			try{
				
				Trade trade = tradeService.selectTradeByBizId(vo.getBizId(),vo.getBizSystem());
            	if(trade!=null){
//            		throw new Exception("交易订单号："+vo.getBizId()+"已存在！");
            		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_FIVE);
            		accountResVo.setRetInfo("交易订单号："+vo.getBizId()+"已存在！");
            		retList.add(accountResVo);
            		continue;
            	}
				
				for(AccountDetailVo accountDetailVo:vo.getDetail()){
					List<SubAccountDetail>  subAccountDetails = new ArrayList<SubAccountDetail>();
					accountDetailVo.setBatchId(vo.getBatchId());
			    	accountDetailVo.setProjectCode(vo.getProjectCode());
			    	accountDetailVo.setBizType(vo.getBizType());
			    	/** 处理本手方**/
			    	//获取本手方子账户记录信息
			    	SubAccount subAccount = subAccountService.selectSubAccount(accountDetailVo.getPayeeAccNo(), accountDetailVo.getSubType(), accountDetailVo.getLendId(),vo.getBizSystem());//子账户
			    	subAccountService.updateByPrimaryKeyToVersion(subAccount);
			    	subAccount = subAccountService.selectSubAccount(accountDetailVo.getPayeeAccNo(), accountDetailVo.getSubType(), accountDetailVo.getLendId(),vo.getBizSystem());//子账户
			    	SubAccountDetail subAccountDetail = new SubAccountDetail();
			    	//交易明细信息封装
					subAccountDetail = getSubAccDetail(accountDetailVo, subAccount.getAmount(),subAccountDetail,vo.getBizSystem(),AccConst.ACC_TYPE_NO_RIVAL);
					subAccountDetail.setBizId(vo.getBizId());
					subAccountDetail.setTradeTime((vo.getTradeTime()!=null&&!"".equals(vo.getTradeTime()))?Date2Utils.str2DateTime(vo.getTradeTime(), null):new Date());
					//处理子账户金额
					subAccount = getSubAccAmount(subAccount, accountDetailVo.getAccountDir(), accountDetailVo.getAmount(),AccConst.ACC_TYPE_NO_RIVAL);
					//更新子账户
					subAccountService.updateByPrimaryKeyToVersion(subAccount);
					//新增交易明细
//					subAccountDetailService.insert(subAccountDetail);
					ModelUtil.setCommonFields(subAccountDetail); //填充公共字段
					subAccountDetails.add(subAccountDetail);
					//调账存在没有对手方的情况
//					if(!AccConst.BUSI_TYPE_CHECKBALANCE.equals(vo.getBizType())){
						/** 处理对手方**/
				    	if(accountDetailVo.getDraweeAccNo()!=null&&!"".equals(accountDetailVo.getDraweeAccNo())){ 
				    		//获取对手方子账户记录信息
				    		SubAccount rivalSubAccount = subAccountService.selectSubAccount(accountDetailVo.getDraweeAccNo(), accountDetailVo.getRivalSubType(), accountDetailVo.getRivalLendId(),vo.getBizSystem());//子账户
				        	SubAccountDetail rivalSubAccountDetail = new SubAccountDetail();
				        	//交易明细信息封装
				        	rivalSubAccountDetail = getSubAccDetail(accountDetailVo, rivalSubAccount.getAmount(),rivalSubAccountDetail,vo.getBizSystem(),AccConst.ACC_TYPE_YES_RIVAL);
				    		rivalSubAccountDetail.setBizId(vo.getBizId());
				    		rivalSubAccountDetail.setTradeTime((vo.getTradeTime()!=null&&!"".equals(vo.getTradeTime()))?Date2Utils.str2DateTime(vo.getTradeTime(), null):new Date());
				    		if(AccConst.ACC_DIR_TYPE_OUT.equals(accountDetailVo.getAccountDir())){//出账
				    			rivalSubAccountDetail.setAccountDir(AccConst.ACC_DIR_TYPE_IN);
				    		}else{
				    			rivalSubAccountDetail.setAccountDir(AccConst.ACC_DIR_TYPE_OUT);
				    		}
				    		
				    		rivalSubAccountDetail.setPayeeAccName(subAccountDetail.getDraweeAccName());
				    		rivalSubAccountDetail.setPayeeAccNo(accountDetailVo.getDraweeAccNo());
				    		rivalSubAccountDetail.setLendId(accountDetailVo.getRivalLendId());
				    		rivalSubAccountDetail.setSubType(accountDetailVo.getRivalSubType());
				    		
				    		rivalSubAccountDetail.setDraweeAccName(subAccountDetail.getPayeeAccName());
				    		rivalSubAccountDetail.setDraweeAccNo(accountDetailVo.getPayeeAccNo());
				    		rivalSubAccountDetail.setRivalLendId(accountDetailVo.getLendId());
				    		rivalSubAccountDetail.setRivalSubType(accountDetailVo.getSubType());
				    		//处理对手方子账户金额
				    		rivalSubAccount = getSubAccAmount(rivalSubAccount, accountDetailVo.getAccountDir(), accountDetailVo.getAmount(),AccConst.ACC_TYPE_YES_RIVAL);
				    		//更新子账户
				    		subAccountService.updateByPrimaryKeyToVersion(rivalSubAccount);
				    		//新增交易明细
//				    		subAccountDetailService.insert(rivalSubAccountDetail);
				    		ModelUtil.setCommonFields(rivalSubAccountDetail); //填充公共字段
				    		subAccountDetails.add(rivalSubAccountDetail);
				    	}
//					}
					subAccountDetailService.batchInsert(subAccountDetails);
					
				}
    		accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
			accountResVo.setRetInfo("交易订单号："+vo.getBizId()+"记账成功！");
			
		}catch(FalsifyException e){
			accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_FAILED_F);
			accountResVo.setRetInfo(e.getMessage());
    	}catch (BalanceException e) {
    		accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_FAILED_C);
			accountResVo.setRetInfo(e.getMessage());
		}catch (Exception e) {
			accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_FAILED_ZERO);
			accountResVo.setRetInfo("交易订单号："+vo.getBizId()+",失败原因："+e.getMessage());
		}	
		retList.add(accountResVo);
		saveTrade(vo);
		}
    	return retList;
	}

	
	 /**
     * 
     * @Description: 处理子账户金额
     * @author:	xianzhiqiang
     * @date:	2017年6月27日 上午11:43:09
     */
    private SubAccount getSubAccAmount(SubAccount subAccount,
    		String accountDir,BigDecimal tradeAmount,String accType) throws Exception{
    	//防篡改校验
    	/*String tempStr = subAccount.getBizSystem()
    			+subAccount.getAccountNo()+subAccount.getAmount()+subAccount.getSubType()+subAccount.getLendId();
    	String currentValue1 = AccUtil.string2MD5(tempStr);
    	if(!currentValue1.equals(subAccount.getValue1())){
    		throw new FalsifyException("客户编号："+subAccount.getAccountNo()+" 子账户类型:"
    				+subAccount.getSubType()+" 子账户编号："+subAccount.getLendId()+"的账户余额有可能被恶意篡改，请及时排查！");
    	}*/
    	
    	//子账户余额
    	BigDecimal subAmount = subAccount.getAmount();
    	BigDecimal tempAmount;
    	if(AccConst.ACC_DIR_TYPE_OUT.equals(accountDir)){ //出账
    		if(AccConst.ACC_TYPE_NO_RIVAL.equals(accType)){ //本手方
    			tempAmount = subAmount.subtract(tradeAmount);
    			if(!AccConst.SUB_ACC_TYPE_BANK.equals(subAccount.getSubType())
    					&&!AccConst.SUB_ACC_TYPE_WX.equals(subAccount.getSubType())
    					&&!AccConst.SUB_ACC_TYPE_ZFB.equals(subAccount.getSubType())
    					&&tempAmount.compareTo(BigDecimal.ZERO) < 0){
    				throw new BalanceException("客户编号："+subAccount.getAccountNo()
    				+" 子账户类型:"+subAccount.getSubType()+" 子账户编号："+subAccount.getLendId()+"余额不足！");
    			}
    			subAccount.setAmount(tempAmount);
    		}else{//对手方
    			subAccount.setAmount(subAmount.add(tradeAmount));
    		}
    		
    	}else{//入账
    		if(AccConst.ACC_TYPE_NO_RIVAL.equals(accType)){//本手方
    			subAccount.setAmount(subAmount.add(tradeAmount));
    		}else{//对手方
    			tempAmount = subAmount.subtract(tradeAmount);
    			if(!AccConst.SUB_ACC_TYPE_BANK.equals(subAccount.getSubType())
    					&&!AccConst.SUB_ACC_TYPE_WX.equals(subAccount.getSubType())
    					&&!AccConst.SUB_ACC_TYPE_ZFB.equals(subAccount.getSubType())
    					&&tempAmount.compareTo(BigDecimal.ZERO) < 0){
    				throw new BalanceException("客户编号："+subAccount.getAccountNo()
    				+" 子账户类型:"+subAccount.getSubType()+" 子账户编号："+subAccount.getLendId()+"余额不足！");
    			}
    			subAccount.setAmount(tempAmount);
    		}
    		
    	}
    	//加密原始数据拼装
    	/*String valueStr = subAccount.getBizSystem()
    			+subAccount.getAccountNo()+subAccount.getAmount()+subAccount.getSubType()+subAccount.getLendId();
    	String newValue1 = AccUtil.string2MD5(valueStr);
    	subAccount.setValue1(newValue1);*/
    	return subAccount;
    }
    
    /**
     * 
     * @Description: 交易明细信息封装
     * @author:	xianzhiqiang
     * @date:	2017年6月27日 上午11:42:09
     */
    private SubAccountDetail getSubAccDetail(AccountDetailVo accountDetailVo,
    		BigDecimal currAmount,SubAccountDetail subAccountDetail,String bizSystem,String accType) throws Exception{
    	
    	ModelUtil.copyProps(accountDetailVo,subAccountDetail);
    	subAccountDetail.setBeginAmount(currAmount);//交易前金额
    	subAccountDetail.setBizSystem(bizSystem);//业务系统
    	subAccountDetail.setUnit(AccConst.CURRENCY_UNIT_RMB);//单位暂时写死为人民币
    	
    	if(AccConst.ACC_DIR_TYPE_OUT.equals(accountDetailVo.getAccountDir())){ //出账
    		if(AccConst.ACC_TYPE_NO_RIVAL.equals(accType)){//本手方
    			
    			subAccountDetail.setEndAmount(currAmount.subtract(accountDetailVo.getAmount()));
    		}else{//对手方
    			subAccountDetail.setEndAmount(currAmount.add(accountDetailVo.getAmount()));
    		}
    		
    	}else{//入账
    		if(AccConst.ACC_TYPE_NO_RIVAL.equals(accType)){//本手方
    			subAccountDetail.setEndAmount(currAmount.add(accountDetailVo.getAmount()));
    		}else{//对手方
    			subAccountDetail.setEndAmount(currAmount.subtract(accountDetailVo.getAmount()));
    		}
    		
    	}
    	/* 设置本手、对手方客户名称*/
    	MainAccount mainAccount;
    	mainAccount = mainAccountService.selectAccByAccountNo(accountDetailVo.getPayeeAccNo(),bizSystem);
    	subAccountDetail.setPayeeAccName(mainAccount.getName());
    	mainAccount = mainAccountService.selectAccByAccountNo(accountDetailVo.getDraweeAccNo(),bizSystem);
    	if(mainAccount!=null){
    		subAccountDetail.setDraweeAccName(mainAccount.getName());
    	}
    	return subAccountDetail;
    }
    
    /**
	 * 
	 * @Description: 获取锁
	 * @author:	xianzhiqiang
	 * @date:	2017年9月25日 下午5:03:31
	 */
	public void getLock(TreeSet<Long> idSet) throws Exception{

        for (Long id:idSet) {
        	log.debug("==============================准备获取锁--->ID:"+id);
    		subAccountService.selectByPrimaryKeyForUpdate(id);
            log.debug("==============================已获取锁--->ID:"+id);
        }
	}
}
