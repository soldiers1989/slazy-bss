package com.slazy.bss.slazypay.service;

import java.util.List;

import com.slazy.bss.slazypay.model.Page;





public interface BaseService<T> {
	
	List<T> selectAll();
	
	T selectByPrimaryKey(Long id);
	
    int insert(T t);
    
    int updateByPrimaryKey(T t);
    
    int deleteByPrimaryKey(String ids);

    Page<T> findTByPage(Page<T> page, T t);
	
    List<T> findTByT(T t);
    
    T findTByTOne(T t);
	
}
