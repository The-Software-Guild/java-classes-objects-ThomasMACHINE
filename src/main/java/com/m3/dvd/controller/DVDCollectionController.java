package com.m3.dvd.controller;

import com.m3.dvd.dao.DVDCollection;
import com.m3.dvd.dto.DVD;
import com.m3.dvd.ui.DVDView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

public class DVDCollectionController {

    private DVDCollection dvdCollection;
    private DVDView view;

    public DVDCollectionController(DVDCollection dvdCollection, DVDView view) {
        this.dvdCollection = dvdCollection;
        this.view = view;
    }

    public void run() {
        while(true){
            int choice = view.getMainMenuSelection();
            switch(choice){
                case 1:
                    listAllDVDs();
                    break;
                case 2: {
                    addDVD();
                    break;
                }
                case 3: {
                    selectDVD();
                    break;
                }
                case 4:
                    removeDVD();
                    break;
                case 5:
                    loadDVDs();
                    break;
                case 6:
                    saveDVDs();
                    break;
                case 7:
                    System.exit(1);
                    break;
                default:
                    view.displayCommandError();
            }
        }
    }

    public void runDVDSelection(DVD dvd){
        while(true){
            int choice = view.getDVDMenuSelection();
            switch(choice){
                case 1:
                    view.displayFullDVDInfo(dvd);
                    break;
                case 2:
                    changeTitle(dvd);
                    break;
                case 3:
                    changeReleaseDate(dvd);
                    break;
                case 4:
                    changeDirectorName(dvd);
                    break;
                case 5:
                    changeStudioName(dvd);
                    break;
                case 6:
                    changeMPAARating(dvd);
                    break;
                case 7:
                    changeUserRating(dvd);
                    break;
                case 8:
                    changeNote(dvd);
                    break;
                case 9:
                    return;
                default:
                    view.displayCommandError();
            }
        }
    }

    private void removeDVD() {
        view.displayRemoveDVDBanner();
        String title = view.getDVDTitleChoice();
        dvdCollection.deleteDVD(title);
    }

    private void selectDVD() {
        view.displayDVDSelectionBanner();
        String title = view.getDVDTitleChoice();
        DVD dvd = dvdCollection.getDVD(title);
        if(dvd != null){
            runDVDSelection(dvd);
            return;
        }
        view.displayCommandError();
    }

    private void addDVD() {
        view.displayAddDVDBanner();

        String title;
        do {
            title = view.getDVDTitleChoice();
        } while (!dvdCollection.isUnique(title));

        DVD dvd = view.createNewDVD(title);
        dvdCollection.addDVD(dvd);
    }

    private void listAllDVDs() {
        view.displayAllDVDsBanner();
        DVD[] dvdArray = dvdCollection.listAll();
        view.displaySimpleDVDInfo(dvdArray);
    }


    private void loadDVDs(){
        view.displayLoadDVDBanner();
        try {
            dvdCollection.loadDVDFromFile();
            view.displaySuccess("Loading DVDs");
        }catch(FileNotFoundException e){
            view.displayFileNotFound();
        }
    }

    private void saveDVDs(){
        view.displaySaveDVDBanner();
        try {
            dvdCollection.saveDVDToFile();
            view.displaySuccess("Saving DVDs");
        }catch(IOException e){
            view.displaySaveError();
        }
    }

    private void changeUserRating(DVD dvd) {
        int newRating = view.getUserRating();
        dvdCollection.editUserRating(dvd, newRating);
    }

    private void changeNote(DVD dvd) {
        String newNote = view.getNote();
        dvdCollection.editNote(dvd, newNote);
    }

    private void changeStudioName(DVD dvd) {
        String newStudio = view.getStudio();
        dvdCollection.editStudio(dvd, newStudio);
    }

    private void changeDirectorName(DVD dvd) {
        String newDirectorName = view.getDirectorName();
        dvdCollection.editDirectorName(dvd, newDirectorName);
    }

    private void changeMPAARating(DVD dvd) {
        int newRating = view.getMPAARating();
        dvdCollection.editMPAARating(dvd, newRating);
    }

    private void changeReleaseDate(DVD dvd) {
        LocalDate newDate = view.getReleaseDate();
        dvdCollection.editReleaseDate(dvd, newDate);
    }

    private void changeTitle(DVD dvd){
        view.displayChangeTitleBanner();
        String newTitle = view.getDVDTitleChoice();
        if(dvdCollection.isUnique(newTitle)){
            dvdCollection.deleteDVD(dvd.getTitle());
            dvd.setTitle(newTitle);
            dvdCollection.addDVD(dvd);
        }
        else{
            view.displayNotUniqueTitle();
        }
    }
}
