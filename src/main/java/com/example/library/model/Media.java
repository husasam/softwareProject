//
//package com.example.library.model;
//
//public abstract class Media{
//    protected String title;
//    protected String id;
//    protected boolean available = true;
//
//    public Media(String title, String id){
//        this.title = title;
//        this.id = id;
//    }
//
//    public String getTitle() { return title; }
//    public String getId() { return id; }
//    public boolean isAvailable() { return available; }
//    public void setAvailable(boolean available) { this.available = available; }
//
//    public abstract int getBorrowDays();
//
//    public abstract double getOverdueFine();
//}
package com.example.library.model;

/**
 * Abstract superclass for all media items in the library.
 * Provides common fields such as title, id, and availability.
 *
 * Subclasses must implement borrowing duration and overdue fine rules.
 *
 * @author Husam
 * @version 1.0
 */
public abstract class Media {

    /** Title of the media item. */
    protected String title;

    /** Unique identifier of the media item. */
    protected String id;

    /** Availability status (true if available). */
    protected boolean available = true;

    /**
     * Creates a new media item.
     *
     * @param title the media title
     * @param id    unique identifier
     */
    public Media(String title, String id){
        this.title = title;
        this.id = id;
    }

    /** @return the media title */
    public String getTitle() { return title; }

    /** @return media ID */
    public String getId() { return id; }

    /** @return true if media is available */
    public boolean isAvailable() { return available; }

    /**
     * Sets availability status.
     *
     * @param available true if available
     */
    public void setAvailable(boolean available) { this.available = available; }

    /**
     * @return number of days the item can be borrowed
     */
    public abstract int getBorrowDays();

    /**
     * @return overdue fine amount
     */
    public abstract double getOverdueFine();
}
