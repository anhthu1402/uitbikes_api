package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.service.AccountService;
import com.java.uitbikes.service.CustomerService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/api/accounts")

public class AccountController {
	@Autowired 
	AccountService accountService;
	
	@Autowired 
	CustomerService customerService;
	
	 //create account (not done)
		@RequestMapping(value = "", method = RequestMethod.POST)
		public Account createAccount(@RequestBody Account a) {
			return accountService.createAccount(a);
		}
//		public void createAccount(String username, String password, String email, String avartar) {
//			Account a = new Account(username, password, email, avartar);
//			accountService.createAccount(a);
//		}
	
	// get all accounts (for in admin)
		@RequestMapping(value = "", method = RequestMethod.GET)
		public List<Account> getAllAccounts(){ 
			return accountService.getAllAccounts();
		}
	
	// get list accounts of admin
		@RequestMapping(value = "/admin", method = RequestMethod.GET)
		public List<Account> getListAccountAdmin(){
			return accountService.getListAccountAdmin();
		}
	
//	 admin/user 
		@RequestMapping(value = "/username/{username}/{password}", method = RequestMethod.GET)
		public Account getIsAccountAdmin(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password){
			return accountService.getIsAccountAdmin(username, password);
		}
	
//	// signin account by username 
//		@RequestMapping(value = "/{username}/{brand_id}", method = RequestMethod.PUT)
//		public Product setBrand(@PathVariable(value = "p_id") Long p_id, @PathVariable(value = "brand_id") Long brand_id) {
//			return productService.setBrand(p_id, brand_id);
//		}
	
		//update password 
		@RequestMapping(value = "/{username}/password/{password}", method = RequestMethod.PUT)
		public boolean updatePassword(@PathVariable(value = "username") String username, @RequestBody String password) {
			return accountService.updatePassword(username, password);
		}
		//update avatar 
		@RequestMapping(value = "/{username}/avatar/{avatar}", method = RequestMethod.PUT)
		public boolean updateAvatar(@PathVariable(value = "username") String username, @RequestBody String avatar) {
			return accountService.updateAvatar(username, avatar);
		}
		//set admin
		@RequestMapping(value = "/{username}/admin/{is_Admin}", method = RequestMethod.PUT)
		public boolean setIsAdminr(@PathVariable(value = "username") String username, @RequestBody boolean admin) {
			return accountService.setIsAdmin(username, admin);
		}
		
		//delete account (not done)
//		@RequestMapping(value = "/{username}", method = RequestMethod.DELETE)
//		public void deleteAccount(@PathVariable(value = "username") String username) {
//			accountService.deleteAccount(username);
//		}
	
}

