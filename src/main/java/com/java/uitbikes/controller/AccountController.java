package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.dto.AccountDto;
import com.java.uitbikes.dto.AvatarDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.LoginForm;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.service.AccountService;
import com.java.uitbikes.service.CustomerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/accounts")

public class AccountController {
	@Autowired 
	AccountService accountService;
	
	@Autowired 
	CustomerService customerService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@RequestMapping(value = "/countaccount", method = RequestMethod.GET)
	public Long countAccount() {
		return accountRepository.count();
	}
	
	 //create account 
	@RequestMapping(value = "", method = RequestMethod.POST)
	public AccountDto createAccount(@RequestBody Account a) {
		return accountService.createAccount(a);
	}
	
	// check if username is already used
	@RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
	public Boolean checkUsername(@PathVariable(value = "username") String username) {
		return accountService.checkUsername(username);
	}
	
	// check if email is already used
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public Boolean checkEmail(@PathVariable(value = "email") String email) {
		return accountService.checkEmail(email);
	}
	
	// get all accounts (for in admin)
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<AccountDto> getAllAccounts(){ 
		return accountService.getAllAccounts();
	}
	// get account by username or email
	@RequestMapping(value = "/{identity}", method = RequestMethod.GET)
	public AccountDto getAccountByNameOrEmail(@PathVariable(value = "identity") String identity) {
		return accountService.getAccountsByNameOrEmail(identity);
	}
	
	// get list accounts of admin
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public List<AccountDto> getListAccountAdmin(){
		return accountService.getListAccountAdmin();
	}
	
	// sign in account by password,email
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public AccountDto signinAccount(@RequestBody LoginForm loginForm) {
		return accountService.signinAccount(loginForm);
	}
	//sign in for admin
	@RequestMapping(value = "/signin/admin", method = RequestMethod.POST)
	public AccountDto signinAdmin(@RequestBody LoginForm loginForm) {
		return accountService.signinAdmin(loginForm);
	}	
		
	//update password 
	@RequestMapping(value = "/password", method = RequestMethod.PUT)
	public boolean updatePassword(@RequestBody LoginForm updateForm) {
		return accountService.updatePassword(updateForm);
	}
	//update avatar 
	@RequestMapping(value = "/{username}/avatar", method = RequestMethod.PUT)
	public boolean updateAvatar(@PathVariable(value = "username") String username, @RequestBody AvatarDto avatar) {
		return accountService.updateAvatar(username, avatar);
	}
	//set admin
	@RequestMapping(value = "{username}/isAdmin/{isAdmin}", method = RequestMethod.PUT)
	public AccountDto setIsAdmin(@PathVariable(value = "username") String username, @PathVariable(value = "isAdmin") boolean isAdmin) {
		return accountService.setIsAdmin(username, isAdmin);
	}
	
	//check password
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public boolean checkPassword(@RequestBody LoginForm loginForm) {
		return accountService.checkPassword(loginForm);
	}
	
	//Update account
	@RequestMapping(value = "/{username}", method = RequestMethod.PUT)
	public Account updateAccount(@PathVariable(value = "username") String username, @RequestBody Account a) {
		return accountService.updateAccount(username, a);
	}
	
}

