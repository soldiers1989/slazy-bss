package com.slazy.bss.user.mapper;

import java.util.List;
import java.util.Map;

import com.slazy.bss.user.model.AddressHarvest;

public interface AddressHarvestMapper {
    int deleteByPrimaryKey(String addressId);

    int insert(AddressHarvest record);

    int insertSelective(AddressHarvest record);

    AddressHarvest selectByPrimaryKey(String addressId);

    int updateByPrimaryKeySelective(AddressHarvest record);

    int updateByPrimaryKey(AddressHarvest record);
    
    List<AddressHarvest> queryAddressInfoByMap(Map<String,String> map);
}