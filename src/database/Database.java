package database;

import java.util.HashMap;

import controller.CineplexManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import middleware.ExceptionHandler;
import middleware.UIHandler;
import models.cinema.Cinema;
import models.cinema.Cineplex;


public class Database {
    
    public static HashMap <String, Cineplex> cineplex = new HashMap <String, Cineplex> ();
    public static HashMap <String, Cinema> cinema = new HashMap <String, Cinema> ();
    
    public static final String extension = ".dat";
    public static final String path = System.getProperty("user.dir") + "/database/data";
    
    public Database() {
        if (! readData(ModelType.CINEPLEX)) {
            System.out.println("Reading Cineplex data failed!");
        }
        else if (! readData(ModelType.CINEMA)) {
            System.out.println("Reading Cinema data failed!");
        }
    }


    private static boolean readData(ModelType modelType) {
        String filePath = path + "/" + modelType.modelName + extension;

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            if (! (object instanceof HashMap)) {
                ExceptionHandler.modelException();
                fileInputStream.close();
                objectInputStream.close();
                return false;
            }

            if (modelType == ModelType.CINEPLEX) {
                Database.cineplex = (HashMap <String, Cineplex>) object;
            }
            else if (modelType == ModelType.CINEMA){
                Database.cinema = (HashMap <String, Cinema>) object;
            }
            
            fileInputStream.close();
            objectInputStream.close();
            return true;
        }

        catch (Exception e) {
            ExceptionHandler.multiExceptions(e);
            return false;
        }
    }

    private static boolean writeData(ModelType modelType) {
        String filePath = path + "/" + modelType.modelName + extension;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            if (modelType == ModelType.CINEPLEX) {
                objectOutputStream.writeObject(Database.cineplex);
            }
            else if (modelType == ModelType.CINEMA) {
                objectOutputStream.writeObject(Database.cinema); 
            }

            fileOutputStream.close();
            objectOutputStream.close();     
            return true;
        }
        catch (Exception e) {
            ExceptionHandler.multiExceptions(e);
            return false;
        }
    }

    public static void saveToDatabase(ModelType modelType) {
        writeData(modelType);
        UIHandler.clearScreen();
    }

    public static boolean resetDatabase() {
        Database.cineplex = new HashMap<String, Cineplex>();
        Database.cinema = new HashMap<String, Cinema>();

        writeData(ModelType.CINEPLEX);
        writeData(ModelType.CINEMA);

        return true;
    }

    public static boolean loadInitialCineplexData() {
        if (Database.cineplex.size() != 0) {
            System.out.println("Database already has active entries. Clear the database before initial load!");
            return false;
        }

        CineplexManager.initializeCineplexData();
        return true;
    }
}
