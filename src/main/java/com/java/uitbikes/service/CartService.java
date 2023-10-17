package com.java.uitbikes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.Cart;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.repository.CartRepository;
import com.java.uitbikes.repository.CustomerRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	// add to cart
	public Cart addToCart(Cart cart, Long customer_id) {
		Cart c = cartRepository.save(cart);
		Customer customer = customerRepository.findById(customer_id).get();
		c.setCustomer(customer);
		return cartRepository.save(c);
	}
	
	// update quantity
	public Cart updateQuantity(Long cart_id, Long quantity) {
		Optional<Cart> cart = cartRepository.findById(cart_id);
		if(cart.isPresent()) {
			Cart c = cart.get();
			c.setQuantity(quantity);
			return cartRepository.save(c);
		}
		return null;
	}
	
	// get all customer's carts
	public List<Cart> getAllCustomerCarts(Long customer_id){
		Optional<Customer> customerOpt=customerRepository.findById(customer_id);
		if(customerOpt.isPresent()) {
			Customer customer=customerOpt.get();
			return customer.getCarts();
		}
		return null;
	}
	
	// get cart by id
	public Cart getCartById(Long id) {
		Optional<Cart> cart = cartRepository.findById(id);
		if(cart.isPresent()) {
			return cart.get();
		}
		return null;
	}
	
	// delete cart
	public Boolean deleteCart(Long cart_id) {
		Optional<Cart> cart = cartRepository.findById(cart_id);
		if(cart.isPresent()) {
			cartRepository.delete(cart.get());
			return true;
		}
		return false;
	}
}
