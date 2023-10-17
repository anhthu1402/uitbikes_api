package com.java.uitbikes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.repository.ChargeRequestRepository;
import com.java.uitbikes.model.ChargeRequest;

@Service
public class ChargeRequestService {
	@Autowired
	ChargeRequestRepository chargeRequestRepository;
	
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
		List<ChargeRequest> requests = chargeRequestRepository.findByCustomerId(customer_id);
		return requests;
	}
	
	//update request's status
	public ChargeRequest updateRequestStatus(Long id, int status) {
		Optional<ChargeRequest> request = chargeRequestRepository.findById(id);
		if(request.isPresent()) {
			ChargeRequest req = request.get();
			req.setStatus(status);
			return chargeRequestRepository.save(req);
		}
		return request.get();
	}
}
