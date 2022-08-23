package com.m3.dvd.dao;

import com.m3.dvd.dto.DVD;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DVDCollection {

    public static final String FILE_NAME = "dvdcollection.txt",
                             DELIMITER = "::";

    private Map<String, DVD> dvdCollection;

    public DVDCollection(){
        dvdCollection = new HashMap<>();
    }

    public DVD[] listAll(){
        return dvdCollection.values().toArray(new DVD[dvdCollection.size()]);
    }

    public void addDVD(DVD newDVD)
            throws KeyAlreadyExistsException {
        // Throw exception if key already exists
        if(!isUnique(newDVD.getTitle()))  throw new KeyAlreadyExistsException("Title is not unique");

        dvdCollection.put(newDVD.getTitle(), newDVD);
    }

    public void deleteDVD(String title){
        dvdCollection.remove(title);
    }

    public DVD getDVD(String title){
        return dvdCollection.get(title);
    }

    public boolean isUnique(String id){
        return !dvdCollection.containsKey(id);
    }

    public void saveDVDToFile() throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(FILE_NAME));

        for(DVD dvd : dvdCollection.values()){
            out.write(marshallDVD(dvd));
        }
        out.flush();
        out.close();
    }

    public String marshallDVD(DVD dvd){
        String marshallForm = dvd.getTitle()       + DELIMITER +
                            dvd.getStudio()        + DELIMITER +
                            dvd.getDirectorName()  + DELIMITER +
                            dvd.getUserRating()    + DELIMITER +
                            dvd.getRatingMPAA()    + DELIMITER +
                            dvd.getReleaseDate().toString()  + DELIMITER +
                            dvd.getNote()          + DELIMITER +
                            "\n";
        return marshallForm;
    }

    public void loadDVDFromFile() throws FileNotFoundException {
        Scanner s = new Scanner(new BufferedReader(new FileReader(FILE_NAME)));

        while(s.hasNextLine()){
            DVD newDVD = unMarshallDVD(s.nextLine());
            dvdCollection.put(newDVD.getTitle(), newDVD);
        }
    }

    /**
     * @param line : TITLE::DIRECTOR::STUDIO::USERRATING::DATE::MPAARATING::NOTE
     */
    private DVD unMarshallDVD(String line){
        String[] splitLine = line.split(DELIMITER);
        int userRating = Integer.parseInt(splitLine[3]),
            mpaaRating = Integer.parseInt(splitLine[4]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(splitLine[5], formatter);

        return new DVD(splitLine[0], splitLine[1], splitLine[2], userRating, date, mpaaRating, splitLine[6]);
    }

    //=============== LOWER LEVEL COMMANDS ===================
    public void editTitle(DVD dvd, String newTitle){
        dvd.setTitle(newTitle);
    }

    public void editDirectorName(DVD dvd, String newName){
        dvd.setDirectorName(newName);
    }

    public void editStudio(DVD dvd, String newStudio){
        dvd.setStudio(newStudio);
    }

    public void editUserRating(DVD dvd, int newRating){
        dvd.setUserRating(newRating);
    }

    public void editMPAARating(DVD dvd, int newRating){
        dvd.setRatingMPAA(newRating);
    }

    public void editReleaseDate(DVD dvd, LocalDate newDate){
        dvd.setReleaseDate(newDate);
    }

    public void editNote(DVD dvd, String newNote) {
        dvd.setNote(newNote);
    }
}
