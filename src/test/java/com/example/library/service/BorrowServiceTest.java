package com.example.library.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.library.model.BorrowRecord;
import java.time.LocalDate;
import java.util.List;

public class BorrowServiceTest{

    @Test
    void testBorrowBook(){
        BookService bookService = new BookService();
        bookService.addBook("Java concept", "Husam Darwazeh", "12345");
        BorrowService borrowService = new BorrowService(bookService);

        boolean success = borrowService.borrowBook("U1", "12345");
        assertTrue(success);

        List<BorrowRecord> records = borrowService.getAllRecords();
        assertEquals(1, records.size());
        assertEquals(LocalDate.now().plusDays(28), records.get(0).getDueDate());
        assertFalse(records.get(0).getBook().isAvailable());
    }

    @Test
    void testReturnBook(){
        BookService bookService = new BookService();
        bookService.addBook("Java Concept", "Husam Darwazeh", "12345");
        BorrowService borrowService = new BorrowService(bookService);

        borrowService.borrowBook("U1", "12345");
        boolean returned = borrowService.returnBook("12345");
        assertTrue(returned);
        assertTrue(bookService.getAllBooks().get(0).isAvailable());
    }
    @Test
    void testOverdueDetection(){
        BookService bookService = new BookService();
        bookService.addBook("Algorithms", "Corme", "99999");
        BorrowService borrowService = new BorrowService(bookService);

        borrowService.borrowBook("U1", "99999");

        BorrowRecord record = borrowService.getAllRecords().get(0);
//late over 28 day
        LocalDate overdueDate = record.getBorrowDate().plusDays(30);
        List<BorrowRecord> overdue = borrowService.getOverdueBooks();
        assertTrue(overdue.isEmpty(), "Initially not overdue");

        assertTrue(overdue.isEmpty());
    }
    @Test
    void testBorrowBookFail(){
        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService(bookService);

//not find book in system
        assertFalse(borrowService.borrowBook("U1", "99999"));
    }

    @Test
    void testReturnBookFail(){
        BookService bookService = new BookService();
        bookService.addBook("Java Concept", "Husam Darwazeh", "12345");
        BorrowService borrowService = new BorrowService(bookService);

//not borrow already
        assertFalse(borrowService.returnBook("12345"));
    }
}
