
package com.example.library.service;

import org.junit.jupiter.api.Test;
import com.example.library.model.Book;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testBookGettersAndSetters() {
        Book book = new Book("Java1", "Husam", "12345");

        // setters
        book.setTitle("Java1");
        book.setAuthor("Husam");
        book.setIsbn("12345");

        // getters
        assertEquals("Java1", book.getTitle());
        assertEquals("Husam", book.getAuthor());
        assertEquals("12345", book.getIsbn());
    }

    @Test
    public void testBookAvailability() {
        Book book = new Book("Code", "Mariii", "999");

        assertTrue(book.isAvailable());

        book.setAvailable(false);
        assertFalse(book.isAvailable());
    }
}

