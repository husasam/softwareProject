package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BorrowService{
    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    private BookService bookService;

    public BorrowService(BookService bookService){
        this.bookService = bookService;
    }

    //borrowbook
    public boolean borrowBook(String userId, String isbn){
        for (Book book : bookService.getAllBooks()){
            if (book.getIsbn().equals(isbn) && book.isAvailable()){
                book.setAvailable(false);
                borrowRecords.add(new BorrowRecord(userId, book));
                return true;
            } }
        return false;
    }

// returnbook  
    public boolean returnBook(String isbn){
    
        for (BorrowRecord record : borrowRecords){
            if (record.getBook().getIsbn().equals(isbn) && !record.isReturned()){
                record.getBook().setAvailable(true);
                record.setReturned(true);
                return true;
            }
        }
        return false;
    }
//detect
    public List<BorrowRecord> getOverdueBooks(){
        LocalDate today = LocalDate.now();
        return borrowRecords.stream()
                .filter(r -> !r.isReturned() && today.isAfter(r.getDueDate()))
                .collect(Collectors.toList());
    }
 public List<BorrowRecord> getAllRecords(){
        return borrowRecords;
    }
}
