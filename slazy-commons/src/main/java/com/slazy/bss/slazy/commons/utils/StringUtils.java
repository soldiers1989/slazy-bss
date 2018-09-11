package com.slazy.bss.slazy.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class StringUtils {

	public static String encodeString(String string, Charset originCharset, Charset targetCharset) {
		return string = new String(string.getBytes(originCharset), targetCharset);
	}

	public static String encodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLEncoder.encode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(string);
	}

	public static String decodeUrl(String string, String charset) {
		if (null != charset && !charset.isEmpty()) {
			try {
				return URLDecoder.decode(string, charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return URLDecoder.decode(string);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}
}
