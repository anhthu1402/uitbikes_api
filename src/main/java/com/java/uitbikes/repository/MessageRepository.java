package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
	@Query(value = "SELECT * FROM message WHERE conversation_id = :id", nativeQuery = true)
	List<Message> findByConversationId(Long id);
}
