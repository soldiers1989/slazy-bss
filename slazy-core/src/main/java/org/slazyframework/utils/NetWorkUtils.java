package org.slazyframework.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetWorkUtils {
	public static String getIpAddrByDeviceName(String faceName) throws SocketException {
		NetworkInterface face = NetworkInterface.getByName(faceName);
		Enumeration<InetAddress> addresses = face.getInetAddresses();
		// 每个网络接口,都会有多个"网络地址",比如一定会有lookback地址,会有siteLocal地址等.以及IPV4或者IPV6
		while (addresses.hasMoreElements()) {
			InetAddress address = addresses.nextElement();
			if (address.isSiteLocalAddress() && !address.isLoopbackAddress() && !address.isMulticastAddress()) {
				return address.getHostAddress();
			}
		}
		throw new RuntimeException(String.format("网络设备[%s]IP地址获取失败", faceName));
	}
}
