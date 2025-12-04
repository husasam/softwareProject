package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.*;
import com.example.library.time.TimeProvider;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class BorrowServiceTimeTest {

    @Test
    public void testBorrowAndOverdueWithMockTime() {
        BookRepository bookRepo = new BookRepository();
        BorrowRepository borrowRepo = new BorrowRepository();
        Book book = new Book("T","A","ISBN1");
        bookRepo.add(book);

        TimeProvider tp = mock(TimeProvider.class);
        when(tp.today()).thenReturn(LocalDate.of(2025,1,1)); // borrow date

        BorrowService svc = new BorrowService(bookRepo, borrowRepo, tp);
        User u = new User("u1");

        assertTrue(svc.borrowBook(u, "ISBN1"));
        assertFalse(book.isAvailable());

        when(tp.today()).thenReturn(LocalDate.of(2025,2,10)); // after 28 days
        assertFalse(svc.getOverdueByUser("u1").isEmpty());
    }
}
