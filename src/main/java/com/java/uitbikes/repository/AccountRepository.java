package com.java.uitbikes.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{
	Optional<Account> findByUsername(String username);
}
