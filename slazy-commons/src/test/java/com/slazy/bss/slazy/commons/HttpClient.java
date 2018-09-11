package com.slazy.bss.slazy.commons;

import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.slazy.commons.restful.RequestParams;

public class HttpClient {

	// 用户注册测试
	public static String registerUser() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8080/user/registerUser");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("password", "admin");
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("dentifyingCode", "000000");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		return respContent;
	}

	// 用户查询测试
	public static String queryUserInfo() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8080/user/queryUserInfo");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		return respContent;
	}

	// 密码修改测试
	public static String alterPassword() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/user/alterPassword");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("password", "adminupdate");
		jsonParam.put("dentifyingCode", "666666");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		return respContent;
	}

	// 登陆
	public static String loginVerify() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/user/loginVerify");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("password", "adminupdate");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}

	// 验证码
	public static String SMSIdentifyingCode() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/sms/SMSIdentifyingCode");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("code", "320011");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}

	// 验证码
	public static String SMSCodeGenerator() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/sms/SMSCodeGenerator");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("token", "000000");
		jsonParam.put("restCode", "100001");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		System.out.println(JSON.toJSONString(req));
		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}

	// 完善客户信息
	public static String perfectionUserInfo() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/user/perfectionUserInfo");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("userName", "");
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("email", "");
		jsonParam.put("sex", "");
		jsonParam.put("birthday", "");
		jsonParam.put("appKey", "");
		jsonParam.put("nickName", "");
		jsonParam.put("regionSchool", "");
		jsonParam.put("area", "");
		jsonParam.put("address", "");
		jsonParam.put("creatTime", "");
		jsonParam.put("finallyTime", "");
		jsonParam.put("icon", "");
		jsonParam.put("wxAccount", "");
		jsonParam.put("wbAccount", "");
		jsonParam.put("zfbAccount", "");
		jsonParam.put("studentNo", "");
		jsonParam.put("userAuth", "");
		jsonParam.put("userType", "");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}
	
	// 添加活动
	public static String addActivity() throws Exception {
			HttpPost httpPost = new HttpPost("http://localhost:8080/activity/addActivity");
			CloseableHttpClient client = HttpClients.createDefault();
			String respContent = null;
			// json方式
			JSONObject jsonParam = new JSONObject();
			jsonParam.put("content", "test");

			RequestParams req = new RequestParams();
			req.setData(jsonParam.toString());

			StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			HttpResponse resp = client.execute(httpPost);
			if (resp.getStatusLine().getStatusCode() == 200) {
				HttpEntity he = resp.getEntity();
				respContent = EntityUtils.toString(he, "UTF-8");
			}
			System.out.println(resp);
			return respContent;
		}

	public static String selectActivity() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8080/activity/queryActivity");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
//		jsonParam.put("content", "test");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}
	
	public static String openAcc() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8082/account/openAcc");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "C_0000001");
		jsonParam.put("customerType", "0");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}
	
	public static String payRelease() throws Exception {
		HttpPost httpPost = new HttpPost("http://localhost:8082/account/payRelease");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("accountNo", "13520991111");
		jsonParam.put("bizId", "1000000");
		jsonParam.put("tranAmount", "10");
		jsonParam.put("payChannel", "16");
		jsonParam.put("tradeTime", "2018-05-09 18:55:33");
		jsonParam.put("feeAmount", "1");
		

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		StringEntity entity = new StringEntity(JSON.toJSONString(req), "utf-8");// 解决中文乱码问题
		entity.setContentEncoding("UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse resp = client.execute(httpPost);
		if (resp.getStatusLine().getStatusCode() == 200) {
			HttpEntity he = resp.getEntity();
			respContent = EntityUtils.toString(he, "UTF-8");
		}
		System.out.println(resp);
		return respContent;
	}
	
	public static void main(String[] args) throws Exception {
//		 registerUser();
//		 queryUserInfo();
		// alterPassword();
		// loginVerify();
//		SMSIdentifyingCode();
//		SMSCodeGenerator();
//		addActivity();
//		selectActivity();
//		openAcc();
		payRelease();
	}

}
