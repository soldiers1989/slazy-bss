package com.slazy.bss.slazypay.utils;

import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @Description:流水号设置
 * @author:	daichangfu
 * @date:	2017年7月3日 下午4:32:08
 */
public class SnUtil {

	private static final ConcurrentMap<String, BlockingQueue<Long>> SN_POOL = new ConcurrentHashMap<String, BlockingQueue<Long>>();
	
	public static final String MESSAGE_ID_DATE_FORMAT = "yyyyMMdd";
	private static final String LOCK_BASE_PATH = "/lock/acc/sn/";
	
	/**
	 * 
	 * @Description: 获取编号
	 * @params: snKey 关键字 ，snPrefix 前缀 ， length 长度
	 * @return: String
	 * @throws Exception
	 * @author:	daichangfu
	 * @date:	2017年7月3日 下午4:31:18
	 */
	public static String getSn(String snKey,String snPrefix,int length) throws Exception{
		return null ;
	}
	
	
	public static String getService() {
		return "serialNumberService";
	}
	
}
