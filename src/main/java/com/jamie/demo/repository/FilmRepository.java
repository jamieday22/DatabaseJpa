package com.jamie.demo.repository;

import com.jamie.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FilmRepository extends JpaRepository<Film, Integer> {
}
