package com.slazy.bss.order.mapper;

import com.slazy.bss.order.model.CommodityInfo;

public interface CommodityInfoMapper {
    int deleteByPrimaryKey(String commodityId);

    int insert(CommodityInfo record);

    int insertSelective(CommodityInfo record);

    CommodityInfo selectByPrimaryKey(String commodityId);

    int updateByPrimaryKeySelective(CommodityInfo record);

    int updateByPrimaryKey(CommodityInfo record);
}