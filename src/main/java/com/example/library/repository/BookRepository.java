package com.example.library.repository;

import com.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple in-memory repository for managing books.
 * Provides basic CRUD-like functionality.
 *
 * This repository does not use a database — all data is stored in memory.
 *
 * @author Husam
 * @version 1.0
 */
public class BookRepository {

    /** Internal list of stored books. */
    private final List<Book> books = new ArrayList<>();

    /**
     * Adds a book to the repository.
     *
     * @param b the book to add
     */
    public void add(Book b) { 
        books.add(b); 
    }

    /**
     * Returns all stored books.
     *
     * @return list of all books
     */
    public List<Book> getAll() { 
        return books; 
    }

    /**
     * Finds a book by its ISBN.
     *
     * @param isbn the ISBN to search for
     * @return an Optional containing the book if found
     */
    public Optional<Book> findByIsbn(String isbn) {
        return books.stream()
                .filter(b -> b.getId().equals(isbn))
                .findFirst();
    }
}
