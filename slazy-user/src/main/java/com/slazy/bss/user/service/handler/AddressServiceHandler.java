package com.slazy.bss.user.service.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.slazyframework.constants.SysConstant.UserConstant;
import org.slazyframework.model.BaseModel;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slazyframework.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slazy.bss.user.mapper.AddressHarvestMapper;
import com.slazy.bss.user.model.AddressHarvest;
import com.slazy.bss.user.service.AddressService;
import com.slazy.bss.user.vo.AddressVO;

@Service("addressService")
public class AddressServiceHandler implements AddressService {
	
	final static Logger logger = LoggerFactory.getLogger(AddressServiceHandler.class);
	
	@Autowired
	private AddressHarvestMapper addressMapper;

	/**
	 * 通过给定条件查询客户地址信息
	 */
	@Override
	public ResponseParams queryAddressByInfo(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String userId = jsonParam.getString("userId");
			String addressId = jsonParam.getString("addressId");
			String addressType = jsonParam.getString("addressType");
			Map<String,String> queryMap = new HashMap<String, String>();
			List<AddressHarvest> queryList = addressMapper.queryAddressInfoByMap(queryMap);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			BaseModel baseModel = new BaseModel(JSON.toJSONString(queryList));
			rp.setData(baseModel);
		} catch (Exception e) {
			logger.error("查询地址信息异常：",e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}

		return rp;
	}

	/**
	 * 通过给定条件更新客户地址信息
	 */
	@Override
	public ResponseParams updateAddressByInfo(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			AddressHarvest addressObj = addressMapper.selectByPrimaryKey(jsonParam.getString("addressId"));
			if (addressObj == null) {
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				rp.setCode(UserConstant.DEFEAT.getCode());
				return rp;
			}
			AddressVO addressVO = JSON.parseObject(params.getData(), AddressVO.class);
			AddressHarvest addressRes = new AddressHarvest();
			BeanUtils.copyBeans(addressVO, addressRes);
			addressMapper.updateByPrimaryKeySelective(addressRes);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
		} catch (Exception e) {
			logger.error("更新客户地址信息异常：", e);
			rp.setMessage(e.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}

		return rp;
	}
	
	/**
	 * 插入客户地址信息
	 */
	@Override
	public ResponseParams insertAddressByUserID(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		rp.setCode(UserConstant.SUCCESS.getCode());
		rp.setMessage(UserConstant.SUCCESS.getMessage());
		try {
			AddressVO addressVO = JSON.parseObject(params.getData(), AddressVO.class);
			addressVO.setAddressId(UUID.randomUUID().toString().replace("-", ""));
			AddressHarvest addressRes = new AddressHarvest();
			BeanUtils.copyBeans(addressVO, addressRes);
			addressMapper.insert(addressRes);

		} catch (Exception e) {
			logger.error("插入客户地址信息异常：", e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}
		return rp;
	}

	/**
	 * 删除客户地址信息
	 */
	@Override
	public ResponseParams deleteAddressByInfo(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String addressId = jsonParam.getString("addressId");
			addressMapper.deleteByPrimaryKey(addressId);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			logger.error("删除客户地址信息异常：",e);
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			rp.setCode(UserConstant.DEFEAT.getCode());
		}

		return rp;
	}

}
