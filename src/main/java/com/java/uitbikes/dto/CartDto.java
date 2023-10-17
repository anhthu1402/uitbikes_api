package com.java.uitbikes.dto;

import com.java.uitbikes.model.Cart;
import com.java.uitbikes.model.Product;

public class CartDto {
	public Long id;
	public Product product;
	public Long quantity;
	
	public CartDto() {
		// TODO Auto-generated constructor stub
	}
	
	public CartDto(Cart cart) {
		this.id = cart.getId();
		this.product = cart.getProduct();
		this.quantity = cart.getQuantity();
	}
}
