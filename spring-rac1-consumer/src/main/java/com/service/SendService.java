package com.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.common.ChannelFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

@Service
public class SendService {
	@Autowired
	ChannelFactory senderFactory;
	
	//队列名称  
    private final static String QUEUE_NAME = "queue"; 
	
	public void send() throws IOException{
		Connection connection = senderFactory.getConnection();
		Channel channel = senderFactory.getChennel(connection);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//发送的消息  
        String message = "hello world!";  
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("已发");
      //关闭频道和连接  
        channel.close();  
        connection.close();
	}
	
}
