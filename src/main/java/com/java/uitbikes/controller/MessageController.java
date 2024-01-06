package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.uitbikes.dto.MessageDto;
import com.java.uitbikes.dto.MessageRequestBodyDto;
import com.java.uitbikes.service.MessageService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/messages")
public class MessageController {
	@Autowired
	MessageService messageService;
	
	//create
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createMessage(@RequestBody MessageRequestBodyDto body) {
		return messageService.createMessage(body);
	}
	
	//get messages by conversation id
	@RequestMapping(value = "/conversation/{id}", method = RequestMethod.GET)
	public List<MessageDto> getAllMessagesByConversationId(@PathVariable(value = "id") Long id) {
		return messageService.getAllMessagesByConversationId(id);
	}

}
