package com.java.uitbikes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.uitbikes.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

}
