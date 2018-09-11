package com.slazy.bss.slazypay.exception;
/**
 * 
 * @Description: 余额不足异常
 * @author:	xianzhiqiang
 * @date:	2017年11月28日 下午5:44:44
 */
public class BalanceException extends Exception {

	public BalanceException(String msg)  
    {  
        super(msg);  
    }  
}
