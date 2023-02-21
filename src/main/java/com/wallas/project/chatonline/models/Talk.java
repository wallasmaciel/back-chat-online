package com.wallas.project.chatonline.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("talks")
public class Talk {
	@Id
	private UUID talk_id;
	private Set<UUID> participants;
	private List<Message> messages;
	
	public Talk() {}
	
	public Talk(Set<UUID> participants, List<Message> messages) {
		this.talk_id = UUID.randomUUID();
		this.participants = new HashSet<>(participants);
		this.messages = new ArrayList<>(messages);
	}
	
	public Talk(UUID talk_id, Set<UUID> participants, List<Message> messages) {
		this.talk_id = talk_id;
		this.participants = new HashSet<>(participants);
		this.messages = new ArrayList<>(messages);
	}

	public UUID getTalk_id() {
		return talk_id;
	}
	public void setTalk_id(UUID talk_id) {
		this.talk_id = talk_id;
	}
	public Set<UUID> getParticipants() {
		return participants;
	}
	public void setParticipants(Set<UUID> participants) {
		this.participants = participants;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
}
