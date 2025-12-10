package com.example.library.service;
import org.junit.jupiter.api.Test;

import com.example.library.model.CD;

import static org.junit.jupiter.api.Assertions.*;

class CDTest {

    @Test
    void testCDCreation() {
        CD c = new CD("T", "Artist", "10");
        assertEquals("T", c.getTitle());
        assertEquals("Artist", c.getArtist());
        assertEquals("10", c.getId());
    }

    @Test
    void testSetArtist() {
        CD c = new CD("T", "Old", "10");
        c.setArtist("New");
        assertEquals("New", c.getArtist());
    }

    @Test
    void testBorrowDays() {
        CD c = new CD("T", "A", "10");
        assertEquals(7, c.getBorrowDays());
    }

    @Test
    void testOverdueFine() {
        CD c = new CD("T", "A", "10");
        assertEquals(20.0, c.getOverdueFine());
    }
}