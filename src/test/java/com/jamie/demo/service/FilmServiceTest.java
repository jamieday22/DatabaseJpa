package com.jamie.demo.service;

import com.jamie.demo.repository.FilmRepository;
import com.jamie.demo.service.impl.FilmServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock private FilmRepository filmRepository;
    private FilmService underTest;

    @BeforeEach
    void setUp() {
        underTest = new FilmServiceImpl(filmRepository);
    }


    @Test
    void canGetAllFilm() {
        //when
        underTest.getAllFilm();
        //then
        verify(filmRepository).findAll();

    }


    @Test
    @Disabled
    void saveFilm() {
    }

    @Test
    @Disabled
    void CanGetFilmByFilm_Id() {

    }

    @Test
    @Disabled

    void updateFilm() {
    }

    @Test
    @Disabled

    void deleteFilm() {
    }
}