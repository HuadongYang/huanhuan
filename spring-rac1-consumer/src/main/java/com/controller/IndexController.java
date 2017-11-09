package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.ThreadTest;
import com.service.BatchNumTest;
import com.service.ReceiveService;
import com.service.RedisServiceTest;
import com.service.SendService;
import com.service.SynDbTest;

@Controller
public class IndexController {
	
	@Autowired
	RedisServiceTest redisServiceTest;
	
	@Autowired
	ThreadPoolTaskExecutor taskExecutor;
	
	@Autowired
	BatchNumTest batchNumTest;
	
	@Autowired
	SynDbTest synDbTest;
	
	@Autowired
	SendService sendService;
	
	@Autowired
	ReceiveService receiveService; 
	
	@RequestMapping("/index")
	@ResponseBody
	public String getWel(){
		return "wel";
	}
	
	@RequestMapping("/redis-test")
	@ResponseBody
	public String getRedsi(){
		return redisServiceTest.redisTest();
	}
	
	
	@RequestMapping("/thread")
	@ResponseBody
	public void threadTest(){
		for (int i = 0; i < 7; i++) {
			taskExecutor.execute(new ThreadTest(synDbTest));
		}
	}
	@RequestMapping("/static")
	@ResponseBody
	public String staticTest(){
		 return batchNumTest.getBatchNum();
	}
	
	@RequestMapping("/synDb")
	@ResponseBody
	public void synDb(){
		for (int i = 0; i < 5; i++) {
			taskExecutor.execute(new ThreadTest(synDbTest));
		}
		//return synDbTest.insertStudent();
		//synDbTest.getIdByClassroomId();
	}
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public void sendMessage() throws Exception{
		sendService.send();
		receiveService.receive();
	}
	
	@RequestMapping("/mybatis")
	@ResponseBody
	public Integer getIdByName(@RequestParam("name")String name) throws Exception{
		return synDbTest.getIdByName("dsf");
	}
	
}
