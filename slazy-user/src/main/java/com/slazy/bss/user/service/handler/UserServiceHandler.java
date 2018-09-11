package com.slazy.bss.user.service.handler;

import java.io.File;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;
import org.slazyframework.constants.SysConstant.SmsStatusConstant;
import org.slazyframework.constants.SysConstant.UserConstant;
import org.slazyframework.model.BaseModel;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slazyframework.service.BaseService;
import org.slazyframework.utils.Base64Util;
import org.slazyframework.utils.BeanUtils;
import org.slazyframework.utils.IDUtils;
import org.slazyframework.utils.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.user.mapper.SmsRecordMapper;
import com.slazy.bss.user.mapper.SysUserMapper;
import com.slazy.bss.user.model.SmsRecord;
import com.slazy.bss.user.model.SysUser;
import com.slazy.bss.user.service.UserService;
import com.slazy.bss.user.utils.JgUtils;
import com.slazy.bss.user.vo.UserInfoVO;

@Service("userService")
public class UserServiceHandler extends BaseService implements UserService {

	final static Logger logger = LoggerFactory.getLogger(UserServiceHandler.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SmsRecordMapper smsMapper;

	@Value("${user.photo.dir}")
	private String photoDir;
	
	/*@Value("${server.port:8080}")
    private String port;//服务端口号
	
	@Value("${local.network.device.name}")
	private String deviceName;//设备网卡名
	
	@Value("${file.httpprefix}")
	private String httpPrefix;//下载文件http地址前缀
	
	@Value("${file.host.IPAddress}")
	private String ipAddress;//应用服务器公网地址
*/
	@Value("${file.httpsPrefix}")
	private String httpsPrefix;//下载文件https地址前缀

	// 所有用户信息查询
	public ResponseParams queryUserInfoByMobile(RequestParams params) {
		SysUser queryUserInfo = null;
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			queryUserInfo = sysUserMapper.selectByMobile(jsonParam.getString("mobile"));
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			UserInfoVO userInfo = new UserInfoVO();
			BeanUtils.copyBeans(queryUserInfo, userInfo);
			rp.setData(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 调试信息
		if (logger.isDebugEnabled())
			String.format("用户信息查询完成，用户ID%s，用户名为%s，手机号为%s", queryUserInfo.getUserId(), queryUserInfo.getUsername(),
					queryUserInfo.getMobile());

		return rp;
	}

	// 所有用户信息查询
	public ResponseParams queryUserInfoById(RequestParams params) {
		SysUser queryUserInfo = null;
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			queryUserInfo = sysUserMapper.selectByPrimaryKey(jsonParam.getString("userId"));
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			UserInfoVO userInfo = new UserInfoVO();
			BeanUtils.copyBeans(queryUserInfo, userInfo);
			rp.setData(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 调试信息
		if (logger.isDebugEnabled())
			String.format("用户信息查询完成，用户ID%s，用户名为%s，手机号为%s", queryUserInfo.getUserId(), queryUserInfo.getUsername(),
					queryUserInfo.getMobile());

		return rp;
	}

	// 用户注册
	@Transactional
	public ResponseParams registerUser(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		rp.setCode(UserConstant.SUCCESS.getCode());
		rp.setMessage(UserConstant.SUCCESS.getMessage());
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			SysUser su = new SysUser();
			su.setUserId(IDUtils.getUUID());
			su.setPassword(jsonParam.getString("password"));
			su.setMobile(jsonParam.getString("mobile"));
			SysUser queryUserInfo = sysUserMapper.selectByMobile(su.getMobile());

			// if(queryUserInfo!=null) {
			// rp.setMessage(UserConstant.DEFEAT.getMessage());
			// rp.setCode(UserConstant.DEFEAT.getCode());
			// return rp;
			// }

			// 1 注册用户信息
			sysUserMapper.insert(su);
			// 2注册极光用户
			JgUtils.JgRegisterUser(su.getMobile(), su.getUserId());
			// 调试信息
			if (logger.isDebugEnabled())
				String.format("注册完成，密码为%s，手机号为%s", su.getPassword(), su.getMobile());

		} catch (Exception e) {
			logger.error("用户注册异常：", e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;
	}

	// 密码重置
	@Transactional
	public ResponseParams alterPasswd(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		rp.setCode(UserConstant.DEFEAT.getCode());
		rp.setMessage(UserConstant.DEFEAT.getMessage());
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String mobile = jsonParam.getString("mobile");
			SysUser sysUser = sysUserMapper.selectByMobile(mobile);
			if (sysUser == null) {
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
				return rp;
			}

			String code = jsonParam.getString("dentifyingCode");
			SmsRecord sms = smsMapper.selectByMobileOnly(mobile);
			Date sendTime = sms.getSendTime();
			if (sendTime.after(DateUtils.addMinutes(new Date(), -3))
					&& sendTime.before(DateUtils.addMinutes(new Date(), 3)) && sms.getMessage().contains(code)) {
				SMSUtils.getInstance().sendMessage(mobile, sms.getMessage(), sms.getSmsType());
				rp.setCode(SmsStatusConstant.SUCCESS.getCode());
				rp.setMessage(SmsStatusConstant.SUCCESS.getMessage());
				sysUser.setPassword(jsonParam.getString("password"));
				sysUserMapper.updateByPrimaryKey(sysUser);
				return rp;
			}

		} catch (Exception e) {
			logger.error("密码重置异常：", e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;

	}

	// 用户登陆验证
	public ResponseParams loginVerify(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		rp.setMessage(UserConstant.DEFEAT.getMessage());
		rp.setCode(UserConstant.DEFEAT.getCode());
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			SysUser sysUser = sysUserMapper.selectByMobile(jsonParam.getString("mobile"));
			if (sysUser == null) {
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
				return rp;
			}
			if (jsonParam.getString("mobile").equals(sysUser.getMobile())
					&& jsonParam.getString("password").equals(sysUser.getPassword())) {
				rp.setCode(UserConstant.SUCCESS.getCode());
				rp.setMessage(UserConstant.SUCCESS.getMessage());
				UserInfoVO userInfo = new UserInfoVO();
				BeanUtils.copyBeans(sysUser, userInfo);
				rp.setData(userInfo);
			}
		} catch (Exception e) {
			logger.error("登陆验证异常：", e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;
	}

	// 完善客户信息
	public ResponseParams perfectionUserInfo(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			SysUser sysUser = sysUserMapper.selectByPrimaryKey(jsonParam.getString("userId"));
			if (sysUser == null) {
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
				return rp;
			}
			UserInfoVO userVO = JSON.parseObject(params.getData(), UserInfoVO.class);
			SysUser user = new SysUser();
			BeanUtils.copyBeans(userVO, user);
			sysUserMapper.updateUserInfoById(user);
			JgUtils.JgUpdateUser(user.getMobile(), user.getUsername());
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("完善客户信息异常：", e);
			rp.setMessage(e.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}

		return rp;
	}

	//上传头像图片
	public ResponseParams uploadUserImage(String token,String restCode,MultipartFile userPicture,String id)  {
		ResponseParams rp = new ResponseParams();
		try {
			SysUser sysUser = sysUserMapper.selectByPrimaryKey(id);
			if(sysUser==null) {
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
				return rp;
			}
			if(null == userPicture){
				rp.setMessage(UserConstant.FILENULL.getMessage());
				rp.setCode(UserConstant.FILENULL.getCode());
				return rp;
			}
			//图片大小不得大于2M
			if(2*1024*1024 < userPicture.getSize()){
				rp.setMessage(UserConstant.OVERSIZE.getMessage());
				rp.setCode(UserConstant.OVERSIZE.getCode());
				return rp;
			}
			String originalFilename = userPicture.getOriginalFilename();
			String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
			String newFileName = UUID.randomUUID().toString().replaceAll("-", "");
			//获取根目录
			File path = new File(ResourceUtils.getURL("classpath:").getPath());
			if(!path.exists()){
				path = new File("");
			}
			logger.info("应用根目录：" + path.getAbsolutePath());
			//上传目录/static/photoImage
			String photoPath = "static" + File.separator + photoDir ;
			File photoFile = new File(path.getAbsolutePath(),photoPath);
			//savePath = "static" + File.separator + photoDir + File.separator + savePath + suffix;
			//File saveFile = new File(path.getAbsolutePath(),savePath);
			if(!photoFile.exists()){
				photoFile.mkdirs();
			}
			File saveFile = new File(photoFile.getAbsolutePath(),newFileName + suffix);
			logger.info("上传文件目录地址:" + saveFile.getAbsolutePath());
			userPicture.transferTo(saveFile);
			sysUser.setIcon(photoDir+File.separator+newFileName+suffix);
			sysUserMapper.updateByPrimaryKey(sysUser);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		}catch(Exception e) {
			logger.error("登陆验证异常：",e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;
	}
	
	//下载头像图片
		public ResponseParams downloadUserImage(String mobile)  {
			ResponseParams rp = new ResponseParams();
			try {
				//JSONObject jsonParam = new JSONObject(params.getData());
				SysUser sysUser = sysUserMapper.selectByMobile(mobile);
				if(sysUser==null) {
					rp.setMessage(UserConstant.DEFEAT.getMessage());
					rp.setCode(UserConstant.DEFEAT.getCode());
					return rp;
				}
				String photoPath = sysUser.getIcon();
				if(null == photoPath){
					rp.setMessage(UserConstant.ISNULL.getMessage());
					rp.setCode(UserConstant.ISNULL.getCode());
					return rp;
				}
				photoPath = photoPath.substring(photoPath.lastIndexOf(photoDir));
				//String httpPhotoUrl=String.format(httpPrefix,NetWorkUtils.getIpAddrByDeviceName(deviceName)) + port + File.separator + photoPath;
				//String httpPhotoUrl=String.format(httpPrefix,ipAddress) + port + File.separator + photoPath;
				String httpPhotoUrl=httpsPrefix + File.separator + photoPath;
				logger.info("下载文件路径：" + httpPhotoUrl);
				rp.setCode(UserConstant.SUCCESS.getCode());
				rp.setMessage(UserConstant.SUCCESS.getMessage());
				rp.setData(new BaseModel(httpPhotoUrl));
				
			}catch(Exception e) {
				logger.error("登陆验证异常：",e);
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
			}
			return rp;
		}

		@Override
		public ResponseParams verifyToken(RequestParams params) {
			ResponseParams rp = new ResponseParams();
			try {
				JSONObject jsonParam = new JSONObject(params.getData());
				String token = params.getToken();
				String userId = jsonParam.getString("userId");
				SysUser sysUser = sysUserMapper.selectByPrimaryKey(userId);
				if (sysUser == null) {
					rp.setMessage(UserConstant.DEFEAT.getMessage());
					rp.setCode(UserConstant.DEFEAT.getCode());
					return rp;
				}
				
				String a_keys = new String(Base64Util.decode(token),"utf-8");
				System.out.println("a_keys=" +a_keys);
//				String a_token = a_keys.split("&")[0];
//				String a_stamp = a_keys.split("&")[1];
				
				String u_keys = new String(Base64Util.decode(sysUser.getToken()),"utf-8");
				System.out.println("u_keys=" + u_keys);
//				String s_token = u_keys.split("&")[0];
//				String s_stamp = u_keys.split("&")[1];
				
				if (!a_keys.equals(u_keys)) {
					rp.setMessage("token验证异常");
					rp.setCode("9999");
					return rp;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				rp.setMessage("token验证异常");
				rp.setCode("9999");
				return rp;
			}
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			return rp;
		}

}