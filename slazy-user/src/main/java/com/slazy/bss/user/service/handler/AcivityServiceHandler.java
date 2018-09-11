package com.slazy.bss.user.service.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.slazyframework.constants.SysConstant.UserConstant;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slazyframework.service.BaseService;
import org.slazyframework.utils.Date2Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slazy.bss.user.mapper.SysActivityMapper;
import com.slazy.bss.user.model.SysActivity;
import com.slazy.bss.user.service.AcivityService;
import com.slazy.bss.user.vo.SysActivityVO;

@Service("acivityService")
public class AcivityServiceHandler extends BaseService implements AcivityService {
	
	final static Logger logger = LoggerFactory.getLogger(AcivityServiceHandler.class);

	@Autowired
	private SysActivityMapper sysActivityMapper;

	//活动查询
	@SuppressWarnings("rawtypes")
	public ResponseParams queryActivity(RequestParams params) {
		logger.info("活动查询入参："+params.getData());
		ResponseParams rp = new ResponseParams();
		try {
		JSONObject jsonParam = new JSONObject(params.getData());
		Map<String,String> map = new HashMap<String,String>();
		String beginTime = Date2Utils.date2Str(Date2Utils.getFirstDateOfMonth(new Date()), null)+" 00:00:00";
		String endTime = Date2Utils.date2Str(Date2Utils.getLastDateOfMonth(new Date()), null)+" 23:59:59";
		map.put("beginTime", beginTime);
		map.put("endTime", endTime);
		List<SysActivity> activityList = sysActivityMapper.selectActivity(map);
		
		rp.setCode(UserConstant.SUCCESS.getCode());
		rp.setMessage(UserConstant.SUCCESS.getMessage());

		SysActivityVO vo = new SysActivityVO();
		vo.setList(activityList);
		rp.setData(vo);
		logger.info("活动查询完毕！");
	}catch(Exception e) {
		logger.error("活动查询异常：",e);
		rp.setMessage(UserConstant.DEFEAT.getMessage());
		rp.setCode(UserConstant.DEFEAT.getCode());
	}
		return rp;
	}

	// 新增活动
	@SuppressWarnings("rawtypes")
	@Transactional
	public ResponseParams addActivity(RequestParams params) {
		logger.info("添加活动入参："+params.getData());
		ResponseParams rp = new ResponseParams();
		rp.setCode(UserConstant.SUCCESS.getCode());
		rp.setMessage(UserConstant.SUCCESS.getMessage());
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			SysActivity activity = new SysActivity();
			activity.setState("0");
			activity.setCreateTime(new Date());
			activity.setContent(jsonParam.getString("content"));
			sysActivityMapper.insert(activity);
			logger.info("添加活动完毕！");
		}catch(Exception e) {
			logger.error("添加活动异常：",e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;
	}

}