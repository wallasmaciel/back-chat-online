package com.wallas.project.chatonline.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.wallas.project.chatonline.entities.LoginRequest;
import com.wallas.project.chatonline.entities.LoginResponse;
import com.wallas.project.chatonline.entities.TalkResponse;
import com.wallas.project.chatonline.entities.UserResponse;
import com.wallas.project.chatonline.models.User;
import com.wallas.project.chatonline.repositories.TalkRepository;
import com.wallas.project.chatonline.repositories.UserRepository;
import com.wallas.project.chatonline.utils.BCryptPassword;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TalkRepository talkRepository;

	@PostMapping("/user/login")
	LoginResponse login(@RequestBody LoginRequest loginPayload) {
		User user = userRepository.findByEmail(loginPayload.getEmail());
		if (user == null || !BCryptPassword.verifyPassword(loginPayload.getPassword(), user.getPassword()))
			throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "invalid authentication.");
		return new LoginResponse(user);
	}
	
	@GetMapping("/user/list/{user_id}")
	List<UserResponse> listOthersUsers(@PathVariable UUID user_id) {
		List<UserResponse> list = new ArrayList<>();
		for (User user : userRepository.findByOtherUsers(user_id))
			list.add(new UserResponse(user));
		return list;
	}
	
	@GetMapping("/user/talk/{first_user_id}/{second_user_id}")
	TalkResponse listTalk(@PathVariable UUID first_user_id, @PathVariable UUID second_user_id) {
		return new TalkResponse(
			talkRepository.findByUsersTalk(first_user_id, second_user_id)
		);
	}
}
