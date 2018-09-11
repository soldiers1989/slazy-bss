package com.slazy.bss.slazy.service.order;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户接口列表
 */
@FeignClient("order-service")
public interface OrderService {

	// 订单信息查询
	@RequestMapping("/order/queryOrderInfosByStatus")
	String queryOrderInfosByStatus(@RequestBody String params);
	
	@RequestMapping("/order/queryOrderInfosById")
	String queryOrderInfosById(@RequestBody String params);
	
	@RequestMapping("/order/queryOrderStatue")
	String queryOrderStatue(@RequestBody String params);

	// 订单发布
	@RequestMapping("/order/orderPublish")
	String orderPublish(@RequestBody String params);

	// 订单明细查询
	@RequestMapping("/order/orderAwait")
	String orderAwait(@RequestBody String params);

	// 订单接收
	@RequestMapping("/order/orderDelivery")
	String orderDelivery(@RequestBody String params);

	// 订单完成
	@RequestMapping("/order/orderComplete")
	String orderComplete(@RequestBody String params);

	// 订单取消
	@RequestMapping("/order/orderCancel")
	String orderCancel(@RequestBody String params);

}
