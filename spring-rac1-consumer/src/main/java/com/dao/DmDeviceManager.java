package com.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.Util.DateUtil;
import com.wrapper.DmStatisticsDeviceEntity;

@Component
public class DmDeviceManager {
	@Autowired
	DataSource dataSource;
	
	public List<Map<String, Object>> getDeviceNumByTime(Date startTime, Date endTime){
		String start = DateUtil.dateToString(DateUtil.DATE_FORMATE, startTime);
		String end = DateUtil.dateToString(DateUtil.DATE_FORMATE, endTime);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder sql = new StringBuilder();
		sql.append(" select model_type type, count(id) numberfrom dm_device de where 1=1 ");
		sql.append("and  ");
		sql.append(" DATE_FORMAT(de.produced_date, '%Y-%m-%d') >=" + start );
		sql.append("and " + end + " >=  DATE_FORMAT(de.produced_date, '%Y-%m-%d')");
		sql.append(" GROUP BY de.model_type ");
		return jdbcTemplate.queryForList(sql.toString());
	}
}
