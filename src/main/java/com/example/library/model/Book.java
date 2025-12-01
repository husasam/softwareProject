
package com.example.library.model;

public class Book extends Media{

    private String author;
    private String isbn;

    public Book(String title, String author, String isbn){
        super(title, isbn);   
        this.author = author;
        this.isbn = isbn;  }

    public String getAuthor(){ return author; }
    public void setAuthor(String author) { this.author = author; }
    
    
    
    public void setTitle(String title){
    	this.title = title; }
    
    
    
    public String getIsbn(){ return isbn; }
    public void setIsbn(String isbn){ this.isbn = isbn; }

    @Override
    public int getBorrowDays(){
        return 28;  // book28day
    }

    @Override
    public double getOverdueFine(){
        return 10.0; // 10 garameh
    }
}
