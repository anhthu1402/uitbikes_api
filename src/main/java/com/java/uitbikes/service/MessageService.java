package com.java.uitbikes.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.MessageDto;
import com.java.uitbikes.dto.MessageRequestBodyDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Conversation;
import com.java.uitbikes.model.Message;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.repository.ConversationRepository;
import com.java.uitbikes.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	ConversationRepository conversationRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	//create
	public Boolean createMessage(MessageRequestBodyDto body) {
		Optional<Conversation> conversation = conversationRepository.findById(body.getChatId());
		Optional<Account> sender = accountRepository.findById(body.getSenderId());
		
		if (conversation.isPresent() && sender.isPresent()) {
			Message message = new Message();
			
			Date sentTime = Date.from(Instant.now());
			message.setTimestamp(sentTime);
			message.setText(body.getText());
			message.setConversation(conversationRepository.findById(body.getChatId()).get());
			message.setSender(accountRepository.findById(body.getSenderId()).get());
			
			messageRepository.save(message);
			
			return true;
		}

		return false;
	}
	
	//get messages by conversation id
	public List<MessageDto> getAllMessagesByConversationId(Long id) {
		List<Message> messages = messageRepository.findByConversationId(id);
		List<MessageDto> result = new ArrayList<MessageDto>();
		
		for (Message m: messages) {
			MessageDto item = new MessageDto(m);
			result.add(item);
		}
		
		return result;
	}
}
