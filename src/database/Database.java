package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import model.Cineplex;
import model.MovieSchedule;
import model.Payment;
import model.BookingHistory;
import model.Cinema;
import model.Seat;
import model.Movie;
import model.MovieGoer;
import model.MovieReview;

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
     * Movie Schedule Model in database
     */
    public static HashMap <String, MovieSchedule> MOVIE_SCHEDULE = new HashMap <String, MovieSchedule>();

    /**
     * Booking History Model in database
     */
    public static HashMap <String, BookingHistory> BOOKING_HISTORY = new HashMap <String, BookingHistory>();

    /**
     * Payment Model in database
     */
    public static HashMap <String, Payment> PAYMENT = new HashMap <String, Payment>();

    /**
     * Movie Model in database
     */
    public static HashMap<String, Movie> MOVIE = new HashMap<String, Movie>();

    /**
     * Movie Goer in database
     */
    public static HashMap<String, MovieGoer> MOVIE_GOER = new HashMap<String, MovieGoer>();

    /**
     * Movie Review in database
     */
    public static HashMap<String, MovieReview> MOVIE_REVIEW = new HashMap<String, MovieReview>();

    /**
     * Total number of seats for every cinema
     */
    public static int totalNumOfSeats = 144;

    /**
     * Total number of rows in a cinema
     */
    public static int numOfRows = 12;

    /**
     * Total number of couple rows in a cinema
     */
    public static int numOfCoupleRows = 4;

    public static int platinumNumOfSeatsPerRow = 6;
    public static int platinumNumOfRow = 4;

    public static double defaultStandardCinemaPrice = 2;
    public static double defautltPlatinumCinemaPrice = 30;
    public static double defaultIMaxCinemaPrice = 3;

    public static double defaultSeatPrice = 4;

    public static double defaultBlockbusterMoviePrice = 3;
    public static double default3DMoviePrice = 5;
    public static double defaultStandardMoviePrice = 2;

    public static double defaultStudentPrice = 1.5;
    public static double defaultAdultPrice = 2;
    public static double defaultSeniorCitizenPrice = 1.5;

    public static double holidayPrice = 1.5;
    public static double weekendPrice = 1.5;

    /**
     * Root path to the database
     */
    private static String path = "../src/database/data";

    /**
     * Database extension
     */
    private static String extension = ".dat";

    public Database() {
        if (!readData(ModelType.CINEPLEX)) {
            System.out.println("Error! Reading of data " + ModelType.CINEPLEX + " failed!");
        } 
        else if (!readData(ModelType.CINEMA)) {
            System.out.println("Error! Reading of data " + ModelType.CINEMA + " failed!");
        } 
        else if (!readData(ModelType.SEAT)) {
            System.out.println("Error! Reading of data " + ModelType.SEAT + " failed!");
        } 
        else if (!readData(ModelType.MOVIE_SCHEDULE)) {
            System.out.println("Error! Reading of data " + ModelType.MOVIE_SCHEDULE + " failed!");
        } 
        else if (!readData(ModelType.MOVIE)) {
            System.out.println("Error! Reading of data " + ModelType.MOVIE + " failed!");
        } 
        else if (!readData(ModelType.MOVIE_GOER)) {
            System.out.println("Error! Reading of data " + ModelType.MOVIE_GOER + " failed!");
        } 
        else if (!readData(ModelType.MOVIE_REVIEW)) {
            System.out.println("Error! Reading of data " + ModelType.MOVIE_REVIEW + " failed!");
        } 
        else if (! readData(ModelType.BOOKING_HISTORY)) {
            System.out.println("Error! Reading of data " + ModelType.MOVIE_SCHEDULE + " failed!");
        }
        else if (! readData(ModelType.PAYMENT)) {
            System.out.println("Error! Reading of data " + ModelType.PAYMENT + " failed!");
        }
    }

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
            else if (modelType == ModelType.MOVIE_SCHEDULE) {
                Database.MOVIE_SCHEDULE = (HashMap <String, MovieSchedule>) object;
            }
            else if (modelType == ModelType.BOOKING_HISTORY) {
                Database.BOOKING_HISTORY = (HashMap <String, BookingHistory>) object;
            }
            else if (modelType == ModelType.PAYMENT) {
                Database.PAYMENT = (HashMap <String, Payment>) object;
            } 
            else if (modelType == ModelType.MOVIE) {
                Database.MOVIE = (HashMap<String, Movie>) object;
            } 
            else if (modelType == ModelType.MOVIE_GOER) {
                Database.MOVIE_GOER = (HashMap<String, MovieGoer>) object;
            } 
            else if (modelType == ModelType.MOVIE_REVIEW) {
                Database.MOVIE_REVIEW = (HashMap<String, MovieReview>) object;
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
            else if (modelType == ModelType.MOVIE_SCHEDULE) {
                objectOutputStream.writeObject(Database.MOVIE_SCHEDULE);
            }
            else if (modelType == ModelType.BOOKING_HISTORY) {
                objectOutputStream.writeObject(Database.BOOKING_HISTORY);
            }
            else if (modelType == ModelType.PAYMENT) {
                objectOutputStream.writeObject(Database.PAYMENT);
            }
            else if (modelType == ModelType.MOVIE) {
                objectOutputStream.writeObject(Database.MOVIE);
            } 
            else if (modelType == ModelType.MOVIE_GOER) {
                objectOutputStream.writeObject(Database.MOVIE_GOER);
            } 
            else if (modelType == ModelType.MOVIE_REVIEW) {
                objectOutputStream.writeObject(Database.MOVIE_REVIEW);
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
            Database.readData(ModelType.MOVIE_SCHEDULE);
            Database.readData(ModelType.BOOKING_HISTORY);
            Database.readData(ModelType.PAYMENT);
            Database.readData(ModelType.MOVIE);
            Database.readData(ModelType.MOVIE_GOER);
            Database.readData(ModelType.MOVIE_REVIEW);
            return true;
        }
        catch (Exception e) {
            System.out.println("Error! " + e.getMessage());
            return false;
        }
    }


    /**
     * Method to save all changes to database. Useful on first load to save dummy data.
     * @return {code true} if write is successful; {@code false} otherwise
     */
    public static boolean writeToDatabase() {
        try {
            Database.writeData(ModelType.CINEPLEX);
            Database.writeData(ModelType.CINEMA);
            Database.writeData(ModelType.SEAT);
            Database.writeData(ModelType.MOVIE_SCHEDULE);
            Database.writeData(ModelType.BOOKING_HISTORY);
            Database.writeData(ModelType.PAYMENT);
            Database.writeData(ModelType.MOVIE);
            Database.writeData(ModelType.MOVIE_GOER);
            Database.writeData(ModelType.MOVIE_REVIEW);
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
        Database.MOVIE_SCHEDULE = new HashMap <String, MovieSchedule>();
        Database.BOOKING_HISTORY = new HashMap <String, BookingHistory>();
        Database.MOVIE = new HashMap <String, Movie>();
        Database.MOVIE_GOER = new HashMap <String, MovieGoer>();
        Database.MOVIE_REVIEW = new HashMap <String, MovieReview>();

        Database.writeData(ModelType.CINEPLEX);
        Database.writeData(ModelType.CINEMA);
        Database.writeData(ModelType.SEAT);
        Database.writeData(ModelType.MOVIE_SCHEDULE);
        Database.writeData(ModelType.BOOKING_HISTORY);
        Database.writeData(ModelType.PAYMENT);
        Database.writeData(ModelType.MOVIE);
        Database.writeData(ModelType.MOVIE_GOER);
        Database.writeData(ModelType.MOVIE_REVIEW);
    }

    /**
     * Method to return an ArrayList of HashMap keys for the collection {@code keySet}
     * @param keySet a HashMap keys collection
     * @return ArrayList of {@code String} containing HashMap keys
     */
    public static ArrayList <String> getKeyList(Collection keySet) {
        ArrayList <String> keyList = new ArrayList<>(keySet);
        return keyList;
    }

    /**
     * Method to return an ArrayList of HashMap values for the collection {@code valueSet}
     * @param valueSet a HashMap values collection
     * @return ArrayList of {@code Value} containing HashMap values
     */
    public static <V> ArrayList <V> getValueList(Collection valueSet) {
        ArrayList <V> valueList = new ArrayList<>(valueSet);
        return valueList;
    }

    /**
     * Method to retrieve the value of a key in the HashMap database
     * @param <K> generic form for Key
     * @param <V> generic form for value
     * @param key the key we wish to search the value for
     * @param data the database
     * @return {@code V} that is the value of the key {@code key}
     */
    public static <K, V> V getValueByKey(K key, HashMap <K, V> data) {
        ArrayList <String> dataList = Database.getKeyList(data.keySet());
        ArrayList <V> valueList = Database.getValueList(data.values());
        int index = dataList.indexOf(key);
        return valueList.get(index);
    }

    /**
     * Method to retrieve the key of a value in the HashMap database
     * @param <K> generic form for Key
     * @param <V> generic form for value
     * @param value the value we wish to search the key for
     * @param data the database
     * @return {@code String} that is the key of the value {@code value}
     */
    public static <K, V> String getKeyByValue(V value, HashMap <K, V> data) {
        ArrayList <String> dataList = Database.getKeyList(data.keySet());
        ArrayList <V> valueList = Database.getValueList(data.values());
        int index = valueList.indexOf(value);
        return dataList.get(index);
    }
}
