package com.wallas.project.chatonline.entities;

import java.util.UUID;

import com.wallas.project.chatonline.models.User;

public class UserResponse {
	private UUID id;
	private String name;
	private String email;
	private String url_picture;

	public UserResponse(User user) {
		this.id = user.getUser_id();
		this.name = user.getName();
		this.email = user.getEmail();
		this.url_picture = user.getUrl_picture();
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getUrl_picture() {
		return url_picture;
	}
}
