package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	@Query(value = "SELECT * FROM review WHERE customer_id = :id", nativeQuery = true)
	List<Review> findByCustomerId(Long id);
}
