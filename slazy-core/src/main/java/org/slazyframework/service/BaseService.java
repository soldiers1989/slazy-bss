package org.slazyframework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("baseService")
public class BaseService {

	@Autowired
	public RestTemplate restTemplate;
	
	
}
