package org.slazyframework.utils;

import java.util.UUID;

public class IDUtils {

	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
