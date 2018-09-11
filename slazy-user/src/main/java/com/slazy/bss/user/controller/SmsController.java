package com.slazy.bss.user.controller;

import org.slazyframework.restful.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.user.service.handler.SmsServiceHandler;

@RestController
@RequestMapping("/sms")
public class SmsController {

	final static Logger logger = LoggerFactory.getLogger(SmsController.class);

	@Autowired
	private SmsServiceHandler smsService;
	/**
	 * 短信验证吗
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SMSIdentifyingCode", method = RequestMethod.POST)
	public String SMSIdentifyingCode(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(smsService.SMSIdentifyingCode(reqParam));
	}
	
	/**
	 * 短信验证吗生成器
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/SMSCodeGenerator", method = RequestMethod.POST)
	public String SMSCodeGenerator(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		String jsonString = JSON.toJSONString(smsService.SMSCodeGenerator(reqParam));
		return jsonString;
	}
	
	
}