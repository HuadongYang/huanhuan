package com.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Util.DateUtil;
import com.Util.JsonUtil;
import com.dao.DmDeviceManager;
import com.wrapper.DmStatisticsDeviceEntity;

//定时插入数据
@Service
public class TimingInsertService {
	
	@Autowired
	DmDeviceManager dmDeviceManager;
	
	public void dayTask() throws IOException{
		Date date = new Date();
		List<Map<String, Object>> list= dmDeviceManager.getDeviceNumByTime(date, date);
		DmStatisticsDeviceEntity entity = new DmStatisticsDeviceEntity(
				DateUtil.dateToString(DateUtil.DATE_FORMATE, date),
				1,
				JsonUtil.objectToJson(list)
				);
		dmDeviceManager.insertData(entity);
	}
	
	public void monthTask() throws IOException{
		Date startTime = DateUtil.getFirstDayOfMonth(new Date());
		Date endTime = DateUtil.getLastDayOfMonth(new Date());
		List<Map<String, Object>> list = dmDeviceManager.getDeviceNumByTime(startTime, endTime);
		DmStatisticsDeviceEntity entity = new DmStatisticsDeviceEntity(
				DateUtil.dateToString(DateUtil.MONTH_FORMATE, startTime),
				2,
				JsonUtil.objectToJson(list)
				);
		dmDeviceManager.insertData(entity);
	}
	
	public void quaterTask() throws IOException{
		//季的第一天到今天
		Date startTime = DateUtil.getFirstDayOfQuarter(new Date());
		List<Map<String, Object>> list = dmDeviceManager.getDeviceNumByTime(startTime, new Date());
		DmStatisticsDeviceEntity entity = new DmStatisticsDeviceEntity(
				DateUtil.dateToString(DateUtil.MONTH_FORMATE, DateUtil.getFirstDayOfQuarter(new Date())),
				3,
				JsonUtil.objectToJson(list)
				);
		if (dmDeviceManager.queryQuaterExist(entity) == 0) {
			dmDeviceManager.insertData(entity);
		}else {
			dmDeviceManager.updateData(entity);
		}
	}


	public void insertDmStatisticsDevice(DmStatisticsDeviceEntity entity){
		
	}
	
	
	
}
