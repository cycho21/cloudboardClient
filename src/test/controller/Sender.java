package test.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import test.model.JsonMessage;
import test.model.Message;
import test.model.MessageRequestHandler;
import test.model.rabbitmq.RabbitmqClient;

import com.rabbitmq.client.Channel;

public class Sender {

	public final static String 					BASIC_QUEUE_NAME = "test.server.cloudboard";
	private RabbitmqClient						client;
	private Channel								channel;
	private JsonMessage							json;
	private MessageRequestHandler messageRequestHandler;

	public Sender() throws IOException {
		json = new JsonMessage();
		createQueue();
	}
	
	public void createQueue() throws IOException {
		
		client = new RabbitmqClient();
		
		channel = client.getChannel();
		
		channel.queueDeclare(BASIC_QUEUE_NAME, false, false, false, null);
		
	}

	public void sendMessage(String message) throws IOException {
		List<Message> messages = new ArrayList<Message>();
		messages.add(new Message(BASIC_QUEUE_NAME, message));
		for (Message m : messages) {
			channel.basicPublish(m.exchange, m.routingKey, null, m.body.getBytes());
		}
	}
	
	public void sendMessage(String clientName, String message) throws IOException {	
		
		List<Message> messages = new ArrayList<Message>();
		messageRequestHandler = new MessageRequestHandler(json);
		json.init_command(clientName, message);
		messageRequestHandler.classify(message);
		
		messages.add(new Message(BASIC_QUEUE_NAME, messageRequestHandler.getMessage()));
		
		for (Message m : messages) {
			channel.basicPublish(m.exchange, m.routingKey, null, m.body.getBytes());
		}
	}
	
	public void closeSender() throws IOException{
		this.client.close();
	}
}
