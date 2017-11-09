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
	//队列名称  
    private final static String QUEUE_NAME = "queue"; 
	
	@Autowired
	ChannelFactory senderFactory;
	
	public void receive() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		Connection connection = senderFactory.getConnection();
		Channel channel = senderFactory.getChennel(connection);
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//创建队列消费者  
        QueueingConsumer consumer = new QueueingConsumer(channel); 
        
        //指定消费队列  
        channel.basicConsume(QUEUE_NAME, true, consumer);
        
        while (true)  
        {  
            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println("Received '" + message + "'");
        }  
        
	}
}
