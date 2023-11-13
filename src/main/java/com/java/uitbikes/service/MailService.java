package com.java.uitbikes.service;

public interface MailService {
	String sendCode(String email);
	String sendNewPassword(String email);
}
