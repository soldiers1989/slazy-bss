package com.slazy.bss.user.mapper;

import java.util.List;
import java.util.Map;

import com.slazy.bss.user.model.SysActivity;

public interface SysActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysActivity record);

    int insertSelective(SysActivity record);

    SysActivity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysActivity record);

    int updateByPrimaryKey(SysActivity record);
    
    List<SysActivity> selectActivity(Map map);
}