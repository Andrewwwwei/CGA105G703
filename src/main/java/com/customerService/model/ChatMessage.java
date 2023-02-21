package com.customerService.model;

public class ChatMessage {
	private String type;
	private String sender;
	private String receiver;
	private String message;
	private String time;
	private String status;

	public ChatMessage(String type, String sender, String receiver, String message, String time, String status) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
		this.status = status;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
