package com.slazy.bss.order.service;

import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;

public interface OrderService {

	// 订单发布
	ResponseParams orderPublish(RequestParams params);
	// 订单接单
	ResponseParams orderAwait(RequestParams params);
	// 订单配送
	ResponseParams orderDelivery(RequestParams params);
	// 订单完成
	ResponseParams orderComplete(RequestParams params);
	// 接单
	ResponseParams orderReceiving(RequestParams params);
	// 订单取消
	ResponseParams orderCancel(RequestParams params);
	// 获取未接单的订单信息
	ResponseParams queryOrderInfosByStatus(RequestParams params);
	// 获取已经生成的订单信息
	ResponseParams queryOrderInfosById(RequestParams params);
	// 订单状态查询
	ResponseParams queryOrderStatue(RequestParams params);
}
