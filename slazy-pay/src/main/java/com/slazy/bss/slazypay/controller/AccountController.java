package com.slazy.bss.slazypay.controller;

import org.slazyframework.restful.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazypay.provider.OpenAccProviderImpl;
import com.slazy.bss.slazypay.provider.PayFinishProviderImpl;
import com.slazy.bss.slazypay.provider.PayReleaseProviderImpl;
import com.slazy.bss.slazypay.provider.PayReturnProviderImpl;
import com.slazy.bss.slazypay.provider.QueryAccInfoProviderImpl;
import com.slazy.bss.slazypay.provider.QueryBalanceProviderImpl;
import com.slazy.bss.slazypay.provider.TradeQuerySingleProviderImpl;
import com.slazy.bss.slazypay.vo.req.OpenAccReqVo;
import com.slazy.bss.slazypay.vo.req.QueryAccInfoReqVo;
import com.slazy.bss.slazypay.vo.req.QueryBalanceReqVo;
import com.slazy.bss.slazypay.vo.req.RechargeReqVo;
import com.slazy.bss.slazypay.vo.req.TradeQuerySingleVo;


@RestController
@RequestMapping("/account")
public class AccountController {

	final static Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	private OpenAccProviderImpl openAccProviderImpl;
	@Autowired
	private PayReleaseProviderImpl payReleaseProviderImpl;
	@Autowired
	private PayFinishProviderImpl payFinishProviderImpl;
	@Autowired
	private PayReturnProviderImpl payReturnProviderImpl;
	@Autowired
	private QueryAccInfoProviderImpl queryAccInfoProviderImpl;
	@Autowired
	private QueryBalanceProviderImpl queryBalanceProviderImpl;
	@Autowired
	private TradeQuerySingleProviderImpl tradeQuerySingleProviderImpl;
	
	/**
	 * 账务开户
	 */
	@RequestMapping(value = "/openAcc", method = RequestMethod.POST)
	public String openAcc(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		OpenAccReqVo vo = JSON.parseObject(reqParam.getData(), OpenAccReqVo.class);
		return JSON.toJSONString(openAccProviderImpl.handle(vo));
	}
	
	/**
	 * 订单发布支付
	 */
	@RequestMapping(value = "/payRelease", method = RequestMethod.POST)
	public String payRelease(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		RechargeReqVo vo = JSON.parseObject(reqParam.getData(), RechargeReqVo.class);
		return JSON.toJSONString(payReleaseProviderImpl.handle(vo));
	}
	
	/**
	 * 订单完成支付
	 */
	@RequestMapping(value = "/payFinish", method = RequestMethod.POST)
	public String payFinish(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		RechargeReqVo vo = JSON.parseObject(reqParam.getData(), RechargeReqVo.class);
		return JSON.toJSONString(payFinishProviderImpl.handle(vo));
	}
	
	/**
	 * 退单支付
	 */
	@RequestMapping(value = "/payReturn", method = RequestMethod.POST)
	public String payReturn(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		RechargeReqVo vo = JSON.parseObject(reqParam.getData(), RechargeReqVo.class);
		return JSON.toJSONString(payReturnProviderImpl.handle(vo));
	}
	
	/**
	 * 账户基础信息查询
	 */
	@RequestMapping(value = "/queryAccInfo", method = RequestMethod.POST)
	public String queryAccInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		QueryAccInfoReqVo vo = JSON.parseObject(reqParam.getData(), QueryAccInfoReqVo.class);
		return JSON.toJSONString(queryAccInfoProviderImpl.handle(vo));
	}
	
	/**
	 * 账户余额查询
	 */
	@RequestMapping(value = "/queryBalance", method = RequestMethod.POST)
	public String queryBalance(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		QueryBalanceReqVo vo = JSON.parseObject(reqParam.getData(), QueryBalanceReqVo.class);
		return JSON.toJSONString(queryBalanceProviderImpl.handle(vo));
	}
	
	/**
	 * 单笔交易查询
	 */
	@RequestMapping(value = "/tradeQuerySingle", method = RequestMethod.POST)
	public String tradeQuerySingle(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		TradeQuerySingleVo vo = JSON.parseObject(reqParam.getData(), TradeQuerySingleVo.class);
		return JSON.toJSONString(tradeQuerySingleProviderImpl.handle(vo));
	}
}
