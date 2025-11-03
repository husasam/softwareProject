package com.example.library.model;

import java.time.LocalDate;

public class BorrowRecord{
    private String userId;
    private Book book;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private boolean returned;

    public BorrowRecord(String userId, Book book){
        this.userId = userId;
        this.book = book;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(28);
        this.returned = false;
    }

    public String getUserId(){
    	return userId; 
    	}
    public Book getBook(){
    	return book; }
    public LocalDate getBorrowDate(){
    	return borrowDate; }
    public LocalDate getDueDate(){
    	return dueDate; }
    public boolean isReturned(){
    	return returned; }
    public void setReturned(boolean returned){
    	this.returned = returned; }
}
