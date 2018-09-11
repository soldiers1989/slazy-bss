package com.slazy.bss.order.service.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.time.DateUtils;
import org.json.JSONObject;
import org.slazyframework.constants.SysConstant;
import org.slazyframework.constants.SysConstant.UserConstant;
import org.slazyframework.constants.SysRestTemplate;
import org.slazyframework.restful.RequestParams;
import org.slazyframework.restful.ResponseParams;
import org.slazyframework.restful.RestTemplateAPI;
import org.slazyframework.service.BaseService;
import org.slazyframework.utils.BeanUtils;
import org.slazyframework.utils.Date2Utils;
import org.slazyframework.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.slazy.bss.order.mapper.CommodityInfoMapper;
import com.slazy.bss.order.mapper.OrderInfoMapper;
import com.slazy.bss.order.model.CommodityInfo;
import com.slazy.bss.order.model.OrderInfo;
import com.slazy.bss.order.service.OrderService;
import com.slazy.bss.order.vo.OrderInfoVO;
import com.slazy.bss.order.vo.OrderPublishReqVO;
import com.slazy.bss.order.vo.OrderPublishRespVO;
import com.slazy.bss.order.vo.OrderQueryBatchRespVO;
import com.slazy.bss.order.vo.UserInfoVO;

@Service("orderService")
public class OrderServiceHandler extends BaseService implements OrderService {
	
	final static Logger logger = LoggerFactory.getLogger(OrderServiceHandler.class);

	@Autowired
	private CommodityInfoMapper commodityInfoMapper;
	
	@Autowired
	private OrderInfoMapper orderInfoMapper;
	
