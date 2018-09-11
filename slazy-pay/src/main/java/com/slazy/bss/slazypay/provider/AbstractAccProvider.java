package com.slazy.bss.slazypay.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import com.slazy.bss.slazypay.model.MainAccount;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.service.MainAccountServiceImpl;
import com.slazy.bss.slazypay.service.RecordAccountServiceImpl;
import com.slazy.bss.slazypay.service.SubAccountDetailServiceImpl;
import com.slazy.bss.slazypay.service.SubAccountServiceImpl;
import com.slazy.bss.slazypay.service.TradeServiceImpl;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.vo.req.AccountDetailVo;
import com.slazy.bss.slazypay.vo.req.AccountVo;
import com.slazy.bss.slazypay.vo.req.BaseReqVo;
import com.slazy.bss.slazypay.vo.res.AccountResVo;
import com.slazy.bss.slazypay.vo.res.ResponseVo;


public abstract class AbstractAccProvider <T extends BaseReqVo> {

	@Autowired
	private SubAccountServiceImpl subAccountService;
	
	@Autowired
	private TradeServiceImpl tradeService;
	
	@Autowired
	private MainAccountServiceImpl mainAccountService;
	
	@Autowired
	private SubAccountDetailServiceImpl subAccountDetailService;
	
	@Autowired
	protected RecordAccountServiceImpl recordAccountService;
	
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
	protected Map<String,Object> verifyAccount(List<AccountVo> list){
		List<AccountResVo> retList = new ArrayList<AccountResVo>();
//		List<String> bizIdList = new ArrayList<String>();
		Map<String,Object> retMap = new HashMap<String,Object>();
		TreeSet<Long> idSet = new TreeSet<Long>();
    	for(AccountVo vo:list){
    		AccountResVo accountResVo = new AccountResVo();
    		accountResVo.setBizId(vo.getBizId());
			accountResVo.setRetCode(AccConst.RET_CODE_SUCCESS_ZERO);
			accountResVo.setRetInfo("校验通过！");
    		try{

	    		for(AccountDetailVo accountDetailVo:vo.getDetail()){
        		
            		//查询本手方主账户记录
                	MainAccount mainAccount = mainAccountService.selectAccByAccountNo(accountDetailVo.getPayeeAccNo(),vo.getBizSystem());
                	
                	if(mainAccount == null){
                		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_H);
                		accountResVo.setRetInfo("账户编号："+accountDetailVo.getPayeeAccNo()+"不存在！");
                		break;
                	}
                	
                	
                	//检查是否已禁用
                	if(AccConst.STATE_INVALID.equals(mainAccount.getStatus())){
//                		throw new Exception("账户编号："+accountDetailVo.getPayeeAccNo()+"已禁用！");
                		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_D);
                		accountResVo.setRetInfo("账户编号："+accountDetailVo.getPayeeAccNo()+"已禁用！");
                		break;
                	}
                	
                	
            		//查询本手方子账户信息
            		SubAccount subAccount = subAccountService.selectSubAccount(accountDetailVo.getPayeeAccNo(),
            				accountDetailVo.getSubType(), accountDetailVo.getLendId(),vo.getBizSystem());
            		//if(不存在) 
            		if(subAccount == null){
            			
                        if(AccConst.SUB_BIZ_ID_VIRTUAL.equals(accountDetailVo.getLendId())
                        		||!(AccConst.SUB_ACC_TYPE_BANK).equals(accountDetailVo.getSubType())){
                        	//开子账户
                        	try {
								subAccount = recordAccountService.openSubAccount(accountDetailVo,vo.getBizSystem(),null);
							} catch (DuplicateKeyException e) {
								subAccount = subAccountService.selectSubAccount(accountDetailVo.getPayeeAccNo(), accountDetailVo.getSubType(), accountDetailVo.getLendId(),vo.getBizSystem());
							//	e.printStackTrace();
							}
                        }
                        if (subAccount==null) {
//                    		throw new Exception("客户编号:"+accountDetailVo.getPayeeAccNo()
//                    		+" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "不存在！");
                    		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_I);
                    		accountResVo.setRetInfo("客户编号:"+accountDetailVo.getPayeeAccNo() +" 子账户：" + accountDetailVo.getLendId()+" 子账户类型："+accountDetailVo.getSubType() + "不存在！");
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
	            			
	            			//检查是否已禁用
	            			if(AccConst.STATE_INVALID.equals(rivalMainAccount.getStatus())){
	//                    		throw new Exception("账户编号："+accountDetailVo.getDraweeAccNo()+"已禁用！");
	                    		accountResVo.setRetCode(AccConst.RET_CODE_FAILED_D);
	                    		accountResVo.setRetInfo("账户编号："+accountDetailVo.getDraweeAccNo()+"已禁用！");
	                    		break;
	                    	}
	            		
    						//查询对手方子账户信息
                			rivalSubAccount = subAccountService.selectSubAccount(
                					accountDetailVo.getDraweeAccNo(),accountDetailVo.getRivalSubType(), accountDetailVo.getRivalLendId(),vo.getBizSystem());
                    		//if(不存在) 
                    		if(rivalSubAccount == null){
                    			
                            	if(AccConst.SUB_BIZ_ID_VIRTUAL.equals(accountDetailVo.getRivalLendId())
                            			||!(AccConst.SUB_ACC_TYPE_BANK).equals(accountDetailVo.getRivalSubType())){
                            	  	//开子账户
                            		try {
										rivalSubAccount = recordAccountService.openSubAccount(accountDetailVo, vo.getBizSystem(), "RIVAL");
									} catch (DuplicateKeyException e) {
										// TODO Auto-generated catch block
										rivalSubAccount = subAccountService.selectSubAccount(
			                					accountDetailVo.getDraweeAccNo(),accountDetailVo.getRivalSubType(), accountDetailVo.getRivalLendId(),vo.getBizSystem());
									}
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
        		//	log.error("记账校验时异常：",e);
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
	
	protected ResponseVo verifyRet(List<AccountVo> listVo) {
		ResponseVo res = new ResponseVo();
		 Map<String,Object> retMap = verifyAccount(listVo);
		 List<AccountResVo> retList = (List<AccountResVo>) retMap.get("retList");
			boolean rollbackFlag = false;
			for(AccountResVo res1:retList){
				if(!res1.getRetCode().equals(AccConst.RET_CODE_SUCCESS_ZERO)){
					//需要执行返回
					rollbackFlag = true;
				}
			}
			if(rollbackFlag){
				//存在异常
				res = recordAccountService.getRetVo(retList,listVo);
			}else{
				TreeSet<Long> ids = (TreeSet<Long>)retMap.get("idSet");
				//调用核心记账方法
				res = recordAccountService.handle(listVo,ids);
			}
			
			return res;
	}
}
