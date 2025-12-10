
package com.example.library.model;

/**
 * Represents a CD in the library system.
 * Extends {@link Media} and adds artist information.
 *
 * CDs can be borrowed for 7 days and have a higher overdue fine.
 *
 * @author Husam
 * @version 1.0
 */
public class CD extends Media {

    /** The artist of the CD. */
    private String artist;

    /**
     * Creates a new CD.
     *
     * @param title  the CD title
     * @param artist the artist name
     * @param id     the CD ID
     */
    public CD(String title, String artist, String id) {
        super(title, id);
        this.artist = artist;
    }

    /** @return artist name */
    public String getArtist() { return artist; }

    /**
     * Sets new artist name.
     *
     * @param artist updated artist name
     */
    public void setArtist(String artist) { this.artist = artist; }

    /** @return number of days allowed to borrow (7 days) */
    @Override
    public int getBorrowDays() { return 7; }

    /** @return overdue fine for CDs (20.0) */
    @Override
    public double getOverdueFine() { return 20.0; }
}
