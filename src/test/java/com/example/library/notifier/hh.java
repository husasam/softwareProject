package com.example.library.notifier;

import com.example.library.LibraryMenu;
import com.example.library.model.Book;
import com.example.library.model.CD;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Collections;

import static org.mockito.Mockito.*;

class LibraryMenuTest {

    @Test
    void testMenuViewBooks() {

        BookService bookService = mock(BookService.class);
        BorrowService borrowService = mock(BorrowService.class);

        Book b = new Book("Title1", "111", "Author1");
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(b));

        User user = new User("u1");

        String fakeInput = "1\n0\n";
        ByteArrayInputStream input = new ByteArrayInputStream(fakeInput.getBytes());

        CD c1 = new CD("c1", "CD One", "Singer1");
        CD c2 = new CD("c2", "CD Two", "Singer2");

        LibraryMenu menu = new LibraryMenu(bookService, borrowService, user);
        menu.start(c1, c2);

        verify(bookService, atLeastOnce()).getAllBooks();
    }

    @Test
    void testMenuBorrowBook() {

        BookService bookService = mock(BookService.class);
        BorrowService borrowService = mock(BorrowService.class);

        User user = new User("u1");

        when(borrowService.borrowBook(user, "111")).thenReturn(true);

        String fakeInput = "2\n111\n0\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        CD c1 = new CD("c1", "CD One", "Singer1");
        CD c2 = new CD("c2", "CD Two", "Singer2");

        LibraryMenu menu = new LibraryMenu(bookService, borrowService, user);
        menu.start(c1, c2);

        verify(borrowService).borrowBook(user, "111");
    }

    @Test
    void testMenuBorrowCD() {

        BookService bookService = mock(BookService.class);
        BorrowService borrowService = mock(BorrowService.class);
        User user = new User("u1");

        CD c1 = new CD("c1", "CD One", "Singer1");
        CD c2 = new CD("c2", "CD Two", "Singer2");

        when(borrowService.borrowCD(user, c1)).thenReturn(true);

        String fakeInput = "4\nc1\n0\n";
        System.setIn(new ByteArrayInputStream(fakeInput.getBytes()));

        LibraryMenu menu = new LibraryMenu(bookService, borrowService, user);
        menu.start(c1, c2);

        verify(borrowService).borrowCD(user, c1);
    }
}
