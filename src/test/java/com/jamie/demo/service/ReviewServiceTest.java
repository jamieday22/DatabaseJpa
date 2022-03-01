package com.jamie.demo.service;

import com.jamie.demo.model.Review;
import com.jamie.demo.repository.ReviewRepository;
import com.jamie.demo.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    private Review review;

    @BeforeEach
    public void setup() {
        //reviewRepository = Mockito.mock(ReviewRepository.class);
        //reviewService = new ReviewServiceImpl(reviewRepository);
        review = Review.builder()
                .review_id(1)
                .Description("good")
                .build();
    }

    // JUnit test for saveReview method
    @DisplayName("JUnit test for saveReview method")
    @Test
    public void givenReviewObject_whenSaveReview_thenReturnReviewObject() {
        // given - precondition or setup
        given(reviewRepository.save(review)).willReturn(review);

        System.out.println(reviewRepository);
        System.out.println(reviewService);

        // when -  action or the behaviour that we are going test
        Review savedReview = reviewService.saveReview(review);

        System.out.println(savedReview);
        // then - verify the output
        assertThat(savedReview).isNotNull();
    }
}