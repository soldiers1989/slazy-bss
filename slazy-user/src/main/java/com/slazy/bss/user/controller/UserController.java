package com.slazy.bss.user.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.aspectj.lang.annotation.Pointcut;
import org.json.JSONException;
import org.slazyframework.restful.RequestParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.user.service.AddressService;
import com.slazy.bss.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private AddressService addressService;

	/**
	 * 用户查询
	 * 
	 * @param params
	 * @throws JSONException
	 */
	@RequestMapping(value = "/queryUserInfoByMobile", method = RequestMethod.POST)
	public String queryUserInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.queryUserInfoByMobile(reqParam));

	}

//	/**
//	 * 查询用户信息
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping("/queryUserInfo/{userId}")
//	public SysUser getAllUserInfo(@PathVariable("userId") String userId) throws Exception {
//		return null;
//	}

	/**
	 * 根据用户ID查询用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/queryUserInfoById", method = RequestMethod.POST)
	public String getAllUserInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.queryUserInfoById(reqParam));
	}
	
	@RequestMapping(value = "/queryUI", method = RequestMethod.GET)
	public String queryUI(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.queryUserInfoById(reqParam));
	}
	
	/**
	 * 用户注册接口
	 * 
	 * @param params
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(@RequestBody String params) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("用户注册开始，参数为%s", params));
		}
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.registerUser(reqParam));
	}

	/**
	 * 查询用户信息修改
	 * 
	 * @return
	 * @throws JSONException
	 */
	@RequestMapping(value = "/alterPassword", method = RequestMethod.POST)
	public String alterPassword(@RequestBody String params) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("用户密码修改功能开始，参数为%s", params));
		}
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.alterPasswd(reqParam));
	}

	/**
	 * 用户登陆验证
	 * 
	 * @param params
	 * @throws JSONException
	 */
	@RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
	public String loginVerify(@RequestBody String params) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("用户登陆验证开始，参数为%s", params));
		}
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.loginVerify(reqParam));
	}

	// TODO 完善客户信息
	@RequestMapping(value = "/perfectionUserInfo", method = RequestMethod.POST)
	public String perfectionUserInfo(@RequestBody String params) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("完善用户信息开始，参数为%s", params));
		}
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.perfectionUserInfo(reqParam));
	}
	
	/**
	 * 上传用户头像
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value="需要手机号mobile,图片文件userPicture参数", notes="上传用户头像操作，图片文件不得超过2M")
	@ApiImplicitParams({
	    @ApiImplicitParam(name = "token", value = "密钥", required = true, dataType = "String"),
		@ApiImplicitParam(name = "restCode", value = "返回码", required = true, dataType = "String"),
		@ApiImplicitParam(name = "userPicture", value = "用户头像图片", required = true, dataType = "File"),
		@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
	})
	@RequestMapping(value = "/uploadUserImage", method = RequestMethod.POST)
	public String uploadUserImage(@RequestParam String token,@RequestParam String restCode,@RequestParam MultipartFile userPicture,@RequestParam String id ) throws Exception {
        if(logger.isDebugEnabled()){
                logger.debug(String.format("上传用户头像开始，参数为token:%s,restcode:%s,id:%s",token,restCode,id));
            }
		return JSON.toJSONString(userService.uploadUserImage(token,restCode,userPicture,id));
	}
	
	/**
	 * 根据用户id获取用户信息
	 * @param params
	 * @return
	 * @throws Exception
	 */
	/*@ApiOperation(value="需要用户id参数", notes="根据用户id获取用户信息")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String")
	@RequestMapping(value = "/getUserInfoById", method = RequestMethod.POST)
	public String getUserInfoById(@RequestBody String params) throws Exception {
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("根据用户id获取用户信息开始，参数为%s", params));
		}
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(userService.getUserInfoById(reqParam));
	}*/
	
	/**
	 * 客户地址信息查询
	 * 
	 * @param params
	 * @throws Exception
	 */
	@ApiOperation(value="客户userId，地址addressId等参数", notes="根据客户id，地址id等信息获取用户地址信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String"),
		@ApiImplicitParam(name = "addressId", value = "地址id", required = true, dataType = "String"),
	})
	@RequestMapping(value = "/queryUserAddressInfo", method = RequestMethod.POST)
	public String queryUserAddressInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(addressService.queryAddressByInfo(reqParam));

	}
	
	/**
	 * 客户地址信息更新
	 * 
	 * @param params
	 * @throws Exception
	 */
	@ApiOperation(value="需要客户地址id及需要更新的参数", notes="根据地址id更新客户地址信息")
	@ApiImplicitParam(name = "addressId", value = "地址id", required = true, dataType = "String")
	@RequestMapping(value = "/updateUserAddressInfo", method = RequestMethod.POST)
	public String updateUserAddressInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(addressService.updateAddressByInfo(reqParam));

	}
	
	/**
	 * 新增客户地址信息
	 * 
	 * @param params
	 * @throws JSONException
	 */
	@ApiOperation(value="需要用户id参数", notes="根据用户id新增地址信息")
	@ApiImplicitParam(name = "userId", value = "用户id", required = true, dataType = "String")
	@RequestMapping(value = "/insertUserAddressInfo", method = RequestMethod.POST)
	public String insertUserAddressInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(addressService.insertAddressByUserID(reqParam));

	}
	
	/**
	 * 删除客户地址信息
	 * 
	 * @param params
	 * @throws JSONException
	 */
	@ApiOperation(value="需要用户地址addressId参数", notes="根据用户地址id删除地址信息")
	@ApiImplicitParam(name = "addressId", value = "地址id", required = true, dataType = "String")
	@RequestMapping(value = "/deleteUserAddressInfo", method = RequestMethod.POST)
	public String deleteUserAddressInfo(@RequestBody String params) throws Exception {
		RequestParams reqParam = JSON.parseObject(params, RequestParams.class);
		return JSON.toJSONString(addressService.deleteAddressByInfo(reqParam));

	}

}