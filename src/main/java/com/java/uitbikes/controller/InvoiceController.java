package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.uitbikes.dto.InvoiceDto;
import com.java.uitbikes.model.Invoice;
import com.java.uitbikes.service.InvoiceService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/invoices")
public class InvoiceController {
	@Autowired
	InvoiceService invoiceService;
	
	//create invoice
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Invoice createInvoice(@RequestBody Invoice invoice) {
		return invoiceService.createInvoice(invoice);
	}
	
	//add Product
	@RequestMapping(value = "/{invoice_id}/product/{p_id}/quantity/{quantity}", method = RequestMethod.PUT)
	public Invoice addProduct(@PathVariable(value = "invoice_id") Long invoice_id, @PathVariable(value = "p_id") Long p_id, @PathVariable(value = "quantity") int quantity) {
		return invoiceService.addProduct(invoice_id, p_id, quantity);
	}
	
	//get all invoices
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Invoice> getAllInvoices() {
        return invoiceService.getAllInvoices();
	}
	
	//get all invoices by customer_id and status
	@RequestMapping(value = "/customer/{customer_id}/status/{status}", method = RequestMethod.GET)
	public List<InvoiceDto> getAllInvoicesByCustomerIdAndStatus(@PathVariable(value = "customer_id") Long customer_id, @PathVariable(value = "status") int status) {
		return invoiceService.getAllInvoicesByCustomerIdAndStatus(customer_id, status);
	}
	
	//get number of completed orders
	@RequestMapping(value = "/numberofcompletedorders", method = RequestMethod.GET)
	public int getNumberOfCompletedOrders() {
		return invoiceService.getNumberOfCompletedOrders();
	}
	
	//get number of cancelled orders
	@RequestMapping(value = "/numberofcancelledorders", method = RequestMethod.GET)
	public int getNumberOfCancelledOrders() {
		return invoiceService.getNumberOfCancelledOrders();
	}
	
	//get total number of orders
	@RequestMapping(value = "/numberoforders", method = RequestMethod.GET)
	public int getNumberOfOrders() {
		return invoiceService.getNumberOfOrders();
	}
	
	//get total revenue
	@RequestMapping(value = "/revenue", method = RequestMethod.GET)
	public Long getRevenue() {
        return invoiceService.getRevenue();
	}
	
	//get revenue within the last 12 months
	
	//update invoice status
	@RequestMapping(value = "/{invoice_id}/status/{status}", method = RequestMethod.PUT)
	public Invoice updateInvoice(@PathVariable(value = "invoice_id") Long invoice_id, @PathVariable(value = "status") int status) {
		return invoiceService.updateInvoice(invoice_id, status);
	}

}