	@Transactional
	@Override
	public ResponseParams<OrderPublishRespVO> orderPublish(RequestParams params) {
		ResponseParams<OrderPublishRespVO> rp = new ResponseParams<OrderPublishRespVO>();
		try {
			OrderPublishReqVO opv = JSON.parseObject(params.getData(), OrderPublishReqVO.class);
			
			OrderInfo order = new OrderInfo();
			BeanUtils.copyBeans(opv, order);
			String orderId = IDUtils.getUUID();
			order.setReceiverId(opv.getReveiverId());
			order.setReceiverName(opv.getReveiverName());
			order.setReceiverPhone(opv.getReveiverPhone());
			order.setReceiverAddress(opv.getReveiverAddress());
			order.setOrderId(orderId);
			order.setOrderStatus(SysConstant.OrderStatusConstant.AWAIT.getCode());
			order.setOrderStatus(SysConstant.OrderStatusConstant.AWAIT.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_AWART.getCode());
			order.setOptDate(new Date());
			order.setCreateDate(new Date());
			order.setVersion(0);
			orderInfoMapper.insert(order);
			
			CommodityInfo cinfo = new CommodityInfo();
			BeanUtils.copyBeans(opv, cinfo);
			String commodityId = IDUtils.getUUID();
			cinfo.setCommodityId(commodityId);
			cinfo.setOrderId(orderId);
			cinfo.setCreateDate(new Date());
			commodityInfoMapper.insert(cinfo);
			
			OrderPublishRespVO resp = new OrderPublishRespVO();
			resp.setAmount(order.getAmount());
			resp.setCommodityId(cinfo.getCommodityId());
			resp.setOrderId(order.getOrderId());
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			rp.setData(resp);
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		return rp;
	}

	@Transactional
	@Override
	public ResponseParams orderAwait(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		JSONObject jsonParam;
		try {
			jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo order = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(order == null) {
				rp.setCode(UserConstant.DEFEAT.getCode());
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				return rp;
			}
			
			order.setOrderStatus(SysConstant.OrderStatusConstant.PUBLISH.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_SUCCESS.getCode());
			order.setPayType(SysConstant.PayTypeConstant.WX.getCode());
			order.setOptDate(new Date());
			
			orderInfoMapper.updateByOptimistic(order);
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		
		return rp;
	}
	
	@Transactional
	@Override
	public ResponseParams orderReceiving(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		JSONObject jsonParam;
		try {
			jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo order = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(order == null) {
				rp.setCode(UserConstant.DEFEAT.getCode());
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				return rp;
			}
			
			String _str_user = RestTemplateAPI.getInstance().execution(restTemplate, SysRestTemplate.QUERY_USER_ID, params);
			UserInfoVO userVO = JSON.parseObject(_str_user, UserInfoVO.class);
			
			order.setDeliveryId(userVO.getUserId());
			order.setDeliveryName(userVO.getUsername());
			order.setDeliveryMobile(userVO.getMobile());
			order.setDeliveryAddress(userVO.getAddress());
			order.setDeliveryStartime(new Date());
			order.setDeliveryEndtime(DateUtils.addHours(new Date(), 1));
			order.setOrderStatus(SysConstant.OrderStatusConstant.RECEIVING.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_SUCCESS.getCode());
			order.setPayType(SysConstant.PayTypeConstant.WX.getCode());
			order.setOptDate(new Date());
			
			orderInfoMapper.updateByOptimistic(order);
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		return rp;
	}

	@Override
	public ResponseParams orderDelivery(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		JSONObject jsonParam;
		try {
			jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo order = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(order == null) {
				rp.setCode(UserConstant.DEFEAT.getCode());
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				return rp;
			}
			
			order.setOrderStatus(SysConstant.OrderStatusConstant.DELIVERING.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_SUCCESS.getCode());
			order.setPayType(SysConstant.PayTypeConstant.WX.getCode());
			order.setOptDate(new Date());
			
			orderInfoMapper.updateByOptimistic(order);
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		
		return rp;
	}

	@Override
	public ResponseParams orderComplete(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		JSONObject jsonParam;
		try {
			jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo order = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(order == null) {
				rp.setCode(UserConstant.DEFEAT.getCode());
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				return rp;
			}
			
			order.setOrderStatus(SysConstant.OrderStatusConstant.COMPLETE.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_SUCCESS.getCode());
			order.setPayType(SysConstant.PayTypeConstant.WX.getCode());
			order.setOptDate(new Date());
			
			orderInfoMapper.updateByOptimistic(order);
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		
		return rp;
	}

	@Override
	public ResponseParams orderCancel(RequestParams params) {
		ResponseParams rp = new ResponseParams();
		JSONObject jsonParam;
		try {
			jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo order = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(order == null) {
				rp.setCode(UserConstant.DEFEAT.getCode());
				rp.setMessage(UserConstant.DEFEAT.getMessage());
				return rp;
			}
			
			order.setOrderStatus(SysConstant.OrderStatusConstant.CANCEL.getCode());
			order.setPayStatus(SysConstant.PayStatusConstant.PAY_SUCCESS.getCode());
			order.setPayType(SysConstant.PayTypeConstant.WX.getCode());
			order.setOptDate(new Date());
			
			orderInfoMapper.updateByOptimistic(order);
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		
		return rp;
	}

	@Override
	public ResponseParams<OrderQueryBatchRespVO> queryOrderInfosByStatus(RequestParams params) {
		OrderQueryBatchRespVO orderRespVO = new OrderQueryBatchRespVO();
		List<OrderInfoVO> orderListVO = new ArrayList<OrderInfoVO>();
		ResponseParams<OrderQueryBatchRespVO> rp = new ResponseParams<OrderQueryBatchRespVO>();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String receiverId = jsonParam.getString("receiverId");
			String orderStatus = jsonParam.getString("orderStatus");
			String timestamp = jsonParam.getString("timestamp");
			List<OrderInfo> orderList = orderInfoMapper.selectByStatus(receiverId,orderStatus, timestamp.equals("") ? null : timestamp);
			
			if(orderList == null)
			{
				rp.setCode(UserConstant.ISNULL.getCode());
				rp.setMessage(UserConstant.ISNULL.getMessage());
				return rp;
			};
			
			OrderInfoVO orderVO = null;
			for(OrderInfo orderInfo : orderList) {
				orderVO = new OrderInfoVO();
				BeanUtils.copyBeans(orderInfo, orderVO);
				orderListVO.add(orderVO);
			}
			
			orderRespVO.setList(orderListVO);
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			rp.setData(orderRespVO);
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		return rp;
	}

	@Override
	public ResponseParams<OrderInfoVO> queryOrderInfosById(RequestParams params) {
		ResponseParams<OrderInfoVO> rp = new ResponseParams<OrderInfoVO>();
		try {
			JSONObject jsonParam = new JSONObject(params.getData());
			String orderId = jsonParam.getString("orderId");
			String receiverId = jsonParam.getString("receiverId");
			OrderInfo orderInfo = orderInfoMapper.selectOrderById(orderId,receiverId);
			
			if(orderInfo == null)
			{
				rp.setCode(UserConstant.ISNULL.getCode());
				rp.setMessage(UserConstant.ISNULL.getMessage());
				return rp;
			}
			
			rp.setCode(UserConstant.SUCCESS.getCode());
			rp.setMessage(UserConstant.SUCCESS.getMessage());
			OrderInfoVO oiVO = new OrderInfoVO();
			BeanUtils.copyBeans(orderInfo, oiVO);
			rp.setData(oiVO);
		} catch (Exception e) {
			rp.setCode(UserConstant.DEFEAT.getCode());
			rp.setMessage(UserConstant.DEFEAT.getMessage());
			e.printStackTrace();
		}
		return rp;
	}

	@Override
	public ResponseParams queryOrderStatue(RequestParams params) {
		// TODO Auto-generated method stub
		return null;
	}

}
