package com.m3.dvd.dto;

import java.time.LocalDate;

public class DVD {
    private String
            title,
            directorName,
            studio,
            note;

    private LocalDate
            releaseDate;

    private int
            ratingMPAA,
            userRating;

    public DVD(String title, String directorName, String studio, int userRating, LocalDate releaseDate, int ratingMPAA, String note) {
        setTitle(title);
        setDirectorName(directorName);
        setStudio(studio);
        setUserRating(userRating);
        setReleaseDate(releaseDate);
        setRatingMPAA(ratingMPAA);
        setNote(note);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRatingMPAA() {
        return ratingMPAA;
    }

    public void setRatingMPAA(int ratingMPAA) {
        this.ratingMPAA = ratingMPAA;
    }

    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}

