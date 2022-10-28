package models.staff;

import models.staff.HolidayDateTime;

import models.movie.Movie;
import models.movie.MovieType;
import models.movie.AgeRating;
import models.movie.ShowingStatus;
import models.movie.DateTime;

import models.cinema.Cinema;

import java.util.*;


public class Staff implements IStaffAccess {
    private String name;
    private String password;
    private int staffID;

    public Staff(String name, String password, int staffID){
        this.name = name;
        this.password = password;
        this.staffID = staffID;
    }

    /*
     * getters and setters
     */
    
    public String getName(){
        return this.name;
    }

    public String getPassword(){
        return this.password;
    }

    public int getStaffID(){
        return this.staffID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setStaffID(int staffID){
        this.staffID = staffID;
    }

    /*
     * Allows Staff to log in 
     * @
     */

    public boolean login(int staffID, String password){
        if (this.staffID == staffID && this.password == password){
            return true;
        }
        else {
            return false;
        }

    }

    /*
     * Allows Staff to add new movies
     */

    public void enterNewMovies(	int movieID, String movieTitle, MovieType movieType, 
    AgeRating movieAgeRating, ShowingStatus showingStatus, String movieCast[], 
    String movieDirector, String movieSynopsis, float movieDuration, 
    Cinema showingVenue[], DateTime showingTimes[]){
    
        Movie newMovie = new Movie(movieID, movieTitle, movieType, movieAgeRating, showingStatus, movieCast, movieDirector, movieSynopsis, movieDuration, showingVenue, showingTimes);
    
    }


    /*
     * Updates certain details for existing movies
     */
    public void updateExistingMovieDetails(int choice, int movieID){
        Scanner sc = new Scanner(System.in);
        switch(choice){
            case 1:
            System.out.println("Enter the new name of the Movie");
            String newMovieName = sc.nextLine();
            Movie.movies.get(movieID).setMovieTitle(newMovieName);
            System.out.println("MovieID: "+ movieID + " - Movie name changed to " + Movie.movies.get(movieID).getMovieTitle());
            break;
            case 2:
            System.out.println("Enter the new Movie Type for " + Movie.movies.get(movieID).getMovieTitle());
            String newMovieType = sc.nextLine();
            MovieType newMovieType2 = new MovieType((float)0.0);
            Movie.movies.get(movieID).setMovieType(newMovieType2);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie type changed to " + Movie.movies.get(movieID).getMovieType());
            break;

            case 3:
            System.out.println("Enter the new age rating for " + Movie.movies.get(movieID).getMovieTitle()+ "(G, PG, PG13, NC16, M18, R21)");
            AgeRating newMovieAgeRating = AgeRating.valueOf(sc.nextLine().toUpperCase());
            Movie.movies.get(movieID).setMovieAgeRating(newMovieAgeRating);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie age rating changed to " + Movie.movies.get(movieID).getMovieAgeRating());
            break;
            
            case 4:
            System.out.println("Enter the new showing status for " + Movie.movies.get(movieID).getMovieTitle()+ "(COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING)");
            ShowingStatus newShowingStatus = ShowingStatus.valueOf(sc.nextLine().toUpperCase());
            // work in progress
            if (newShowingStatus == ShowingStatus.END_OF_SHOWING){
                System.out.println("Deleting " + Movie.movies.get(movieID).getMovieTitle() + " from movie list...");
                Movie.movies.remove(movieID);
                return;
            }
            Movie.movies.get(movieID).setShowingStatus(newShowingStatus);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie showing status changed to " + Movie.movies.get(movieID).getShowingStatus());
            break;
            
            case 5:
            System.out.println("Enter which cast number is to be updated.");
            int castNumber = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the updated name of cast " + castNumber + ".");
            String newCastName = sc.nextLine();
            Movie.movies.get(movieID).setMovieCast(newCastName, castNumber);
            
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s cast " + castNumber + "'s name changed to " + Movie.movies.get(movieID).getMovieCast()[castNumber]);
            break;
            
            case 6:
            System.out.println("Enter the new name of the Movie Director");
            String newDirectorName = sc.nextLine();
            Movie.movies.get(movieID).setMovieDirector(newDirectorName);
            System.out.println("MovieID: "+ movieID + " " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie director changed to "+ Movie.movies.get(movieID).getMovieDirector());
            break;

            case 7:
            System.out.println("Enter the new synopsis of  " + Movie.movies.get(movieID).getMovieTitle() + ".");
            String newSynopsis = sc.nextLine();
            Movie.movies.get(movieID).setMovieSynopsis(newSynopsis);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie synopsis changed to " + Movie.movies.get(movieID).getMovieSynopsis());
            break;

            case 8:
            System.out.println("Enter new duration of " + Movie.movies.get(movieID).getMovieTitle() + ".");
            float newMovieDuration = sc.nextFloat();
            Movie.movies.get(movieID).setMovieDuration(newMovieDuration);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie duration changed to " + Movie.movies.get(movieID).getMovieDuration());
            break;

            case 9:
            System.out.println("Select the new showing venues");
            break;
            
            case 10:
            System.out.println("Select a showing time to be updated. (Enter ID number)");
            for (int i = 0; i < Movie.movies.get(movieID).getShowingTime().length; i++){
                System.out.println(i + " : " + "Date: " + Movie.movies.get(movieID).getShowingTime()[i].getYear() + Movie.movies.get(movieID).getShowingTime()[i].getMonth() + Movie.movies.get(movieID).getShowingTime()[i].getDate() + " Time: " + Movie.movies.get(movieID).getShowingTime()[i].getHour() + Movie.movies.get(movieID).getShowingTime()[i].getMinute());
            }
            int showingTimeID = sc.nextInt();

            System.out.println("Enter the new showing time");
            System.out.println("year:");
            int year = sc.nextInt();
            System.out.println("month:");
            int month = sc.nextInt();
            System.out.println("date:");
            int date = sc.nextInt();
            System.out.println("hour:");
            int hour = sc.nextInt();
            System.out.println("minute:");
            int minute = sc.nextInt();
            System.out.println("day:");
            int day = sc.nextInt();

            DateTime newShowingTime = new DateTime(minute, hour, day, date, month, year);
            
            Movie.movies.get(movieID).setShowingTime(newShowingTime, showingTimeID);
            System.out.println("MovieID: "+ movieID + " - " +  Movie.movies.get(movieID).getMovieTitle() + "'s Movie showing time "+ showingTimeID + " changed to " + Movie.movies.get(movieID).getShowingTime()[showingTimeID].getYear() + Movie.movies.get(movieID).getShowingTime()[showingTimeID].getMonth() + Movie.movies.get(movieID).getShowingTime()[showingTimeID].getDate() + " Time: " + Movie.movies.get(movieID).getShowingTime()[showingTimeID].getHour() + Movie.movies.get(movieID).getShowingTime()[showingTimeID].getMinute());
            break;    
        }
    }


    /* 
     * method to change ticket prices 
     */
    public void changeTicketPrice(){


    }

    /*
     * method to add holiday date
     * holidays is an arraylist of holiday
     * return 0 if holiday added
     * return 1 if holiday is already in holidays list
     */
    public int addHoliday(int year, int month, int date, int hour, int minute, int day){
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++)
        {
            if (HolidayDateTime.holidays.get(i).getYear() == year && HolidayDateTime.holidays.get(i).getMonth() == month && HolidayDateTime.holidays.get(i).getDate() == date && HolidayDateTime.holidays.get(i).getHour() == hour && HolidayDateTime.holidays.get(i).getMinute() == minute && HolidayDateTime.holidays.get(i).getDay() == day){
                return 1;
            }
        }
        HolidayDateTime holiday = new HolidayDateTime(minute, hour, day, date, month, year);
        return 0;
    }

    /*
     * method to remove holiday date
     * return 0 is successful, 1 if not found, 2 if the holiday arraylist is empty
     */

    public int deleteHoliday(int year, int month, int date, int hour, int minute, int day){
        if (HolidayDateTime.holidays.size() == 0){
            return 2;
        }
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++)
        {
            if (HolidayDateTime.holidays.get(i).getYear() == year && HolidayDateTime.holidays.get(i).getMonth() == month && HolidayDateTime.holidays.get(i).getDate() == date && HolidayDateTime.holidays.get(i).getHour() == hour && HolidayDateTime.holidays.get(i).getMinute() == minute && HolidayDateTime.holidays.get(i).getDay() == day){
                HolidayDateTime.holidays.remove(i);
                return 0;
            }
        }
        return 1;
    }

