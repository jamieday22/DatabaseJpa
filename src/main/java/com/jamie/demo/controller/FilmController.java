package com.jamie.demo.controller;

import com.jamie.demo.model.Film;
import com.jamie.demo.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Film createFilm(@RequestBody Film film){
        return filmService.saveFilm(film);
    }

    @GetMapping
    public List<Film> getAllFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("{film_id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("film_id") int film_id){
        return filmService.getFilmById(film_id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{film_id}")
    public ResponseEntity<Film> updateFilm(@PathVariable("film_id") int film_id,
                                             @RequestBody Film film){
        return filmService.getFilmById(film_id)
                .map(savedFilm -> {

                    savedFilm.setTitle(film.getTitle());
                    savedFilm.setDescription(film.getDescription());
//                    savedFilm.setRelease_Year(film.getRelease_Year());
                    savedFilm.setDescription(film.getDescription());
                    savedFilm.setRating(film.getRating());
//                    savedFilm.setLanguage_Id(film.getLanguage_Id());


                    Film updatedFilm = filmService.updateFilm(savedFilm);
                    return new ResponseEntity<>(updatedFilm, HttpStatus.OK);

                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{film_id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("film_id") int film_id){

        filmService.deleteFilm(film_id);

        return new ResponseEntity<String>("Film deleted successfully!.", HttpStatus.OK);

    }

}