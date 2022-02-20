package com.jamie.demo.controller;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.jamie.demo.model.Actor;
import com.jamie.demo.service.ActorService;
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


@WebMvcTest
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ActorService actorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenActorObject_whenCreateActor_thenReturnSavedActor() throws Exception{

        // given - precondition or setup
        Actor actor = Actor.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .build();
        given(actorService.saveActor(any(Actor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when - action or behaviour that we are going test
        ResultActions response = mockMvc.perform(post("/api/actor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(actor)));

        // then - verify the result or output using assert statements
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.first_name",
                        is(actor.getActor_id())))
                .andExpect(jsonPath("$.last_name",
                        is(actor.getLastName())));


    }

    // JUnit test for Get All actors REST API
    @Test
    public void givenListOfActors_whenGetAllActors_thenReturnActorsList() throws Exception{
        // given - precondition or setup
        List<Actor> listOfActors = new ArrayList<>();
        listOfActors.add(Actor.builder().firstName("Dave").lastName("Johnson").build());
        listOfActors.add(Actor.builder().firstName("Tony").lastName("Dave").build());
        given(actorService.getAllActors()).willReturn(listOfActors);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/actors"));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",
                        is(listOfActors.size())));

    }
}