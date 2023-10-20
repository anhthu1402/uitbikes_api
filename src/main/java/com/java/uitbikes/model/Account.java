package com.java.uitbikes.model;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
	private Boolean isAdmin;
	
	@Column(name= "avatar")
	private String avatar;
	
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
}
