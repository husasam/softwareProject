package com.example.library.service;

import com.example.library.model.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class MediaExtensionTest {

    @Test
    void testBorrowCDDueDate() {
        CD cd = new CD("Top Hits", "Amr Diab", "CD001");
        BorrowRecord record = new BorrowRecord("user1", cd);
        assertEquals(LocalDate.now().plusDays(7), record.getDueDate());
    }

    @Test
    void testCDOverdueFine() {
        CD cd = new CD("Old Album", "Fairouz", "CD002");
        BorrowRecord record = new BorrowRecord("user1", cd);
        record.setReturned(false);
        record.setReturned(false);

        BorrowService borrowService = new BorrowService(new BookService());
        double fine = cd.getOverdueFine();

        assertEquals(20.0, fine, "CD fine should be 20 NIS");
    }

    @Test
    void testMixedMediaFineCalculation() {
        Book book = new Book("Clean Code", "Robert Martin", "B001");
        CD cd = new CD("Top Hits", "Amr Diab", "CD001");

        BorrowRecord r1 = new BorrowRecord("user1", book);
        BorrowRecord r2 = new BorrowRecord("user1", cd);
        r1.setReturned(false);
        r2.setReturned(false);

        r1.setDueDate(LocalDate.now().minusDays(3)); // overdue
        r2.setDueDate(LocalDate.now().minusDays(2)); // overdue

        BorrowService borrowService = new BorrowService(new BookService());
        double fineBook = book.getOverdueFine(); // 10
        double fineCD = cd.getOverdueFine(); // 20

        assertEquals(30.0, fineBook + fineCD, "Total fine should be 30 NIS across media types");
    }
}
