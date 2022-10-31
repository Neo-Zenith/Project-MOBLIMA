package models.staff;

import models.staff.HolidayDateTime;

import models.movie.Movie;
import models.movie.MovieFactory;
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

    public void updateExistingMovieDetails(String movieTitle, String movieType, int choice){
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

            String oldMovieTitle = m.getMovieTitle();
            MovieAgeRating oldMovieAgeRating = m.getMovieAgeRating();
            MovieShowingStatus oldShowingStatus = m.getShowingStatus();
            ArrayList<String> oldMovieCast = m.getMovieCast(); 
            String oldMovieDirector = m.getMovieDirector();
            String oldMovieSynopsis = m.getMovieSynopsis();
            double oldMovieDuration = m.getMovieDuration();
            ArrayList<DateTime> oldShowingTime = m.getShowingTime();
            Movie.movies.remove(m);

            Movie newMovie;
            MovieFactory movieFactory = new MovieFactory();
            newMovie = movieFactory.createMovie(oldMovieTitle, newMovieType, oldMovieAgeRating, oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis, oldMovieDuration, oldShowingTime);
            Movie.movies.add(newMovie);
            System.out.println(m.getMovieTitle() + "'s Movie type changed to " + newMovie.getMovieType());
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
            System.out.println("Would you like to 1.edit, 2.remove or 3.add cast members:");
            int innerchoice = sc.nextInt();
            sc.nextLine();
                
            switch(innerchoice){
                case 1:
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

                case 2: 
                System.out.println("Enter which cast number is to be removed. (Enter Cast Number)");
                for (int i = 0; i < m.getMovieCast().size(); i++){
                    System.out.println("Cast Number " + (i+1) + ": " + m.getMovieCast().get(i));
                }
                castNumber = sc.nextInt();
                sc.nextLine();
                m.getMovieCast().remove(castNumber - 1);
                System.out.println("Cast removed");
                break;

                case 3:
                System.out.println("Enter the name of the cast to be added.");
                String castName = sc.nextLine();
                m.getMovieCast().add(castName);
                System.out.println("Cast added");
                break;
            }

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
            System.out.println("Would you like to 1. edit, 2. remove or 3. add showing times:");
            innerchoice = sc.nextInt();
            sc.nextLine();
            switch(innerchoice){
                case 1:
                System.out.println("Enter which showing time is to be updated. (Enter ID number)");
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

                case 2:
                System.out.println("Enter which showing time is to be removed. (Enter ID number)");
                for (int i = 0; i < m.getShowingTime().size(); i++){
                    System.out.println((i+1) + " : " + "Date: " + m.getShowingTime().get(i).getYear() + m.getShowingTime().get(i).getMonth() + m.getShowingTime().get(i).getDate() + " Time: " + m.getShowingTime().get(i).getHour() + m.getShowingTime().get(i).getMinute());
                }
                showingTimeID = sc.nextInt();
                sc.nextLine();
                m.getMovieCast().remove(showingTimeID- 1);
                System.out.println("Showing time removed.");
                break;

                case 3:
                System.out.println("Enter the time to be added.");

                System.out.println("year:");
                year = sc.nextInt();
                System.out.println("month:");
                month = sc.nextInt();
                System.out.println("date:");
                date = sc.nextInt();
                System.out.println("hour:");
                hour = sc.nextInt();
                System.out.println("minute:");
                minute = sc.nextInt();
                System.out.println("day:");
                day = sc.nextInt();

                newShowingTime = new DateTime(minute, hour, day, date, month, year);
                
                m.getShowingTime().add(newShowingTime);
                System.out.println("New showing time successfully added");
                break;
            }
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
    // public void changeTicketPrice(String movieName, String movieType){
    //         Scanner sc = new Scanner(System.in);
    //         System.out.println("1. SeatPrice");
    //         double newSeatPrice = sc.nextDouble();
    //         System.out.println("2. Cinema Price");
    //         double newCinemaPrice = sc.nextDouble();
    //         System.out.println("3. MovieType Price");
    //         System.out.println("Standard Movie Price");
    //         double newStandardPrice = sc.nextDouble();
    //         System.out.println("ThreeD Movie Price");
    //         double newThreeDPrice = sc.nextDouble();
    //         System.out.println("Blockbuster Movie Price");
    //         double newBlockBusterPrice = sc.nextDouble();
    //         ThreeDMovie.setPrice(newBlockBusterPrice);

            
    //         double newMoviePrice = sc.nextDouble();


    //     }
        
    // }

    /*
     * method to add holiday date
     * holidays is an arraylist of holiday
     * return 0 if holiday added
     * return 1 if holiday is already in holidays list
     */
    public int addHoliday(HolidayDateTime holiday){
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++)
        {
            if (HolidayDateTime.holidays.get(i).getYear() == holiday.getYear() && HolidayDateTime.holidays.get(i).getMonth() == holiday.getMonth() && HolidayDateTime.holidays.get(i).getDate() == holiday.getDate() && HolidayDateTime.holidays.get(i).getHour() == holiday.getHour() && HolidayDateTime.holidays.get(i).getMinute() == holiday.getMinute() && HolidayDateTime.holidays.get(i).getDay() == holiday.getDay()){
                System.out.println("Holiday already exists!");
                printHolidayList();
                return 1;
            }
        }
        HolidayDateTime.holidays.add(holiday);
        System.out.println("Holiday Added");
        printHolidayList();
        return 0;
    }

    /*
     * method to remove holiday date
     * return 0 is successful, 1 if not found, 2 if the holiday arraylist is empty
     */

    public int deleteHoliday(HolidayDateTime holiday){
        if (HolidayDateTime.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return 2;
        }
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++)
        {
            if (HolidayDateTime.holidays.get(i).getYear() == holiday.getYear() && HolidayDateTime.holidays.get(i).getMonth() == holiday.getMonth() && HolidayDateTime.holidays.get(i).getDate() == holiday.getDate() && HolidayDateTime.holidays.get(i).getHour() == holiday.getHour() && HolidayDateTime.holidays.get(i).getMinute() == holiday.getMinute() && HolidayDateTime.holidays.get(i).getDay() == holiday.getDay()){
                HolidayDateTime.holidays.remove(i);
                System.out.println("Holiday removed");
                printHolidayList();
                return 0;
            }
        }
        System.out.println("Holiday cannot be found");
        printHolidayList();
        return 1;
    }

    public void printHolidayList(){
        if (HolidayDateTime.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return;
        }
        System.out.println("Here are the list of holidays:");
        for (int i = 0; i < HolidayDateTime.holidays.size(); i++){
            HolidayDateTime.holidays.get(i).printHolidayTime();
        }
        System.out.println("");
    }

    /*
     * sorts and prints the top five movies based on overallRating
     */

    public void printTopFiveOverallRatings(){
        Collections.sort(Movie.movies, new SortByOverallRating());
        Movie m;

        int top = 5;
        if (Movie.movies.size() < 5){
            top = Movie.movies.size();
            System.out.println("There are only " + top + " movies. Printing top " + top + " movies based on overall ratings:");
        }
        else {
            System.out.println("Printing top 5 movies based on overall ratings:");
        }

        for (int i = 0; i < top; i++) {
			m = Movie.movies.get(i);
            System.out.println("Rank " + (i+1) + ":");
			System.out.println("Movie Title: " + m.getMovieTitle() + " (" + m.getShowingStatus() + ")");
			System.out.println("Overall Review Rating: " + m.getMovieOverallReviewRating());
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
