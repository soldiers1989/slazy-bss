package com.slazy.bss.user.filter;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slazyframework.constants.SysConstant;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.user.service.UserService;

@WebFilter(filterName = "tokenFilter", urlPatterns = { "/activity/*", "/user/*", "/sms/*" })
public class TokenFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(TokenFilter.class);

	@Autowired
	private UserService userService;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ServletRequest requestWrapper = null;
		if (request instanceof HttpServletRequest) {
			requestWrapper = new RestHttpServletRequestWrapper((HttpServletRequest) request);
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(requestWrapper.getInputStream(), "utf-8"));
		StringBuffer sb = new StringBuffer("");
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		br.close();

		logger.info("获取参数为" + sb.toString());
		RequestParams reqParam = JSON.parseObject(sb.toString(), RequestParams.class);
		HttpServletRequest hsr = (HttpServletRequest) request;
		if (hsr.getRequestURI().indexOf("registerUser") > 0 || hsr.getRequestURI().indexOf("loginVerify") > 0) {
			logger.info("注册和登录不进行过滤");
			chain.doFilter(requestWrapper, response);
			return;
		}

		if (!userService.verifyToken(reqParam).getCode().equals(SysConstant.UserConstant.SUCCESS.getCode())) {
			logger.info(String.format("token验证失败"));
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setCharacterEncoding("UTF-8");
			httpResponse.setContentType("application/json; charset=utf-8");
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			ResponseParams rp = new ResponseParams();
			rp.setMessage("token验证异常");
			rp.setCode("9999");
			httpResponse.getWriter().write(JSON.toJSONString(rp));
			return;
		}

		logger.info("token验证成功");
		chain.doFilter(requestWrapper, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
