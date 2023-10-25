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
import com.java.uitbikes.model.Account;
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
	public Account createAccount(@RequestBody Account a) {
		return accountService.createAccount(a);
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

	//	 admin/user 
	@RequestMapping(value = "/username/{username}/{pw}", method = RequestMethod.GET)
	public Account getIsAccountAdmin(@PathVariable(value = "username") String username, @PathVariable(value = "pw") String password){
		return accountService.getIsAccountAdmin(username, password);
	}
	
	// signin account by password,email
	@RequestMapping(value = "/{email}/pw/{pw}", method = RequestMethod.GET)
	public AccountDto signinAccount(@PathVariable(value = "email") String email, @PathVariable(value = "pw") String password) {
		return accountService.signinAccount(email, password);
	}
//	//sign in for admin
//		@RequestMapping(value = "/username/{username}/password/{password}/isAdmin", method = RequestMethod.GET)
//		public Account getIsAccountAdmin(@PathVariable(value = "username") String username, @RequestBody String password) {
//			return accountService.getIsAccountAdmin(username, password);
//		}	
//		
		
	//update password 
	@RequestMapping(value = "pw/{username}/{pw}", method = RequestMethod.PUT)
	public boolean updatePassword(@PathVariable(value = "username") String username, @PathVariable(value = "pw")  String pw) {
		return accountService.updatePassword(username, pw);
	}
	//update avatar 
	@RequestMapping(value = "avatar/{username}/{avatar}", method = RequestMethod.PUT)
	public boolean updateAvatar(@PathVariable(value = "username") String username, @PathVariable(value = "avatar")  String avatar) {
		return accountService.updateAvatar(username, avatar);
	}
	//set admin
	@RequestMapping(value = "isAdmin/{username}/{isAdmin}", method = RequestMethod.PUT)
	public boolean setIsAdminr(@PathVariable(value = "username") String username, @PathVariable(value = "isAdmin") boolean isAdmin) {
		return accountService.setIsAdmin(username, isAdmin);
	}
}

