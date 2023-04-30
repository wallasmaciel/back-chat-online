package com.wallas.project.chatonline.models;

import java.util.Date;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {
	private UUID message_id;
	private UUID to;
	private UUID from;
	private String content;
	private String type;
	private Date date;
	
	public Message() {}
	
	public Message(String jsonBody) throws JSONException {
		JSONObject jsonObject  = new JSONObject(jsonBody);
		this.message_id = UUID.fromString(jsonObject.getString("message_Id"));
		this.to = UUID.fromString(jsonObject.getString("to"));
		this.from = UUID.fromString(jsonObject.getString("from"));
		this.content = jsonObject.getString("content");
		if (jsonObject.has("type"))
			this.type = jsonObject.getString("type");
		this.date = new Date(jsonObject.getInt("date"));
	}
	
	public Message(UUID to, UUID from, String content) {
		this.message_id = UUID.randomUUID();
		this.to = to;
		this.from = from;
		this.content = content;
		this.type = "text";
		this.date = new Date();
	}

	public Message(UUID to, UUID from, String content, String type) {
		this.message_id = UUID.randomUUID();
		this.to = to;
		this.from = from;
		this.content = content;
		this.type = type;
		this.date = new Date();
	}
	
	public Message(UUID id, UUID to, UUID from, String content) {
		this.message_id = id;
		this.to = to;
		this.from = from;
		this.content = content;
		this.type = "text";
		this.date = new Date();
	}

	public Message(UUID id, UUID to, UUID from, String content, String type) {
		this.message_id = id;
		this.to = to;
		this.from = from;
		this.content = content;
		this.type = type;
		this.date = new Date();
	}

	public Message(UUID id, UUID to, UUID from, String content, String type, Date date) {
		this.message_id = id;
		this.to = to;
		this.from = from;
		this.content = content;
		this.type = type;
		this.date = date;
	}

	public UUID getMessage_Id() {
		return message_id;
	}
	public void setMessage_Id(UUID message_id) {
		this.message_id = message_id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
