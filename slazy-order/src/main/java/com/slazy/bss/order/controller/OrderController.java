package com.slazy.bss.order.controller;

import org.slazyframework.restful.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	final static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(value = "/orderPublish", method = RequestMethod.POST)
	public String orderPublish(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.orderPublish(reqParam));
	}
	
	@RequestMapping(value = "/orderAwait", method = RequestMethod.POST)
	public String orderAwait(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.orderAwait(reqParam));
	}
	
	@RequestMapping(value = "/orderDelivery", method = RequestMethod.POST)
	public String orderDelivery(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.orderDelivery(reqParam));
	}
	
	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST)
	public String orderComplete(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.orderComplete(reqParam));
	}
	
	@RequestMapping(value = "/orderCancel", method = RequestMethod.POST)
	public String orderCancel(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.orderCancel(reqParam));
	}
	
	@RequestMapping(value = "/queryOrderInfosByStatus", method = RequestMethod.POST)
	public String queryOrderInfosByStatue(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.queryOrderInfosByStatus(reqParam));
	}
	
	@RequestMapping(value = "/queryOrderInfosById", method = RequestMethod.POST)
	public String queryOrderInfosById(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.queryOrderInfosById(reqParam));
	}
	
	@RequestMapping(value = "/queryOrderStatue", method = RequestMethod.POST)
	public String queryOrderStatue(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(orderService.queryOrderStatue(reqParam));
	}
}
