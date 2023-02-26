package com.wallas.project.chatonline;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.models.Talk;
import com.wallas.project.chatonline.models.User;
import com.wallas.project.chatonline.repositories.TalkRepository;
import com.wallas.project.chatonline.repositories.UserRepository;
import com.wallas.project.chatonline.utils.BCryptPassword;

@Configuration
public class Temp implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TalkRepository talkRepository;
	@Override
	public void run(String... args) throws Exception {
		User wallasUser = new User(
			"Wallas Vitor", 
			"wallas@gmail.com", 
			BCryptPassword.encode("123456"), 
			"https://images.pexels.com/photos/4323761/pexels-photo-4323761.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
		);
		// Verify if email not exists before insert 
		if (!userRepository.findByEmail(wallasUser.getEmail()).isPresent())
			userRepository.insert(wallasUser);
		
		User manuUser = new User(
			"Manu Gabriela", 
			"manu@gmail.com", 
			BCryptPassword.encode("123456"), 
			"https://images.pexels.com/photos/5081397/pexels-photo-5081397.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
		);
		// Verify if email not exists before insert 
		if (!userRepository.findByEmail(manuUser.getEmail()).isPresent())
			userRepository.insert(manuUser);
		// ---------------------------------------------------------
		/*Set<UUID> participants = new HashSet<>();
		participants.add(UUID.fromString("eb4498da-3144-4bb9-8fb7-f9e3a5189460"));
		participants.add(UUID.fromString("a44ad869-6141-44e0-9336-5dba234402e9"));
		// 
		List<Message> messages = new ArrayList<>();
		messages.add(
			new Message(
				UUID.fromString("eb4498da-3144-4bb9-8fb7-f9e3a5189460"),
				UUID.fromString("a44ad869-6141-44e0-9336-5dba234402e9"),
				"Conteudo da mensagem 1",
				new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2023-02-20 19:00:00")
			)
		);
		messages.add(
				new Message(
					UUID.fromString("a44ad869-6141-44e0-9336-5dba234402e9"),
					UUID.fromString("eb4498da-3144-4bb9-8fb7-f9e3a5189460"),
					"Conteudo da mensagem 2",
					new SimpleDateFormat("yyyy-mm-dd HH:MM:ss").parse("2023-02-20 19:01:00")
				)
			);
		// 
		talkRepository.insert(new Talk(participants, messages));*/
		/*
		System.out.println("---------------------");
		for (Talk talk : talkRepository.findAll()) {
			System.out.println(
				talk.getTalk_id() + "|"
			);
		
			for (Message message : talk.getMessages())
				System.out.println(
					message.getMessage_Id() + "|" +
					message.getTo() + "|" + 
					message.getFrom() + "|" +
					message.getContent()
				);
		}

		Optional<Message> talk = talkRepository.findByMessageTalk(UUID.fromString("5571b228-06a8-437c-9e99-0506f7440d5e"));
		System.out.println("-------dasd--------------");
		if (talk.isPresent())
			System.out.println("sim");
		else
			System.out.println("nao");
		*/
	}
}
