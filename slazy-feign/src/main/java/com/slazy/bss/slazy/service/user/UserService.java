package com.slazy.bss.slazy.service.user;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户接口列表
 *
 */
@FeignClient("user-service")
public interface UserService {

	// 用户信息查询
	@RequestMapping("/user/queryUserInfoByMobile")
	String queryUserInfoByMobile(String params);

	// 用户信息查询
	@RequestMapping("/user/queryUserInfoById")
	String queryUserInfoById(String params);

	// 新增用户
	@RequestMapping("/user/registerUser")
	String registerUser(String params);

	// 密码重置
	@RequestMapping("/user/alterPassword")
	String alterPasswd(String params);

	// 用户登陆验证
	@RequestMapping("/user/loginVerify")
	String loginVerify(String params);

	// 完善客户信息
	@RequestMapping("/user/perfectionUserInfo")
	String perfectionUserInfo(String params);

	// 上传图片
	@RequestMapping("/user/uploadUserImage")
	String uploadUserImage(String params);

	// 客户地址信息查询
	@RequestMapping("/user/updateUserAddressInfo")
	String updateUserAddressInfo(String params);

	// 客户地址更新
	@RequestMapping("/user/queryUserAddressInfo")
	String queryUserAddressInfo(String params);

	// 客户地址新增
	@RequestMapping("/user/insertUserAddressInfo")
	String insertUserAddressInfo(String params);

	// 客户地址删除
	@RequestMapping("/user/deleteUserAddressInfo")
	String deleteUserAddressInfo(String params);

	// 短信验证吗
	@RequestMapping("/sms/SMSIdentifyingCode")
	String SMSIdentifyingCode(String params);

	// 短信验证吗生成
	@RequestMapping("/sms/SMSCodeGenerator")
	String SMSCodeGenerator(String params);

	// 活动信息查询
	@RequestMapping("/activity/queryActivity")
	String queryActivity(String params);

	// 新增活动
	@RequestMapping("/activity/addActivity")
	String addActivity(String params);

}
