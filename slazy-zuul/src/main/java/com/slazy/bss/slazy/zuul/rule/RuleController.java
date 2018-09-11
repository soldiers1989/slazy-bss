package com.slazy.bss.slazy.zuul.rule;

import org.springframework.web.bind.annotation.RequestMapping;

public class RuleController {
	@RequestMapping("/index")
	public Object index() {
		return "index";
	}
	
	@RequestMapping("/home")
	public Object home() {
		return "home";
	}
}
