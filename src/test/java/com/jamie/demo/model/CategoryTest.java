package com.jamie.demo.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category categoryTest = new Category("Action");

    @Test
    void getCategory_id() {
        assertEquals(0,categoryTest.getCategory_id(),"This test has failed");
    }

    @Test
    void getName() {
        assertEquals("Action",categoryTest.getName(),"This test has failed");
    }

    @Disabled
    @Test
    void setCategory_id() {
    }

    @Test
    void setName() {
        categoryTest.setName("Action");
        assertEquals("Action",categoryTest.getName(),"This test has failed");
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