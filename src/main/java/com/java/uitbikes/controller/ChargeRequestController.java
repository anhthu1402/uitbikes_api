package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.uitbikes.model.ChargeRequest;
import com.java.uitbikes.service.ChargeRequestService;

@RestController
@RequestMapping(value = "/api/requests")
public class ChargeRequestController {
	@Autowired
	ChargeRequestService chargeRequestService;
	
	//create request
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ChargeRequest createRequest(@RequestBody ChargeRequest requestDetail) {
		return chargeRequestService.createRequest(requestDetail);
	}
	
	//get all requests
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ChargeRequest> getAllRequests(){
		return chargeRequestService.getAllRequests();
	}
	
	//get all requests by cutomer_id
	@RequestMapping(value = "/customer/{customer_id}", method = RequestMethod.GET)
	public List<ChargeRequest> getAllRequestsByCustomerId(@PathVariable(value = "customer_id") Long customer_id) {
		return chargeRequestService.getAllRequestsByCustomerId(customer_id);
	}
	
	//update request's status
	@RequestMapping(value = "/{request_id}/status/{status}", method = RequestMethod.PUT)
	public ChargeRequest updateBrand(@PathVariable(value = "request_id") Long request_id, @PathVariable(value = "status") int status) {
		return chargeRequestService.updateRequestStatus(request_id, status);
	}
}