    /*
     * sorts and prints the top five movies based on overallRating
     */

    public void printTopFiveOverallRatings(ArrayList <Movie> movies){
        Collections.sort(movies, new SortByOverallRating());
        for (int i = 0; i < 5; i++){
            System.out.println(movies.get(i));
        }

    }


    /*
     * sorts and prints the top five movies based on ticketSales
     */

    public void printTopFiveTicketSales(ArrayList <Movie> movies){
        Collections.sort(movies, new SortByTicketSales());
        for (int i = 0; i < 5; i++){
            System.out.println(movies.get(i));
        }
    }

}


/*
 * creates custom comparator class to compare overallRatings
 * sorts the movies array list in descending order
 */

class SortByOverallRating implements Comparator<Movie>{
    
    /*
     * overrides the compare method
     */

    public int compare(Movie movie1, Movie movie2){
        double rating1 = movie1.getMovieOverallReviewRating();
        double rating2 = movie2.getMovieOverallReviewRating();

        if (rating1 == rating2){
            return 0;
        }
        else if (rating1 > rating2){
            return 1;
        }
        else return -1;
    } 
}

/*
 * creates custom comparator class to compare movieTicketSales
 * sorts the movies array list in descending order 
 */

class SortByTicketSales implements Comparator<Movie>{
    /*
     * overrides the compare method
     */
    public int compare(Movie movie1, Movie movie2){
        int ticketSales1 = movie1.getMovieTicketSales();
        int ticketSales2 = movie2.getMovieTicketSales();

        if (ticketSales1 == ticketSales2){
            return 0;
        }
        else if (ticketSales1 > ticketSales2){
            return 1;
        }
        else return -1;
    }
}
