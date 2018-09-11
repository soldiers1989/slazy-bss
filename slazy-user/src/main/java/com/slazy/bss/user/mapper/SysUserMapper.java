package com.slazy.bss.user.mapper;

import com.slazy.bss.user.model.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String userId);
    
    SysUser selectByMobile(String mobile);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    void updateUserInfoById(SysUser userId);
}