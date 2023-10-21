package com.java.uitbikes.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.*;
import com.java.uitbikes.model.*;
import com.java.uitbikes.repository.*;

@Service
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	//create invoice
	public Invoice createInvoice(Invoice invoice) {
		Date purchasedDate= Date.from(Instant.now());
		invoice.setDate(purchasedDate);
//		invoice.setCustomer(customerRepository.findById(invoice.getCustomer().getId()).get());
		
		return invoiceRepository.save(invoice);
	}
	
	//add product
	public Invoice addProduct(Long id, Long pId, int quantity) {
		Invoice invoice = invoiceRepository.findById(id).get();
		Product product = productRepository.findById(pId).get();
		invoice.addProduct(product, quantity);
		
		return invoiceRepository.save(invoice);
	}
	
	//get all invoices
	public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
	}
	
	//get all invoices by customer_id and status
	public List<InvoiceDto> getAllInvoicesByCustomerIdAndStatus(Long customer_id, int status) {
		Customer customer = customerRepository.findById(customer_id).get();
		List<Invoice> invoices = customer.getInvoices();
		List<InvoiceDto> customerInvoices = new ArrayList<InvoiceDto>();
        for (Invoice i: invoices) {
        	InvoiceDto invoice = new InvoiceDto(i, customer_id);
        	customerInvoices.add(invoice);
        }
        
        List<InvoiceDto> result = new ArrayList<InvoiceDto>();
        if (status == -1)
        	result = customerInvoices;
        else {
			for (InvoiceDto i: customerInvoices)
				if (i.getStatus() == status)
					result.add(i);
		}
        
        return result;
	}
	
	//get number of completed orders
	public int getNumberOfCompletedOrders() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
        return invoices.size();
	}
	
	//get number of cancelled orders
	public int getNumberOfCancelledOrders() {
		List<Invoice> invoices = invoiceRepository.findByStatus(3);
        return invoices.size();
	}
	
	//get total number of orders
	public int getNumberOfOrders() {
		List<Invoice> invoices = invoiceRepository.findAll();
        return invoices.size();
	}
	
	//get total revenue
	public Long getRevenue() {
		List<Invoice> invoices = invoiceRepository.findByStatus(2);
        Long result = 0L;
        for (Invoice i: invoices) {
        	result += i.getTotal();
        }
        return result;
	}
	
	//get revenue within the last 12 months
	
	
	//update invoice status
	public Invoice updateInvoice(Long id, int status) {
		Optional<Invoice> request = invoiceRepository.findById(id);
		if(request.isPresent()) {
			Invoice req = request.get();
			req.setStatus(status);
			return invoiceRepository.save(req);
		}
		return request.get();
	}
}
