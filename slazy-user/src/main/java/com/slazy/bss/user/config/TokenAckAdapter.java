package com.slazy.bss.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.slazy.bss.user.ack.TokenInterceptor;

@Configuration
public class TokenAckAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		 registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/user/**");
//		 super.addInterceptors(registry);
	}
}
