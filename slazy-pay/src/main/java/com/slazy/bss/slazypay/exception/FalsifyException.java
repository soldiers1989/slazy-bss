package com.slazy.bss.slazypay.exception;

/**
 * 
 * @Description: 余额被恶意篡改异常
 * @author:	xianzhiqiang
 * @date:	2017年11月28日 下午5:42:17
 */
public class FalsifyException extends Exception {
	
	public FalsifyException(String msg)  
    {  
        super(msg);  
    }  
}
