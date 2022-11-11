package src.model;

import java.io.Serializable;
import java.util.*;

import src.controller.*;
import src.database.*;
import src.model.enums.*;

/**
 * Stores and modifies all the default prices of each weight in a movie ticket
 * 
 * @author Lee Juin
 * @version 1.0
 */
public class Prices implements Serializable {
    /**
     * Default standard cinema price
     */
    private double defaultStandardCinemaPrice;

    /**
     * Default platinum cinema price
     */
    private double defaultPlatinumCinemaPrice;

    /**
     * Default IMAX cinema price
     */
    private double defaultIMaxCinemaPrice;

    /**
     * Default seat price
     */
    private double defaultSeatPrice;

    /**
     * Default blockbuster movie price
     */
    private double defaultBlockbusterMoviePrice;

    /**
     * Default 3D movie price
     */
    private double default3DMoviePrice;

    /**
     * Default standard movie price
     */
    private double defaultStandardMoviePrice;

    /**
     * Default child ticket price
     */
    private double defaultChildPrice;

    /**
     * Default student ticket price
     */
    private double defaultStudentPrice;

    /**
     * Default adult ticket price
     */
    private double defaultAdultPrice;

    /**
     * Default senior citizen ticket price
     */
    private double defaultSeniorCitizenPrice;

    /**
     * Default holiday ticket price
     */
    private double holidayPrice;

    /**
     * Default weekend ticket price
     */
    private double weekendPrice;
    private final static long serialVersionUID = 15L;

    /**
     * Empty constructor
     */
    public Prices() {
    }

    /**
     * Constructor to set the prices back to default
     * 
     * @param defaultStandardCinemaPrice   is the default standard cinema price
     *                                     weight
     * @param defautltPlatinumCinemaPrice  is the default platinum cinema price
     *                                     weight
     * @param defaultIMaxCinemaPrice       is the default IMAX cinema price weight
     * @param defaultSeatPrice             is the default seat price weight
     * @param defaultBlockbusterMoviePrice is the default blockbuster movie price
     *                                     weight
     * @param default3DMoviePrice          is the default 3D movie price weight
     * @param defaultStandardMoviePrice    is the default standard movie price
     *                                     weight
     * @param defaultChildPrice            is the default child ticket price weight
     * @param defaultStudentPrice          is the default student ticket price
     *                                     weight
     * @param defaultAdultPrice            is the default adult ticket price weight
     * @param defaultSeniorCitizenPrice    is the default senior citizen ticket
     *                                     price weight
     * @param holidayPrice                 is the default holiday price weight
     * @param weekendPrice                 is the default weekend price weight
     */
    public Prices(double defaultStandardCinemaPrice,
            double defautltPlatinumCinemaPrice,
            double defaultIMaxCinemaPrice,
            double defaultSeatPrice,
            double defaultBlockbusterMoviePrice,
            double default3DMoviePrice,
            double defaultStandardMoviePrice,
            double defaultChildPrice,
            double defaultStudentPrice,
            double defaultAdultPrice,
            double defaultSeniorCitizenPrice,
            double holidayPrice,
            double weekendPrice) {

        this.setDefault3DMoviePrice(default3DMoviePrice);
        this.setDefaultAdultPrice(defaultAdultPrice);
        this.setDefaultBlockbusterMoviePrice(defaultBlockbusterMoviePrice);
        this.setDefaultChildPrice(defaultChildPrice);
        this.setDefaultIMaxCinemaPrice(defaultIMaxCinemaPrice);
        this.setDefaultPlatinumCinemaPrice(defautltPlatinumCinemaPrice);
        this.setDefaultSeatPrice(defaultSeatPrice);
        this.setDefaultSeniorCitizenPrice(defaultSeniorCitizenPrice);
        this.setDefaultStandardCinemaPrice(defaultStandardCinemaPrice);
        this.setDefaultStandardMoviePrice(defaultStandardMoviePrice);
        this.setDefaultStudentPrice(defaultStudentPrice);
        this.setHolidayPrice(holidayPrice);
        this.setWeekendPrice(weekendPrice);
    }

