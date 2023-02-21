package com.wallas.project.chatonline.models;

import java.util.Date;
import java.util.UUID;

public class Message {
	private UUID to;
	private UUID from;
	private String content;
	private Date date;
	
	public Message() {}
	
	public Message(UUID to, UUID from, String content) {
		this.to = to;
		this.from = from;
		this.content = content;
		this.date = new Date();
	}

	public Message(UUID to, UUID from, String content, Date date) {
		this.to = to;
		this.from = from;
		this.content = content;
		this.date = date;
	}

	public UUID getTo() {
		return to;
	}
	public void setTo(UUID to) {
		this.to = to;
	}
	public UUID getFrom() {
		return from;
	}
	public void setFrom(UUID from) {
		this.from = from;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
