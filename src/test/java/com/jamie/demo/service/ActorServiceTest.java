package com.jamie.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jamie.demo.exception.ResourceNotFoundException;
import com.jamie.demo.model.Actor;
import com.jamie.demo.repository.ActorRepository;
import com.jamie.demo.service.impl.ActorServiceImpl;
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
public class ActorServiceTest {

    @Mock
    private ActorRepository actorRepository;

    @InjectMocks
    private ActorServiceImpl actorService;

    private Actor actor;

    @BeforeEach
    public void setup() {
        //actorRepository = Mockito.mock(ActorRepository.class);
        //actorService = new ActorServiceImpl(actorRepository);
        actor = Actor.builder()
                .actor_id(1)
                .firstName("Dave")
                .lastName("Smith")
                .build();
    }

    // JUnit test for saveActor method
    @DisplayName("JUnit test for saveActor method")
    @Test
    public void givenActorObject_whenSaveActor_thenReturnActorObject(){
        // given - precondition or setup
        given(actorRepository.save(actor)).willReturn(actor);

        System.out.println(actorRepository);
        System.out.println(actorService);

        // when -  action or the behaviour that we are going test
        Actor savedActor = actorService.saveActor(actor);

        System.out.println(savedActor);
        // then - verify the output
        assertThat(savedActor).isNotNull();
    }

    // JUnit test for saveActor method
//    @DisplayName("JUnit test for saveActor method which throws exception")
//    @Test
//    public void givenExistingId_whenSaveActor_thenThrowsException(){
//        // given - precondition or setup
//        given(actorRepository.findById(actor.getActor_id()))
//                .willReturn(Optional.of(actor));
//
//        System.out.println(actorRepository);
//        System.out.println(actorService);
//
//        // when -  action or the behaviour that we are going test
//        org.junit.jupiter.api.Assertions.assertThrows(ResourceNotFoundException.class, () -> {
//            actorService.saveActor(actor);
//        });
//
//        // then
//        verify(actorRepository, never()).save(any(Actor.class));
//    }

    // JUnit test for getAllActors method
    @DisplayName("JUnit test for getAllActors method")
    @Test
    public void givenActorsList_whenGetAllActors_thenReturnActorsList(){
        // given - precondition or setup

        Actor actor1 = Actor.builder()
                .actor_id(2)
                .firstName("Tony")
                .lastName("Stark")
                .build();

        given(actorRepository.findAll()).willReturn(List.of(actor,actor1));

        // when -  action or the behaviour that we are going test
        List<Actor> actorList = actorService.getAllActors();

        // then - verify the output
        assertThat(actorList).isNotNull();
        assertThat(actorList.size()).isEqualTo(2);
    }

    // JUnit test for getAllActors method
    @DisplayName("JUnit test for getAllActors method (negative scenario)")
    @Test
    public void givenEmptyActorsList_whenGetAllActors_thenReturnEmptyActorsList(){
        // given - precondition or setup

        Actor actor1 = Actor.builder()
                .actor_id(2)
                .firstName("Tony")
                .lastName("Stark")
                .build();

        given(actorRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Actor> actorList = actorService.getAllActors();

        // then - verify the output
        assertThat(actorList).isEmpty();
        assertThat(actorList.size()).isEqualTo(0);
    }

    // JUnit test for getActorById method
    @DisplayName("JUnit test for getActorById method")
    @Test
    public void givenActorId_whenGetActorById_thenReturnActorObject(){
        // given
        given(actorRepository.findById(1)).willReturn(Optional.of(actor));

        // when
        Actor savedActor = actorService.getActorById(actor.getActor_id()).get();

        // then
        assertThat(savedActor).isNotNull();
    
        }

    // JUnit test for updateActor method
    @DisplayName("JUnit test for updateActor method")
    @Test
    public void givenActorObject_whenUpdateActor_thenReturnUpdatedActor(){
        // given - precondition or setup
        given(actorRepository.save(actor)).willReturn(actor);
        actor.setActor_id(1);
        actor.setFirstName("Dave");
        // when -  action or the behaviour that we are going test
        Actor updatedActor = actorService.updateActor(actor);

        // then - verify the output
        assertThat(updatedActor.getActor_id()).isEqualTo(1);
        assertThat(updatedActor.getFirstName()).isEqualTo("Dave");
    }

    // JUnit test for deleteActor method
    @DisplayName("JUnit test for deleteActor method")
    @Test
    public void givenActorId_whenDeleteActor_thenNothing(){
        // given - precondition or setup
        int actor_id = 1;

        willDoNothing().given(actorRepository).deleteById(actor_id);

        // when -  action or the behaviour that we are going test
        actorService.deleteActor(actor_id);

        // then - verify the output
        verify(actorRepository, times(1)).deleteById(actor_id);
    }

}


