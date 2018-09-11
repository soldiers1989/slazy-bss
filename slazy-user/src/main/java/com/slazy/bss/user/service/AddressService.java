package com.slazy.bss.user.service;

import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;

public interface AddressService {
	
	ResponseParams queryAddressByInfo(RequestParams params);

	ResponseParams updateAddressByInfo(RequestParams params);

	ResponseParams insertAddressByUserID(RequestParams params);

	ResponseParams deleteAddressByInfo(RequestParams params);
}
