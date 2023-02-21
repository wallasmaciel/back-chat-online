package com.wallas.project.chatonline.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
public class User {
	@Id
	private UUID user_id;
	private String name;
	private String email;
	private String password;
	private String url_picture;
	
	public User() {}
	
	public User(String name, String email, String password, String url_picture) {
        this.user_id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.url_picture = url_picture;
    }
	
	public User(UUID user_id, String name, String email, String password, String url_picture) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.url_picture = url_picture;
    }
	
	public UUID getUser_id() {
		return user_id;
	}
	public void setUser_id(UUID user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl_picture() {
		return url_picture;
	}
	public void setUrl_picture(String url_picture) {
		this.url_picture = url_picture;
	}
}
