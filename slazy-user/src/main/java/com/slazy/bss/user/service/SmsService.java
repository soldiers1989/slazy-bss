package com.slazy.bss.user.service;

import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;

public interface SmsService {

	ResponseParams SMSIdentifyingCode(RequestParams params);

	ResponseParams SMSCodeGenerator(RequestParams params);
}
