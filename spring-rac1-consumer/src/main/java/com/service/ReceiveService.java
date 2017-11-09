package com.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.common.ChannelFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

@Service
public class ReceiveService {
	//��������  
    private final static String QUEUE_NAME = "queue"; 
	
	@Autowired
	ChannelFactory senderFactory;
	
	public void receive() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		Connection connection = senderFactory.getConnection();
		Channel channel = senderFactory.getChennel(connection);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//��������������  
        QueueingConsumer consumer = new QueueingConsumer(channel); 
        
        //ָ�����Ѷ���  
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        while (true)  
        {  
            //nextDelivery��һ�������������ڲ�ʵ����ʵ���������е�take������  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println("Received '" + message + "'");
        }  
        
	}
}
