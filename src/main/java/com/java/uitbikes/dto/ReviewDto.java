package com.java.uitbikes.dto;

import java.util.Date;
import java.util.List;

import com.java.uitbikes.model.Product;
import com.java.uitbikes.model.ReviewImages;

public class ReviewDto {
	private Long id;
	private int rate;
	private String text;
	private String username;
	private List<ReviewImages> images;
	private Date timestamp;
	private Product product;
	
	public ReviewDto() {}
	
	public ReviewDto(Long id, int rate, String text, String username, List<ReviewImages> images, Date timestamp, Product product) {
		this.id = id;
		this.rate = rate;
		this.text = text;
		this.username = username;
		this.images = images;
		this.timestamp = timestamp;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ReviewImages> getImages() {
		return images;
	}

	public void setImages(List<ReviewImages> images) {
		this.images = images;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
