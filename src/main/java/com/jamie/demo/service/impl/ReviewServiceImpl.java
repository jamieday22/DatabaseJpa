package com.jamie.demo.service.impl;

import com.jamie.demo.model.Review;
import com.jamie.demo.repository.ReviewRepository;
import com.jamie.demo.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(int review_id) {
        return reviewRepository.findById(review_id);
    }

    @Override
    public Review updateReview(Review updatedReview) {
        return reviewRepository.save(updatedReview);
    }

    @Override
    public void deleteReview(int review_id) {
        reviewRepository.deleteById(review_id);
    }

    @Override
    public Review saveReview(int film_id, String description) {
        return null;
    }
}