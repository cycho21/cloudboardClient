package test.controller;

import java.io.IOException;

import test.model.rabbitmq.RabbitmqClient;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver extends Thread{
	private String								queueName;
	private RabbitmqClient						client;
	private Channel								channel;
	private String								message;
	private QueueingConsumer					consumer;
	private MainController						mc;

	public Receiver(MainController mc)  throws java.io.IOException,
			java.lang.InterruptedException {
		this.mc = mc;
		
		client = new RabbitmqClient();
		channel = client.getChannel();
	}
	
	public void createQueue(String clientName) throws IOException{
		queueName = clientName;
		channel.queueDeclare(queueName, false, false, false, null);
		consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);		
	}
	
	@Override
	public void run() {
		try {
			this.receiveMessage();
		} catch (ShutdownSignalException | ConsumerCancelledException
				| InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void receiveMessage() throws InterruptedException {
		
		while (true) {
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			
			String message = new String(delivery.getBody());
			
			System.out.println(message);
			
			this.setMessage(message);
			
			if(!message.equals("")){
				
				mc.receiveMessage();
			}
		}
	}
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void closeReceiver() throws IOException {
		this.channel.queueDelete(queueName);
		this.client.close();
	}
}
