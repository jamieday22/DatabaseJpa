package com.jamie.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.jamie.demo.model.Film;
import com.jamie.demo.service.FilmService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.ArgumentMatchers.*;
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

    @Test
    public void givenFilmObject_whenCreateFilm_thenReturnSavedFilm() throws Exception{

        // given - precondition or setup
        Film film = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();
        given(filmService.saveFilm(any(Film.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(film)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.title",
                        is(film.getTitle())))
                .andExpect(jsonPath("$.description",
                        is(film.getDescription())))
//                .andExpect(jsonPath("$.release_year",
//                        is(film.getRelease_Year())))
                .andExpect(jsonPath("$.rating",
                        is(film.getRating())));


    }

    // JUnit test for Get All films REST API
    @Test
    public void givenListOfFilms_whenGetAllFilms_thenReturnFilmsList() throws Exception{
        // given - precondition or setup
        List<Film> listOfFilms = new ArrayList<>();
        listOfFilms.add(Film.builder().Title("new film").Description("its a new film").Rating("PG").build());
        listOfFilms.add(Film.builder().Title("new film2").Description("its a new film2").Rating("PG").build());
        given(filmService.getAllFilms()).willReturn(listOfFilms);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/films"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfFilms.size())));

    }

    // positive scenario - valid film id
    // JUnit test for GET film by id REST API
    @Test
    public void givenFilmId_whenGetFilmById_thenReturnFilmObject() throws Exception{
        // given - precondition or setup
        int film_id = 1;
        Film film = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();
        given(filmService.getFilmById(film_id)).willReturn(Optional.of(film));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/films/{film_id}", film_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(film.getTitle())))
                .andExpect(jsonPath("$.description", is(film.getDescription())))
//                .andExpect(jsonPath("$.Release_Year", is(film.getRelease_Year())))
                .andExpect(jsonPath("$.rating", is(film.getRating())));

    }

    // negative scenario - valid film id
    // JUnit test for GET film by id REST API
    @Test
    public void givenInvalidFilmId_whenGetFilmById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        int film_id = 1;
        Film film = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();
        given(filmService.getFilmById(film_id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/films/{film_id}", film_id));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update film REST API - positive scenario
    @Test
    public void givenUpdatedFilm_whenUpdateFilm_thenReturnUpdateFilmObject() throws Exception{
        // given - precondition or setup
        int film_id = 1;
        Film savedFilm = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();

        Film updatedFilm = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();
        given(filmService.getFilmById(film_id)).willReturn(Optional.of(savedFilm));
        given(filmService.updateFilm(any(Film.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/films/{film_id}", film_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFilm)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.title", is(updatedFilm.getTitle())))
                .andExpect(jsonPath("$.description", is(updatedFilm.getDescription())))
//                .andExpect(jsonPath("$.Release_Year", is(updatedFilm.getRelease_Year())))
                .andExpect(jsonPath("$.rating", is(updatedFilm.getRating())));


    }

    // JUnit test for update film REST API - negative scenario
    @Test
    public void givenUpdatedFilm_whenUpdateFilm_thenReturn404() throws Exception{
        // given - precondition or setup
        int film_id = 1;
        Film savedFilm = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();

        Film updatedFilm = Film.builder()
                .Title("new film")
                .Description("its a new film")
//                .Release_Year(2002)
                .Rating("PG")
                .build();
        given(filmService.getFilmById(film_id)).willReturn(Optional.empty());
        given(filmService.updateFilm(any(Film.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/films/{film_id}", film_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedFilm)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete film REST API
    @Test
    public void givenFilmId_whenDeleteFilm_thenReturn200() throws Exception{
        // given - precondition or setup
        int film_id = 1;
        willDoNothing().given(filmService).deleteFilm(film_id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/films/{film_id}", film_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}
