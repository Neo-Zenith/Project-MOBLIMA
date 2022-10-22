package models.user;
import java.util.*;


import models.movie.Movie;
import models.movie.MovieType;
import models.movie.AgeRating;
import models.movie.ShowingStatus;
import models.movie.DateTime;

import models.cinema.Cinema;


public class CinemaStaff extends HumanUser {
    private String name;
    private String password;
    private int staffID;

    public CinemaStaff(String name, int staffID, String password) {
        super(name, staffID);
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    /*
     * grants access to methods to staffs only if id and password matches
     * bugged atm need to fix soon
     */

    public boolean login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hi" + this.name + "Please login to access staff functions");
        System.out.println("Enter your staffID");
        int staffID = sc.nextInt();
        System.out.println(staffID);
        System.out.println(this.staffID);
        sc.nextLine();
        System.out.println("Enter your password");
        String password = sc.next();
        System.out.println(password);
        
        System.out.println(this.password);
        if ((password.equals(this.password)) && (staffID == this.staffID)){
            return true;
        }
        return false;
    }

    /*
     * Queries the CinemaStaff to set a new movie into the list of movies
     * Method that is only accessible to valid cinema staffs
     * Movie Type and Cinema Type not completed yet
     * MovieID starts from 0 and increments (because easier to use it is used to access the movies arraylist rather than searching through the arraylist every time to update.)
     */
    public void queryAndSetNewMovie(){
        Scanner sc = new Scanner(System.in);
        
        // if (login() == false){
        //     System.out.println("Invalid User, Exiting MOBLIMA");    
        //     return;
        // }       

        

        System.out.println("Adding New Movies - Enter any number to start adding and -1 to stop adding");
        int choice = sc.nextInt();
        sc.nextLine();

        while (choice != -1){
            int movieID = 0;

            System.out.println("Enter the title of movie ID " + movieID);
            String movieTitle =  sc.nextLine();

            System.out.println("Enter the type of movie of " + movieTitle + "(1 - ThreeDMovie, 2 - BlockbusterMovie, 3 - ImaxMovie)");
            MovieType movieType = new MovieType((float)0.0);
            
            System.out.println("Enter the age rating of " + movieTitle + "(G, PG, PG13, NC16, M18, R21)");
            AgeRating movieAgeRating = AgeRating.valueOf(sc.nextLine().toUpperCase());

            System.out.println("Enter the showing status of " + movieTitle + " (COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING)");
            ShowingStatus showingStatus = ShowingStatus.valueOf(sc.nextLine().toUpperCase());


            System.out.println("Enter the number of casts of " + movieTitle);
            int numOfCast = sc.nextInt();
            sc.nextLine();
            
            String[] movieCast = new String[numOfCast];
            String castName;

            for (int i = 0; i < numOfCast; i++){
                System.out.println("Enter the name of the cast " + (i + 1) + " of " + movieTitle);
                castName = sc.nextLine();
                movieCast[i] = castName;
            }

            System.out.println("Enter the director of " + movieTitle);
            String movieDirector = sc.nextLine();
            
            System.out.println("Enter the synopsis of " + movieTitle);
            String movieSynopsis = sc.nextLine();

            System.out.println("Enter the duration of " + movieTitle + " in minutes");
            float movieDuration = sc.nextFloat();
        
            
            System.out.println("Enter number of cinemas showing " + movieTitle);
            int numOfShowingCinemas = sc.nextInt();
            sc.nextLine();

            Cinema[] showingVenue = new Cinema[numOfShowingCinemas];
            int cinemaID;

            for (int i = 0; i < numOfShowingCinemas; i++){
                System.out.println("Enter the ID of cinema " + (i + 1));
                cinemaID = sc.nextInt();
                //showingVenue[i]
            }
        
            System.out.println("Enter the number of show times of " + movieTitle);
        
            int numOfShowingTimes = sc.nextInt();
            DateTime[] showingTimes = new DateTime[numOfShowingTimes];

            for(int i = 0; i < numOfShowingTimes; i++){
                System.out.println("Show Time " + (i + 1) + ":");
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
                
                
                DateTime showingTime = new DateTime(hour, minute, day, date, month, year);
                showingTimes[i] = showingTime;
            }
        
            Movie newMovie = new Movie(movieID, movieTitle, movieType, movieAgeRating, showingStatus, movieCast, movieDirector, movieSynopsis, movieDuration, showingVenue, showingTimes);
            Movie.movies.add(newMovie);

            System.out.println(movieTitle + " is successfully added into movie list!");
            System.out.println("Adding New Movies - Enter any number to start adding and -1 to stop adding");
            choice = sc.nextInt();
            sc.nextLine();
            movieID++;
        }
    }
    /*
     * Staff is able to update the details of the movie
     * Method that is only accessible to valid cinema staffs
     * Movie Type and Cinema Type not completed yet
     * Now only update values added, need to include add values and remove values (for array attributes, may need to change the into a arraylist to add/remove items.)
     * need to add a printMovieInfo under Movie class 
     */
    public void updateMovieDetails(){
        
        // if (login() == false){
        //     System.out.println("Invalid User, Exiting MOBLIMA");    
        //     return;
        // }       
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Updating Movie Details - Enter the movie ID to be updated (Enter -1 to exit)");
        int movieID = sc.nextInt();
        sc.nextLine();

        while (movieID != -1){
 
            System.out.println("What would you like to update for " + Movie.movies.get(movieID).getMovieTitle() + "? (Enter -1 to update details of other movies)");
            System.out.println("==============================");
            System.out.println("1. Movie Title");
            System.out.println("2. Movie Type");
            System.out.println("3. Movie Age Rating");
            System.out.println("4. Movie Showing Status");
            System.out.println("5. Movie Cast");
            System.out.println("6. Movie Director");
            System.out.println("7. Movie Synopsis");
            System.out.println("8. Movie Duration");
            System.out.println("9. Showing Venue");
            System.out.println("10. Showing Time");
            System.out.println("==============================");
        

            int choice = sc.nextInt();      
            sc.nextLine();
            while (choice <= 10 && choice >= 1 && choice != -1){
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
                System.out.println("What would you like to update for " + Movie.movies.get(movieID).getMovieTitle() + "? (Enter -1 to update details of other movies)");
                System.out.println("==============================");
                System.out.println("1. Movie Title");
                System.out.println("2. Movie Type");
                System.out.println("3. Movie Age Rating");
                System.out.println("4. Movie Showing Status");
                System.out.println("5. Movie Cast");
                System.out.println("6. Movie Director");
                System.out.println("7. Movie Synopsis");
                System.out.println("8. Movie Duration");
                System.out.println("9. Showing Venue");
                System.out.println("10. Showing Time");
                System.out.println("==============================");
            
                choice = sc.nextInt();
                sc.nextLine();
                
            }
            System.out.println("Enter the movie ID to be updated (Enter -1 to exit)");
            movieID = sc.nextInt();
            sc.nextLine();
        }    
    }
}

