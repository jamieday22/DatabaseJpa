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

//    @Test
//    public void givenActorObject_whenCreateActor_thenReturnSavedActor() throws Exception{
//
//        // given - precondition or setup
//        Actor actor = Actor.builder()
//                .firstName("Dave")
//                .lastName("Johnson")
//                .build();
//        given(actorService.saveActor(any(Actor.class)))
//                .willAnswer((invocation)-> invocation.getArgument(0));
//
//        // when - action or behaviour that we are going test
//        ResultActions response = mockMvc.perform(post("/api/actor")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(actor)));
//
//        // then - verify the result or output using assert statements
//        response.andDo(print()).
//                andExpect(status().isCreated())
//                .andExpect(jsonPath("$.first_name",
//                        is(actor.getActor_id())))
//                .andExpect(jsonPath("$.last_name",
//                        is(actor.getLastName())));
//
//
//    }

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

    // positive scenario - valid actor id
    // JUnit test for GET actor by id REST API
    @Test
    public void givenActorId_whenGetActorById_thenReturnActorObject() throws Exception{
        // given - precondition or setup
        int actor_id = 1;
        Actor actor = Actor.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .build();
        given(actorService.getActorById(actor_id)).willReturn(Optional.of(actor));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/actors/{actor_id}", actor_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(actor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(actor.getLastName())));


    }

    // negative scenario - valid actor id
    // JUnit test for GET actor by id REST API
    @Test
    public void givenInvalidActorId_whenGetActorById_thenReturnEmpty() throws Exception{
        // given - precondition or setup
        int actor_id = 1;
        Actor actor = Actor.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .build();
        given(actorService.getActorById(actor_id)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/actors/{actor_id}", actor_id));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());

    }
    // JUnit test for update actor REST API - positive scenario
    @Test
    public void givenUpdatedActor_whenUpdateActor_thenReturnUpdateActorObject() throws Exception{
        // given - precondition or setup
        int actor_id = 1;
        Actor savedActor = Actor.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .build();

        Actor updatedActor = Actor.builder()
                .firstName("Johnson")
                .lastName("Dave")
                .build();
        given(actorService.getActorById(actor_id)).willReturn(Optional.of(savedActor));
        given(actorService.updateActor(any(Actor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/actors/{actor_id}", actor_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedActor)));


        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.firstName", is(updatedActor.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(updatedActor.getLastName())));

    }

    // JUnit test for update actor REST API - negative scenario
    @Test
    public void givenUpdatedActor_whenUpdateActor_thenReturn404() throws Exception{
        // given - precondition or setup
        int actor_id = 1;
        Actor savedActor = Actor.builder()
                .firstName("Dave")
                .lastName("Johnson")
                .build();

        Actor updatedActor = Actor.builder()
                .firstName("Dave")
                .lastName("Dave")
                .build();
        given(actorService.getActorById(actor_id)).willReturn(Optional.empty());
        given(actorService.updateActor(any(Actor.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(put("/api/actors/{actor_id}", actor_id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedActor)));

        // then - verify the output
        response.andExpect(status().isNotFound())
                .andDo(print());
    }

    // JUnit test for delete actor REST API
    @Test
    public void givenActorId_whenDeleteActor_thenReturn200() throws Exception{
        // given - precondition or setup
        int actor_id = 1;
        willDoNothing().given(actorService).deleteActor(actor_id);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(delete("/api/actors/{actor_id}", actor_id));

        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print());
    }
}

    