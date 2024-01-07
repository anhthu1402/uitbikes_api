package com.java.uitbikes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.Account;

import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	Optional<Account> findByUsername(String username);
	Optional<Account> findByEmail(String email);
	
	@Query(value = "SELECT * FROM account WHERE customer_id = :id", nativeQuery = true)
	Account findByCustomerId(Long id);
}
