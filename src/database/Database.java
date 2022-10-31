package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import model.Cineplex;
import model.Cinema;
import model.Seat;

public class Database {
    /**
     * Cineplex Model in database
     */
    public static HashMap <String, Cineplex> CINEPLEX = new HashMap <String, Cineplex>();

    /**
     * Cinema Model in database
     */
    public static HashMap <String, Cinema> CINEMA = new HashMap <String, Cinema>();

    /**
     * Seat Model in database
     */
    public static HashMap <String, Seat> SEAT = new HashMap <String, Seat>();

    /**
     * Total number of seats for every cinema
     */
    public static int totalNumOfSeats = 480;

    /**
     * Total number of rows in a cinema
     */
    public static int numOfRows = 24;

    /**
     * Total number of couple rows in a cinema
     */
    public static int numOfCoupleRows = 4;

    /**
     * Root path to the database
     */
    private static String path = "../src/database/data";

    /**
     * Database extension
     */
    private static String extension = ".dat";

    /**
     * Method to read serialized data from database file
     * @param modelType 
     * @return {@code true} if read is successful; {@code false} otherwise
     */
    public static boolean readData(ModelType modelType) {
        String filePath = Database.path + "/" + modelType.getFileName() + extension;

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();

            if (! (object instanceof HashMap)) {
                objectInputStream.close();
                return false;
            }

            if (modelType == ModelType.CINEPLEX) {
                Database.CINEPLEX = (HashMap <String, Cineplex>) object;
            }
            else if (modelType == ModelType.CINEMA) {
                Database.CINEMA = (HashMap <String, Cinema>) object;
            }
            else if (modelType == ModelType.SEAT) {
                Database.SEAT = (HashMap <String, Seat>) object;
            }

            objectInputStream.close();
            fileInputStream.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error! Reading of data " + modelType.getFileName() + " failed!");
            return false;
        }
    }

    /**
     * Method to write serialized data into database file
     * @param modelType
     * @return {@code true} if write is successful; {@code false} otherwise
     */
    public static boolean writeData(ModelType modelType) {
        String filePath = Database.path + "/" + modelType.getFileName() + extension;

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            if (modelType == ModelType.CINEPLEX) {
                objectOutputStream.writeObject(Database.CINEPLEX);
            }
            else if (modelType == ModelType.CINEMA) {
                objectOutputStream.writeObject(Database.CINEMA);
            }
            else if (modelType == ModelType.SEAT) {
                objectOutputStream.writeObject(Database.SEAT);
            }

            fileOutputStream.close();
            objectOutputStream.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("Error! Failed to write to file " + modelType + "!");
            return false;
        }
    }

    /**
     * Method to reload the database. Useful when a change was recently made and user requires
     * immediate reflected changes.
     * @return {@code true} when reload is successful; {@code false} otherwise
     */
    public static boolean remountDatabase() {
        try {
            Database.readData(ModelType.CINEPLEX);
            Database.readData(ModelType.CINEMA);
            Database.readData(ModelType.SEAT);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }

    /**
     * Method to reset the database
     */
    public static void resetDatabase() {
        Database.CINEPLEX = new HashMap <String, Cineplex>();
        Database.CINEMA = new HashMap <String, Cinema>();
        Database.SEAT = new HashMap <String, Seat>();

        Database.writeData(ModelType.CINEPLEX);
        Database.writeData(ModelType.CINEMA);
        Database.writeData(ModelType.SEAT);
    }
}
