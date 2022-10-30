package models;
import java.util.*;

import models.movie.*;
import models.movie.MovieGoerFactory;
import models.movie.MovieAgeRating;
import models.movie.MovieShowingStatus;
import models.movie.DateTime;



import models.staff.*;

/*
 * Testbench file. Feel free to test your models here.
 */
public class Testbench {
    public static void main(String [] args) {
        /*
         * Modify the code here to test your models
         */
        // TestCinemaCineplexSeat();
        TestStaff();
    }


    /*
     * Create your own testbench function and run on main()
     */
    
    public static void TestStaff(){
        
        Staff newStaff = new Staff("Jonathan", "testpass1", 1);
        Scanner sc = new Scanner(System.in);
        
        HolidayDateTime holiday1 = new HolidayDateTime(20, 17, 5, 21, 7, 2022);
        HolidayDateTime holiday2 = new HolidayDateTime(20, 17, 5, 21, 7, 2022);
            
        newStaff.addHoliday(HolidayDateTime.holidays, holiday1);
        newStaff.addHoliday(HolidayDateTime.holidays, holiday2);
        newStaff.deleteHoliday(HolidayDateTime.holidays, holiday2);

        System.out.println("");
        MovieFactory movieFactory = new MovieFactory();

        ArrayList<String> cast = new ArrayList<String>();
        cast.add("cat");
        cast.add("dog");

        DateTime date1 = new DateTime(20, 17, 5, 21, 7, 2022);
        DateTime date2 = new DateTime(20, 17, 5, 23, 9, 2022);
        ArrayList<DateTime> dateList = new ArrayList<DateTime>();
        dateList.add(date1);
        dateList.add(date2);

        Movie movie1 = movieFactory.createMovie("Zootopia", "Blockbuster",
                MovieAgeRating.G, MovieShowingStatus.NOW_SHOWING,
                cast, "Rabbit", "So good", 50.6, dateList);
        
        movie1.setMovieTitle("Zootopia");

        Movie movie2 = movieFactory.createMovie("Avenger", "Blockbuster",
                MovieAgeRating.G, MovieShowingStatus.NOW_SHOWING,
                cast, "Rabbit", "So good", 50.6, dateList);
        // movie2.setMovieTitle("Avenger");

        Movie movie3 = movieFactory.createMovie("Avenger", "ThreeD",
                MovieAgeRating.G, MovieShowingStatus.NOW_SHOWING,
                cast, "Rabbit", "So good", 50.6, dateList);
        movie1.printMovies(Movie.movies);

        MovieGoerFactory factory = new MovieGoerFactory();
        MovieGoer customer = factory.createMovieGoer("Adult", 1, "Yeek Sheng", "yeek123@gmail.com", "89200447", 20);
        // customer.buyTicket();
        customer.setUserMovieReview("Zootopia", "Blockbuster", "This is such a good movie!", 10);
        customer.setUserMovieReview("Zootopia", "Blockbuster", "Disney movie is the best!", 9);

        customer.setUserMovieReview("Avenger", "Blockbuster","This is such a good movie!", 7);
        customer.setUserMovieReview("Avenger", "ThreeD","This is such a good movie!", 9);
        

        ArrayList<MovieReview> reviews = new ArrayList<MovieReview>();
        reviews = movie1.getMovieReviews();
        MovieReview review;
        for (int i = 0; i < reviews.size(); i++) {
            review = reviews.get(i);
            System.out.println(review.getReview());
        }
        System.out.println(movie1.getMovieOverallReviewRating());
        
        
        for (int i = 1; i <= 10; i++){
            newStaff.updateExistingMovieDetails(Movie.movies, "Avenger", "ThreeD", i);
        }    
        
        newStaff.printTopFiveOverallRatings(Movie.movies);

    }

}
