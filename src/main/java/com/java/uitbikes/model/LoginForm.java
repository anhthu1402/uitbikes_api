package com.java.uitbikes.model;

public class LoginForm {
	private String email;
	private String pw;
	
	public LoginForm() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginForm(String email, String pw) {
		this.email = email;
		this.pw = pw;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
}
