package com.example.library.repository;

import com.example.library.model.Book;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    @Test
    void testAddAndGetAll() {
        BookRepository repo = new BookRepository();
        repo.add(new Book("T", "A", "1"));

        assertEquals(1, repo.getAll().size());
    }

    @Test
    void testFindByIsbn() {
        BookRepository repo = new BookRepository();
        Book book = new Book("T", "A", "1");
        repo.add(book);

        Optional<Book> result = repo.findByIsbn("1");
        assertTrue(result.isPresent());
        assertEquals(book, result.get());
    }
}
