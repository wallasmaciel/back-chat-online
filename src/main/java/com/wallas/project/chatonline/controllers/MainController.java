package com.wallas.project.chatonline.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.wallas.project.chatonline.entities.ChatMessageData;
import com.wallas.project.chatonline.entities.ConnectionData;
import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.models.Talk;
import com.wallas.project.chatonline.repositories.TalkRepository;
import com.wallas.project.chatonline.utils.JSONConvert;

@Controller
public class MainController {
  private Connection connection;
  private Channel channel;
  private Map<String, String> listConsumer;
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  private TalkRepository talkRepository;

  public MainController(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
	  this.connection = connectionFactory.newConnection();
	  this.channel = connection.createChannel();
	  this.listConsumer = new HashMap<>();
  }

  @MessageMapping("/listener")
  public void listener(@Payload ConnectionData webSocketConnection, MessageListenerAdapter messageListenerAdapter) throws Exception {
  	// Create new queue for user
    Queue queue = new Queue("/user/" + webSocketConnection.getId());
    channel.queueDeclare(queue.getName(), queue.isDurable(), queue.isExclusive(), queue.isAutoDelete(), queue.getArguments());
    /** Verify consumer is open for user
    * avoid multiple open unnecessary channels
    */
    String consumeExists = listConsumer.get(queue.getName());
    if (consumeExists != null) 
    	channel.basicCancel(consumeExists);
    // Consume queue
    listConsumer.put(
    	queue.getName(), 
	    channel.basicConsume(queue.getName(), (consumerTag, message) -> {
	    	String utf8Message = new String(message.getBody(), "UTF-8"); 
			System.out.println("[*] Received message: '" + utf8Message + "' " + message.toString());
			simpMessagingTemplate.convertAndSend("/topic" + queue.getName() + "/consume", utf8Message);
			channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
		}, ConsumerTag-> {})
    );
  }
 
  @MessageMapping("/send")
  public void send(@Payload ChatMessageData chatMessageData, MessageListenerAdapter messageListenerAdapte) throws Exception {
	  // Insert message into conversation between users
	  UUID to = UUID.fromString(chatMessageData.getTo());
	  UUID from = UUID.fromString(chatMessageData.getFrom());
	  Talk talk = talkRepository.findByUsersTalk(to, from);
	  talk.getMessages().add(new Message(to, from, chatMessageData.getContent()));
	  talkRepository.save(talk);
	  // Publish to rabbitmq channel
	  channel.basicPublish("", "/user/" + chatMessageData.getTo(), null, JSONConvert.parse(chatMessageData).getBytes());
  }
}
