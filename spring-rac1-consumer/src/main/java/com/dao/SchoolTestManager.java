package com.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SchoolTestManager {
	
	private static Integer lid = 45;
	
	@Autowired
	DataSource dataSource;
	
	public Integer getIdByClassroomId(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select max(id) from student  ";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Integer insertStudent(Integer id){
		lid += 1;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into student values( " + id + ", 'qwe', 'ewr', " + lid + " ) ";
		return jdbcTemplate.update(sql);
	}
}
