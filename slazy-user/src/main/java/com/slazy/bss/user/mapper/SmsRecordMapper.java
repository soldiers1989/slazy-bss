package com.slazy.bss.user.mapper;

import com.slazy.bss.user.model.SmsRecord;

public interface SmsRecordMapper {
    int deleteByPrimaryKey(String recordId);

    int insert(SmsRecord record);

    int insertSelective(SmsRecord record);

    SmsRecord selectByPrimaryKey(String recordId);
    
    SmsRecord selectByMobileOnly(String mobile);

    int updateByPrimaryKeySelective(SmsRecord record);

    int updateByPrimaryKey(SmsRecord record);
}