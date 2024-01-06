package com.java.uitbikes.model;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "timestamp")
	private Date timestamp;
	
	@ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "username")
	private Account sender;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id", referencedColumnName = "id")
	private Conversation conversation;
	
	public Message() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Account getSender() {
		return sender;
	}

	public void setSender(Account sender) {
		this.sender = sender;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
}
