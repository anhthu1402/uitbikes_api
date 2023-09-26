package com.java.uitbikes.dto;

import com.java.uitbikes.model.Product;

public class ProductDetailDto {
	private Long id;
	private String color;
	private String image;
	private Long price;
	private Long quantity;
	
	public ProductDetailDto() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductDetailDto(Product p) {
		this.id = p.getId();
		this.color = p.getColor();
		this.image = p.getImage();
		this.price = p.getPrice();
		this.quantity = p.getQuantity();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
}
