package com.java.uitbikes.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.CartDto;
import com.java.uitbikes.model.Cart;
import com.java.uitbikes.model.Customer;
import com.java.uitbikes.model.Product;
import com.java.uitbikes.repository.CartRepository;
import com.java.uitbikes.repository.CustomerRepository;
import com.java.uitbikes.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	// add to cart
	public Cart addToCart(Cart cart, Long customer_id, Long p_id) {
		Cart c = cartRepository.save(cart);
		Product product =productRepository.findById(p_id).get();
		c.setProduct(product);
		Customer customer = customerRepository.findById(customer_id).get();
		c.setCustomer(customer);
		return cartRepository.save(c);
	}
	
	// check if product's already in customer's cart
	public Cart checkProductInCart(Long customer_id, Long p_id) {
		Customer customer = customerRepository.findById(customer_id).get();
		List<Cart> carts = customer.getCarts();
		for (Cart cart : carts) {
			if(cart.getProduct().getId() == p_id) {
				return cart;
			}
		}
		return null;
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
	public List<CartDto> getAllCustomerCarts(Long customer_id){
		Optional<Customer> customerOpt=customerRepository.findById(customer_id);
		if(customerOpt.isPresent()) {
			List<CartDto> result = new ArrayList<CartDto>();
			Customer customer=customerOpt.get();
			List<Cart> carts = customer.getCarts();
			for (Cart cart : carts) {
				result.add(new CartDto(cart));
			}
			return result;
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
