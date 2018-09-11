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
import com.slazy.bss.user.service.AcivityService;

@RestController
@RequestMapping("/activity")
public class ActivityController {

	final static Logger logger = LoggerFactory.getLogger(ActivityController.class);

	@Autowired
	private AcivityService acivityService;

	/**
	 * 活动查询
	 */
	@RequestMapping(value = "/queryActivity", method = RequestMethod.POST)
	public String queryActivity(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(acivityService.queryActivity(reqParam));

	}


	/**
	 * 新增活动
	 */
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST)
	public String addActivity(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(acivityService.addActivity(reqParam));
	}

}