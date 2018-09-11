package com.slazy.bss.user.service;

import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;

public interface AcivityService {

	ResponseParams queryActivity(RequestParams params);

	ResponseParams addActivity(RequestParams params);
}