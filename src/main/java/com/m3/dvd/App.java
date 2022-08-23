package com.m3.dvd;

import com.m3.dvd.controller.DVDCollectionController;
import com.m3.dvd.dao.DVDCollection;
import com.m3.dvd.ui.*;

public class App {

    public static void main(String[] args)
    {
        DVDView view = new DVDView(new Console());
        DVDCollection dvdCollection = new DVDCollection();
        DVDCollectionController controller = new DVDCollectionController(dvdCollection, view);
        controller.run();
    }
}
