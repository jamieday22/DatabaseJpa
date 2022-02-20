package com.jamie.demo.service.impl;

import com.jamie.demo.model.Film;
import com.jamie.demo.repository.FilmRepository;
import com.jamie.demo.service.FilmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Film> getFilmById(int film_id) {
        return filmRepository.findById(film_id);
    }

    @Override
    public Film updateFilm(Film updatedFilm) {
        return filmRepository.save(updatedFilm);
    }

    @Override
    public void deleteFilm(int film_id) {
        filmRepository.deleteById(film_id);
    }
}
