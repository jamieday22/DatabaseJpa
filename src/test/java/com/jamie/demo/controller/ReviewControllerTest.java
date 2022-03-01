package com.jamie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamie.demo.model.Review;
import com.jamie.demo.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(ReviewController.class)
public class ReviewControllerTest   {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenReviewObject_whenCreateReview_thenReturnSavedReview() throws Exception{

        // given - precondition or setup
        Review review = Review.builder()
                .Description("good")
                .build();
        given(reviewService.saveReview(any(Review.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/reviews")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(review)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.description",
                        is(review.getDescription())));



    }

    // JUnit test for Get All reviews REST API
    @Test
    public void givenListOfReviews_whenGetAllReviews_thenReturnReviewsList() throws Exception{
        // given - precondition or setup
        List<Review> listOfReviews = new ArrayList<>();
        listOfReviews.add(Review.builder().Description("Good").build());
        listOfReviews.add(Review.builder().Description("Bad").build());
        given(reviewService.getAllReviews()).willReturn(listOfReviews);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/reviews"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfReviews.size())));

    }

    // positive scenario - valid review id
    // JUnit test for GET review by id REST API
    @Test
    public void givenReviewId_whenGetReviewById_thenReturnReviewObject() throws Exception{
        // given - precondition or setup
        int review_id = 1;
        Review review = Review.builder()
                .Description("Good")
                .build();
        given(reviewService.getReviewById(review_id)).willReturn(Optional.of(review));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/reviews/{review_id}", review_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.description", is(review.getDescription())));


    }

    // negative scenario - valid review id
    // JUnit test for GET review by id REST API
    @Test
    public void givenInvalidReviewId_whenGetReviewById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        int review_id = 1;
        Review review = Review.builder()
                .Description("Good")
                .build();
        given(reviewService.getReviewById(review_id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/reviews/{review_id}", review_id));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update review REST API - positive scenario
    @Test
    public void givenUpdatedReview_whenUpdateReview_thenReturnUpdateReviewObject() throws Exception{
        // given - precondition or setup
        int review_id = 1;
        Review savedReview = Review.builder()
                .Description("Good")
                .build();

        Review updatedReview = Review.builder()
                .Description("Good")
                .build();
        given(reviewService.getReviewById(review_id)).willReturn(Optional.of(savedReview));
        given(reviewService.updateReview(any(Review.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/reviews/{review_id}", review_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReview)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.description", is(updatedReview.getDescription())));


    }

    // JUnit test for update review REST API - negative scenario
    @Test
    public void givenUpdatedReview_whenUpdateReview_thenReturn404() throws Exception{
        // given - precondition or setup
        int review_id = 1;
        Review savedReview = Review.builder()
                .Description("Good")
                .build();

        Review updatedReview = Review.builder()
                .Description("Good")
                .build();
        given(reviewService.getReviewById(review_id)).willReturn(Optional.empty());
        given(reviewService.updateReview(any(Review.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/reviews/{review_id}", review_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedReview)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete review REST API
    @Test
    public void givenReviewId_whenDeleteReview_thenReturn200() throws Exception{
        // given - precondition or setup
        int review_id = 1;
        willDoNothing().given(reviewService).deleteReview(review_id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/reviews/{review_id}", review_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
