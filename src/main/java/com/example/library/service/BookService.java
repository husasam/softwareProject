/*
package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {

    private List<Book> books = new ArrayList<>();

    public BookService() {
        this.books = new ArrayList<>();
    }

    public BookService(BookRepository bookRepo) {
        this.books = bookRepo.getAll();
    }

    public BookService(BookRepository bookRepo, BorrowRepository borrowRepo) {
        this.books = bookRepo.getAll();
    }

    public void addBook(String title, String author, String isbn){
        if (title == null || author == null || isbn == null ||
            title.isEmpty() || author.isEmpty() || isbn.isEmpty())
        {
            throw new IllegalArgumentException("Book information cannot be empty");
        }

        books.add(new Book(title, author, isbn));
    }

    public List<Book> searchBook(String keyword){
        return books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getIsbn().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks(){
        return books;
    }
}
*/
package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service responsible for managing book operations such as
 * adding, searching, and retrieving books.
 *
 * @author 
 * @version 1.0
 */
public class BookService {

    /** Internal list storing all books. */
    private List<Book> books = new ArrayList<>();

    /**
     * Default constructor creates an empty list of books.
     */
    public BookService() {
        this.books = new ArrayList<>();
    }

    /**
     * Initializes BookService using a repository.
     *
     * @param bookRepo Repository containing stored books
     */
    public BookService(BookRepository bookRepo) {
        this.books = bookRepo.getAll();
    }

    /**
     * Initializes BookService using repositories.
     *
     * @param bookRepo Book repository
     * @param borrowRepo Borrow repository (not used directly)
     */
    public BookService(BookRepository bookRepo, BorrowRepository borrowRepo) {
        this.books = bookRepo.getAll();
    }

    /**
     * Adds a new book to the system.
     *
     * @param title Book title
     * @param author Book author
     * @param isbn Book ISBN
     * @throws IllegalArgumentException if any field is empty
     */
    public void addBook(String title, String author, String isbn){
        if (title == null || author == null || isbn == null ||
            title.isEmpty() || author.isEmpty() || isbn.isEmpty())
        {
            throw new IllegalArgumentException("Book information cannot be empty");
        }

        books.add(new Book(title, author, isbn));
    }

    /**
     * Searches for books by keyword in title, author, or ISBN.
     *
     * @param keyword Search keyword
     * @return List of matching books
     */
    public List<Book> searchBook(String keyword){
        return books.stream()
                .filter(book ->
                        book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                        book.getIsbn().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns all books in the library.
     *
     * @return List of all books
     */
    public List<Book> getAllBooks(){
        return books;
    }
}
