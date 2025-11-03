package com.example.library.service;


import org.junit.jupiter.api.Test;

import com.example.library.model.Book;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest{

    @Test
    public void testBookGettersAndSetters(){
       Book Book = new Book(null, null, null);
        Book.setTitle("Java1");
        Book.setAuthor("HSusam");
        Book.setIsbn("12345");

        assertEquals("Java1", Book.getTitle());
        assertEquals("Husam", Book.getAuthor());
        assertEquals("12345", Book.getIsbn());
    }
    @Test
    public void testBookAvailability(){
        Book book = new Book(" Code", " Mariii", "999");
        assertTrue(book.isAvailable());
        book.setAvailable(false);
        assertFalse(book.isAvailable());
    }
}
