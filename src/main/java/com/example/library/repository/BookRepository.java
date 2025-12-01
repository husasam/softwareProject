package com.example.library.repository;

import com.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Simple in-memory repository for books.
 */
public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public void add(Book b) { books.add(b); }
    public List<Book> getAll() { return books; }
    public Optional<Book> findByIsbn(String isbn) {
        return books.stream().filter(b -> b.getId().equals(isbn)).findFirst();
    }
}
