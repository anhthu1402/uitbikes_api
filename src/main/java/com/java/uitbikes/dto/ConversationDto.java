package com.java.uitbikes.dto;

import java.util.ArrayList;
import java.util.List;

import com.java.uitbikes.model.Conversation;
import com.java.uitbikes.model.Message;

public class ConversationDto {
	private Long id;
	private String username;
	private List<MessageDto> messages = new ArrayList<MessageDto>();
	
	public ConversationDto() {}
	
	public ConversationDto(Conversation c) {
		this.id = c.getId();
		this.username = c.getAccount().getUsername();
		for (Message m: c.getMessages()) {
			MessageDto item = new MessageDto(m);
			this.messages.add(item);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<MessageDto> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDto> messages) {
		this.messages = messages;
	}
}
