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
        //����MabbitMQ��������ip����������   
        factory.setHost("127.0.0.1"); 
        //����һ������  
        Connection connection = factory.newConnection();
        return connection;
	}
	
	public static Channel getChennel(Connection connection) throws IOException{
		return connection.createChannel();
	}
	
	
	
}
