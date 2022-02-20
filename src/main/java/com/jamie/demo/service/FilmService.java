package com.jamie.demo.service;

import com.jamie.demo.model.Film;

import java.util.List;
import java.util.Optional;

public interface FilmService {
    Film saveFilm(Film film);
    List<Film> getAllFilms();
    Optional<Film> getFilmById(int film_id);
    Film updateFilm(Film updatedFilm);
    void deleteFilm(int film_id);
}