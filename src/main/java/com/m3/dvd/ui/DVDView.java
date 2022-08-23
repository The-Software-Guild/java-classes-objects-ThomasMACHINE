package com.m3.dvd.ui;

import com.m3.dvd.dto.DVD;

import java.time.LocalDate;

public class DVDView {

    public final String[] CHOICE_MENU_MAIN = {
            "1. List all DVDs",
            "2. Add a new DVD entry",
            "3. Select DVD",
            "4. Remove a DVD",
            "5. Load DVDs",
            "6. Save DVDs",
            "7. Exit"
            };

    // TODO : Abstract choice_menus into their own class that controls their own commands
    public final String[] CHOICE_MENU_DVD = {
            "1. Get full DVD information",
            "2. Change title",
            "3. Change release date",
            "4. Change Director's name",
            "5. Change Studio name",
            "6. Change MPAA rating",
            "7. Change User Rating",
            "8. Change DVD note",
            "9. Go back to main menu"
    };

    UserIO io;

    public DVDView(UserIO io) {
        this.io = io;
    }

    public int getMainMenuSelection(){
        displayMainMenu();
        return io.readInt("Please select an item.",1, CHOICE_MENU_MAIN.length);
    }

    public int getDVDMenuSelection(){
        displayDVDMenu();
        return io.readInt("Please select an item", 1, CHOICE_MENU_DVD.length);
    }


    public void displayDVDMenu(){
        io.print("DVD selection Menu");
        for(String option : CHOICE_MENU_DVD){
            io.print(option);
        }
    }
    public void displayMainMenu(){
        io.print("Main Menu");
        for(String option : CHOICE_MENU_MAIN){
            io.print(option);
        }
    }

    public void displayAllDVDsBanner(){
        io.print("=== DVD COLLECTION ===");
    }

    public void displayFileNotFound(){
        io.print("File was not found, no DVD's were loaded");
    }

    public void displayAddDVDBanner(){
        io.print("=== ADD DVD ===");
    }
    public void displayChangeTitleBanner(){
        io.print("=== CHANGE TITLE ===");
    }

    public void displayDVDSelectionBanner(){
        io.print("=== SELECT DVD ===");
    }

    public void displayRemoveDVDBanner(){
        io.print("=== REMOVE DVD ===");
    }

    public void displayLoadDVDBanner(){
        io.print("=== LOADING DVDs ===");
    }

    public void displaySaveDVDBanner(){
        io.print("=== SAVING DVDs ===");
    }
    public void displaySuccess(String Action){
        io.print(Action + " completed successfully.");
    }
    public void displaySaveError(){
        io.print("Error while saving the DVD information, none was saved.");
    }
    public void displaySimpleDVDInfo(DVD[] dvds){
        for(DVD dvd : dvds){
            io.print("title: " + dvd.getTitle() + " | MPAA rating: " + dvd.getRatingMPAA() + " | User rating: " + dvd.getUserRating());
        }
    }

    public void displayCommandError(){ io.print("Command did not yield any results");}
    public void displayFullDVDInfo(DVD dvd){
            io.print("Title: " + dvd.getTitle() + " | MPAA rating: " + dvd.getRatingMPAA() + " | User rating: " + dvd.getUserRating() +
                    "\nDirector: " + dvd.getDirectorName() + " | Release date: " + dvd.getReleaseDate() +
                    "\nNote: " + dvd.getNote());
    }

    public String getDVDTitleChoice(){
        return io.readString("Please write the title of a DVD");
    }

    public DVD createNewDVD(String title){
        String director  = getDirectorName(),
               studio    = getStudio(),
               note      = getNote();
        int userRating   = getUserRating(),
            mpaaRating   = getMPAARating();
        LocalDate releaseDate = getReleaseDate();

        return new DVD(title, director, studio, userRating, releaseDate, mpaaRating, note);
        }

    public int getMPAARating() {
        return io.readInt("Please write the MPAA rating. (1-5)", 1, 5);
    }

    public LocalDate getReleaseDate() {
        return io.readDate("Please write release date in the format: dd/mm/yyyy");
    }

    public int getUserRating() {
        return io.readInt("Please write the user rating rating: 1-5", 1, 5);
    }

    public String getStudio() {
        return io.readString("Please write the name of the studio");
    }

    public String getDirectorName() {
        return io.readString("Please write the name of the director of the movie");
    }

    public void displayNotUniqueTitle() {
        io.print("Action failed, title was not unique");
    }

    public String getNote() {
        return io.readString("Please write the note on this DVD");
    }
}

