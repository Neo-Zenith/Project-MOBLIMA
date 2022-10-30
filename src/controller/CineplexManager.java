package controller;

import models.cinema.*;
import database.*;
import middleware.ExceptionHandler;

import java.util.*;

public class CineplexManager {
    
    public CineplexManager() {}

    public static boolean createCineplex(String cineplexName, int numOfCinema) {
        Cineplex cineplex = new Cineplex(cineplexName, numOfCinema);
        Database.cineplex.put(cineplexName, cineplex);
        Database.saveToDatabase(ModelType.CINEPLEX);
        return true;
    }


    public static boolean updateCineplex(String oldName, String newName) {
        ArrayList <Cineplex> queryResult = new ArrayList<>();

        for (Cineplex cineplex : Database.cineplex.values()) {
            if (cineplex.cineplexName == oldName) {
                queryResult.add(cineplex);
            }
        }

        if (queryResult.size() == 0) {
            ExceptionHandler.noQueryException();
            return false;
        }

        else if (queryResult.size() > 1) {
            ExceptionHandler.enforcedSingleQueryException(queryResult.size());
            return false;
        }

        else {
            queryResult.get(0).setCineplexName(newName);
            return true;
        }
    }


    public static ArrayList <Cineplex> queryCineplexByName(String cineplexName) {
        ArrayList <Cineplex> queryList = new ArrayList<>();

        for (Cineplex cineplex: Database.cineplex.values()) {
            if (cineplex.cineplexName == cineplexName) {
                queryList.add(cineplex);
            }
        }
        return queryList;
    }


    public static void initializeCineplexData() {
        CineplexManager.createCineplex("Cathay Cineplex", 5);
        CineplexManager.createCineplex("Golden Village Cineplex", 12);
        CineplexManager.createCineplex("Shaw Theatre", 8);
    }
}
