package test;

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

	public static void main(String[] args) throws Exception {
		// perfectionUserInfo();
		// registerUser();
//		 orderPublish();
		// alterPassword();
		// loginVerify();
		// SMSIdentifyingCode();
		// SMSCodeGenerator();
		// queryUserInfo();
		// orderQuery();
		 queryOrderInfosByStatus();
		// orderAwait();
		// orderDelivery();
		// orderComplete();
//		orderCancel();
	}

	// 用户注册测试
	public static String orderCancel() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/orderCancel");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("receiverId", "2");
		jsonParam.put("orderId", "853adaf1dbed45b4950e4d7de7fc9ead");
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

	// 用户注册测试
	public static String orderComplete() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/orderComplete");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("receiverId", "2");
		jsonParam.put("orderId", "23d4409582634bc48d4f713b1430702d");
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

	// 用户注册测试
	public static String orderDelivery() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/orderDelivery");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("receiverId", "2");
		jsonParam.put("orderId", "23d4409582634bc48d4f713b1430702d");
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

	// 用户注册测试
	public static String orderAwait() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/orderAwait");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("receiverId", "2");
		jsonParam.put("orderId", "23d4409582634bc48d4f713b1430702d");
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

	// 用户注册测试
	public static String queryOrderInfosByStatus() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/queryOrderInfosByStatus");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("receiverId", "2");
		jsonParam.put("orderStatus", "1001");
		jsonParam.put("timestamp", "");
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

	// 用户注册测试
	public static String orderPublish() throws Exception {

		HttpPost httpPost = new HttpPost("http://localhost:8081/order/orderPublish");
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

	// 用户注册测试
	public static String registerUser() throws Exception {

		HttpPost httpPost = new HttpPost("http://120.79.199.55:8080/user/registerUser");
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

		HttpPost httpPost = new HttpPost("https://localhost:9001/queryUserInfo");
		CloseableHttpClient client = HttpClients.createDefault();
		String respContent = null;
		// json方式
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("mobile", "13699138599");

		RequestParams req = new RequestParams();
		req.setData(jsonParam.toString());

		String json = "{\"data\":{\"mobile\":\"18701197321\",\"userId\":\"1\"},\"restCode\":\"007\",\"token\":\"MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTImMTUyMjEyMDMxNTQzNw==\"}";

		StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
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
		HttpPost httpPost = new HttpPost("http://120.79.199.55:8080/user/alterPassword");
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

	// 验证�?
	public static String SMSIdentifyingCode() throws Exception {
		HttpPost httpPost = new HttpPost("http://120.79.199.55:8080/sms/SMSIdentifyingCode");
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

	// 验证�?
	public static String SMSCodeGenerator() throws Exception {
		HttpPost httpPost = new HttpPost("https://localhost:8080/sms/SMSCodeGenerator");
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
		jsonParam.put("userName", "aaa");
		jsonParam.put("userId", "1");
		jsonParam.put("mobile", "13699138599");
		jsonParam.put("password", "123");
		jsonParam.put("email", "2");
		jsonParam.put("sex", "");
		jsonParam.put("birthday", "");
		jsonParam.put("appKey", "3");
		jsonParam.put("nickName", "3");
		jsonParam.put("regionSchool", "3");
		jsonParam.put("area", "1");
		jsonParam.put("address", "1");
		jsonParam.put("creatTime", "1");
		jsonParam.put("finallyTime", "1");
		jsonParam.put("icon", "1");
		jsonParam.put("wxAccount", "1");
		jsonParam.put("wbAccount", "1");
		jsonParam.put("zfbAccount", "1");
		jsonParam.put("studentNo", "1");
		jsonParam.put("userAuth", "1");
		jsonParam.put("userType", "1s");

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

}
