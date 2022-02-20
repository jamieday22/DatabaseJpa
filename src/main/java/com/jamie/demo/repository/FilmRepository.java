package com.jamie.demo.repository;

import com.jamie.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {


}