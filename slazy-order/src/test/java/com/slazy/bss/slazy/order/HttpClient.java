package com.slazy.bss.slazy.order;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slazyframework.restful.RequestParams;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClient {

	// 用户注册测试
	public static String orderPublish() throws Exception {

		HttpPost httpPost = new HttpPost("http://127.0.0.1:8081/order/orderPublish");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("deliveryId", "1");
		jsonParam.put("deliveryName", "zhaojian");
		jsonParam.put("deliveryPhone", "18701197321");
		jsonParam.put("deliveryAddress", "清华园");
		jsonParam.put("deliverySchoolId", "11");
		jsonParam.put("deliveryStartime", "18711115555");
		jsonParam.put("deliveryEndtime", "18711115555");
		
		jsonParam.put("reveiverId", "2");
		jsonParam.put("reveiverName", "along");
		jsonParam.put("reveiverPhone", "13699138599");
		jsonParam.put("reveiverAddress", "清华园");
		jsonParam.put("reveiverSchoolId", "12");
		jsonParam.put("reveiverStarttime", "12");
		jsonParam.put("reveiverEndtime", "12");
		
		jsonParam.put("expressMessage", "你好，欢迎使用顺丰快递");
		jsonParam.put("expressName", "顺丰快递");
		jsonParam.put("commoditySize", "5g");
		jsonParam.put("amount", "5");
		jsonParam.put("remark", "快去快回");
		jsonParam.put("coupon", "888888");
		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());
		req.setRestCode("007");
		req.setToken("MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTImMTUyMjEyMDMxNTQzNw==");

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
		return respContent;
	}


	public static void main(String[] args) throws Exception {
		orderPublish();
	}

}
