package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import com.dao.MybatisInter;

@Component
public class MybatisManager {
	
	@Resource
	private MybatisInter mybatisInter;
	
	public Integer getIdByName(String name){
		return mybatisInter.getId(name);
	}
}
