package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceTest {
	
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	public String redisTest(){
		StringRedisTemplate stringRedisTemplate =new StringRedisTemplate(redisConnectionFactory);
		stringRedisTemplate.opsForValue().set("key1", "value1");
		return stringRedisTemplate.opsForValue().get("key1");
	}
	
}
