package com.jamie.demo.service;

import com.jamie.demo.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Review saveReview(Review review);
    List<Review> getAllReviews();
    Optional<Review> getReviewById(int review_id);
    Review updateReview(Review updatedReview);
    void deleteReview(int review_id);

    Review saveReview(int film_id, String description);
}