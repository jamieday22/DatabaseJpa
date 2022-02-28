package com.jamie.demo.repository;

import com.jamie.demo.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


}

