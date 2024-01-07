package com.java.uitbikes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "review_time")
	private Date timestamp;
	
	@Column(name = "rate")
	private int rate;
	
	@Column(name = "text")
	private String text;
	
	@ManyToOne
	@JoinColumn(name="customer_id", referencedColumnName = "customer_id")
	private Customer customer;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_detail_id")
	private InvoiceDetail detail;
	
	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
	private List<ReviewImages> images = new ArrayList<>();

	public Review() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public InvoiceDetail getDetail() {
		return detail;
	}

	public void setDetail(InvoiceDetail detail) {
		this.detail = detail;
	}

	public List<ReviewImages> getImages() {
		return images;
	}

	public void setImages(List<ReviewImages> images) {
		this.images = images;
	}
	
	public void addImage(String url) {
		ReviewImages image = new ReviewImages(url, this);
		images.add(image);
	}
}
