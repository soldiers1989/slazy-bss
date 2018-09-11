package com.slazy.bss.order.mapper;

import com.slazy.bss.order.model.OrderEstimate;

public interface OrderEstimateMapper {
    int deleteByPrimaryKey(String estimateId);

    int insert(OrderEstimate record);

    int insertSelective(OrderEstimate record);

    OrderEstimate selectByPrimaryKey(String estimateId);

    int updateByPrimaryKeySelective(OrderEstimate record);

    int updateByPrimaryKey(OrderEstimate record);
}