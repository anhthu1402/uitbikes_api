package com.java.uitbikes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "review_images")
public class ReviewImages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "image")
	private String image;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="review_id")
	private Review review;
	
	public ReviewImages() {}
	
	public ReviewImages(String url, Review review) {
		this.image = url;
		this.review = review;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}
}
