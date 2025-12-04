//
//package com.example.library.model;
//
//public class Book extends Media{
//
//    private String author;
//    private String isbn;
//
//    public Book(String title, String author, String isbn){
//        super(title, isbn);   
//        this.author = author;
//        this.isbn = isbn;  }
//
//    public String getAuthor(){ return author; }
//    public void setAuthor(String author) { this.author = author; }
//    
//    
//    
//    public void setTitle(String title){
//    	this.title = title; }
//    
//    
//    
//    public String getIsbn(){ return isbn; }
//    public void setIsbn(String isbn){ this.isbn = isbn; }
//
//    @Override
//    public int getBorrowDays(){
//        return 28;  // book28day
//    }
//
//    @Override
//    public double getOverdueFine(){
//        return 10.0; // 10 garameh
//    }
//}
package com.example.library.model;

/**
 * Represents a book in the library system.
 * Extends {@link Media} and adds author and ISBN fields.
 *
 * @author Husam
 * @version 1.0
 */
public class Book extends Media {

    /** The author of the book. */
    private String author;

    /** The ISBN identifier of the book. */
    private String isbn;

    /**
     * Creates a new Book.
     *
     * @param title  the title of the book
     * @param author the author name
     * @param isbn   the ISBN identifier
     */
    public Book(String title, String author, String isbn){
        super(title, isbn);
        this.author = author;
        this.isbn = isbn;
    }

    /** @return the author name */
    public String getAuthor(){ return author; }

    /**
     * Sets the book author.
     *
     * @param author new author name
     */
    public void setAuthor(String author) { this.author = author; }

    /**
     * Sets the book title.
     *
     * @param title new book title
     */
    public void setTitle(String title){ this.title = title; }

    /** @return the book ISBN */
    public String getIsbn(){ return isbn; }

    /**
     * Sets the ISBN.
     *
     * @param isbn new ISBN
     */
    public void setIsbn(String isbn){ this.isbn = isbn; }

    /** @return number of days allowed to borrow (28 days) */
    @Override
    public int getBorrowDays(){ return 28; }

    /** @return overdue fine for books (10.0) */
    @Override
    public double getOverdueFine(){ return 10.0; }
}
