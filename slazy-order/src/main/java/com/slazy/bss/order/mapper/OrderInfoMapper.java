package com.slazy.bss.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.slazy.bss.order.model.OrderInfo;

public interface OrderInfoMapper {
	
    int deleteByPrimaryKey(String orderId);

    int insert(OrderInfo record);

    int insertSelective(OrderInfo record);

    OrderInfo selectByPrimaryKey(String orderId);
    
    List<OrderInfo> selectByStatus(@Param(value = "receiverId") String receiverId,@Param(value = "orderStatus") String orderStatus,@Param("createDate") String createDate);

    int updateByPrimaryKeySelective(OrderInfo record);

    int updateByPrimaryKey(OrderInfo record);
    
    int updateByOptimistic(OrderInfo record);
    
    OrderInfo selectOrderById(@Param(value = "orderId") String orderId,@Param(value = "receiverId") String receiverId);
    
}