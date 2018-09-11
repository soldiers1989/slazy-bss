package com.slazy.bss.order.mapper;

import com.slazy.bss.order.model.Coupon;

public interface CouponMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);
}