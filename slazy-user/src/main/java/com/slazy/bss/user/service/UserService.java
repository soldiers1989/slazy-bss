package com.slazy.bss.user.service;

import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

	ResponseParams queryUserInfoByMobile(RequestParams params);

	ResponseParams queryUserInfoById(RequestParams params);

	ResponseParams registerUser(RequestParams params);

	ResponseParams alterPasswd(RequestParams params);

	ResponseParams loginVerify(RequestParams params);
	// 完善客户信息
	ResponseParams perfectionUserInfo(RequestParams params);

	ResponseParams uploadUserImage(String token, String restCode, MultipartFile userPicture, String mobile);
	// 下载头像图片
	//ResponseParams downloadUserImage(RequestParams params);
	
	ResponseParams verifyToken(RequestParams params);

}