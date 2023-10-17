package com.java.uitbikes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.java.uitbikes.dto.InvoiceDetailDto;

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
	private int status;
	
	@Column(name = "customer_id")
	private Long customerId;
	
	@OneToMany
    @JoinTable(name="invoice_detail",
            joinColumns = @JoinColumn(name="invoice_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id")
    )
    private List<InvoiceDetailDto> details = new ArrayList<>();

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
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public List<InvoiceDetailDto> getDetails() {
		return details;
	}

	public void setDetails(List<InvoiceDetailDto> details) {
		this.details = details;
	}
	
	public void addProduct(InvoiceDetailDto product) {
		this.details.add(product);
	}
}
