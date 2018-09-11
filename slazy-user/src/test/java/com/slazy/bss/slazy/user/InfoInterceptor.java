package com.slazy.bss.slazy.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slazyframework.restful.RequestParams;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Configuration
public class InfoInterceptor {
	private static final String execution_str_01 = "execution(* com.slazy.bss.user.controller.*.*(..))";// controller包下任意方法
	private static final String execution_str_02 = "execution(* com.slazy.bss.user.controller..*.*(..))";// controller包或子包下任意方法
	private static final String execution_str_03 = "@annotation(org.springframework.web.bind.annotation.RequestMapping)";// 带RequestMapping注解的方法
	private static final String execution_str_04 = "execution(* com.slazy.bss.user.controller..*(..))";// controller包或子包下任意方法

	@Pointcut(execution_str_04)
	private void controllerCut() {
	}

	@Before(value = "controllerCut()")
	public void cutBefore(JoinPoint joinPoint) throws UnsupportedEncodingException, IOException {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest httpServletRequest = requestAttributes.getRequest();
		System.out.println("query string=" + httpServletRequest.getQueryString());
		System.out.println("获取参数为：" + httpServletRequest.getParameter("token"));
		System.out.println("获取参数为：" + httpServletRequest.getAttribute("token"));
		System.out.println("AOP==>" + joinPoint.getArgs());
	}
}