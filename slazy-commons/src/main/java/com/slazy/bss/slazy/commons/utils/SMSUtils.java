package com.slazy.bss.slazy.commons.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.http.HttpRequest;

import com.slazy.bss.slazy.commons.model.MessageResultVO;

public class SMSUtils {

	private static SMSUtils smsUtils = new SMSUtils();

	private static final Logger logger = LoggerFactory.getLogger(SMSUtils.class);

	private static final String cfmcodeUsername;// 验证码短信提交账户

	private static final String cfmcodePwd;// 验证码短信提交密码

	private static final String sendUrl;// 短信发送地址

	private static final String cfmcodeProductId;// 验证码短信产品编号

	public static SMSUtils getInstance() {
		return smsUtils;
	}

	static {
		// 初始化配置信息
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

	/**
	 * 
	 * @param phoneNo
	 *            发送短信号码
	 * @param content
	 *            发送短信内容
	 * @param type
	 *            短信类型
	 * @return 返回发送结果
	 */
	public MessageResultVO sendMessage(String phoneNo, String content, String type) throws Exception {
		logger.info(String.format("发送短信时接收到的请求参数，发送号码：%s,发送内容：%s，短信类型：%s", phoneNo, content, type));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sname", cfmcodeUsername);
		paramMap.put("spwd", cfmcodePwd);
		paramMap.put("scorpid", "");
		paramMap.put("sprdid", cfmcodeProductId);
		paramMap.put("sdst", phoneNo);
		paramMap.put("smsg", content);
		logger.info("请求参数为：" + paramMap);
		String result = HttpRequest.post(sendUrl).header("Content-Type", "application/x-www-form-urlencoded")
				.disableCache().form(paramMap).execute().body();
		logger.info(String.format("调用三方短信接口响应报文:%s", result));

		// 解析xml
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
	
	/**
	 * 生成验证码
	 * @return
	 */
	public String getGeneratorCode()
	{
		return String.valueOf(new Random().nextInt(899999) + 100000);
	}

}
