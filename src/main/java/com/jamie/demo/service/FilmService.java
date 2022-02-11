package com.jamie.demo.service;

import com.jamie.demo.model.Film;



import java.util.List;



public interface FilmService {
    Film saveFilm(Film film);
    List<Film> getAllFilm();
    Film getFilmByFilm_Id(int film_id);

    Film updateFilm(Film film, int film_id);
    void deleteFilm(int film_id);
}