package com.example.library.service;

import com.example.library.model.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookService {
    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, String isbn){
        if (title == null || author == null || isbn == null ||
            title.isEmpty() || author.isEmpty() || isbn.isEmpty()){
            throw new IllegalArgumentException("Book information cannot be empty");
        }
        books.add(new Book(title, author, isbn));}


    public List<Book> searchBook(String keyword){
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                                book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                                book.getIsbn().equalsIgnoreCase(keyword))
                .collect(Collectors.toList());
    }

    public List<Book> getAllBooks(){
        return books;
    }
    
}
