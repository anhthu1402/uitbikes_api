package com.java.uitbikes.service;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		public List<Account> getAllAccounts() {
			return accountRepository.findAll();
		}
	
	// get accounts by username
		public Account getListAccountsByName(String username){
			Account account = accountRepository.findByUsername(username).get();
			return account;
		}
		//get list account of admin
		public List<Account> getListAccountAdmin(){
			List<Account> accounts = getAllAccounts();
			List<Account> list = new ArrayList<Account>();
			for (Account account : accounts) {
				if(account.getIsAdmin()==true) {
					list.add(account);
				}
			}
			return list;
		}
		
		//sign in by password and username
		public Boolean signinAccount(String username, String password) {
			List<Account> accounts = getAllAccounts();
			for (Account account : accounts) {
				if(account.getName().equals(username)) {
					if(account.getPw().equals(password)) {
						return true;
					}
				}
			}
			return false;
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
		
		//delete account
//		public void deleteAccount(String username) {
//			Optional<Account> account = accountRepository.findById(username);
//			Account a = account.get(); 
//			accountRepository.deleteById(username);
//			customerRepository.deleteById(a.getCustomer().getId());
//		}
	
	
}
