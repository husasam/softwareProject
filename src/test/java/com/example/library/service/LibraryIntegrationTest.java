package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibraryIntegrationTest {

    //  Sprint 3: Reminder Email Tests ----------
    @Test
    void testSendOverdueReminderEmails() {
        BookService bookService = new BookService();
        bookService.addBook("Clean Code", "sami ", "111");
        bookService.addBook("Java Design", "hadi ", "222");

        BorrowService borrowService = new BorrowService(bookService);
        BorrowRecord record1 = new BorrowRecord("user1", bookService.getAllBooks().get(0));
        record1.setDueDate(LocalDate.now().minusDays(5)); // Overdue
        borrowService.getAllRecords().add(record1);

        BorrowRecord record2 = new BorrowRecord("user2", bookService.getAllBooks().get(1));
        record2.setDueDate(LocalDate.now().plusDays(10)); // Not overdue
        borrowService.getAllRecords().add(record2);

        MockEmailServer emailServer = new MockEmailServer();
        ReminderService reminderService = new ReminderService(borrowService, emailServer);

        Map<String, String> userEmails = new HashMap<>();
        userEmails.put("user1", "user1@test.com");
        userEmails.put("user2", "user2@test.com");

        reminderService.sendOverdueReminders(userEmails, LocalDate.now());

        // user1 should receive an email, user2 should not
        assertEquals(1, emailServer.getSentEmails().size());
        assertTrue(emailServer.getSentEmails().get("user1@test.com")
                .contains("1 overdue book(s)"));
    }

    // Sprint 4: Borrow Restrictions Tests ----------
    @Test
    void testCannotBorrowIfHasFine() {
        BookService bookService = new BookService();
        bookService.addBook("Java Basics", "John Doe", "123");

        User user = new User("user1");
        user.addFine(10.0); // has fine

        BorrowService borrowService = new BorrowService(bookService);
        boolean success = borrowService.borrowBook(user, "123");
        assertFalse(success, "User with fine should not be able to borrow");
    }

    @Test
    void testCannotBorrowIfOverdue() {
        BookService bookService = new BookService();
        bookService.addBook("Algorithms", "Cormen", "999");

        BorrowService borrowService = new BorrowService(bookService);
        User user = new User("user2");

        BorrowRecord overdue = new BorrowRecord(user.getUserId(), bookService.getAllBooks().get(0));
        overdue.setDueDate(LocalDate.now().minusDays(3)); // overdue
        borrowService.getAllRecords().add(overdue);

        boolean success = borrowService.borrowBook(user, "999");
        assertFalse(success, "User with overdue book should not borrow another");
    }

    @Test
    void testBorrowAllowedIfNoFineAndNoOverdue() {
        BookService bookService = new BookService();
        bookService.addBook("AI Revolution", "Husam Darwazeh", "777");

        BorrowService borrowService = new BorrowService(bookService);
        User user = new User("user3");

        boolean success = borrowService.borrowBook(user, "777");
        assertTrue(success, "User with no fines/overdues can borrow");
    }

    //  Sprint 4: Unregister User Tests ----------
    @Test
    void testAdminCanUnregisterUserWithoutFineOrOverdue() {
        AdminService admin = new AdminService();
        admin.login("admin", "1234");

        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService(bookService);
        UserService userService = new UserService(borrowService, admin);

        userService.registerUser("user1");

        boolean result = userService.unregisterUser("user1");
        assertTrue(result, "Admin should be able to unregister clean user");
    }

    @Test
    void testCannotUnregisterUserWithFine() {
        AdminService admin = new AdminService();
        admin.login("admin", "1234");

        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService(bookService);
        UserService userService = new UserService(borrowService, admin);

        User user = new User("user2");
        user.addFine(15.0);
        userService.getAllUsers().add(user);

        boolean result = userService.unregisterUser("user2");
        assertFalse(result, "User with unpaid fine cannot be unregistered");
    }

    @Test
    void testCannotUnregisterUserWithOverdueBook() {
        AdminService admin = new AdminService();
        admin.login("admin", "1234");

        BookService bookService = new BookService();
        bookService.addBook("Database Systems", "Elmasri", "333");

        BorrowService borrowService = new BorrowService(bookService);
        UserService userService = new UserService(borrowService, admin);

        User user = new User("user3");
        userService.getAllUsers().add(user);

        BorrowRecord record = new BorrowRecord(user.getUserId(), bookService.getAllBooks().get(0));
        record.setDueDate(LocalDate.now().minusDays(2)); // overdue
        borrowService.getAllRecords().add(record);

        boolean result = userService.unregisterUser("user3");
        assertFalse(result, "User with overdue book cannot be unregistered");
    }

    @Test
    void testOnlyAdminCanUnregisterUser() {
        AdminService admin = new AdminService(); // not logged in

        BookService bookService = new BookService();
        BorrowService borrowService = new BorrowService(bookService);
        UserService userService = new UserService(borrowService, admin);

        userService.registerUser("user4");

        boolean result = userService.unregisterUser("user4");
        assertFalse(result, "Non-logged admin cannot unregister users");
    }
}
