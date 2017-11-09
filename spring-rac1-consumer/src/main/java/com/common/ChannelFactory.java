package com.common;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class ChannelFactory {
	
	public static Connection getConnection() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();  
        //设置MabbitMQ所在主机ip或者主机名   
        factory.setHost("127.0.0.1"); 
        //创建一个连接  
        Connection connection = factory.newConnection();
        return connection;
	}
	
	public static Channel getChennel(Connection connection) throws IOException{
		return connection.createChannel();
	}
	
	
	
}
