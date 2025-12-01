
package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
import com.example.library.time.SystemTimeProvider;
import com.example.library.time.TimeProvider;   
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class BorrowService{

    private List<BorrowRecord> borrowRecords = new ArrayList<>();
    private BookService bookService;

    private TimeProvider timeProvider;

    public BorrowService(BookService bookService){
        this.bookService = bookService;
        this.timeProvider = new SystemTimeProvider(); 
    }

    public BorrowService(BookService bookService, TimeProvider tp){
        this.bookService = bookService;
        this.timeProvider = tp; 
    }
    public BorrowService(BookRepository bookRepo, BorrowRepository borrowRepo, TimeProvider tp){
        this.bookService = new BookService(bookRepo, borrowRepo); 
        this.timeProvider = tp;
    }

    public boolean borrowBook(User user, String isbn){

        List<BorrowRecord> overdueBooks = getOverdueBooksByUser(user.getUserId());
        if (!overdueBooks.isEmpty() || !user.canBorrow()) {
            System.out.println("Cannot borrow: You have overdue books or unpaid fines.");
            return false;
        }

        // findbook
        for(Book book : bookService.getAllBooks()){
            if(book.getIsbn().equals(isbn) && book.isAvailable()){

                book.setAvailable(false);

                BorrowRecord record = new BorrowRecord(user.getUserId(), book);

                LocalDate today = timeProvider.today();
                record.setBorrowDate(today);
                record.setDueDate(today.plusDays(book.getBorrowDays()));

                borrowRecords.add(record);
                return true;
            }
        }

        return false;
    }

    public boolean borrowCD(User user, CD cd){

        List<BorrowRecord> overdueBooks = getOverdueBooksByUser(user.getUserId());
        if(!overdueBooks.isEmpty() || !user.canBorrow()) {
            System.out.println("Cannot borrow: You have overdue items or unpaid fines.");
            return false;
        }

        if(cd.isAvailable()){
            cd.setAvailable(false);

            BorrowRecord record = new BorrowRecord(user.getUserId(), cd);

            LocalDate today = timeProvider.today();
            record.setBorrowDate(today);
            record.setDueDate(today.plusDays(cd.getBorrowDays()));

            borrowRecords.add(record);
            return true;
        }

        return false;
    }

    //  Return by ISBN (Books only)
    public boolean returnBook(String isbn){
        for (BorrowRecord r : borrowRecords){
            if (r.getBook() != null 
                    && r.getBook().getIsbn().equals(isbn) 
                    && !r.isReturned()) {

                r.getBook().setAvailable(true);
                r.setReturned(true);
                return true;
            }
        }
        return false;
    }

    // return CD
    public boolean returnCD(String cdId){
        for (BorrowRecord r : borrowRecords){
            if (r.getCD() != null 
                    && r.getCD().getId().equals(cdId)
                    && !r.isReturned()) {

                r.getCD().setAvailable(true);
                r.setReturned(true);
                return true;
            }
        }
        return false;
    }

    public List<BorrowRecord> getOverdueBooks(){

        LocalDate today = timeProvider.today();

        return borrowRecords.stream()
                .filter(r -> !r.isReturned() && today.isAfter(r.getDueDate()))
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> getOverdueBooksByUser(String userId){
        return getOverdueBooks().stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<BorrowRecord> getOverdueByUser(String userId){
        return getOverdueBooksByUser(userId);
    }

    public List<BorrowRecord> getAllRecords(){
        return borrowRecords;
    }
    public Map<String, List<BorrowRecord>> getBorrowedBooksByUser(){
        return borrowRecords.stream()
                .collect(Collectors.groupingBy(BorrowRecord::getUserId));
    }

    public boolean isOverdue(BorrowRecord record, LocalDate currentDate){
        return !record.isReturned() && currentDate.isAfter(record.getDueDate());
    }
}
