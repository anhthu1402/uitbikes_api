package com.java.uitbikes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.service.MailService;

@RestController
@CrossOrigin
@RequestMapping("api/mail")
public class SendMailController {
	@Autowired
	MailService mailService;
	
	@RequestMapping(value = "/send-code/email/{email}", method = RequestMethod.GET)
	public String sendCode(@PathVariable(value = "email") String email) {
		return mailService.sendCode(email);
	}
	
	@RequestMapping(value = "/send-new-password/email/{email}", method = RequestMethod.GET)
	public String sendNewPassword(@PathVariable(value = "email") String email) {
		return mailService.sendNewPassword(email);
	}
}
