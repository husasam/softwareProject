
package com.example.library.model;

import java.time.LocalDate;

/**
 * Represents a borrowing record for a book or a CD.
 * Stores borrow date, due date, and return status.
 *
 * Each record belongs to a specific user and exactly one media item.
 * Supports checking overdue status based on current date.
 *
 * @author Husam
 * @version 1.0
 */
public class BorrowRecord {

    /** ID of the user who borrowed the item. */
    private String userId;

    /** Book being borrowed (null if CD is borrowed). */
    private Book book;

    /** CD being borrowed (null if Book is borrowed). */
    private CD cd;

    /** Date when the item was borrowed. */
    private LocalDate borrowDate;

    /** The date when the item must be returned. */
    private LocalDate dueDate;

    /** Return status of the borrowed item. */
    private boolean returned;

    /**
     * Creates a BorrowRecord for a book.
     *
     * @param userId the user borrowing the item
     * @param book   the borrowed book
     */
    public BorrowRecord(String userId, Book book){
        this.userId = userId;
        this.book = book;
        this.cd = null;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(book.getBorrowDays());
        this.returned = false;
    }

    /**
     * Creates a BorrowRecord for a CD.
     *
     * @param userId the user borrowing the item
     * @param cd     the borrowed CD
     */
    public BorrowRecord(String userId, CD cd) {
        this.userId = userId;
        this.cd = cd;
        this.book = null;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(cd.getBorrowDays());
        this.returned = false;
    }

    /**
     * Creates a BorrowRecord for any media type.
     *
     * @param userId     the user ID
     * @param media      the borrowed media item
     * @param borrowDate the date of borrowing
     */
    public BorrowRecord(String userId, Media media, LocalDate borrowDate){
        this.userId = userId;

        if (media instanceof Book) {
            this.book = (Book) media;
            this.cd = null;
        } else if (media instanceof CD) {
            this.cd = (CD) media;
            this.book = null;
        } else {
            this.book = null;
            this.cd = null;
        }

        this.borrowDate = borrowDate;

        if (media != null)
            this.dueDate = borrowDate.plusDays(media.getBorrowDays());
        else
            this.dueDate = borrowDate;

        this.returned = false;
    }

    /** @return user ID */
    public String getUserId(){ return userId; }

    /** @return the book (or null) */
    public Book getBook(){ return book; }

    /** @return the CD (or null) */
    public CD getCD(){ return cd; }

    /**
     * @return the borrowed media (Book or CD), or null
     */
    public Media getMedia() {
        if (book != null) return book;
        if (cd != null) return cd;
        return null;
    }

    /** @return borrow date */
    public LocalDate getBorrowDate(){ return borrowDate; }

    /** @return due date */
    public LocalDate getDueDate(){ return dueDate; }

    /** @return true if item is returned */
    public boolean isReturned(){ return returned; }

    /** @param borrowDate new borrow date */
    public void setBorrowDate(LocalDate borrowDate){ this.borrowDate = borrowDate; }

    /** @param dueDate new due date */
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    /** @param returned updated return status */
    public void setReturned(boolean returned) { this.returned = returned; }

    /**
     * Checks if the borrowed media is overdue.
     *
     * @param currentDate today's date
     * @return true if overdue and not returned
     */
    public boolean isOverdue(LocalDate currentDate) {
        return !returned && currentDate.isAfter(dueDate);
    }
}
