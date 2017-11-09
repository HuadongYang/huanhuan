package com.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
public interface MybatisInter {
	
	public Integer getId(@Param("name")String name);
	
}
