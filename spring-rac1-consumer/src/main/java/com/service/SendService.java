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
	
	//��������
    private final static String QUEUE_NAME = "queue"; 
	
	public void send() throws IOException{
		Connection connection = senderFactory.getConnection();
		Channel channel = senderFactory.getChennel(connection);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//���͵���Ϣ  
        String message = "hello world!";  
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("�ѷ�");
      //�ر�Ƶ��������  
        channel.close();  
        connection.close();
	}
	
}
