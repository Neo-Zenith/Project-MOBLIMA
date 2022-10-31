package controller;

import model.Cineplex;
import model.Cinema;
import database.Database;
import handler.DatabaseHandler;

import java.util.ArrayList;

public class CineplexManager {
    
    public CineplexManager() {}

    /**
     * Method to instantiate a cineplex instance and save to database
     * @param cineplexName  name of the cineplex
     * @param numOfCinemas  number of cinemas for a cineplex
     * @param cinemas   ArrayList of {@link Cinema} objects
     */
    public static void createCineplex(String cineplexName, int numOfCinemas, ArrayList <Cinema> cinemas) {
        String UUID = String.format("CP%03d", DatabaseHandler.generateUUID(Database.CINEPLEX));
        Cineplex cineplex = new Cineplex(UUID, cineplexName, numOfCinemas, cinemas);
        DatabaseManager.saveUpdateToDatabase(UUID, cineplex, Database.CINEPLEX);
    }
}
