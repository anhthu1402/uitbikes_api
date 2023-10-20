package com.java.uitbikes.service;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.AccountDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Customer;
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
		public Account createAccount(Account a) {
			Customer customer = customerService.createCustomer();
			Account account = accountRepository.save(a);
			account.setCustomer(customer);
			return  accountRepository.save(account);
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
	
	// get accounts by username
		public AccountDto getListAccountsByName(String username){
			Account account = accountRepository.findByUsername(username).get();
			return new AccountDto(account);
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
		
		//sign in by password and username
		public AccountDto signinAccount(String username, String password) {
			List<Account> accounts = accountRepository.findAll();
			for (Account account : accounts) {
				if(account.getUsername().equals(username)) {
					if(account.getPw().equals(password)) {
						return new AccountDto(account);
					}
				}
			}
			return null;
		}
		//sign in for admin
		public Account getIsAccountAdmin(String username, String password) {
			Optional<Account> account = accountRepository.findByUsername(username);
			if(account.isPresent()) {
				Account a = account.get();
				if(a.getIsAdmin() == true) {
					if (a.getPw().equals(password)) {
						return a;
					}
				}
			}
			return null;
		}
		
		//update password	
		public boolean updatePassword(String username, String password) {
			Optional<Account> account = accountRepository.findByUsername(username);
			if(account.isPresent()) {
				Account a = account.get();
				a.setPw(password);
				accountRepository.save(a);
				return true;
			}
			return false;
		}
		//update avartar
		public boolean updateAvatar(String username, String avatar) {
			Optional<Account> account = accountRepository.findByUsername(username);
			if(account.isPresent()) {
				Account a = account.get();
				a.setAvatar(avatar);
				accountRepository.save(a);
				return true;
			}
			return false;
		}
		//set admin
		public boolean setIsAdmin(String username, boolean admin) {
			Optional<Account> account = accountRepository.findByUsername(username);
			if(account.isPresent()) {
				Account a = account.get();
				a.setIsAdmin(admin);;
				accountRepository.save(a);
				return true;
			}
			return false;
		}
		
}
