package controller;

import model.Cineplex;
import model.Cinema;
import model.enums.CinemaClass;
import database.Database;
import handler.DatabaseHandler;

import java.util.ArrayList;
import java.util.Collections;

public class CineplexManager {
    
    public CineplexManager() {}

    /**
     * Method to instantiate a cineplex instance and save to database
     * @param cineplexName  name of the cineplex
     * @param numOfCinemas  number of cinemas for a cineplex
     * @param cinemas   ArrayList of {@link Cinema} objects
     */
    public static void createCineplex(String cineplexName, int numOfCinemas, ArrayList <Cinema> cinemas, String cineplexLocation) {
        String UUID = String.format("CP%04d", DatabaseHandler.generateUUID(Database.CINEPLEX));
        Cineplex cineplex = new Cineplex(UUID, cineplexName, numOfCinemas, cinemas, cineplexLocation);
        DatabaseManager.saveUpdateToDatabase(UUID, cineplex, Database.CINEPLEX);
    }

    /**
     * Method to print out information of all the cineplexes in the database
     * @return ArrayList of {@link Cineplex} objects
     */
    public static ArrayList <Cineplex> printCineplexesInfo() {
        ArrayList <Cineplex> cineplexes = new ArrayList<>();
        ArrayList <String> cineplexKeyList = Database.getKeyList(Database.CINEPLEX.keySet());
        ArrayList <Cineplex> cineplexValueList = Database.getValueList(Database.CINEPLEX.values());
        Collections.sort(cineplexKeyList);
        Collections.sort(cineplexValueList);

        int index = 1;
        for (int i = 0; i < cineplexValueList.size(); i ++) {
            cineplexes.add(cineplexValueList.get(i));
            System.out.println(index + ".");
            System.out.print("Cineplex Branch:     ");
            System.out.println(cineplexValueList.get(i).getCineplexName());
            System.out.println("Location: ");
            System.out.println(cineplexValueList.get(i).getCineplexLocation());
            System.out.println("Standard Cinema: " + 
                            CineplexManager.countCinemaClass(cineplexValueList.get(i), CinemaClass.STANDARD));
            System.out.println("Platinum Cinema: " + 
                            CineplexManager.countCinemaClass(cineplexValueList.get(i), CinemaClass.PLATINUM));
            System.out.println("IMAX Cinema: " + 
                            CineplexManager.countCinemaClass(cineplexValueList.get(i), CinemaClass.IMAX));
            System.out.println("");
            index ++;
        }

        return cineplexes;
    }

    /**
     * Method that counts the number of different cinema classes for a cineplex
     * @param cineplex the target {@link Cineplex}
     * @param cinemaCLass the target {@link CinemaClass}
     * @return the number of cinemas of which belong to {@param cinemaClass}
     */
    public static int countCinemaClass(Cineplex cineplex, CinemaClass cinemaClass) {
        int count = 0;
        ArrayList <Cinema> cinemas = cineplex.getCinemas();

        for (int i = 0; i < cinemas.size(); i ++) {
            if (cinemas.get(i).getCinemaClass() == cinemaClass) {
                count ++;
            }
        }
        return count;
    }


    public static Cineplex getCineplexByCinema(Cinema targetCinema) {
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());

        for (int i = 0; i < cineplexes.size(); i ++) {
            Cineplex cineplex = cineplexes.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j ++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                if (cinema.getUUID().equals(targetCinema.getUUID())) {
                    return cineplex;
                }
            }
        }

        return null;
    }
}
