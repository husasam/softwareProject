
package com.example.library.model;

import java.time.LocalDate;


public class BorrowRecord {

    private String userId;

    private Book book;

    private CD cd;

    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    
    public BorrowRecord(String userId, Book book){
        this.userId = userId;
        this.book = book;
        this.cd = null;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(book.getBorrowDays());
        this.returned = false;
    }

    public BorrowRecord(String userId, CD cd) {
        this.userId = userId;
        this.cd = cd;
        this.book = null;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(cd.getBorrowDays());
        this.returned = false;
    }

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
        if (media != null) {
            this.dueDate = borrowDate.plusDays(media.getBorrowDays());
        } else {
            this.dueDate = borrowDate;
        }
        this.returned = false;
    }

//get
    public String getUserId(){
        return userId;
    }

    public Book getBook(){
        return book;
    }

    public CD getCD(){
        return cd;
    }

    public Media getMedia() {
        if (book != null) return book;
        if (cd != null) return cd;
        return null;
    }

    public LocalDate getBorrowDate(){
        return borrowDate;
    }

    public LocalDate getDueDate(){
        return dueDate;
    }

    public boolean isReturned(){
        return returned;
    }

 //set
    public void setBorrowDate(LocalDate borrowDate){
        this.borrowDate = borrowDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }


    public boolean isOverdue(LocalDate currentDate) {
        return !returned && currentDate.isAfter(dueDate);
    }
}
