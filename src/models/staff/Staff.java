package models.staff;

import models.staff.HolidayDateTime;

import models.movie.Movie;
import models.movie.MovieAgeRating;
import models.movie.MovieShowingStatus;
import models.movie.DateTime;


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

    // public void enterNewMovies(String movieTitle, String movieType, MovieAgeRating movieAgeRating,
    // MovieShowingStatus showingStatus,
    // ArrayList<String> movieCast,
    // String movieDirector, String movieSynopsis, double movieDuration,
    // ArrayList<DateTime> showingTime){
    
    //     Movie newMovie = new Movie(movieTitle, movieType, movieAgeRating, showingStatus, movieCast, movieDirector, movieSynopsis, movieDuration, showingTime);
    
    // }


    /*
     * Updates certain details for existing movies
     */



    public void updateExistingMovieDetails(List <Movie> movies, String movieTitle, String movieType, int choice){
        Scanner sc = new Scanner(System.in);
        int index = getIndex(Movie.movies, movieTitle, movieType);
        if (index == -1){
            System.out.println("Movie does not exist!");
            return;
        }

        Movie m = Movie.movies.get(index);

        switch(choice){
            
            case 1:
            System.out.println("Enter the new name of the Movie");
            String newMovieName = sc.nextLine();
            m.setMovieTitle(newMovieName);
            System.out.println("Movie name changed to " + m.getMovieTitle());
            break;
            case 2:
            System.out.println("Enter the new Movie Type for " + m.getMovieTitle());
            String newMovieType = sc.nextLine();
            m.setMovieType(newMovieType);
            System.out.println(m.getMovieTitle() + "'s Movie type changed to " + m.getMovieType());
            break;

            case 3:
            System.out.println("Enter the new age rating for " + m.getMovieTitle()+ "(G, PG, PG13, NC16, M18, R21)");
            MovieAgeRating newMovieAgeRating = MovieAgeRating.valueOf(sc.nextLine().toUpperCase());
            m.setMovieAgeRating(newMovieAgeRating);
            System.out.println(m.getMovieTitle() + "'s Movie age rating changed to " + m.getMovieAgeRating());
            break;
            
            case 4:
            System.out.println("Enter the new showing status for " + m.getMovieTitle()+ "(COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING)");
            MovieShowingStatus newShowingStatus = MovieShowingStatus.valueOf(sc.nextLine().toUpperCase());
            // work in progress
            if (newShowingStatus == MovieShowingStatus.END_OF_SHOWING){
                System.out.println("Deleting " + m.getMovieTitle() + " from movie list...");
                Movie.movies.remove(index);
            }
            m.setShowingStatus(newShowingStatus);
            System.out.println(m.getMovieTitle() + "'s Movie showing status changed to " + m.getShowingStatus());
            break;
            
            case 5:
            System.out.println("Enter which cast number is to be updated. (Enter Cast Number)");
            for (int i = 0; i < m.getMovieCast().size(); i++){
                System.out.println("Cast Number " + (i+1) + ": " + m.getMovieCast().get(i));
            }
            int castNumber = sc.nextInt();
            sc.nextLine();
            System.out.println("Enter the updated name of cast " + castNumber + ".");
            String newCastName = sc.nextLine();
            m.setMovieCast(newCastName, castNumber);
            
            System.out.println(m.getMovieTitle() + "'s cast " + castNumber + "'s name changed to " + m.getMovieCast().get(castNumber));
            break;
            
            case 6:
            System.out.println("Enter the new name of the Movie Director");
            String newDirectorName = sc.nextLine();
            m.setMovieDirector(newDirectorName);
            System.out.println(m.getMovieTitle() + "'s Movie director changed to "+ m.getMovieDirector());
            break;

            case 7:
            System.out.println("Enter the new synopsis of " + m.getMovieTitle() + ".");
            String newSynopsis = sc.nextLine();
            m.setMovieSynopsis(newSynopsis);
            System.out.println(m.getMovieTitle() + "'s Movie synopsis changed to " + m.getMovieSynopsis());
            break;

            case 8:
            System.out.println("Enter new duration of " + m.getMovieTitle() + ".");
            float newMovieDuration = sc.nextFloat();
            m.setMovieDuration(newMovieDuration);
            System.out.println(m.getMovieTitle() + "'s Movie duration changed to " + m.getMovieDuration());
            break;

            // need to add showing venues
            // case 9:
            // System.out.println("Select the new showing venues");
            // break;
            
            case 10:
            System.out.println("Select a showing time to be updated. (Enter ID number)");
            for (int i = 0; i < m.getShowingTime().size(); i++){
                System.out.println((i+1) + " : " + "Date: " + m.getShowingTime().get(i).getYear() + m.getShowingTime().get(i).getMonth() + m.getShowingTime().get(i).getDate() + " Time: " + m.getShowingTime().get(i).getHour() + m.getShowingTime().get(i).getMinute());
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
            
            m.setShowingTime(newShowingTime, showingTimeID);
            System.out.println(m.getMovieTitle() + "'s Movie showing time "+ showingTimeID + " changed to " + m.getShowingTime().get(showingTimeID).getYear() + m.getShowingTime().get(showingTimeID).getMonth() + m.getShowingTime().get(showingTimeID).getDate() + " Time: " + m.getShowingTime().get(showingTimeID).getHour() + m.getShowingTime().get(showingTimeID).getMinute());
            break;  
            
            default:
            break;
        }
    }

    public int getIndex(List <Movie> movies, String movieTitle, String movieType){
        for (int i = 0; i < movies.size(); i++){
            Movie m;
            m = movies.get(i);
            if (m.getMovieTitle().equals(movieTitle) && m.getMovieType().equals(movieType)){
                return i;
            }
        }
        return -1;
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
    public int addHoliday(ArrayList <HolidayDateTime> holidays, HolidayDateTime holiday){
        for (int i = 0; i < holidays.size(); i++)
        {
            if (holidays.get(i).getYear() == holiday.getYear() && holidays.get(i).getMonth() == holiday.getMonth() && holidays.get(i).getDate() == holiday.getDate() && holidays.get(i).getHour() == holiday.getHour() && holidays.get(i).getMinute() == holiday.getMinute() && holidays.get(i).getDay() == holiday.getDay()){
                System.out.println("Holiday already exists!");
                printHoliday(holidays);
                return 1;
            }
        }
        holidays.add(holiday);
        System.out.println("Holiday Added");
        printHoliday(holidays);
        return 0;
    }

    /*
     * method to remove holiday date
     * return 0 is successful, 1 if not found, 2 if the holiday arraylist is empty
     */

    public int deleteHoliday(ArrayList <HolidayDateTime> holidays, HolidayDateTime holiday){
        if (HolidayDateTime.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return 2;
        }
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++)
        {
            if (holidays.get(i).getYear() == holiday.getYear() && holidays.get(i).getMonth() == holiday.getMonth() && holidays.get(i).getDate() == holiday.getDate() && holidays.get(i).getHour() == holiday.getHour() && holidays.get(i).getMinute() == holiday.getMinute() && holidays.get(i).getDay() == holiday.getDay()){
                HolidayDateTime.holidays.remove(i);
                System.out.println("Holiday removed");
                printHoliday(holidays);
                return 0;
            }
        }
        System.out.println("Holiday cannot be found");
        printHoliday(holidays);
        return 1;
    }

    public void printHoliday(ArrayList <HolidayDateTime> holidays){
        if (holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return;
        }
        System.out.println("Here are the list of holidays:");
        for (int i = 0; i < holidays.size(); i++){
            holidays.get(i).printHolidayTime();
        }
        System.out.println("");
    }

    /*
     * sorts and prints the top five movies based on overallRating
     */

    public void printTopFiveOverallRatings(List<Movie> movies){
        Collections.sort(movies, new SortByOverallRating());
        Movie m;

        int top = 5;
        if (movies.size() < 5){
            top = movies.size();
            System.out.println("There are only " + top + " movies. Printing top " + top + " movies based on overall ratings:");
        }
        else {
            System.out.println("Printing top 5 movies based on overall ratings:");
        }

        for (int i = 0; i < top; i++) {
			m = movies.get(i);
            System.out.println("Rank " + (i+1) + ":");
			System.out.println("Movie Title: " + m.getMovieTitle() + " (" + m.getShowingStatus() + ")");
			System.out.println("Movie Type: " + m.getMovieType());
			System.out.println("Rated: " + m.getMovieAgeRating());
			System.out.println("Duration: " + m.getMovieDuration());
			System.out.println("Review rating: " + m.getMovieOverallReviewRating());
			System.out.println("Director: " + m.getMovieDirector());
			System.out.print("Cast: ");
			for (int j = 0; j < m.getMovieCast().size(); j++) {
				System.out.print(m.getMovieCast().get(j) + " ");
			}
			System.out.println("");
			System.out.println("Synopsis: " + m.getMovieSynopsis());
			// System.out.print("Showing venues: ");
			// for(int j=0; j<m.showingVenue.size(); j++){
			// System.out.print(m.showingVenue.get(j).getCinemaID() + " ");
			// }
			System.out.println("Showing time: ");
			for (int j = 0; j < m.getShowingTime().size(); j++) {
				System.out.print(m.getShowingTime().get(j).getMinute() + " min ");
				System.out.print(m.getShowingTime().get(j).getHour() + " hr ");
				System.out.print(m.getShowingTime().get(j).getDay() + " day ");
				System.out.print(m.getShowingTime().get(j).getDate() + "/");
				System.out.print(m.getShowingTime().get(j).getMonth() + "/");
				System.out.println(m.getShowingTime().get(j).getYear());
			}
			System.out.println("");
		}

    }



    /*
     * sorts and prints the top five movies based on ticketSales
     */

    // public void printTopFiveTicketSales(ArrayList <Movie> movies){
    //     Collections.sort(movies, new SortByTicketSales());
    //     for (int i = 0; i < 5; i++){
    //         System.out.println(movies.get(i));
    //     }
    // }

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
        else if (rating1 < rating2){
            return 1;
        }
        else return -1;
    } 
}

/*
 * creates custom comparator class to compare movieTicketSales
 * sorts the movies array list in descending order 
 */

// class SortByTicketSales implements Comparator<Movie>{
//     /*
//      * overrides the compare method
//      */
//     public int compare(Movie movie1, Movie movie2){
//         int ticketSales1 = movie1.getMovieTicketSales();
//         int ticketSales2 = movie2.getMovieTicketSales();

//         if (ticketSales1 == ticketSales2){
//             return 0;
//         }
//         else if (ticketSales1 < ticketSales2){
//             return 1;
//         }
//         else return -1;
//     }
// }
