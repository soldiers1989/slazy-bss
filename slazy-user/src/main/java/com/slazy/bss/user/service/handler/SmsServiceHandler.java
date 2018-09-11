package com.slazy.bss.user.service.handler;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slazyframework.constants.SysConstant.SmsConstant;
import org.slazyframework.constants.SysConstant.SmsStatusConstant;
import org.slazyframework.constants.SysConstant.UserConstant;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slazyframework.service.BaseService;
import org.slazyframework.utils.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slazy.bss.user.mapper.SmsRecordMapper;
import com.slazy.bss.user.mapper.SysUserMapper;
import com.slazy.bss.user.model.SmsRecord;
import com.slazy.bss.user.model.SysUser;
import com.slazy.bss.user.service.SmsService;
import com.slazy.bss.user.vo.SmsMessageVO;

@Service("smsService")
public class SmsServiceHandler extends BaseService implements SmsService {

	final static Logger logger = LoggerFactory.getLogger(SmsServiceHandler.class);
	@Autowired
	private SmsRecordMapper smsMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;

	// 短信验证吗
	public ResponseParams SMSIdentifyingCode(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		SmsRecord sms = null;
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String mobile = jsonParam.getString("mobile");
			String code = jsonParam.getString("code");
			sms = smsMapper.selectByMobileOnly(mobile);
			
			if(null == sms) 
			{
				rp.setCode(SmsStatusConstant.DEFEAT.getCode());
				rp.setMessage(SmsStatusConstant.DEFEAT.getMessage());
				return rp;
			}
			
			Date sendTime = sms.getSendTime();
			if(sendTime.after(DateUtils.addMinutes(new Date(), -3)) && sendTime.before(DateUtils.addMinutes(new Date(), 3)) && sms.getMessage().contains(code))
			{
				SMSUtils.getInstance().sendMessage(mobile, sms.getMessage(), sms.getSmsType());
				rp.setCode(SmsStatusConstant.SUCCESS.getCode());
				rp.setMessage(SmsStatusConstant.SUCCESS.getMessage());
				return rp;
			}
			rp.setCode(SmsStatusConstant.TIMEOUT.getCode());
			rp.setMessage(SmsStatusConstant.TIMEOUT.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rp;
	}
	
	// 生成短信验证吗
	public ResponseParams SMSCodeGenerator(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		SysUser user = null;
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String verifyCode = SMSUtils.getInstance().getGeneratorCode();
			String mobile = jsonParam.getString("mobile");
			
			user = sysUserMapper.selectByMobile(mobile);
			if(user == null) {
				rp.setCode(UserConstant.ISNULL.getCode());
				rp.setMessage(UserConstant.ISNULL.getMessage());
				return rp;
			}
			
			SmsRecord sms = new SmsRecord();
			sms.setRecordId(UUID.randomUUID().toString().replace("-", ""));
			sms.setMobile(user.getMobile());
			sms.setMessage("【微网通联】验证码：" + verifyCode + "，3分钟内有效。感谢您注册小懒科技，如非本人操作请忽略。");
			sms.setSendTime(new Date());
			sms.setSmsType(SmsConstant.MARKET.getCode());
			sms.setTemplateNo("1001");
			
			smsMapper.insert(sms);
			
			SmsMessageVO smsVo = new SmsMessageVO();
			smsVo.setMobile(mobile);
			smsVo.setCode(verifyCode);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			rp.setData(smsVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rp;
	}


}