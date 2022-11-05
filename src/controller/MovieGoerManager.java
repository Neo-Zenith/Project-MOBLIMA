package controller;

import java.util.*;
import database.*;
import model.*;
import handler.*;
import model.enums.*;

public class MovieGoerManager {

    public MovieGoerManager() {}

    /**
     * Creates an {@Adult} instance and save to {@Link Database}
     * @param name is the name of the user
     * @param email is the email of the user
     * @param mobileNum is the mobile number of the user
     * @param username is the username of the user
     * @param password is the password of the user
     * @return {@link MovieGoer} instance that was created
     */
    public static MovieGoer createGoerAdult(String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%04d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Adult(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }


    /**
     * Creates an {@Child} instance and save to {@Link Database}
     * @param name is the name of the user
     * @param email is the email of the user
     * @param mobileNum is the mobile number of the user
     * @param username is the username of the user
     * @param password is the password of the user
     * @return {@link MovieGoer} instance that was created
     */
    public static MovieGoer createGoerChild(String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%04d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Child(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }


    /**
     * Creates an {@SeniorCitizen} instance and save to {@Link Database}
     * @param name is the name of the user
     * @param email is the email of the user
     * @param mobileNum is the mobile number of the user
     * @param username is the username of the user
     * @param password is the password of the user
     * @return {@link MovieGoer} instance that was created
     */
    public static MovieGoer createGoerSeniorCitizen(String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%04d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new SeniorCitizen(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }
    
    
    /**
     * Creates an {@Student} instance and save to {@Link Database}
     * @param name is the name of the user
     * @param email is the email of the user
     * @param mobileNum is the mobile number of the user
     * @param username is the username of the user
     * @param password is the password of the user
     * @return {@link MovieGoer} instance that was created
     */
    public static MovieGoer createGoerStudent(String name, String email, String mobileNum, String username, String password){
        String UUID = String.format("MG%04d", DatabaseHandler.generateUUID(Database.MOVIE_GOER));
        MovieGoer goer = new Student(UUID, name, email, mobileNum, username, password);
        DatabaseManager.saveUpdateToDatabase(UUID, goer, Database.MOVIE_GOER);
        return goer;
    }


    public static void viewBookingHistory(MovieGoer movieGoer) {}


    /**
     * Higher-level method to check if the movies can be ranked by top 5
     * @param choice is the key to sort the movies
     * @param staff is to check if the calling object is a staff or not
     * @return {@code true} if calling object can 
     */
    public static boolean rankTop5(String choice, boolean staff) {
        if(! staff){
            if(! MovieGoer.getViewTop5MovieSales()) {
                System.out.println("Ranking by Top 5 Movie Sales is unavailable");
                return false; 
            }
            if(! MovieGoer.getViewTop5OverallRatings()) {
                System.out.println("Ranking by Top 5 Overall Sales is unavailable");
                return false;
            }
        }

        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        if (movies.size() <= 1) {
            return false;
        }
        MovieManager.sortMovie(choice);
        return true;
    }


    public static MovieGoer getGoerDetails(String name) {
        ArrayList<MovieGoer> goerlist = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < goerlist.size(); i++) {
            if (goerlist.get(i).getName() == name) {
                return goerlist.get(i);
            }
        }
        return null;
    }
}
