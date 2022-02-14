package com.jamie.demo.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmTest {

    private com.jamie.demo.model.Actor Actor;

    Film filmTest = new Film("TestName","2","film desc",
            1990,"PG");

    @Test
    void getFilm_id() {
        assertEquals(0,filmTest.getFilm_id(),"This test has failed");
    }

    @Test
    void getTitle() {
        assertEquals("TestName",filmTest.getTitle(),"This test has failed");
    }

    @Test
    void getLanguage_id() {
    }

    @Test
    void getDescription() {
        assertEquals("film desc",filmTest.getDescription(),"This test has failed");
    }

    @Test
    void getRelease_year() {
        assertEquals(1990,filmTest.getRelease_year(),"This test has failed");
    }

    @Test
    void getRating() {
        assertEquals("PG",filmTest.getRating(),"This test has failed");
    }

    @Test
    void getActor() {
        Set<Actor> testSet = new HashSet<>();
        testSet.add(new Actor("first_name","last_name"));
        filmTest.setActor(testSet);
        assertEquals(testSet,filmTest.getActor(),"This test has failed");
    }
    @Disabled
    @Test
    void setFilm_id() {
    }

    @Test
    void setTitle() {
        filmTest.setTitle("Title1");
        assertEquals("Title1",filmTest.getTitle(),"This test has failed");
    }

    @Test
    void setLanguage_id() {
        filmTest.setLanguage_id("2");
        assertEquals("2",filmTest.getLanguage_id(),"This test has failed");
    }

    @Test
    void setDescription() {
        filmTest.setDescription("Description2");
        assertEquals("Description2",filmTest.getDescription(),"This test has failed");
    }

    @Test
    void setRelease_year() {
        filmTest.setRelease_year(2020);
        assertEquals(2020,filmTest.getRelease_year(),"This test has failed");
    }

    @Test
    void setRating() {
        filmTest.setRating("18");
        assertEquals("18",filmTest.getRating(),"This test has failed");
    }
    @Disabled
    @Test
    void setActor() {
        Actor.setActor_id(2);
        assertEquals(2,filmTest.getActor(),"This test has failed");
    }

    @Disabled
    @Test
    void testToString() {
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
}