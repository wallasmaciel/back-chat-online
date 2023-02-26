package com.wallas.project.chatonline.entities;

import java.util.UUID;

public class SendRequest {
	private UUID to;
    private UUID from;
    private String content;

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
}
