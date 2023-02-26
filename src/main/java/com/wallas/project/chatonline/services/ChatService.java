package com.wallas.project.chatonline.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.models.Talk;
import com.wallas.project.chatonline.repositories.TalkRepository;
import com.wallas.project.chatonline.utils.JSONConvert;

@Service
public class ChatService {
	@Autowired
	private ConnectionFactory connectionFactory;
	@Autowired
	private TalkRepository talkRepository;

	public void saveMessage(Message message) throws IOException, TimeoutException {
		Channel channel = connectionFactory.newConnection().createChannel();
		// Create new queues for users if not exists
		// User send message
		Queue queueTo = new Queue("/user/" + message.getTo());
		channel.queueDeclare(queueTo.getName(), queueTo.isDurable(), queueTo.isExclusive(), queueTo.isAutoDelete(), queueTo.getArguments());
		// User receiver message
		Queue queueFrom = new Queue("/user/" + message.getFrom());
		channel.queueDeclare(queueFrom.getName(), queueFrom.isDurable(), queueFrom.isExclusive(), queueFrom.isAutoDelete(), queueFrom.getArguments());
		// Insert message into conversation between users
		Optional<Talk> talk = talkRepository.findByUsersTalk(message.getTo(), message.getFrom());
		// If not exists talk, create and insert into db
		if (!talk.isPresent()) 
			talkRepository.save(
				talkRepository.insert(
					new Talk(
						Set.of(message.getTo(), message.getFrom()),
						List.of(new Message(message.getMessage_Id(), message.getTo(), message.getFrom(), message.getContent()))
					)
				)
			);
		// if exists, add the new message to talk and insert
		else {
			talk.get().getMessages().add(new Message(message.getMessage_Id(), message.getTo(), message.getFrom(), message.getContent()));
			talkRepository.save(talk.get());
		}
		// Publish to rabbitmq users' channel
		channel.basicPublish("", queueTo.getName(), null, JSONConvert.parse(message).getBytes());
		channel.basicPublish("", queueFrom.getName(), null, JSONConvert.parse(message).getBytes());
		channel.close();
	}
}
