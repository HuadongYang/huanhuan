package com.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Util.DateUtil;
import com.Util.JsonUtil;
import com.dao.DmDeviceManager;
@Service
public class DealOldData {
	
	@Autowired
	DmDeviceManager dmDeviceManager;
	
	public void dealHistoryData() throws ParseException, IOException{
		String sqlData = dealQueryDevice();
		dmDeviceManager.insertData(sqlData);
	}
	
	public String dealQueryDevice() throws ParseException, IOException{
		List<Map<String, Object>> devicesData = dmDeviceManager.getAllDeviceNum();
		List<Map<String, Object>> devices = dealDevices(devicesData);
		Date dateBefore = null;
		Date now = null;
		Map<String, Object> map = null;
		StringBuilder sql = new StringBuilder();
		for(Map<String, Object> device : devices){
			if (dateBefore == null) {
				sql.append(mapToSql(device) + ",");
				dateBefore = DateUtil.stringToDate(device.get("time").toString(), DateUtil.DATE_FORMATE);
				continue;
			}
			now = DateUtil.stringToDate(device.get("time").toString(), DateUtil.DATE_FORMATE);
			while (DateUtil.dayPoor(dateBefore, now)>1) {
				dateBefore = DateUtil.dayAfter(dateBefore);
				map = new HashMap<String, Object>();
				map.put("time", DateUtil.dateToString( DateUtil.DATE_FORMATE, dateBefore));
				map.put("type", null);
				map.put("number", null);
				sql.append(mapToSql(map) + ",");
			}
			sql.append(mapToSql(device) + ",");
			dateBefore = now;
		}
		return sql.substring(0, sql.length()-1);
	}
	
	public List<Map<String, Object>> dealDevices(List<Map<String, Object>> devices) throws IOException{
		List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
		List<Map<String, String>> dataType = new ArrayList<Map<String,String>>();
		Map<String, String> data = null;
		String date = null;
		for(Map<String, Object> device : devices){
			if (date == null) {
				date = device.get("time").toString();
				data = new HashMap<String, String>();
				data.put("type", device.get("type").toString());
				data.put("number", device.get("number").toString());
				dataType.add(data);
				continue;
			}
			if (date.equals(device.get("time").toString())) {
				data = new HashMap<String, String>();
				data.put("type", device.get("type").toString());
				data.put("number", device.get("number").toString());
				dataType.add(data);
				continue;
			}else {
				Map<String, Object> tempMap = new HashMap<String, Object>();
				tempMap.put("time", date);
				tempMap.put("type", "1");
				tempMap.put("number", JsonUtil.objectToJson(dataType));
				dataType = new ArrayList<Map<String,String>>();
				lists.add(tempMap);
				//新的一天
				date = device.get("time").toString();
				data = new HashMap<String, String>();
				data.put("type", device.get("type").toString());
				data.put("number", device.get("number").toString());
				dataType.add(data);
				continue;
			}
		}
		//最后一天
		Map<String, Object> tempMap = new HashMap<String, Object>();
		tempMap.put("time", date);
		tempMap.put("type", "1");
		tempMap.put("number", JsonUtil.objectToJson(dataType));
		lists.add(tempMap);
		return lists;
	}
	
	public String mapToSql(Map<String, Object> map){
		StringBuilder dataSql = new StringBuilder("(");
		dataSql.append("'" + map.get("time") + "'" + ",");
		dataSql.append(map.get("type") + ",");
		if (map.get("number") == null) {
			dataSql.append(map.get("number"));
		}else {
			dataSql.append("'" + map.get("number") + "'");
		}
		dataSql.append(")");
		return dataSql.toString();
	}
}
