package com.java.uitbikes.dto;

import com.java.uitbikes.model.Customer;
import com.java.uitbikes.model.Account;

public class AccountDto {
	public String username;
	public Customer customer;
	public String email;
	public Boolean isAdmin;
	public String avatar;
	
	public AccountDto() {
		// TODO Auto-generated constructor stub
	}
	
	public AccountDto(Account a) {
		this.username = a.getUsername();
		this.customer = a.getCustomer();
		this.email = a.getEmail();
		this.isAdmin = a.getIsAdmin();
		this.avatar = a.getAvatar();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
