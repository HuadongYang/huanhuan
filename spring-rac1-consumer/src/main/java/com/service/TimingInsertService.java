package com.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.Util.DateUtil;
import com.Util.JsonUtil;
import com.dao.DmDeviceManager;
import com.wrapper.DmStatisticsDeviceEntity;

//定时插入数据
public class TimingInsertService {
	
	@Autowired
	DmDeviceManager dmDeviceManager;
	
	public void dayTask() throws IOException{
		Date date = DateUtil.dayBefore(new Date());
		List<Map<String, Object>> list= dmDeviceManager.getDeviceNumByTime(date, date);
		DmStatisticsDeviceEntity entity = new DmStatisticsDeviceEntity(
				DateUtil.dateToString(DateUtil.DATE_FORMATE, date),
				"day",
				JsonUtil.objectToJson(list)
				);
		
		
	}
	
	public void monthTask(){
		Date startTime = DateUtil.getFirstDayOfMonth(new Date());
		Date endTime = DateUtil.getLastDayOfMonth(new Date());
		List<Map<String, Object>> list = dmDeviceManager.getDeviceNumByTime(startTime, endTime);
	}
	
	public void quaterTask(){
		Date startTime = DateUtil.getFirstDayOfMonth(new Date());
		List<Map<String, Object>> list = dmDeviceManager.getDeviceNumByTime(startTime, new Date());
	}


	public void insertDmStatisticsDevice(DmStatisticsDeviceEntity entity){
		
	}
	
	
	
}
