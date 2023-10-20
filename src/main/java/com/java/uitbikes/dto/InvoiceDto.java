package com.java.uitbikes.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.uitbikes.model.*;

public class InvoiceDto {
	private Long id;
	private Long customer_id;
	private Date date;
	private Long total;
	private int status;
	private List<InvoiceDetail> details = new ArrayList<>();
	
	public InvoiceDto() {}
	
	public InvoiceDto(Invoice invoice, Long customer_id) {
		this.id = invoice.getId();
		this.date = invoice.getDate();
		this.total = invoice.getTotal();
		this.status = invoice.getStatus();
		this.customer_id = customer_id;
		this.details = invoice.getDetails();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Long getCustomerId() {
		return customer_id;
	}

	public void setCustomerId(Long customer_id) {
		this.customer_id = customer_id;
	}

	public List<InvoiceDetail> getDetails() {
		return details;
	}

	public void setDetails(List<InvoiceDetail> details) {
		this.details = details;
	}
}
