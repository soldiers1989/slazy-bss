package com.slazy.bss.slazypay.provider;

import org.apache.log4j.Logger;

import com.slazy.bss.slazypay.vo.res.ResponseVo;



public interface BaseAccProvider <T> {

	final static Logger log = Logger.getLogger(BaseAccProvider.class);
	
	/**
	 * 
	 * @param 处理程序
	 * @return
	 * @throws Exception
	 */
	ResponseVo handle(T vo) ;
	
}
