
package com.example.library.model;

public abstract class Media{
    protected String title;
    protected String id;
    protected boolean available = true;

    public Media(String title, String id){
        this.title = title;
        this.id = id;
    }

    public String getTitle() { return title; }
    public String getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public abstract int getBorrowDays();

    public abstract double getOverdueFine();
}
