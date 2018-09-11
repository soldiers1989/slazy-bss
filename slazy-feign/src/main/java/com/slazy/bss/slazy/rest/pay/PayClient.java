package com.slazy.bss.slazy.rest.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.slazy.bss.slazy.service.pay.PayService;

@RestController
public class PayClient {
	
	@Autowired
	PayService payService;
	
	@RequestMapping(value = "/queryPayInfo", method = RequestMethod.POST, consumes="application/json")
	public String queryPayInfo(@RequestBody String params) {
		return payService.queryPayInfo();
	}
	
	@RequestMapping(value = "/openAcc", method = RequestMethod.POST, consumes="application/json")
	public String openAcc(@RequestBody String params) {
		return payService.openAcc(params);
	}
	
	@RequestMapping(value = "/payRelease", method = RequestMethod.POST, consumes="application/json")
	public String payRelease(@RequestBody String params) {
		return payService.payRelease(params);
	}
	
	@RequestMapping(value = "/payFinish", method = RequestMethod.POST, consumes="application/json")
	public String payFinish(@RequestBody String params) {
		return payService.payFinish(params);
	}
	
	@RequestMapping(value = "/payReturn", method = RequestMethod.POST, consumes="application/json")
	public String payReturn(@RequestBody String params) {
		return payService.payReturn(params);
	}
	
	@RequestMapping(value = "/queryAccInfo", method = RequestMethod.POST, consumes="application/json")
	public String queryAccInfo(@RequestBody String params) {
		return payService.queryAccInfo(params);
	}
	
	@RequestMapping(value = "/queryBalance", method = RequestMethod.POST, consumes="application/json")
	public String queryBalance(@RequestBody String params) {
		return payService.queryBalance(params);
	}
	
	@RequestMapping(value = "/tradeQuerySingle", method = RequestMethod.POST, consumes="application/json")
	public String tradeQuerySingle(@RequestBody String params) {
		return payService.tradeQuerySingle(params);
	}
}
