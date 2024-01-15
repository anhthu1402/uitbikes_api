package com.java.uitbikes.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.uitbikes.dto.ReviewDto;
import com.java.uitbikes.dto.ReviewRequestDto;
import com.java.uitbikes.dto.ReviewUpdateDto;
import com.java.uitbikes.model.*;
import com.java.uitbikes.repository.*;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ReviewImagesRepository imagesRepository;
	
	@Autowired
	InvoiceDetailRepository detailRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ProductRepository productRepository;

	//create
	public Boolean createReview(ReviewRequestDto body) {
		Optional<InvoiceDetail> detail = detailRepository.findById(body.getDetailId());
		Optional<Customer> customer = customerRepository.findById(body.getCustomerId());
		
		if (detail.isPresent() && customer.isPresent()) {
			Review review = new Review();
			Date time = Date.from(Instant.now());
			
			review.setTimestamp(time);
			review.setRate(body.getRate());
			review.setText(body.getText());
			review.setDetail(detail.get());
			review.setCustomer(customer.get());
			
			if (!body.getImages().isEmpty()) {
				for (String i: body.getImages()) {
					review.addImage(i);
				}
			}
			
			reviewRepository.save(review);
			return true;
		} 
		
		return false;
	}
	
	//get all
	public List<ReviewDto> getAllReviews() {
		List<Review> reviews = reviewRepository.findAll();
		List<ReviewDto> result = new ArrayList<ReviewDto>();
		
		for (Review r: reviews) {
			ReviewDto item = new ReviewDto();
			item.setId(r.getId());
			item.setRate(r.getRate());
			item.setText(r.getText());
			item.setTimestamp(r.getTimestamp());
			item.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
			item.setProduct(r.getDetail().getProduct());
			item.setImages(r.getImages());
			
			result.add(item);
		}
		
		return result;
	}
	
	//get by id
	public ReviewDto findById(Long id) {
		Optional<Review> review = reviewRepository.findById(id);
		
		if (review.isPresent()) {
			Review r = review.get();
			ReviewDto result = new ReviewDto();
			result.setId(r.getId());
			result.setRate(r.getRate());
			result.setText(r.getText());
			result.setTimestamp(r.getTimestamp());
			result.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
			result.setProduct(r.getDetail().getProduct());
			result.setImages(r.getImages());
			
			return result;
		}
		
		return null;
	}
	
	//get by customer id
	public List<ReviewDto> findByCustomerId(Long id) {
		Account account = accountRepository.findByCustomerId(id);
		
		if (account != null) {
			List<Review> reviews = reviewRepository.findByCustomerId(id);
			List<ReviewDto> result = new ArrayList<ReviewDto>();
			
			for (Review r: reviews) {
				ReviewDto item = new ReviewDto();
				item.setId(r.getId());
				item.setRate(r.getRate());
				item.setText(r.getText());
				item.setTimestamp(r.getTimestamp());
				item.setUsername(account.getUsername());
				item.setProduct(r.getDetail().getProduct());
				item.setImages(r.getImages());
				
				result.add(item);
			}
			
			return result;
		}
		
		return null;
	}
	
	//get by product id
	public List<ReviewDto> findByProductId(Long id) {
		Optional<Product> product = productRepository.findById(id);
		
		if (product.isPresent()) {
			List<Review> reviews = reviewRepository.findAll();
			List<ReviewDto> result = new ArrayList<ReviewDto>();
			
			for (Review r: reviews) {
				if (r.getDetail().getProduct().getId() == id) {
					ReviewDto item = new ReviewDto();
					item.setId(r.getId());
					item.setRate(r.getRate());
					item.setText(r.getText());
					item.setTimestamp(r.getTimestamp());
					item.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
					item.setProduct(product.get());
					item.setImages(r.getImages());
					
					result.add(item);
				}
			}
			
			return result;
		}
		
		return null;
	}
	
	//get by product name
	public List<ReviewDto> findByProductName(String name) {
		List<Product> products = productRepository.findByName(name);
		
		if (!products.isEmpty()) {
			List<Review> reviews = reviewRepository.findAll();
			List<ReviewDto> result = new ArrayList<ReviewDto>();
			
			for (Review r: reviews) {
				if (r.getDetail().getProduct().getName().equals(name)) {
					ReviewDto item = new ReviewDto();
					item.setId(r.getId());
					item.setRate(r.getRate());
					item.setText(r.getText());
					item.setTimestamp(r.getTimestamp());
					item.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
					item.setProduct(r.getDetail().getProduct());
					item.setImages(r.getImages());
					
					result.add(item);
				}
			}
			
			return result;
		}
		
		return null;
	}
	
	//edit content && rate
	public ReviewDto updateReview(Long id, ReviewUpdateDto body) {
		Optional<Review> review = reviewRepository.findById(id);
		
		if (review.isPresent()) {
			Review r = review.get();
			r.setRate(body.getRate());
			r.setText(body.getText());
			reviewRepository.save(r);
			
			ReviewDto result = new ReviewDto();
			result.setId(r.getId());
			result.setRate(r.getRate());
			result.setText(r.getText());
			result.setTimestamp(r.getTimestamp());
			result.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
			result.setProduct(r.getDetail().getProduct());
			result.setImages(r.getImages());
			
			return result;
		}
		
		return null;
	}
	
	//add image
	public ReviewDto addImage(Long id, String url) {
		Optional<Review> review = reviewRepository.findById(id);
		
		if (review.isPresent()) {
			Review r = review.get();
			r.addImage(url);
			reviewRepository.save(r);
			
			ReviewDto result = new ReviewDto();
			result.setId(r.getId());
			result.setRate(r.getRate());
			result.setText(r.getText());
			result.setTimestamp(r.getTimestamp());
			result.setUsername(accountRepository.findByCustomerId(r.getCustomer().getId()).getUsername());
			result.setProduct(r.getDetail().getProduct());
			result.setImages(r.getImages());
			
			return result;
		}
		
		return null;
	}
	
	//remove image
	public ReviewDto removeImage(Long id) {
		Optional<ReviewImages> img = imagesRepository.findById(id);
		
		if (img.isPresent()) {
			Review review = img.get().getReview();
			imagesRepository.delete(img.get());
			
			ReviewDto result = new ReviewDto();
			result.setId(review.getId());
			result.setRate(review.getRate());
			result.setText(review.getText());
			result.setTimestamp(review.getTimestamp());
			result.setUsername(accountRepository.findByCustomerId(review.getCustomer().getId()).getUsername());
			result.setProduct(review.getDetail().getProduct());
			result.setImages(review.getImages());
			
			return result;
		}
		
		return null;
	}
	
	//delete
	public Boolean deleteReview(Long id) {
		Optional<Review> review = reviewRepository.findById(id);
		
		if (review.isPresent()) {
			List<ReviewImages> imgs = imagesRepository.findByReviewId(id);
			
			if (!imgs.isEmpty()) {
				for (ReviewImages img: imgs) {
					imagesRepository.delete(img);
				}
			}
			review.get().getDetail().setReview(null);
			reviewRepository.delete(review.get());
			return true;
		}
		
		return false;
	}
}
