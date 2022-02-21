package com.jamie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jamie.demo.model.Film;
import com.jamie.demo.service.FilmService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(FilmController.class)
public class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FilmService filmService;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void givenFilmObject_whenCreateFilm_thenReturnSavedFilm() throws Exception{
//
//        // given - precondition or setup
//        Film film = Film.builder()
//                .firstName("Dave")
//                .lastName("Johnson")
//                .build();
//        given(filmService.saveFilm(any(Film.class)))
//                .willAnswer((invocation)-> invocation.getArgument(0));
//
//        // when - action or behaviour that we are going test
//        ResultActions response = mockMvc.perform(post("/api/film")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(film)));
//
//        // then - verify the result or output using assert statements
//        response.andDo(print()).
//                andExpect(status().isCreated())
//                .andExpect(jsonPath("$.first_name",
//                        is(film.getFilm_id())))
//                .andExpect(jsonPath("$.last_name",
//                        is(film.getLastName())));
//
//
//    }

    // JUnit test for Get All films REST API
    @Test
    public void givenListOfFilms_whenGetAllFilms_thenReturnFilmsList() throws Exception{
        // given - precondition or setup
        List<Film> listOfFilms = new ArrayList<>();
        listOfFilms.add(Film.builder().Title("New").Description("description").Rating("pg").build());
        listOfFilms.add(Film.builder().Title("description").Description("New").Rating("pg").build());
        given(filmService.getAllFilms()).willReturn(listOfFilms);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/films"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfFilms.size())));

    }
}