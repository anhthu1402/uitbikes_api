package com.java.uitbikes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_detail")
public class InvoiceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "invoice_id")
    private Invoice invoice;
 
    @ManyToOne
    @JoinColumn(name = "p_id")
    private Product product;

	@Column(name = "quantity")
	private int quantity;
	
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "detail")
	private Review review;
	
	public InvoiceDetail() {}
	
	public InvoiceDetail(Invoice invoice, Product product, int quantity) {
		this.invoice = invoice;
		this.product = product;
		this.quantity = quantity;
	}
	
	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
}
