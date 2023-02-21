package com.wallas.project.chatonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.wallas.project.chatonline.entities.LoginRequest;
import com.wallas.project.chatonline.entities.LoginResponse;
import com.wallas.project.chatonline.models.User;
import com.wallas.project.chatonline.repositories.UserRepository;
import com.wallas.project.chatonline.utils.BCryptPassword;

@RestController
@RequestMapping("/chat")
public class ChatController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/login")
	LoginResponse login(@RequestBody LoginRequest loginPayload) {
		User user = userRepository.findByEmail(loginPayload.getEmail());
		if (user == null || !BCryptPassword.verifyPassword(loginPayload.getPassword(), user.getPassword()))
			throw new HttpClientErrorException(HttpStatusCode.valueOf(401), "invalid authentication.");
		return new LoginResponse(user);
	}
}
