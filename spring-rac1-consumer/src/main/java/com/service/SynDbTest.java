package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MybatisManager;
import com.dao.SchoolTestManager;

@Service
public class SynDbTest {
	@Autowired
	SchoolTestManager schoolTestManager;
	
	@Autowired
	MybatisManager mybatisManager;
	
	public synchronized void getIdByClassroomId(){
		//∂¡»°
		Integer sid = schoolTestManager.getIdByClassroomId();
		System.out.println(Thread.currentThread().getName()+"---"+sid);
		//≤Â»Î
		Integer c = schoolTestManager.insertStudent(sid+1);
	}
	
	public Integer insertStudent(){
		Integer c = schoolTestManager.getIdByClassroomId();
		return c;
	}
	
	public Integer getIdByName(String name){
		Integer name1 = mybatisManager.getIdByName(name);
		return mybatisManager.getIdByName(name);
	}
	
}
