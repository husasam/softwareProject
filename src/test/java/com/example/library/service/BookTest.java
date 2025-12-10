
package com.example.library.service;

import org.junit.jupiter.api.Test;
import com.example.library.model.Book;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
	@Test
    void testBookCreation() {
        Book b = new Book("Title", "Author", "123");
        assertEquals("Title", b.getTitle());
        assertEquals("Author", b.getAuthor());
        assertEquals("123", b.getIsbn());
    }

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
   
    
    @Test
    public void testAvailabilityToggle() {
        Book book = new Book("X", "Y", "Z");

        assertTrue(book.isAvailable());

        book.setAvailable(false);
        assertFalse(book.isAvailable());

        book.setAvailable(true);
        assertTrue(book.isAvailable());
    }
    @Test
    public void testSettersChangeValues() {
        Book book = new Book("Old", "Someone", "000");

        book.setTitle("roberthok");
        book.setAuthor("sami syla");
        book.setIsbn("999");

        assertEquals("roberthok", book.getTitle());
        assertEquals("sami syla", book.getAuthor());
        assertEquals("999", book.getIsbn());
    }
    @Test
    public void testBookConstructor() {
        Book book = new Book("Algorithms", "CLRS", "111");

        assertEquals("Algorithms", book.getTitle());
        assertEquals("CLRS", book.getAuthor());
        assertEquals("111", book.getIsbn());
        assertTrue(book.isAvailable());  // عادة الكتب تكون available افتراضياً
    }
    @Test
    void testBorrowDays() {
        Book b = new Book("T", "A", "1");
        assertEquals(28, b.getBorrowDays());
    }
    @Test
    void testOverdueFine() {
        Book b = new Book("T", "A", "1");
        assertEquals(10.0, b.getOverdueFine());
    }

}

