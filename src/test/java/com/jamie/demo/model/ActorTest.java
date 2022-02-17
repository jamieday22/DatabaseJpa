package com.jamie.demo.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {


    Actor actorTest = new Actor("Dave", "Smith");



    @Test
    void getActor_id() {
        assertEquals(0, actorTest.getActor_id(),"This test has failed");
    }

    @Test
    void getFirst_name() {
        assertEquals("Dave",actorTest.getFirst_name(),"This test has failed");
    }

    @Test
    void getLast_name() {
        assertEquals("Smith",actorTest.getLast_name(),"This test has failed");
    }

    @Disabled
    @Test
    void getFilm() {
    }

    @Disabled
    @Test
    void setActor_id() {
    }

    @Test
    void setFirst_name() {
        actorTest.setFirst_name("Dave");
        assertEquals("Dave",actorTest.getFirst_name(),"This test has failed");
    }

    @Test
    void setLast_name() {
        actorTest.setLast_name("Smith");
        assertEquals("Smith",actorTest.getLast_name(),"This test has failed");
    }

    @Disabled
    @Test
    void setFilm() {
    }

    @Disabled
    @Test
    void testEquals() {
    }

    @Disabled
    @Test
    void canEqual() {
    }

    @Disabled
    @Test
    void testHashCode() {
    }

    @Disabled
    @Test
    void testToString() {
    }
}