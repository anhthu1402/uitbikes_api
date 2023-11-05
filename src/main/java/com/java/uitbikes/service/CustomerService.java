package com.java.uitbikes.service;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.AccountDto;
import com.java.uitbikes.model.Account;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.repository.AccountRepository;
import com.java.uitbikes.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	//create customer
	public Customer createCustomer() {
		Date registerDate= Date.from(Instant.now());			
		Customer customer = customerRepository.save(new Customer());
		customer.setRegisterDate(registerDate);
		return customerRepository.save(customer);
	}
	
	//get all customers
	public List<Customer> getAllCustomers(){
		return customerRepository.findAll();
	}
		
	//get customer by id
	public Customer getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			return customer.get();
		}
		return new Customer();
	}
	
	//update customer
	public AccountDto updateCustomer(String username, Customer customerDetail) {
		Optional<Account> account = accountRepository.findByUsername(username);
		if(account.isPresent()) {
			Account a = account.get();
			Customer customer = a.getCustomer();
			customer.setName(customerDetail.getName());
			customer.setAddress(customerDetail.getAddress());
			customer.setDate(customerDetail.getDate());
			customer.setGender(customerDetail.getGender());
			customer.setIdNumber(customerDetail.getIdNumber());
			customer.setPhone(customerDetail.getPhone());
			customerRepository.save(customer);
//			a.setCustomer(customer);
//			accountRepository.save(a);
			return new AccountDto(a);
		}
		return new AccountDto(account.get());
	}
		
	//update balance
	public Customer updateBalance(Long id, Long balance) {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isPresent()) {
			customer.get().setBalance(balance);
			return customerRepository.save(customer.get());
		}
		return null;
	}
}
