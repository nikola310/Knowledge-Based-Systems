package com.sbnz.doctor.dto;

public class WebsocketMessage {

	private String content;

	public WebsocketMessage() {
	}

	public WebsocketMessage(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
