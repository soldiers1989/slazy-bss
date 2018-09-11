package test;

import org.slazyframework.utils.HttpClientUtils;

public class HttpsClient {
	
	public static void main(String[] args){ 
        String url = "https://localhost:9001/queryUserInfo";
        String json = "{\"data\":{\"mobile\":\"18701197321\",\"userId\":\"1\"},\"restCode\":\"007\",\"token\":\"MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0NTY3ODkwMTImMTUyMjEyMDMxNTQzNw==\"}";
        String resp = HttpClientUtils.doPost(url, json, "utf-8");
        System.out.println(resp);
    }
}
