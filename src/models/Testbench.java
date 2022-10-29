package models;

import java.util.*;
import models.movie.Adult;
import models.movie.BlockbusterMovie;
import models.movie.Child;
import models.movie.DateTime;
import models.movie.Movie;
import models.movie.MovieAgeRating;
import models.movie.MovieFactory;
import models.movie.MovieGoer;
import models.movie.MovieGoerFactory;
import models.movie.MovieReview;
import models.movie.MovieShowingStatus;
import models.movie.SeniorCitizen;
import models.movie.StandardMovie;
import models.movie.ThreeDMovie;

/*
 * Testbench file. Feel free to test your models here.
 */
public class Testbench {
    public static void main(String[] args) {
        /*
         * Modify the code here to test your models
         */
        // TestCinemaCineplexSeat();
        TestMovie();
    }

    /*
     * Create your own testbench function and run on main()
     */

    public static void TestMovie() {

        Scanner sc = new Scanner(System.in);

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
        movie1.printMovies(movie1.movies);

        MovieGoerFactory factory = new MovieGoerFactory();
        MovieGoer customer = factory.createMovieGoer("Adult", 1, "Yeek Sheng", "yeek123@gmail.com", "89200447", 20);
        // customer.buyTicket();
        customer.setUserMovieReview("Zootopia", "This is such a good movie!", 10);
        customer.setUserMovieReview("Zootopia", "Disney movie is the best!", 9);
        ArrayList<MovieReview> reviews = new ArrayList<MovieReview>();
        reviews = movie1.getMovieReviews();
        MovieReview review;
        for (int i = 0; i < reviews.size(); i++) {
            review = reviews.get(i);
            System.out.println(review.getReview());
        }
        System.out.println(movie1.getMovieOverallReviewRating());
    }

}