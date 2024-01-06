package com.java.uitbikes.dto;

import com.java.uitbikes.model.Message;

public class MessageDto {
	private Long id;
	private Long chatId;
	private String senderId;
	private String text;
	
	public MessageDto() {}
	
	public MessageDto(Message m) {
		this.id = m.getId();
		this.chatId = m.getConversation().getId();
		this.senderId = m.getSender().getUsername();
		this.text = m.getText();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getChatId() {
		return chatId;
	}

	public void setChatId(Long chatId) {
		this.chatId = chatId;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
