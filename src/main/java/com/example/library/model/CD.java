
package com.example.library.model;

public class CD extends Media {

    private String artist;

    public CD(String title, String artist, String id) {
        super(title, id);
        this.artist = artist;
    }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    @Override
    public int getBorrowDays() {
        return 7; // CD 7 day
    }

    @Override
    public double getOverdueFine() {
        return 20.0; //   cd 20 garameh 
    }
}
