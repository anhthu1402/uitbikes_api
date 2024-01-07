package com.java.uitbikes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.java.uitbikes.model.ReviewImages;

public interface ReviewImagesRepository extends JpaRepository<ReviewImages, Long> {
	@Query(value = "SELECT * FROM review_images WHERE review_id = :id", nativeQuery = true)
	List<ReviewImages> findByReviewId(Long id);
}
