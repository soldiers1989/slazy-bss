package com.slazy.bss.user.mapper;

import com.slazy.bss.user.model.SysRolePermission;

public interface SysRolePermissionMapper {
    int insert(SysRolePermission record);

    int insertSelective(SysRolePermission record);
}