    /**
     * Gets the default standard cinema price weight
     * 
     * @return The default standard cinema price weight
     */
    public double getDefaultStandardCinemaPrice() {
        return this.defaultStandardCinemaPrice;
    }

    /**
     * Gets the default IMAX cinema price weight
     * 
     * @return The default IMAX cinema price weight
     */
    public double getDefaultIMaxCinemaPrice() {
        return this.defaultIMaxCinemaPrice;
    }

    /**
     * Gets the default platinum cinema price weight
     * 
     * @return The default platinum cinema price weight
     */
    public double getDefaultPlatinumCinemaPrice() {
        return this.defaultPlatinumCinemaPrice;
    }

    /**
     * Sets the default standard cinema price weight
     * 
     * @param defaultStandardCinemaPrice is the default standard cinema price weight
     */
    public void setDefaultStandardCinemaPrice(double defaultStandardCinemaPrice) {
        this.defaultStandardCinemaPrice = defaultStandardCinemaPrice;
        ArrayList<Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());
        for (int i = 0; i < cineplexs.size(); i++) {
            Cineplex cineplex = cineplexs.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                if (cinema.getCinemaClass() == CinemaClass.STANDARD) {
                    cinema.setCinemaPrice(defaultStandardCinemaPrice);
                }
                DatabaseManager.saveUpdateToBuffer(cinema.getUUID(), cinema, Database.CINEMA);
            }
            DatabaseManager.saveUpdateToBuffer(cineplex.getUUID(), cineplex, Database.CINEPLEX);
        }
    }

    /**
     * Sets the default IMAX cinema price weight
     * 
     * @param defaultIMaxCinemaPrice is the default IMAX cinema price weight
     */
    public void setDefaultIMaxCinemaPrice(double defaultIMaxCinemaPrice) {
        this.defaultIMaxCinemaPrice = defaultIMaxCinemaPrice;
        ArrayList<Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());
        for (int i = 0; i < cineplexs.size(); i++) {
            Cineplex cineplex = cineplexs.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                if (cinema.getCinemaClass() == CinemaClass.IMAX) {
                    cinema.setCinemaPrice(defaultIMaxCinemaPrice);
                }
                DatabaseManager.saveUpdateToBuffer(cinema.getUUID(), cinema, Database.CINEMA);
            }
            DatabaseManager.saveUpdateToBuffer(cineplex.getUUID(), cineplex, Database.CINEPLEX);
        }
    }

    /**
     * Sets the default platinum cinema price weight
     * 
     * @param defaultPlatinumCinemaPrice is the default platinum cinema price weight
     */
    public void setDefaultPlatinumCinemaPrice(double defaultPlatinumCinemaPrice) {
        this.defaultPlatinumCinemaPrice = defaultPlatinumCinemaPrice;
        ArrayList<Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());
        for (int i = 0; i < cineplexs.size(); i++) {
            Cineplex cineplex = cineplexs.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                if (cinema.getCinemaClass() == CinemaClass.PLATINUM) {
                    cinema.setCinemaPrice(defaultPlatinumCinemaPrice);
                }
                DatabaseManager.saveUpdateToBuffer(cinema.getUUID(), cinema, Database.CINEMA);
            }
            DatabaseManager.saveUpdateToBuffer(cineplex.getUUID(), cineplex, Database.CINEPLEX);
        }
    }

    /**
     * Gets the default seat price weight
     * 
     * @return The default seat price weight
     */
    public double getDefaultSeatPrice() {
        return this.defaultSeatPrice;
    }

    /**
     * Sets the default seat price weight
     * 
     * @param defaultSeatPrice is the default seat price weight
     */
    public void setDefaultSeatPrice(double defaultSeatPrice) {
        this.defaultSeatPrice = defaultSeatPrice;
        ArrayList<Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());
        for (int i = 0; i < cineplexs.size(); i++) {
            Cineplex cineplex = cineplexs.get(i);
            for (int j = 0; j < cineplex.getCinemas().size(); j++) {
                Cinema cinema = cineplex.getCinemas().get(j);
                for (int k = 0; k < cinema.getSeats().size(); k++) {
                    Seat seat = cinema.getSeats().get(k);
                    seat.setSeatPrice(defaultSeatPrice);
                    DatabaseManager.saveUpdateToBuffer(seat.getUUID(), seat, Database.SEAT);
                }
                DatabaseManager.saveUpdateToBuffer(cinema.getUUID(), cinema, Database.CINEMA);
            }
            DatabaseManager.saveUpdateToBuffer(cineplex.getUUID(), cineplex, Database.CINEPLEX);
        }
    }

    /**
     * Gets the default blockbuster movie price weight
     * 
     * @return The default blockbuster movie price weight
     */
    public double getDefaultBlockbusterMoviePrice() {
        return this.defaultBlockbusterMoviePrice;
    }

    /**
     * Gets the default 3D movie price weight
     * 
     * @return The default 3D movie price weight
     */
    public double getDefault3DMoviePrice() {
        return this.default3DMoviePrice;
    }

    /**
     * Gets the default standard movie price weight
     * 
     * @return The default standard movie price weight
     */
    public double getDefaultStandardMoviePrice() {
        return this.defaultStandardMoviePrice;
    }

    /**
     * Sets the default blockbuster movie price weight
     * 
     * @param defaultBlockbusterMoviePrice is the default blockbuster movie price
     *                                     weight
     */
    public void setDefaultBlockbusterMoviePrice(double defaultBlockbusterMoviePrice) {
        this.defaultBlockbusterMoviePrice = defaultBlockbusterMoviePrice;
        ArrayList<Movie> movies = Database.getValueList(Database.MOVIE.values());
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getMovieType() == MovieType.Blockbuster) {
                movie.setMoviePrice(defaultBlockbusterMoviePrice);
                DatabaseManager.saveUpdateToBuffer(movie.getUUID(), movie, Database.MOVIE);
            }
        }
    }

    /**
     * Sets the default 3D movie price weight
     * 
     * @param default3DMoviePrice is the default 3D movie price weight
     */
    public void setDefault3DMoviePrice(double default3DMoviePrice) {
        this.default3DMoviePrice = default3DMoviePrice;
        ArrayList<Movie> movies = Database.getValueList(Database.MOVIE.values());
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getMovieType() == MovieType.ThreeD) {
                movie.setMoviePrice(default3DMoviePrice);
                DatabaseManager.saveUpdateToBuffer(movie.getUUID(), movie, Database.MOVIE);
            }
        }
    }

    /**
     * Sets the default standard movie price weight
     * 
     * @param defaultStandardMoviePrice is the default standard movie price weight
     */
    public void setDefaultStandardMoviePrice(double defaultStandardMoviePrice) {
        this.defaultStandardMoviePrice = defaultStandardMoviePrice;
        ArrayList<Movie> movies = Database.getValueList(Database.MOVIE.values());
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            if (movie.getMovieType() == MovieType.Standard) {
                movie.setMoviePrice(defaultStandardMoviePrice);
                DatabaseManager.saveUpdateToBuffer(movie.getUUID(), movie, Database.MOVIE);
            }
        }
    }

    /**
     * Gets the default child ticket price weight
     * 
     * @return The default child ticket price weight
     */
    public double getDefaultChildPrice() {
        return this.defaultChildPrice;
    }

    /**
     * Gets the default student ticket price weight
     * 
     * @return The default student ticket price weight
     */
    public double getDefaultStudentPrice() {
        return this.defaultStudentPrice;
    }

    /**
     * Gets the default adult ticket price weight
     * 
     * @return The default adult ticket price weight
     */
    public double getDefaultAdultPrice() {
        return this.defaultAdultPrice;
    }

    /**
     * Gets the default senior citizen ticket price weight
     * 
     * @return The default senior citizen ticket price weight
     */
    public double getDefaultSeniorCitizenPrice() {
        return this.defaultSeniorCitizenPrice;
    }

    /**
     * Sets the default child ticket price weight
     * 
     * @param defaultChildPrice is the default child ticket price weight
     */
    public void setDefaultChildPrice(double defaultChildPrice) {
        this.defaultChildPrice = defaultChildPrice;
        ArrayList<MovieGoer> movieGoers = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < movieGoers.size(); i++) {
            MovieGoer movieGoer = movieGoers.get(i);
            if (movieGoer.getMovieGoerAge() == MovieGoerAge.Child) {
                movieGoer.setGoerPrice(defaultChildPrice);
                DatabaseManager.saveUpdateToBuffer(movieGoer.getUUID(), movieGoer, Database.MOVIE_GOER);
            }
        }
    }

    /**
     * Sets the default student ticket price weight
     * 
     * @param defaultStudentPrice is the default student ticket price weight
     */
    public void setDefaultStudentPrice(double defaultStudentPrice) {
        this.defaultStudentPrice = defaultStudentPrice;
        ArrayList<MovieGoer> movieGoers = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < movieGoers.size(); i++) {
            MovieGoer movieGoer = movieGoers.get(i);
            if (movieGoer.getMovieGoerAge() == MovieGoerAge.Student) {
                movieGoer.setGoerPrice(defaultStudentPrice);
                DatabaseManager.saveUpdateToBuffer(movieGoer.getUUID(), movieGoer, Database.MOVIE_GOER);
            }
        }
    }

    /**
     * Sets the default adult ticket price weight
     * 
     * @param defaultAdultPrice is the default adult ticket price weight
     */
    public void setDefaultAdultPrice(double defaultAdultPrice) {
        this.defaultAdultPrice = defaultAdultPrice;
        ArrayList<MovieGoer> movieGoers = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < movieGoers.size(); i++) {
            MovieGoer movieGoer = movieGoers.get(i);
            if (movieGoer.getMovieGoerAge() == MovieGoerAge.Adult) {
                movieGoer.setGoerPrice(defaultAdultPrice);
                DatabaseManager.saveUpdateToBuffer(movieGoer.getUUID(), movieGoer, Database.MOVIE_GOER);
            }
        }
    }

    /**
     * Sets the default senior citizen ticket price weight
     * 
     * @param defaultSeniorCitizenPrice is the default senior citizen ticket price
     *                                  weight
     */
    public void setDefaultSeniorCitizenPrice(double defaultSeniorCitizenPrice) {
        this.defaultSeniorCitizenPrice = defaultSeniorCitizenPrice;
        ArrayList<MovieGoer> movieGoers = Database.getValueList(Database.MOVIE_GOER.values());
        for (int i = 0; i < movieGoers.size(); i++) {
            MovieGoer movieGoer = movieGoers.get(i);
            if (movieGoer.getMovieGoerAge() == MovieGoerAge.SeniorCitizen) {
                movieGoer.setGoerPrice(defaultSeniorCitizenPrice);
                DatabaseManager.saveUpdateToBuffer(movieGoer.getUUID(), movieGoer, Database.MOVIE_GOER);
            }
        }
    }

    /**
     * Gets the default holiday ticket price weight
     * 
     * @return The default holiday ticket price weight
     */
    public double getHolidayPrice() {
        return this.holidayPrice;
    }

    /**
     * Gets the default weekend ticket price weight
     * 
     * @return The default weekend ticket price weight
     */
    public double getWeekendPrice() {
        return this.weekendPrice;
    }

    /**
     * Sets the default holiday ticket price weight
     * 
     * @param holidayPrice is the default holiday ticket price weight
     */
    public void setHolidayPrice(double holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    /**
     * Sets the default weekend ticket price weight
     * 
     * @param weekendPrice is the default weekend ticket price weight
     */
    public void setWeekendPrice(double weekendPrice) {
        this.weekendPrice = weekendPrice;
    }
}
