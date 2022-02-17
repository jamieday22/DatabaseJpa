package com.jamie.demo.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LanguageTest {


    Language languageTest = new Language("English");

    @Test
    void getLanguage_id() {
        assertEquals(0,languageTest.getLanguage_id(),"This test has failed");
    }

    @Test
    void getName() {
        assertEquals("English",languageTest.getName(),"This test has failed");
    }

    @Disabled
    @Test
    void setLanguage_id() {
    }

    @Test
    void setName() {
        languageTest.setName("Eng");
        assertEquals("Eng",languageTest.getName(),"This test has failed");
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