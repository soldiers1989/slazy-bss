package org.slazyframework.restful;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

public class RestTemplateAPI {

	public static RestTemplateAPI getInstance() {
		return new RestTemplateAPI();
	}

	public String execution(RestTemplate restTemplate, String url, RequestParams params) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<JSONObject> entity = new HttpEntity<JSONObject>((JSONObject) JSONObject.toJSON(params), headers);
		HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity,String.class);
		return response.getBody();
	}

}
