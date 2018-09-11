package com.slazy.bss.slazy.rest.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.slazy.bss.slazy.service.order.OrderService;

@RestController
public class OrderClient {

	@Autowired
	OrderService orderService;

	@RequestMapping(value = "/queryOrderInfosById", method = RequestMethod.POST, consumes = "application/json")
	public String queryOrderInfosById(@RequestBody String params) {
		return orderService.queryOrderInfosById(params);
	}

	@RequestMapping(value = "/orderPublish", method = RequestMethod.POST, consumes = "application/json")
	public String orderPublish(@RequestBody String params) throws Exception {
		return orderService.orderPublish(params);
	}

	@RequestMapping(value = "/orderAwait", method = RequestMethod.POST, consumes = "application/json")
	public String orderAwait(@RequestBody String params) throws Exception {
		return orderService.orderAwait(params);
	}

	@RequestMapping(value = "/orderDelivery", method = RequestMethod.POST, consumes = "application/json")
	public String orderDelivery(@RequestBody String params) throws Exception {
		return orderService.orderDelivery(params);
	}

	@RequestMapping(value = "/orderComplete", method = RequestMethod.POST, consumes = "application/json")
	public String orderComplete(@RequestBody String params) throws Exception {
		return orderService.orderComplete(params);
	}

	@RequestMapping(value = "/orderCancel", method = RequestMethod.POST, consumes = "application/json")
	public String orderCancel(@RequestBody String params) throws Exception {
		return orderService.orderCancel(params);
	}

	@RequestMapping(value = "/queryOrderInfosByStatus", method = RequestMethod.POST, consumes = "application/json")
	public String queryOrderInfosByStatus(@RequestBody String params) throws Exception {
		return orderService.queryOrderInfosByStatus(params);
	}

	@RequestMapping(value = "/queryOrderStatue", method = RequestMethod.POST, consumes = "application/json")
	public String queryOrderStatue(@RequestBody String params) throws Exception {
		return orderService.queryOrderStatue(params);
	}

}
