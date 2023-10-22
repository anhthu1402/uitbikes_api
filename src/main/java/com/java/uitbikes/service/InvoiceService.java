package com.java.uitbikes.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
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
	public RevenueMonths getRevenueByMonth() {
		List<Invoice> allInvoices = invoiceRepository.findByStatus(2);
		
		List<String> allDates = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		SimpleDateFormat month_date = new SimpleDateFormat("MMM-yyyy");
		
		List<Long> allRevenues = new ArrayList<Long>();
		
		for (int i = 12; i > 0; i--) {
		    cal.add(Calendar.MONTH, -1);
		    String month_name = month_date.format(cal.getTime());
		    allDates.add(month_name);
		    
		    Long revenue = 0L;
		    for (Invoice in: allInvoices) {
		    	if (month_date.format(new Date(in.getDate().getTime())).equals(month_name)) {
		    		revenue += in.getTotal();
		    	}
		    }
		    allRevenues.add(revenue);
		}
		
		return new RevenueMonths(allDates, allRevenues);
	}
	
	//update invoice status
	public Invoice updateInvoice(Long id, int status) {
		Optional<Invoice> request = invoiceRepository.findById(id);
		if(request.isPresent()) {
			Invoice req = request.get();
			req.setStatus(status);
			if (status == 3) {
				Customer cus = customerRepository.findById(req.getCustomer().getId()).get();
				cus.setBalance(cus.getBalance()+req.getTotal());
				customerRepository.save(cus);
			}
			return invoiceRepository.save(req);
		}
		return request.get();
	}
}
