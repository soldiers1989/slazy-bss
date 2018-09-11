package com.slazy.bss.user.mapper;

import com.slazy.bss.user.model.ACK;

public interface ACKMapper {
    int deleteByPrimaryKey(String authId);

    int insert(ACK record);

    int insertSelective(ACK record);

    ACK selectByPrimaryKey(String authId);

    int updateByPrimaryKeySelective(ACK record);

    int updateByPrimaryKey(ACK record);
}