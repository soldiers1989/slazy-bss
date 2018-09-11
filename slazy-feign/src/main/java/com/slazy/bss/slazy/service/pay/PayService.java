package com.slazy.bss.slazy.service.pay;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户接口列表
 *
 */
@FeignClient("pay-service")
public interface PayService {

	// 用户信息查询
	@RequestMapping("/pay/queryPayInfo")
	String queryPayInfo();

	// 账务开户
	@RequestMapping("/account/openAcc")
	String openAcc(@RequestBody String params);
	
	// 订单发布支付
	@RequestMapping("/account/payRelease")
	String payRelease(@RequestBody String params);
	
	// 订单完成支付
	@RequestMapping("/account/payFinish")
	String payFinish(@RequestBody String params);
	
	// 退单支付
	@RequestMapping("/account/payReturn")
	String payReturn(@RequestBody String params);
	
	// 账户基础信息查询
	@RequestMapping("/account/queryAccInfo")
	String queryAccInfo(@RequestBody String params);
	
	// 账户余额查询
	@RequestMapping("/account/queryBalance")
	String queryBalance(@RequestBody String params);
	
	// 单笔交易查询
	@RequestMapping("/account/tradeQuerySingle")
	String tradeQuerySingle(@RequestBody String params);
}
