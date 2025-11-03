package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookServiceTest{

    @Test
    void testAddAndSearchBook(){
        BookService service = new BookService();
        service.addBook("Java concept", "husam darwazeh", "12345");
        assertEquals(1, service.searchBook("Java").size());
        assertEquals(1, service.searchBook("12345").size());
    }
    @Test
    void testAddBookWithInvalidData(){
        BookService service = new BookService();
        assertThrows(IllegalArgumentException.class, () ->{
            service.addBook("", "Author", "123");
        });
    }

}
