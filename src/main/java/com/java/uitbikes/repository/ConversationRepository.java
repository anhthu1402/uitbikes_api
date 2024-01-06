package com.java.uitbikes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.Conversation;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	@Query(value = "SELECT * FROM conversation WHERE username = :username", nativeQuery = true)
	Optional<Conversation> findByUsername(String username);
}
