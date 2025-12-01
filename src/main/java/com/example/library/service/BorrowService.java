/*
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
*/

package com.example.library.service;

import com.example.library.model.*;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowRepository;
import com.example.library.time.SystemTimeProvider;
import com.example.library.time.TimeProvider;   
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for handling borrowing and returning of library items such as books and CDs.
 * <p>
 * This class maintains a list of borrow records and provides functionality to:
 * <ul>
 *     <li>Borrow books or CDs if the user is eligible.</li>
 *     <li>Return books or CDs.</li>
 *     <li>Check for overdue items.</li>
 *     <li>Group borrow records by user.</li>
 * </ul>
 * <p>
 * The class can work with a custom {@link TimeProvider} to allow testing with simulated dates.
 */
public class BorrowService {

    /**
     * List of all borrow records.
     */
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    /**
     * Service for accessing book-related operations.
     */
    private BookService bookService;

    /**
     * Provides current date information. Can be replaced for testing purposes.
     */
    private TimeProvider timeProvider;

    /**
     * Constructor using a {@link BookService}.
     * Uses the default {@link SystemTimeProvider}.
     *
     * @param bookService the book service to interact with library books
     */
    public BorrowService(BookService bookService) {
        this.bookService = bookService;
        this.timeProvider = new SystemTimeProvider(); 
    }

    /**
     * Constructor using a {@link BookService} and a custom {@link TimeProvider}.
     *
     * @param bookService the book service to interact with library books
     * @param tp the time provider to use for date operations
     */
    public BorrowService(BookService bookService, TimeProvider tp) {
        this.bookService = bookService;
        this.timeProvider = tp; 
    }

    /**
     * Constructor using repositories and a custom {@link TimeProvider}.
     *
     * @param bookRepo the repository for books
     * @param borrowRepo the repository for borrow records
     * @param tp the time provider to use for date operations
     */
    public BorrowService(BookRepository bookRepo, BorrowRepository borrowRepo, TimeProvider tp) {
        this.bookService = new BookService(bookRepo, borrowRepo); 
        this.timeProvider = tp;
    }

    /**
     * Allows a user to borrow a book if they have no overdue books and are eligible.
     *
     * @param user the user borrowing the book
     * @param isbn the ISBN of the book to borrow
     * @return true if the borrow operation was successful, false otherwise
     */
    public boolean borrowBook(User user, String isbn) {

        List<BorrowRecord> overdueBooks = getOverdueBooksByUser(user.getUserId());
        if (!overdueBooks.isEmpty() || !user.canBorrow()) {
            System.out.println("Cannot borrow: You have overdue books or unpaid fines.");
            return false;
        }

        for (Book book : bookService.getAllBooks()) {
            if (book.getIsbn().equals(isbn) && book.isAvailable()) {
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

    /**
     * Allows a user to borrow a CD if they have no overdue items and are eligible.
     *
     * @param user the user borrowing the CD
     * @param cd the CD to borrow
     * @return true if the borrow operation was successful, false otherwise
     */
    public boolean borrowCD(User user, CD cd) {

        List<BorrowRecord> overdueBooks = getOverdueBooksByUser(user.getUserId());
        if (!overdueBooks.isEmpty() || !user.canBorrow()) {
            System.out.println("Cannot borrow: You have overdue items or unpaid fines.");
            return false;
        }

        if (cd.isAvailable()) {
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

    /**
     * Returns a book by ISBN.
     *
     * @param isbn the ISBN of the book to return
     * @return true if the return operation was successful, false otherwise
     */
    public boolean returnBook(String isbn) {
        for (BorrowRecord r : borrowRecords) {
            if (r.getBook() != null && r.getBook().getIsbn().equals(isbn) && !r.isReturned()) {
                r.getBook().setAvailable(true);
                r.setReturned(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a CD by its ID.
     *
     * @param cdId the ID of the CD to return
     * @return true if the return operation was successful, false otherwise
     */
    public boolean returnCD(String cdId) {
        for (BorrowRecord r : borrowRecords) {
            if (r.getCD() != null && r.getCD().getId().equals(cdId) && !r.isReturned()) {
                r.getCD().setAvailable(true);
                r.setReturned(true);
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves all overdue borrow records (books and CDs).
     *
     * @return a list of overdue borrow records
     */
    public List<BorrowRecord> getOverdueBooks() {
        LocalDate today = timeProvider.today();
        return borrowRecords.stream()
                .filter(r -> !r.isReturned() && today.isAfter(r.getDueDate()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves overdue borrow records for a specific user.
     *
     * @param userId the user ID to filter by
     * @return a list of overdue borrow records for the user
     */
    public List<BorrowRecord> getOverdueBooksByUser(String userId) {
        return getOverdueBooks().stream()
                .filter(r -> r.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * Alias for {@link #getOverdueBooksByUser(String)}.
     *
     * @param userId the user ID to filter by
     * @return a list of overdue borrow records for the user
     */
    public List<BorrowRecord> getOverdueByUser(String userId) {
        return getOverdueBooksByUser(userId);
    }

    /**
     * Retrieves all borrow records (returned and unreturned).
     *
     * @return a list of all borrow records
     */
    public List<BorrowRecord> getAllRecords() {
        return borrowRecords;
    }

    /**
     * Groups borrow records by user ID.
     *
     * @return a map of user IDs to lists of borrow records
     */
    public Map<String, List<BorrowRecord>> getBorrowedBooksByUser() {
        return borrowRecords.stream()
                .collect(Collectors.groupingBy(BorrowRecord::getUserId));
    }

    /**
     * Checks if a borrow record is overdue on a given date.
     *
     * @param record the borrow record to check
     * @param currentDate the date to compare against
     * @return true if the record is overdue, false otherwise
     */
    public boolean isOverdue(BorrowRecord record, LocalDate currentDate) {
        return !record.isReturned() && currentDate.isAfter(record.getDueDate());
    }
}
