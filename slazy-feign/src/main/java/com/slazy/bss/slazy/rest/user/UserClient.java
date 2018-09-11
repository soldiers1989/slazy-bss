package com.slazy.bss.slazy.rest.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.slazy.bss.slazy.service.user.UserService;

@RestController
public class UserClient {
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/queryUserInfoByMobile", method = RequestMethod.POST, consumes="application/json")
	public String queryUserInfoByMobile(@RequestBody String params) {
		return userService.queryUserInfoByMobile(params);
	}
	
	@RequestMapping(value = "/queryUserInfoById", method = RequestMethod.POST, consumes="application/json")
	public String queryUserInfoById(@RequestBody String params) {
		return userService.queryUserInfoById(params);
	}
	
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes="application/json")
	public String registerUser(@RequestBody String params) {
		return userService.registerUser(params);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes="application/json")
	public String login(@RequestBody String params) {
		return userService.loginVerify(params);
	}
	
	@RequestMapping(value = "/alterPassword", method = RequestMethod.POST, consumes="application/json")
	public String alterPassword(@RequestBody String params) {
		return userService.alterPasswd(params);
	}
	
	@RequestMapping(value = "/perfectionUserInfo", method = RequestMethod.POST, consumes="application/json")
	public String perfectionUserInfo(@RequestBody String params) {
		return userService.perfectionUserInfo(params);
	}
	
	@RequestMapping(value = "/uploadUserImage", method = RequestMethod.POST, consumes="application/json")
	public String uploadUserImage(@RequestBody String params) {
		return userService.uploadUserImage(params);
	}
	
	@RequestMapping(value = "/updateUserAddressInfo", method = RequestMethod.POST, consumes="application/json")
	public String updateUserAddressInfo(@RequestBody String params) {
		return userService.updateUserAddressInfo(params);
	}
	
	@RequestMapping(value = "/queryUserAddressInfo", method = RequestMethod.POST, consumes="application/json")
	public String queryUserAddressInfo(@RequestBody String params) {
		return userService.queryUserAddressInfo(params);
	}
	
	@RequestMapping(value = "/insertUserAddressInfo", method = RequestMethod.POST, consumes="application/json")
	public String insertUserAddressInfo(@RequestBody String params) {
		return userService.insertUserAddressInfo(params);
	}
	
	@RequestMapping(value = "/deleteUserAddressInfo", method = RequestMethod.POST, consumes="application/json")
	public String deleteUserAddressInfo(@RequestBody String params) {
		return userService.deleteUserAddressInfo(params);
	}
	
	@RequestMapping(value = "/SMSIdentifyingCode", method = RequestMethod.POST, consumes="application/json")
	public String SMSIdentifyingCode(@RequestBody String params) {
		return userService.SMSIdentifyingCode(params);
	}
	
	@RequestMapping(value = "/SMSCodeGenerator", method = RequestMethod.POST, consumes="application/json")
	public String SMSCodeGenerator(@RequestBody String params) {
		return userService.SMSCodeGenerator(params);
	}
	
	@RequestMapping(value = "/queryActivity", method = RequestMethod.POST, consumes="application/json")
	public String queryActivity(@RequestBody String params) {
		return userService.queryActivity(params);
	}
	
	@RequestMapping(value = "/addActivity", method = RequestMethod.POST, consumes="application/json")
	public String addActivity(@RequestBody String params) {
		return userService.addActivity(params);
	}
}
