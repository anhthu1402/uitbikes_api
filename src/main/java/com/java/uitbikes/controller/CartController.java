package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.java.uitbikes.dto.CartDto;
import com.java.uitbikes.model.Cart;
import com.java.uitbikes.service.CartService;

@RestController
@RequestMapping(value = "/api/carts")
public class CartController {
	@Autowired 
	CartService cartService;
	
	// add to cart
	@RequestMapping(value = "/customer/{customer_id}/product/{p_id}", method = RequestMethod.POST)
	public Cart addToCart(@RequestBody Cart cart, @PathVariable(value = "customer_id") Long customer_id, @PathVariable(value = "p_id") Long p_id) {
		return cartService.addToCart(cart, customer_id, p_id);
	}
	
	// get all customer's carts
	@RequestMapping(value = "/customer/{customer_id}", method = RequestMethod.GET)
	public List<CartDto> getAllCustomerCarts(@PathVariable(value = "customer_id") Long customer_id){
		return cartService.getAllCustomerCarts(customer_id);
	}
	
	// get cart by id
	@RequestMapping(value = "/{cart_id}", method = RequestMethod.GET)
	public Cart getCartById(@PathVariable(value = "cart_id") Long cart_id) {
		return cartService.getCartById(cart_id);
	}
	
	// delete cart
	@RequestMapping(value = "/{cart_id}", method = RequestMethod.DELETE)
	public Boolean deleteCart(@PathVariable(value = "cart_id") Long cart_id) {
		return cartService.deleteCart(cart_id);
	}
	
	// update quantity
	@RequestMapping(value = "/{cart_id}/quantity/{quantity}", method = RequestMethod.PUT)
	public Cart updateQuantity(@PathVariable(value = "cart_id") Long cart_id, @PathVariable(value = "quantity") Long quantity) {
		return cartService.updateQuantity(cart_id, quantity);
	}
}
