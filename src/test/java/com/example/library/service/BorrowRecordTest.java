package com.example.library.service;
import org.junit.jupiter.api.Test;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.CD;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class BorrowRecordTest {

    @Test
    void testBorrowBookConstructor() {
        Book b = new Book("T", "A", "1");
        BorrowRecord r = new BorrowRecord("u1", b);

        assertEquals("u1", r.getUserId());
        assertEquals(b, r.getBook());
        assertEquals(b, r.getMedia());
        assertFalse(r.isReturned());
        assertEquals(LocalDate.now().plusDays(28), r.getDueDate());
    }

    @Test
    void testBorrowCDConstructor() {
        CD c = new CD("T", "Artist", "5");
        BorrowRecord r = new BorrowRecord("u1", c);

        assertEquals(c, r.getCD());
        assertEquals(c, r.getMedia());
        assertEquals(LocalDate.now().plusDays(7), r.getDueDate());
    }

    @Test
    void testOverdue() {
        Book b = new Book("T", "A", "1");
        BorrowRecord r = new BorrowRecord("u1", b);

        r.setDueDate(LocalDate.now().minusDays(1));
        assertTrue(r.isOverdue(LocalDate.now()));
    }

    @Test
    void testNotOverdueIfReturned() {
        Book b = new Book("T", "A", "1");
        BorrowRecord r = new BorrowRecord("u1", b);
        r.setReturned(true);

        r.setDueDate(LocalDate.now().minusDays(5));
        assertFalse(r.isOverdue(LocalDate.now()));
    }
}