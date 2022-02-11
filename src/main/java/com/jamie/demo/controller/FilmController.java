package com.jamie.demo.controller;

import java.util.List;

import com.jamie.demo.model.Film;
import com.jamie.demo.service.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/film")
public class FilmController {

    private FilmService filmService;

    public FilmController(FilmService filmService) {
        super();
        this.filmService = filmService;
    }

    // build create film REST API
    @PostMapping()
    public ResponseEntity<Film> saveFilm(@RequestBody Film film){
        return new ResponseEntity<Film>(filmService.saveFilm(film), HttpStatus.CREATED);
    }

    // build get all film REST API
    @GetMapping
    public List<Film> getAllFilm(){
        return filmService.getAllFilm();
    }

    // build get film by id REST API
    // http://localhost:8080/api/langauge/1
    @GetMapping("{film_id}")
    public ResponseEntity<Film> getFilmByFilm_Id(@PathVariable("film_id") int film_id){
        return new ResponseEntity<Film>(filmService.getFilmByFilm_Id(film_id), HttpStatus.OK);
    }

    // build update film REST API
    // http://localhost:8080/api/langauge/1
    @PutMapping("{film_id}")
    public ResponseEntity<Film> updateFilm(@PathVariable("film_id") int film_id
            ,@RequestBody Film film){
        return new ResponseEntity<Film>(filmService.updateFilm(film, film_id), HttpStatus.OK);
    }

    // build delete film REST API
    // http://localhost:8080/api/film/1
    @DeleteMapping("{film_id}")
    public ResponseEntity<String> deleteFilm(@PathVariable("film_id") int film_id){

        // delete film from DB
        filmService.deleteFilm(film_id);

        return new ResponseEntity<String>("Film deleted successfully!.", HttpStatus.OK);
    }

}
