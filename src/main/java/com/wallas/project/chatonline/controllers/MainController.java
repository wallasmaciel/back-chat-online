package com.wallas.project.chatonline.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.wallas.project.chatonline.entities.ChatMessageData;
import com.wallas.project.chatonline.entities.ConnectionData;
import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.repositories.TalkRepository;
import com.wallas.project.chatonline.services.ChatService;

@Controller
public class MainController {
  private Channel channel;
  private Map<String, String> listConsumer;
  @Autowired
  private SimpMessagingTemplate simpMessagingTemplate;
  @Autowired
  private ChatService chatService;
  @Autowired
  private TalkRepository talkRepository;

  public MainController(ConnectionFactory connectionFactory) throws Exception {
	  this.channel = connectionFactory.newConnection().createChannel();
	  this.listConsumer = new HashMap<>();
  }
  
  @EventListener
  private void handleSessionConnected(SessionConnectEvent event) {
      System.out.println("Conectado...");
  }

  @EventListener
  private void handleSessionDisconnect(SessionDisconnectEvent event) {
	  System.out.println("Desconectado...");
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
			// Message receiver in JSON convert to class
			Message receiverMessage = new Message(utf8Message);
			// Verify exists message
			Optional<Message> talkContainsMessage = talkRepository.findByMessageTalk(receiverMessage.getMessage_Id());
			// Talk exists 
			try {
				if (!talkContainsMessage.isPresent())
					chatService.saveMessage(receiverMessage);
				else
					simpMessagingTemplate.convertAndSend("/topic" + queue.getName() + "/consume", utf8Message);
				channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}, ConsumerTag-> {})
    );
  }
 
  @MessageMapping("/send")
  public void send(@Payload ChatMessageData chatMessageData, MessageListenerAdapter messageListenerAdapte) throws Exception {
	  UUID to = UUID.fromString(chatMessageData.getTo());
	  UUID from = UUID.fromString(chatMessageData.getFrom());
	  // 
	  chatService.saveMessage(
		  new Message(to, from, chatMessageData.getContent())
	  );
  }
}
