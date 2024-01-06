package com.java.uitbikes.dto;

public class MessageRequestBodyDto {
	private String text;
	private Long chatId;
	private String senderId;
	
	public MessageRequestBodyDto() {}
	
	public MessageRequestBodyDto(String text, Long chat, String sender) {
		this.text = text;
		this.chatId = chat;
		this.senderId = sender;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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
}
