package com.slazy.bss.slazy.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringBootApplication
public class SlazyZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SlazyZuulApplication.class, args);
	}
}
