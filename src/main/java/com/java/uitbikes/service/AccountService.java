package com.java.uitbikes.service;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.AccountDto;
import com.java.uitbikes.dto.AvatarDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.model.LoginForm;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.repository.CustomerRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	CustomerService customerService;
	
	// create account
	public AccountDto createAccount(Account a) {
		Customer customer = customerService.createCustomer();
		Account account = accountRepository.save(a);
		account.setCustomer(customer);
		accountRepository.save(account);
		return new AccountDto(account);
	}
	
	// check if username is already used
	public Boolean checkUsername(String username) {
		List<Account> list = accountRepository.findAll();
		for (Account account : list) {
			if(account.getUsername().equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	// check if email is already used
	public Boolean checkEmail(String email) {
		List<Account> list = accountRepository.findAll();
		for (Account account : list) {
			if(account.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
		
	// get all accounts (for read in admin)
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		List<AccountDto> result = new ArrayList<AccountDto>();
		for (Account account : accounts) {
			result.add(new AccountDto(account));				
		}
		return result;
	}
	
	// get accounts by username or email
	public AccountDto getAccountsByNameOrEmail(String identity){
		Optional<Account> account = accountRepository.findByUsername(identity);
		if (account.isPresent()) 
			return new AccountDto(account.get());
		else {
			account = accountRepository.findByEmail(identity);
			if (account.isPresent()) 
				return new AccountDto(account.get());
		}
		return null;
	}
	
	// get accounts by email
	public AccountDto getAccountsByEmail(String email){
		Optional<Account> account = accountRepository.findByEmail(email);
		if (account.isPresent()) 
			return new AccountDto(account.get());
		return null;
	}
	
	//get list account of admin
	public List<AccountDto> getListAccountAdmin(){
		List<AccountDto> accounts = getAllAccounts();
		List<AccountDto> result = new ArrayList<AccountDto>();
		for (AccountDto account : accounts) {
			if(account.getIsAdmin()==true) {
				result.add(account);
			}
		}
		return result;
	}
	
	//sign in by password and email
	public AccountDto signinAccount(LoginForm loginForm) {
		Optional<Account> account = accountRepository.findByEmail(loginForm.getEmail());
		if (account.isPresent()) {
			if (account.get().getPw().equals(loginForm.getPw()))
				return new AccountDto(account.get());
		}
		return null;
	}
	
	//sign in for admin
	public AccountDto signinAdmin(LoginForm loginForm) {
		Optional<Account> account = accountRepository.findByEmail(loginForm.getEmail());
		if(account.isPresent()) {
			Account a = account.get();
			if(a.getIsAdmin() == true) {
				if (a.getPw().equals(loginForm.getPw())) {
					return new AccountDto(a);
				}
			}
		}
		return null;
	}
	
	//update password by email
	public boolean updatePassword(LoginForm updateForm) {
		Optional<Account> account = accountRepository.findByEmail(updateForm.getEmail());
		if(account.isPresent()) {
			Account a = account.get();
			a.setPw(updateForm.getPw());
			accountRepository.save(a);
			return true;
		}
		return false;
	}
	
	//update avatar
	public boolean updateAvatar(String username, AvatarDto avatar) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if(account.isPresent()) {
			Account a = account.get();
			a.setAvatar(avatar.getAvatar());
			accountRepository.save(a);
			return true;
		}
		return false;
	}
	
	//set admin
	public boolean setIsAdmin(String username, boolean isAdmin) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if(account.isPresent()) {
//			Account a = account.get();
			account.get().setIsAdmin(isAdmin);
			accountRepository.save(account.get());
			return true;
		}
		return false;
	}
	
	//check password
	public boolean checkPassword(LoginForm loginForm) {
		Optional<Account> account = accountRepository.findByEmail(loginForm.getEmail());
		if(account.isPresent()) {
			Account a = account.get();
			if (a.getPw().equals(loginForm.getPw())) {
				return true;
			}
		}
		return false;
	}	
}
