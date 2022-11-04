package controller;

import model.CinemaStaff;
import model.MovieGoer;

import database.Database;

import java.util.ArrayList;

public class UserManager {
    
    public static Object login(String username, String password){
        ArrayList <CinemaStaff> cinemaStaffList = Database.getValueList(Database.CINEMA_STAFF.values());
        for (int i = 0; i < cinemaStaffList.size(); i++){
            CinemaStaff c = cinemaStaffList.get(i);
            if (c.getUsername().equals(username) && c.getPassword().equals(password)){
                return c;
            }
        }
        
        ArrayList <MovieGoer> movieGoerList = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < movieGoerList.size(); i++){
            MovieGoer m = movieGoerList.get(i);
            if (m.getUsername().equals(username) && m.getPassword().equals(password)){
                return m;
            }
        }
        return null;
    }

    
    public static boolean checkUniqueUser(String username) {
        ArrayList <CinemaStaff> cinemaStaffList = Database.getValueList(Database.CINEMA_STAFF.values());
        ArrayList <MovieGoer> movieGoerList = Database.getValueList(Database.MOVIE_GOER.values());

        for (int i = 0; i < cinemaStaffList.size(); i ++) {
            CinemaStaff cinemaStaff = cinemaStaffList.get(i);

            if (cinemaStaff.getUsername().equals(username)) {
                return false;
            }
        }

        for (int i = 0; i < movieGoerList.size(); i ++) {
            MovieGoer movieGoer = movieGoerList.get(i);

            if (movieGoer.getUsername().equals(username)) {
                return false;
            }
        }

        return true;
    }
}
