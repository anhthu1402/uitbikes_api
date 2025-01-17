package com.java.uitbikes.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "account")

public class Account {
	@Id
	@Column(name = "username")
	private String username;
	
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customer;
    
	@Column(name= "password")
	private String pw;
	
	@Column(name= "email")
	private String email;
	
	@Column(name= "is_admin")
	private Boolean isAdmin = false;
	
	@Column(name= "avatar")
	private String avatar = "https://res.cloudinary.com/dpwehcnso/image/upload/v1695480885/uitbikes/uitbiker1_pwb3z9.png?fbclid=IwAR0wlDrmAZq-7ltfJ62phszK9Y-J98ly2em7OvIa5_pH2dSKdJDskZymumE";
	
	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	private List<Message> messages = new ArrayList<Message>();
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "account")
	private Conversation conversation;
	
	public Account() {}
	
	public Account(String username, String password, String email, String avatar) {
		this.username = username;
		this.customer.setId(null);
		this.pw=password;
		this.email=email;
		this.avatar=avatar;
		this.isAdmin=false;
	}
	
	public String getPw() {
		return pw;
	}
	public String getEmail() {
		return email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public String getAvatar() {
		return avatar;
	}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}
	
}
