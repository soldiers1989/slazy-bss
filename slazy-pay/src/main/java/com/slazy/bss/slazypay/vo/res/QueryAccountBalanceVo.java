package com.slazy.bss.slazypay.vo.res;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: 账户余额Vo
 *
 * @author wangyanan
 *
 */
public class QueryAccountBalanceVo implements Serializable{

	private static final long serialVersionUID = -3343807372777706081L;
	
	private String accountNo;     //客户编号
    private String userName;       //客户名称
    private String customerIdType; //客户类型   1:出借人 0：借款人
    private BigDecimal balance;       //余额
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCustomerIdType() {
		return customerIdType;
	}
	public void setCustomerIdType(String customerIdType) {
		this.customerIdType = customerIdType;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
    
    

}
