package com.slazy.bss.slazy.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.slazy.bss.user.controller.UserController;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jmessage.api.JMessageClient;
import cn.jmessage.api.group.CreateGroupResult;
import cn.jmessage.api.user.UserInfoResult;

public class Msn {
	
	final static Logger logger = LoggerFactory.getLogger(Msn.class);  
	final static String appkey = null;
	final static String masterSecret = null;

	public static void testGetUserInfo() {
		JMessageClient client = new JMessageClient(appkey, masterSecret);
		try {
			UserInfoResult res = client.getUserInfo("test_user");
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Message: " + e.getMessage());
		}
	}

	public static void testCreateGroup() {
		JMessageClient client = new JMessageClient(appkey, masterSecret);
		try {
			CreateGroupResult res = client.createGroup("test_user", "test_gname1", "description", "test_user");
		} catch (APIConnectionException e) {
			logger.error("Connection error. Should retry later. ", e);
		} catch (APIRequestException e) {
			logger.error("Error response from JPush server. Should review and fix it. ", e);
			logger.info("HTTP Status: " + e.getStatus());
			logger.info("Error Message: " + e.getMessage());
		}
	}

}
