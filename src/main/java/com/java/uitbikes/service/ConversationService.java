package com.java.uitbikes.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.ConversationDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Conversation;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.repository.ConversationRepository;
import com.java.uitbikes.repository.MessageRepository;

@Service
public class ConversationService {
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ConversationRepository conversationRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	//create
	public ConversationDto createConversation(String username) {
		Optional<Account> account = accountRepository.findById(username);
		if (account.isPresent()) {
			Conversation conversation = new Conversation();
			conversation.setAccount(account.get());
			return new ConversationDto(conversationRepository.save(conversation));
		}
		
		return null;
	}
	
	//read all
	public List<Object> getAllConversation() {
		List<Conversation> conversations = conversationRepository.findAll();
		List<Object> result = new ArrayList<>();
		
		for (Conversation c: conversations) {
			Map<String, Object> item = new HashMap<>();
			item.put("id", c.getId());
			item.put("username", c.getAccount().getUsername());
			item.put("avatar", c.getAccount().getAvatar());
			
			result.add(item);
		}
		
		return result;
	}
	
	//read by username
	public ConversationDto getConversationByUsername(String username) {
		Optional<Conversation> conversation = conversationRepository.findByUsername(username);
		if (conversation.isPresent()) {
			ConversationDto result = new ConversationDto(conversation.get());
			return result;
		} else {
			return createConversation(username);
		}
	}
}
