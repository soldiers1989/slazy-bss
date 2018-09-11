package com.slazy.bss.order.mapper;

import com.slazy.bss.order.model.CouponUsers;

public interface CouponUsersMapper {
    int deleteByPrimaryKey(String couponUserId);

    int insert(CouponUsers record);

    int insertSelective(CouponUsers record);

    CouponUsers selectByPrimaryKey(String couponUserId);

    int updateByPrimaryKeySelective(CouponUsers record);

    int updateByPrimaryKey(CouponUsers record);
}