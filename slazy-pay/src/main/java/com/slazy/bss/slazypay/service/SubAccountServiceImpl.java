package com.slazy.bss.slazypay.service;



import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slazy.bss.slazypay.dao.SubAccountMapper;
import com.slazy.bss.slazypay.model.SubAccount;
import com.slazy.bss.slazypay.model.SubAccountDetail;
import com.slazy.bss.slazypay.utils.AccConst;
import com.slazy.bss.slazypay.utils.AccUtil;
import com.slazy.bss.slazypay.utils.CoreUtil;
import com.slazy.bss.slazypay.vo.req.AmtLockReqVo;
import com.slazy.bss.slazypay.vo.req.SubAccountReqVo;
import com.slazy.bss.slazypay.vo.res.BalanceDetailResVo;
import com.slazy.bss.slazypay.vo.res.QueryAccountBalanceVo;
import com.slazy.bss.slazypay.vo.res.QueryBalanceResVo;

@Service("subAccountService")
@Transactional
public class SubAccountServiceImpl extends BaseServiceImpl<SubAccount>{

	@Autowired
	private SubAccountMapper subAccountMapper;
	/**
     * 
     * @Description: 查询子账户信息
     * @author:	xianzhiqiang
     * @date:	2017年6月27日 上午11:43:09
     */
	public SubAccount selectSubAccount(String accountNo, String subType, String lendId,String bizSystem) {
		
		SubAccount subAccount = new SubAccount();
		subAccount.setAccountNo(accountNo);
		subAccount.setSubType(subType);
		subAccount.setLendId(lendId);
		subAccount.setBizSystem(bizSystem);
		return subAccountMapper.selectSubAccount(subAccount);
	}
	/**
     * 
     * @Description: 更新子账户信息
     * @author:	xianzhiqiang
     * @date:	2017年6月27日 上午11:43:09
     */
	public int updateByPrimaryKeyToVersion(SubAccount subAccount) throws Exception {
		subAccount.setModifyTime(CoreUtil.generateTimestamp());//修改时间
		int res = subAccountMapper.updateByPrimaryKeyToVersion(subAccount);
//		if(res==0){
//			throw new Exception("乐观锁异常！"); 
//		}
		return res;
	}
	public List<QueryBalanceResVo> selectBalanceByAccountNo(String accountNo,String bizSystem,String subType, String lendId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("accountNo", accountNo);
		map.put("bizSystem", bizSystem);
		map.put("subType", subType);
		map.put("lendId", lendId);
		return subAccountMapper.selectBalanceByAccountNo(map);
	}
	public List<SubAccountReqVo> selectAllSubAccount(String id) {
		
		return subAccountMapper.selectAllSubAccount(id);
	}
	public Integer selectAllSubAccountCount(String id) {
		
		return subAccountMapper.selectAllSubAccountCount(id);
	}
	public List<SubAccountReqVo> selectMainAccountSub(String id, String subType,String lendId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("subType", subType);
		map.put("lendId", lendId);
		return subAccountMapper.selectMainAccountSub(map);
	}
	public Integer selectMainAccountSubCount(String id, String subType,String lendId) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("id", id);
		map.put("subType", subType);
		map.put("lendId", lendId);
		return subAccountMapper.selectMainAccountSubCount(map);
	}
	public List<SubAccount> selectSubAccounts(String accountNo, String subType, String lendid, String bizSystem) {
		SubAccount subAccount = new SubAccount();
		subAccount.setAccountNo(accountNo);
		subAccount.setSubType(subType);
		subAccount.setLendId(lendid);
		subAccount.setBizSystem(bizSystem);
		return subAccountMapper.selectSubAccounts(subAccount);
	}

	/**
	 * 
	 * @Description: 查询被换卡的银行卡账户信息
	 * @param: status 启用状态
	 * @return:
	 * @throws 
	 * @author:	dingyaru
	 * @date:	2017年9月28日 下午4:34:15
	 */
	public List<SubAccount> selectSubAccountInfo(String accountNo, String subType, String lendId, String status, String bizSystem) {
		
		SubAccount subAccount = new SubAccount();
		subAccount.setAccountNo(accountNo);
		subAccount.setSubType(subType);
		subAccount.setLendId(lendId);
		subAccount.setStatus(status);
		subAccount.setBizSystem(bizSystem);
		return subAccountMapper.selectSubAccountInfo(subAccount);
	}
	/**
	 * 
	 * @Description: 查询子账户余额信息
	 * @author:	xianzhiqiang
	 * @date:	2017年10月30日 下午6:56:28
	 */
	public List<BalanceDetailResVo> selectSubAccountBalance(String accountNo, String subType, String lendId,String bizSystem){
		Map<String,String> map = new HashMap<String,String>();
		map.put("accountNo", accountNo);
		map.put("subType", subType);
		map.put("lendId", lendId);
		map.put("bizSystem", bizSystem);
		return subAccountMapper.selectSubAccountBalance(map);
	}
	
	public SubAccount selectByPrimaryKeyForUpdate(Long id) {
		return subAccountMapper.selectByPrimaryKeyForUpdate(id);
	}
	
	/**
	 * 
	 * @Description: 执行金额锁定/解锁
	 * @author:	xianzhiqiang
	 * @date:	2017年11月27日 下午2:29:21
	 */
	public boolean toLockAmt(List<SubAccountDetail> sdList,AmtLockReqVo vo){
		
		Map<String,String> map = null;
		int errorCount = 0;
		StringBuilder sb = new StringBuilder();
		
		for(SubAccountDetail detail:sdList){
			SubAccount sub = this.selectSubAccount(
					detail.getPayeeAccNo(), detail.getSubType(), detail.getLendId(), detail.getBizSystem());
			
			//防篡改校验
			map = falsifyVerify(sub);
			
			if(map.get(AccConst.RET_CODE_FAILED_ZERO)!=null){
				sb.append(map.get(AccConst.RET_CODE_FAILED_ZERO));
				errorCount++;
			}
			
			BigDecimal lockAmount = new BigDecimal("0");//锁定金额
			BigDecimal amount = new BigDecimal("0");//账户金额
			
			//锁定金额
			if("1".equals(vo.getLockType())){
				//计算锁定金额
				lockAmount = (sub.getLockAmount()!=null?sub.getLockAmount():new BigDecimal("0")).add(detail.getAmount());
				//计算账户金额
				amount = sub.getAmount().subtract(detail.getAmount());
				if(!AccConst.SUB_ACC_TYPE_BANK.equals(sub.getSubType())&&amount.compareTo(BigDecimal.ZERO) < 0){
					sb.append("客户编号："+sub.getAccountNo()
    				+" 子账户类型:"+sub.getSubType()+" 子账户编号："+sub.getLendId()+"余额不足！");
					errorCount++;
					continue;
				}
			}else{//解锁金额
				//计算锁定金额
				lockAmount = (sub.getLockAmount()!=null?sub.getLockAmount():new BigDecimal("0")).subtract(detail.getAmount());
				if(!AccConst.SUB_ACC_TYPE_BANK.equals(sub.getSubType())&&lockAmount.compareTo(BigDecimal.ZERO) < 0){
					sb.append("客户编号："+sub.getAccountNo()
    				+" 子账户类型:"+sub.getSubType()+" 子账户编号："+sub.getLendId()+"的已锁定余额不足！");
					errorCount++;
					continue;
				}
				//计算账户金额
				amount = sub.getAmount().add(detail.getAmount());
			}
			//更新金额		
			sub.setLockAmount(lockAmount);
			sub.setAmount(amount);
			//加密原始数据拼装
	    	String valueStr = sub.getBizSystem()
	    			+sub.getAccountNo()+sub.getAmount()+sub.getSubType()+sub.getLendId();
	    	String newValue1 = AccUtil.string2MD5(valueStr);
	    	sub.setValue1(newValue1);
			this.updateByPrimaryKey(sub);
		}
		
		if(errorCount>0){
			throw new RuntimeException(sb.toString());
		}
		
		return true;
	}
	
	/**
	 * 
	 * @Description: 防篡改校验
	 * @author:	xianzhiqiang
	 * @date:	2017年11月27日 下午3:06:18
	 */
	public Map falsifyVerify(SubAccount subAccount){
		
		Map<String,String> map  = new HashMap<String,String>();
		map.put(AccConst.RET_CODE_SUCCESS_ZERO, "验证通过！");
		
		String tempStr = subAccount.getBizSystem()
    			+subAccount.getAccountNo()+subAccount.getAmount()+subAccount.getSubType()+subAccount.getLendId();
		
    	String currentValue1 = AccUtil.string2MD5(tempStr);
    	
    	if(!currentValue1.equals(subAccount.getValue1())){
    		map.put(AccConst.RET_CODE_FAILED_ZERO,"客户编号："+subAccount.getAccountNo()+" 子账户类型:"
    				+subAccount.getSubType()+" 子账户编号："+subAccount.getLendId()+"的账户余额有可能被恶意篡改，请及时排查！");
    	}
		return map;
	}
	public List<SubAccount> findByMainAccount(String accountNo) {
		return subAccountMapper.findByMainAccount(accountNo);
	}
	
	/**
	 * 
	 * @Description: 批量插入
	 * @author:	xianzhiqiang
	 * @date:	2017年12月5日 下午4:35:23
	 */
	public void batchInsert(List<SubAccount> list){
		subAccountMapper.batchInsert(list);
	}
	/**
	 * @Description：账户所有子账户的和
	 * @param accountNo
	 * @return
	 */
	public List<QueryAccountBalanceVo> findAllSumAmount(String bizSystem, String endTime) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bizSystem", bizSystem);
		map.put("endTime", endTime);
		return subAccountMapper.findAllSumAmount(map);
	}
}
