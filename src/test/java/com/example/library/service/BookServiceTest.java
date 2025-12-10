
package com.example.library.service;

import com.example.library.model.Book;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

  
    @Test
    void testAddAndSearchBook() {
        BookService service = new BookService();
        service.addBook("Java concept", "husam darwazeh", "12345");

        assertEquals(1, service.searchBook("Java").size());
        assertEquals(1, service.searchBook("12345").size());
    }

    @Test
    void testAddBookWithInvalidData() {
        BookService service = new BookService();
        assertThrows(IllegalArgumentException.class, () -> {
            service.addBook("", "Author", "123");
        });
    }

  
    @Test
    void testSearchBookNotFound() {
        BookService service = new BookService();
        service.addBook("Algorithms", "CLRS", "999");

        assertTrue(service.searchBook("xyz").isEmpty());
        assertTrue(service.searchBook("000").isEmpty());
    }

    @Test
    void testSearchCaseInsensitive() {
        BookService service = new BookService();
        service.addBook("Data Structures", "Mark Smith", "ABC12");

        assertEquals(1, service.searchBook("data").size());
        assertEquals(1, service.searchBook("mark").size());
        assertEquals(1, service.searchBook("abc12").size());
    }

    @Test
    void testGetAllBooks() {
        BookService service = new BookService();
        service.addBook("OOP", "Robert", "111");
        service.addBook("Networks", "Andrew", "222");

        List<Book> all = service.getAllBooks();

        assertEquals(2, all.size());
        assertEquals("OOP", all.get(0).getTitle());
        assertEquals("Networks", all.get(1).getTitle());
    }

    @Test
    void testAddMultipleBooks() {
        BookService service = new BookService();
        service.addBook("Book A", "A", "1");
        service.addBook("Book B", "B", "2");
        service.addBook("Book C", "C", "3");

        assertEquals(3, service.getAllBooks().size());
    }

 
    @Test
    void testConstructorWithBookRepository() {
        Book book1 = new Book("T1", "A1", "ID1");
        Book book2 = new Book("T2", "A2", "ID2");

        BookRepository repo = new BookRepository() {
            @Override
            public List<Book> getAll() {
                return Arrays.asList(book1, book2);
            }
        };

        BookService service = new BookService(repo);

        assertEquals(2, service.getAllBooks().size());
    }

    @Test
    void testConstructorWithBookAndBorrowRepository() {
    	BookRepository repo = new BookRepository() {
    	    @Override
    	    public List<Book> getAll() {
    	        return Collections.singletonList(
    	                new Book("RepoBook", "RepoAuthor", "R1")
    	        );
    	    }
    	};


        BorrowRepository borrowRepo = new BorrowRepository() {}; // dummy mock

        BookService service = new BookService(repo, borrowRepo);
        assertEquals(1, service.getAllBooks().size());
    }


    @Test
    void testBookBorrowDays() {
        Book b = new Book("X", "Y", "Z");
        assertEquals(28, b.getBorrowDays());
    }

    @Test
    void testBookFine() {
        Book b = new Book("X", "Y", "Z");
        assertEquals(10.0, b.getOverdueFine());
    }

    @Test
    void testBookSetters() {
        Book b = new Book("mesk", "sameh", "ID1");

        b.setTitle("hala");
        b.setAuthor("sami");
        b.setIsbn("ID2");

        assertEquals("hala", b.getTitle());
        assertEquals("sami", b.getAuthor());
        assertEquals("ID2", b.getIsbn());
    }
}
