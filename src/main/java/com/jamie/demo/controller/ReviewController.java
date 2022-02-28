package com.jamie.demo.controller;

import com.jamie.demo.model.Review;
import com.jamie.demo.repository.ReviewRepository;
import com.jamie.demo.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Review createReview(@RequestBody int film_id, String description){
//        return reviewService.saveReview(film_id,description);
//    }

    ReviewRepository reviewRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Review createReview(@RequestBody Review review){
        return reviewService.saveReview(review);
    }


    @GetMapping
    public List<Review> getAllReviews(){
        return reviewService.getAllReviews();
    }

    @GetMapping("{review_id}")
    public ResponseEntity<Review> getReviewById(@PathVariable("review_id") int review_id){
        return reviewService.getReviewById(review_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{review_id}")
    public ResponseEntity<Review> updateReview(@PathVariable("review_id") int review_id,
                                                   @RequestBody Review review){
        return reviewService.getReviewById(review_id)
                .map(savedReview -> {

                    savedReview.setFilm_Id(review.getFilm_Id());
                    savedReview.setDescription(review.getDescription());



                    Review updatedReview = reviewService.updateReview(savedReview);
                    return new ResponseEntity<>(updatedReview, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{review_id}")
    public ResponseEntity<String> deleteReview(@PathVariable("review_id") int review_id){

        reviewService.deleteReview(review_id);

        return new ResponseEntity<String>("Review deleted successfully!.", HttpStatus.OK);

    }

}
