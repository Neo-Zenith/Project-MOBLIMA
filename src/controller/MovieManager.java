package controller;

import java.util.ArrayList;

import model.BlockbusterMovie;
import model.Cinema;
import model.Cineplex;
import model.StandardMovie;
import model.ThreeDMovie;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.Movie;
import model.MovieReview;
import model.DateTime;
import handler.DatabaseHandler;
import database.Database;
import model.MovieSchedule;

public class MovieManager {

    public static Movie createStandardMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new StandardMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static Movie createBlockbusterMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new BlockbusterMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static Movie createThreeDMovie(String movieTitle, MovieAgeRating movieAgeRating,
            MovieShowingStatus showingStatus,
            ArrayList<String> movieCast, String movieDirector, String movieSynopsis, double movieDuration,
            double moviePrice) {
        String UUID = String.format("MV%03d", DatabaseHandler.generateUUID(Database.MOVIE));
        Movie movie = new ThreeDMovie(UUID, movieTitle, movieAgeRating, showingStatus,
                movieCast, movieDirector, movieSynopsis, movieDuration, moviePrice);
        DatabaseManager.saveUpdateToDatabase(UUID, movie, Database.MOVIE);
        return movie;
    }

    public static void updateMovieTicketSold(Movie movie) {
        int ticket = movie.getMovieTicketsSold();
        ticket++;
        movie.setMovieTicketsSold(ticket);
    }

    public static void calculateOverallReviewRating(Movie movie) {
        if (movie.getMovieReviews().size() == 0) {
            movie.setMovieOverallReviewRating(0.0);
            return;
        }

        double overallRating = 0;
        for (int i = 0; i < movie.getMovieReviews().size(); i++) {
            overallRating += movie.getMovieReviews().get(i).getMovieReviewRating();
        }
        movie.setMovieOverallReviewRating(overallRating / movie.getMovieReviews().size());
    }


    public static void writeMovieReview(Movie movie, MovieReview newReview) {
        movie.getMovieReviews().add(newReview);
    }


    public static ArrayList <Movie> getAllMovieList() {
        return Database.getValueList(Database.MOVIE.values());
    }

    public static ArrayList <Movie> getMovieList(Movie movie) {
        ArrayList <Movie> movies = new ArrayList<>();
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        for (int i = 0; i < movieSchedules.size(); i++) {
            MovieSchedule movieSchedule = movieSchedules.get(i);
            Movie _movie = movieSchedule.getMovieOnShow();
            if (_movie.getMovieTitle().equals(movie.getMovieTitle())) {
                movies.add(_movie);
            }
        }
        return movies;
    }
 
    public static void printMovieList(){
        ArrayList<MovieSchedule> schedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());
        ArrayList<String> movieTitles = new ArrayList<>();
        if(schedules.size() == 0){
            System.out.println("No Movie found!!");
        }
        
        //getting arraylist of unique movie titles
        for(int i = 0; i<schedules.size(); i++){
            String movieTitle = schedules.get(i).getMovieOnShow().getMovieTitle();
            if(!movieTitles.contains(movieTitle)){
                movieTitles.add(movieTitle);
            }     
        }

        for(int i = 0; i< movieTitles.size(); i++){
            String movieTitle = movieTitles.get(i);
            //printing out the movie details
            for(int j = 0; j<schedules.size(); j++){
                if(schedules.get(j).getMovieOnShow().getMovieTitle().equals(movieTitle)){
                    System.out.println("Movie Title: " + schedules.get(j).getMovieOnShow().getMovieTitle());
                    System.out.println("Showing Status: " + schedules.get(j).getMovieOnShow().getMovieShowingStatus());
                    System.out.println("Synopsis: " + schedules.get(j).getMovieOnShow().getMovieSynopsis());
                    System.out.println("Director: " + schedules.get(j).getMovieOnShow().getMovieDirector());
                    System.out.print("Cast: ");
                    for(int k = 0; k < schedules.get(j).getMovieOnShow().getMovieCast().size(); k++) {
                        System.out.print(schedules.get(j).getMovieOnShow().getMovieCast().get(k) + " ");
                    }
                    System.out.println("");
                    for(int m = 0; m<schedules.get(j).getMovieOnShow().getMovieReviews().size(); m++){
                        System.out.println("Review " + (m+1) + ": " + schedules.get(j).getMovieOnShow().getMovieReviews().get(m));
                    }
                    System.out.println("Review Rating: " +schedules.get(j).getMovieOnShow().getMovieOverallReviewRating());
                    System.out.println("Age Rating: " + schedules.get(j).getMovieOnShow().getMovieAgeRating());
                    System.out.println("Duration: " + schedules.get(j).getMovieOnShow().getMovieDuration());
                    System.out.println("Tickets sold:" + schedules.get(j).getMovieOnShow().getMovieTicketsSold());
                    break;
                }
            }
            //printing out movie schedule
            System.out.println("-----------------------------------------------------------");
            for(int j = 0; j<schedules.size(); j++){
                if(schedules.get(j).getMovieOnShow().getMovieTitle().equals(movieTitle)){
                    System.out.print("Movie UUID: " + schedules.get(j).getMovieOnShow().getUUID());
                    System.out.println(" [" + schedules.get(j).getMovieOnShow().getMovieType() + "] ");
                    System.out.println("___________");
                    for(int k=0; k<schedules.get(j).getShowingVenues().size(); k++){
                        System.out.print("Cinema: " + schedules.get(j).getShowingVenues().get(k).getUUID());
                        System.out.print(" Showing at: ");
                        schedules.get(j).getShowingTime().get(k).printTime();
                        System.out.println("");
                    }
                    System.out.println("");
                }

            }
        }
        return;
    }
}