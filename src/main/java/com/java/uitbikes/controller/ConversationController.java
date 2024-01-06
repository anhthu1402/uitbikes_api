package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.uitbikes.dto.ConversationDto;
import com.java.uitbikes.service.ConversationService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/conversations")
public class ConversationController {
	@Autowired
	ConversationService conversationService;
	
	//create
	@RequestMapping(value = "/username/{username}", method = RequestMethod.POST)
	public ConversationDto createConversation(@PathVariable(value = "username") String username) {
		return conversationService.createConversation(username);
	}
	
	//read all
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Object> getAllConversation() {
		return conversationService.getAllConversation();
	}
	
	//read by username
	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public ConversationDto getConversationByUsername(@PathVariable(value = "username") String username) {
		return conversationService.getConversationByUsername(username);
	}
}
