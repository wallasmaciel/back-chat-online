package com.wallas.project.chatonline.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.wallas.project.chatonline.models.Message;
import com.wallas.project.chatonline.models.Talk;

public class TalkResponse {
	private UUID talk_id;
	private List<Message> messages;
	
	public TalkResponse() {
		this.messages = new ArrayList<>();
	}

	public TalkResponse(Talk talk) {
		this();
		this.talk_id = talk.getTalk_id();
		if (talk.getMessages() != null)
			this.messages.addAll(talk.getMessages());
	}

	public UUID getTalk_id() {
		return talk_id;
	}

	public void setTalk_id(UUID talk_id) {
		this.talk_id = talk_id;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
