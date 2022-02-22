package com.jamie.demo.service;

import com.jamie.demo.model.Film;
import com.jamie.demo.repository.FilmRepository;
import com.jamie.demo.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

 

import static org.assertj.core.api.Assertions.assertThat;

        import com.jamie.demo.exception.ResourceNotFoundException;
        import com.jamie.demo.model.Film;
        import com.jamie.demo.repository.FilmRepository;
        import com.jamie.demo.service.impl.FilmServiceImpl;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.DisplayName;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;

        import static org.mockito.ArgumentMatchers.any;
        import static org.mockito.BDDMockito.given;
        import static org.mockito.BDDMockito.willDoNothing;
        import static org.mockito.Mockito.*;

        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;

        import java.util.Collections;
        import java.util.List;
        import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {

    @Mock
    private FilmRepository filmRepository;

    @InjectMocks
    private FilmServiceImpl filmService;

    private Film film;

    @BeforeEach
    public void setup() {
        //filmRepository = Mockito.mock(FilmRepository.class);
        //filmService = new FilmServiceImpl(filmRepository);
        film = Film.builder()
                .film_id(1)
                .Title("SuperBad")
                .Description("McLovin")
                .Rating("PG")
                .build();
    }

    // JUnit test for saveFilm method
    @DisplayName("JUnit test for saveFilm method")
    @Test
    public void givenFilmObject_whenSaveFilm_thenReturnFilmObject(){
        // given - precondition or setup
        given(filmRepository.save(film)).willReturn(film);

        System.out.println(filmRepository);
        System.out.println(filmService);

        // when -  action or the behaviour that we are going test
        Film savedFilm = filmService.saveFilm(film);

        System.out.println(savedFilm);
        // then - verify the output
        assertThat(savedFilm).isNotNull();
    }

    // JUnit test for saveFilm method
//    @DisplayName("JUnit test for saveFilm method which throws exception")
//    @Test
//    public void givenExistingId_whenSaveFilm_thenThrowsException(){
//        // given - precondition or setup
//        given(filmRepository.findById(film.getFilm_id()))
//                .willReturn(Optional.of(film));
//
//        System.out.println(filmRepository);
//        System.out.println(filmService);
//
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            filmService.saveFilm(film);
//        });
//
//        // then
//        verify(filmRepository, never()).save(any(Film.class));
//    }

    // JUnit test for getAllFilms method
    @DisplayName("JUnit test for getAllFilms method")
    @Test
    public void givenFilmsList_whenGetAllFilms_thenReturnFilmsList(){
        // given - precondition or setup

        Film film1 = Film.builder()
                .film_id(2)
                .Title("SuperBad")
                .Description("McLovin")
                .Rating("PG")
                .build();

        given(filmRepository.findAll()).willReturn(List.of(film,film1));

        // when -  action or the behaviour that we are going test
        List<Film> filmList = filmService.getAllFilms();

        // then - verify the output
        assertThat(filmList).isNotNull();
        assertThat(filmList.size()).isEqualTo(2);
    }

    // JUnit test for getAllFilms method
    @DisplayName("JUnit test for getAllFilms method (negative scenario)")
    @Test
    public void givenEmptyFilmsList_whenGetAllFilms_thenReturnEmptyFilmsList(){
        // given - precondition or setup

        Film film1 = Film.builder()
                .film_id(2)
                .Title("SuperBad")
                .Description("McLovin")
                .Rating("PG")
                .build();

        given(filmRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Film> filmList = filmService.getAllFilms();

        // then - verify the output
        assertThat(filmList).isEmpty();
        assertThat(filmList.size()).isEqualTo(0);
    }

    // JUnit test for getFilmById method
    @DisplayName("JUnit test for getFilmById method")
    @Test
    public void givenFilmId_whenGetFilmById_thenReturnFilmObject(){
        // given
        given(filmRepository.findById(1)).willReturn(Optional.of(film));

        // when
        Film savedFilm = filmService.getFilmById(film.getFilm_id()).get();

        // then
        assertThat(savedFilm).isNotNull();

    }

    // JUnit test for updateFilm method
    @DisplayName("JUnit test for updateFilm method")
    @Test
    public void givenFilmObject_whenUpdateFilm_thenReturnUpdatedFilm(){
        // given - precondition or setup
        given(filmRepository.save(film)).willReturn(film);
        film.setFilm_id(1);
        film.setTitle("SuperBad");
        // when -  action or the behaviour that we are going test
        Film updatedFilm = filmService.updateFilm(film);

        // then - verify the output
        assertThat(updatedFilm.getFilm_id()).isEqualTo(1);
        assertThat(updatedFilm.getTitle()).isEqualTo("SuperBad");
    }

    // JUnit test for deleteFilm method
    @DisplayName("JUnit test for deleteFilm method")
    @Test
    public void givenFilmId_whenDeleteFilm_thenNothing(){
        // given - precondition or setup
        int film_id = 1;

        willDoNothing().given(filmRepository).deleteById(film_id);

        // when -  action or the behaviour that we are going test
        filmService.deleteFilm(film_id);

        // then - verify the output
        verify(filmRepository, times(1)).deleteById(film_id);
    }

}

