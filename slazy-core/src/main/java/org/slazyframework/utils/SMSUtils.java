package org.slazyframework.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.slazyframework.model.MessageResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.http.HttpRequest;


public class SMSUtils {

	private static SMSUtils smsUtils = new SMSUtils();

	private static final Logger logger = LoggerFactory.getLogger(SMSUtils.class);

	private static final String cfmcodeUsername;

	private static final String cfmcodePwd;

	private static final String sendUrl;

	private static final String cfmcodeProductId;

	public static SMSUtils getInstance() {
		return smsUtils;
	}

	static {
		InputStream is = SMSUtils.class.getClassLoader().getResourceAsStream("sms.properties");
		Properties prop = new Properties();
		try {
			prop.load(is);
		} catch (Exception e) {
			logger.error(ExceptionUtil.getMessage(e));
		}
		System.out.println(prop);
		cfmcodeUsername = prop.getProperty("sms.cfmcodeUsername");
		cfmcodePwd = prop.getProperty("sms.cfmcodePwd");
		sendUrl = prop.getProperty("sms.url");
		cfmcodeProductId = prop.getProperty("sms.cfmcodeProductID");
	}

	public MessageResultVO sendMessage(String phoneNo, String content, String type) throws Exception {
		logger.info(String.format("手机号：%s,短信内容%s短信类型%s", phoneNo, content, type));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sname", cfmcodeUsername);
		paramMap.put("spwd", cfmcodePwd);
		paramMap.put("scorpid", "");
		paramMap.put("sprdid", cfmcodeProductId);
		paramMap.put("sdst", phoneNo);
		paramMap.put("smsg", content);
		String result = HttpRequest.post(sendUrl).header("Content-Type", "application/x-www-form-urlencoded")
				.disableCache().form(paramMap).execute().body();
		logger.info(String.format("发送结果%s", result));

		DocumentFactory factory = DocumentFactory.getInstance();
		Document document = DocumentHelper.parseText(result);
		Map<String, String> namespaceMap = new HashMap<String, String>();
		namespaceMap.put("t", "http://tempuri.org/");
		factory.setXPathNamespaceURIs(namespaceMap);
		String state = factory.createXPath("/t:CSubmitState/t:State").selectSingleNode(document).getStringValue();
		String msgId = factory.createXPath("/t:CSubmitState/t:MsgID").selectSingleNode(document).getStringValue();
		String msgState = factory.createXPath("/t:CSubmitState/t:MsgState").selectSingleNode(document).getStringValue();
		String reserve = factory.createXPath("/t:CSubmitState/t:Reserve").selectSingleNode(document).getStringValue();
		MessageResultVO res = new MessageResultVO(state, msgId, msgState, reserve);
		return res;
	}
	
	public String getGeneratorCode()
	{
		return String.valueOf(new Random().nextInt(899999) + 100000);
	}

}
