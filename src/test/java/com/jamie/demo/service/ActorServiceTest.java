package com.jamie.demo.service;

import com.jamie.demo.repository.ActorRepository;
import com.jamie.demo.service.impl.ActorServiceImpl;
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
class ActorServiceTest {

    @Mock private ActorRepository actorRepository;
    private ActorService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ActorServiceImpl(actorRepository);
    }


    @Test
    void canGetAllActor() {
        //when
        underTest.getAllActor();
        //then
        verify(actorRepository).findAll();

    }


    @Test
    @Disabled
    void saveActor() {
    }

    @Test
    @Disabled
    void CanGetActorByActor_Id() {

    }

    @Test
    @Disabled

    void updateActor() {
    }

    @Test
    @Disabled

    void deleteActor() {
    }
}