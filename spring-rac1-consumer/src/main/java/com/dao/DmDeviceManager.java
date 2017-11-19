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
		sql.append(" select model_type type, count(id) number from dm_device de where 1=1 ");
		sql.append(" and  ");
		sql.append(" DATE_FORMAT(de.produced_date, '%Y-%m-%d') >= '" + start );
		sql.append(" ' and '" + end + "' >=  DATE_FORMAT(de.produced_date, '%Y-%m-%d')");
		sql.append(" GROUP BY de.model_type ");
		return jdbcTemplate.queryForList(sql.toString());
	}
	
	public List<Map<String, Object>> getAllDeviceNum(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select model_type type, count(id) number,  DATE_FORMAT(produced_date, '%Y-%m-%d') time from dm_device "
				+ " group by model_type, produced_date order by produced_date " ;
		return jdbcTemplate.queryForList(sql);
	}
	
	public Integer getLength(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select count(1) from dm_device ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Integer queryQuaterExist(DmStatisticsDeviceEntity entity){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select count(id) from dm_statistics_device where product_time=? and date_type=? ";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[]{entity.getProductTime(), entity.getDateType()}, Integer.class);
		} catch (Exception e) {
			return 0;
		}
		
		
	}
	
	public void insertData(DmStatisticsDeviceEntity dmStatisticsDeviceEntity) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		StringBuilder sql = new StringBuilder();
		sql.append("insert into dm_statistics_device (product_time,date_type,data_num) values (?,?,?)");
		Object[] objects = new Object[]{
				dmStatisticsDeviceEntity.getProductTime(),
				dmStatisticsDeviceEntity.getDateType(),
				dmStatisticsDeviceEntity.getDataNum()
		};
		jdbcTemplate.update(sql.toString(),objects);
	}
	
	public void insertData(String sqlData){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into dm_statistics_device (product_time,date_type,data_num) values " + sqlData;
		jdbcTemplate.update(sql);
	}
	
	public void updateData(DmStatisticsDeviceEntity dmStatisticsDeviceEntity){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "update dm_statistics_device set data_num= ? where product_time=? and date_type=? ";
		Object[] objects = new Object[]{
				dmStatisticsDeviceEntity.getDataNum(),
				dmStatisticsDeviceEntity.getProductTime(),
				dmStatisticsDeviceEntity.getDateType()
		};
		jdbcTemplate.update(sql,objects);
	}
}

