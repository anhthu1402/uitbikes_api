package com.java.uitbikes.dto;

import java.util.ArrayList;
import java.util.List;

import com.java.uitbikes.model.Customer;

public class InvoicePostDto {
	private Customer customer;
	private Long total;
	private List<InvoiceItemDto> details = new ArrayList<>();
	
	public InvoicePostDto() {}
	
	public InvoicePostDto(Customer cus, Long total, List<InvoiceItemDto> products) {
		this.customer = cus;
		this.total = total;
		this.details = products;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<InvoiceItemDto> getDetails() {
		return details;
	}

	public void setDetails(List<InvoiceItemDto> details) {
		this.details = details;
	}

	public Customer getCustomer() {
		return customer;
	}
}
