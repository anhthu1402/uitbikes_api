package com.java.uitbikes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "invoice_id")
	private Long id;
	
	@Column(name = "date_purchased")
	private Date date;
	
	@Column(name = "total")
	private Long total;
	
	@Column(name = "status")
	private int status = 0;
	
	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy="invoice", cascade = CascadeType.ALL)
	private List<InvoiceDetail> details = new ArrayList<>();

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<InvoiceDetail> getDetails() {
		return details;
	}

	public void setDetails(List<InvoiceDetail> details) {
		this.details = details;
	}
	
	public void addProduct(Product product, int quantity) {
		InvoiceDetail invoicedetail = new InvoiceDetail(this, product, quantity);
		details.add(invoicedetail);
	}
}
