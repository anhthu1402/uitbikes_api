package com.java.uitbikes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.java.uitbikes.dto.*;
import com.java.uitbikes.service.ReviewService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/reviews")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	//create
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Boolean createReview(@RequestBody ReviewRequestDto body) {
		return reviewService.createReview(body);
	}

	//get all
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<ReviewDto> getAllReviews() {
		return reviewService.getAllReviews();
	}
	
	//get by id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ReviewDto findById(@PathVariable(value = "id") Long id) {
		return reviewService.findById(id);
	}
	
	//get by customer id
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public List<ReviewDto> findByCustomerId(@PathVariable(value = "id") Long id) {
		return reviewService.findByCustomerId(id);
	}
	
	//get by product id
	@RequestMapping(value = "/product/id/{id}", method = RequestMethod.GET)
	public List<ReviewDto> findByProductId(@PathVariable(value = "id") Long id) {
		return reviewService.findByProductId(id);
	}
	
	//get by product name
	@RequestMapping(value = "/product/name/{name}", method = RequestMethod.GET)
	public List<ReviewDto> findByProductName(@PathVariable(value = "name") String name) {
		return reviewService.findByProductName(name);
	}
	
	//edit content && rate
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ReviewDto updateReview(@PathVariable(value = "id") Long id, @RequestBody ReviewUpdateDto body) {
		return reviewService.updateReview(id, body);
	}
	
	//add image
	@RequestMapping(value = "/{id}/add-image", method = RequestMethod.PUT)
	public ReviewDto addImage(@PathVariable(value = "id") Long id, @RequestBody String url) {
		return reviewService.addImage(id, url);
	}
	
	//remove image
	@RequestMapping(value = "/remove-image/{id}", method = RequestMethod.DELETE)
	public ReviewDto removeImage(@PathVariable(value = "id") Long id) {
		return reviewService.removeImage(id);
	}
	
	//delete
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Boolean deleteReview(@PathVariable(value = "id") Long id) {
		return reviewService.deleteReview(id);
	}
}
