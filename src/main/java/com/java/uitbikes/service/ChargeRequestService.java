package com.java.uitbikes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.repository.ChargeRequestRepository;
import com.java.uitbikes.repository.CustomerRepository;
import com.java.uitbikes.model.ChargeRequest;
import com.java.uitbikes.model.Customer;

@Service
public class ChargeRequestService {
	@Autowired
	ChargeRequestRepository chargeRequestRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	//create request
	public ChargeRequest createRequest(ChargeRequest request) {
		return chargeRequestRepository.save(request);
	}
	
	//get all requests
	public List<ChargeRequest> getAllRequests(){
		return chargeRequestRepository.findAll();
	}
	
	//get all requests by cutomer_id
	public List<ChargeRequest> getAllRequestsByCustomerId(Long customer_id) {
		Customer customer = customerRepository.findById(customer_id).get();
		return customer.getChargeRequests();
	}
	
	//update request's status
	public ChargeRequest updateRequestStatus(Long id, int status) {
		Optional<ChargeRequest> request = chargeRequestRepository.findById(id);
		if(request.isPresent()) {
			ChargeRequest req = request.get();
			if(status==1) {
				Customer customer = req.getCustomer();
				customer.setBalance(customer.getBalance() + req.getMoney());
				customerRepository.save(customer);
			}
			req.setStatus(status);
			return chargeRequestRepository.save(req);
		}
		return request.get();
	}
}
