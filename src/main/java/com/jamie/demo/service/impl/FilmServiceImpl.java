package com.jamie.demo.service.impl;

import java.util.List;

import com.jamie.demo.exception.ResourceNotFoundException;
import com.jamie.demo.model.Film;
import com.jamie.demo.repository.FilmRepository;
import org.springframework.stereotype.Service;
import com.jamie.demo.service.FilmService;

@Service  // this is the get post put delete
public class FilmServiceImpl implements FilmService {


    private FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        super();
        this.filmRepository = filmRepository;
    }

    @Override
    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public List<Film> getAllFilm() {
        return filmRepository.findAll();
    }


    @Override
    public Film getFilmByFilm_Id(int film_id) {
//		Optional<Film> film = filmRepository.findById(Film_id);
//		if(film.isPresent()) {
//			return film.get();
//		}else {
//			throw new ResourceNotFoundException("Film", "film_id", film_id);
//		}
        return filmRepository.findById(film_id).orElseThrow(() ->
                new ResourceNotFoundException("Film", "film_id", film_id));

    }

    @Override
    public Film updateFilm(Film film, int film_id) {

        // we need to check whether film with given id is exists in DB or not
        Film existingFilm = filmRepository.findById(film_id).orElseThrow(
                () -> new ResourceNotFoundException("Film", "film_id", film_id));

        existingFilm.setTitle(film.getTitle());
//        existingFilm.setDescription(film.getDescription());
//        existingFilm.setRelease_year(film.getRelease_year());
//        existingFilm.setRating(film.getRating());
//
        // save existing employee to DB
        filmRepository.save(existingFilm);
        return existingFilm;
    }

    @Override
    public void deleteFilm(int film_id) {

        // check whether a film exist in a DB or not
        filmRepository.findById(film_id).orElseThrow(() ->
                new ResourceNotFoundException("Film", "film_id", film_id));
        filmRepository.deleteById(film_id);
    }

}




//existingFilm.setTitle(film.getTitle());
//        existingFilm.setDescription(film.getDescription());
//        existingFilm.setRelease_year(film.getRelease_year());
//        existingFilm.setRating(film.getRating());