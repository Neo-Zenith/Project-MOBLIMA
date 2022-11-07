package controller;

import java.util.*;
import database.*;
import model.*;
import model.enums.*;


public class DatabaseManager {

    public DatabaseManager() {
    }

    
    public static void initializeCineplexData() {
        ArrayList <Cineplex> cineplexs = Database.getValueList(Database.CINEPLEX.values());

        if (cineplexs.size() != 0) {
            System.out.println("Error! Existing cineplex data in the database!");
            System.out.println("Consider resetting database before loading initial data!");
            return;
        }

        String cineplexName;
        String cineplexLocation;
        int numOfCinemas;
        ArrayList<Cinema> cinemas = new ArrayList<>();

        // Data 1
        cineplexName = "CATHAY CINEPLEX CAUSEWAY POINT";
        cineplexLocation = """
            1 Woodlands Square
            Level 7, Causeway Point
            Singapore 738099
                """;
        numOfCinemas = 4;
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 2
        cineplexName = "CATHAY CINEPLEX AMK HUB";
        cineplexLocation = """
            53 Ang Mo Kio Ave 3
            Level 4, AMK Hub
            Singapore 569933
                """;
        numOfCinemas = 6;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        // Data 3
        cineplexName = "CATHAY CINEPLEX JEM";
        cineplexLocation = """
            50 Jurong Gateway Road
            Level 5, Jem
            Singapore 608549
                """;
        numOfCinemas = 5;
        cinemas = new ArrayList<>();
        for (int i = 0; i < numOfCinemas; i++) {
            cinemas.add(initializeCinemaData(i % 3));
        }
        CineplexManager.createCineplex(cineplexName, numOfCinemas, cinemas, cineplexLocation);

        System.out.println("Cineplex data loaded successfully!");
    }

    
    public static Cinema initializeCinemaData(int choice) {
        CinemaClass cinemaClass;
        ArrayList <Seat> seats; 

        switch (choice) {
            case 0:
                // Data 1
                cinemaClass = CinemaClass.STANDARD;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createStandardCinema(seats);
            case 1:
                // Data 2
                cinemaClass = CinemaClass.IMAX;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createIMaxCinema(seats);

            case 2:
                // Data 3
                cinemaClass = CinemaClass.PLATINUM;
                seats = initializeSeatData(cinemaClass);
                return CinemaManager.createPlatinumCinema(seats);
        }
        return null;
    }

    
    public static ArrayList <Seat> initializeSeatData(CinemaClass cinemaClass) {
        int numOfSeatsPerRow;
        ArrayList <Seat> seats = new ArrayList<>();

        if (cinemaClass != CinemaClass.PLATINUM) {
            numOfSeatsPerRow = Database.totalNumOfSeats / Database.numOfRows;

            for (int i = 0; i < Database.numOfCoupleRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createCoupleSeat());
                }
            }

            for (int i = 0; i < Database.numOfRows - Database.numOfCoupleRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat());
                }
            }
        }
        else {
            numOfSeatsPerRow = Database.platinumNumOfSeatsPerRow;
            int numofRows = Database.platinumNumOfRow;

            for (int i = 0; i < numofRows; i ++) {
                for (int j = 0; j < numOfSeatsPerRow; j ++) {
                    seats.add(SeatManager.createStandardSeat());
                }
            }

        }
        return seats;
    }


    public static void initializeMovieScheduleData() {
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());

        if (movieSchedules.size() != 0) {
            System.out.println("Error! Existing movie schedule data in the database!");
            System.out.println("Consider resetting database before loading initial data!");
            return;
        }

        Movie movieOnShow;
        ArrayList <Cinema> showingVenue;
        ArrayList <String> showingVenueUUID;
        ArrayList <ArrayList <Seat>> seatingPlan = new ArrayList<ArrayList<Seat>>();
        ArrayList <DateTime> showingTime = new ArrayList<DateTime>();
        CinemaClass cinemaClass;
        Cineplex cineplex;
        ArrayList <Cineplex> cineplexes = Database.getValueList(Database.CINEPLEX.values());
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        Collections.sort(cineplexes);

        // Data 1
        movieOnShow = movies.get(0);
        cinemaClass = CinemaClass.PLATINUM;
        cineplex = cineplexes.get(0);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 14, 6, 21, 11, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);

        // Data 2
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(1);
        cinemaClass = CinemaClass.STANDARD;
        cineplex = cineplexes.get(2);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(00, 13, 4, 22, 12, 2022));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);

        // Data 3  
        showingTime = new ArrayList<>();
        movieOnShow = movies.get(2);
        cinemaClass = CinemaClass.IMAX;
        cineplex = cineplexes.get(1);
        showingVenue = new ArrayList<>();
        showingVenueUUID = new ArrayList<>();
        seatingPlan = new ArrayList<>();
        showingVenue.addAll(CinemaManager.filterCinemaByClass(cinemaClass, cineplex));
        for (int i = 0; i < showingVenue.size(); i++) {
            showingVenueUUID.add(showingVenue.get(i).getUUID());
            seatingPlan.add(showingVenue.get(i).duplicateSeats());
            showingTime.add(new DateTime(30, 12, 4, 25, 1, 2023));
        }

        MovieScheduleManager.createMovieSchedule(movieOnShow.getUUID(), showingVenueUUID, seatingPlan, showingTime);
        System.out.println("Movie Schedule data loaded successfully!");
    }


    public static void initializeMovie() {
        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());

        if (movies.size() != 0) {
            System.out.println("Error! Existing movie data in the database!");
            System.out.println("Consider resetting database before loading initial data!");
            return;
        }

        String title;
        MovieAgeRating movieAgeRating;
        MovieShowingStatus movieShowingStatus;
        ArrayList <String> movieCast = new ArrayList<>();
        String director;
        String synopsis;
        double duration;

        // Movie1
        title = "The Conjuring";
        movieAgeRating = MovieAgeRating.M18;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 123.0;
        MovieManager.createBlockbusterMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);

        // Movie2
        title = "Zootopia";
        movieAgeRating = MovieAgeRating.G;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast = new ArrayList<String>();
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 155.0;
        MovieManager.createThreeDMovie(title, movieAgeRating, movieShowingStatus, 
                                        movieCast, director, synopsis, duration);

        // Movie3
        title = "Spiderman";
        movieAgeRating = MovieAgeRating.G;
        movieShowingStatus = MovieShowingStatus.NOW_SHOWING;
        movieCast = new ArrayList<String>();
        movieCast.add("Yeek");
        movieCast.add("Jerick");
        director = "LeeJuin";
        synopsis = "Good";
        duration = 155.0;
        MovieManager.createStandardMovie(title, movieAgeRating, movieShowingStatus, 
                                            movieCast, director, synopsis, duration);
        
        System.out.println("Movie data loaded successfully!");
    }


    public static void initalizeCinemaStaff() {
        ArrayList <CinemaStaff> cinemaStaffs = Database.getValueList(Database.CINEMA_STAFF.values());

        if (cinemaStaffs.size() != 0) {
            System.out.println("Error! Existing staff data in the database!");
            System.out.println("Consider resetting database before loading initial data!");
            return;
        }

        String name = "CinemaStaf";
        String username = "admin";
        String password = "password";
        CinemaStaffManager.createCinemaStaff(name, password, username);

        System.out.println("Cinema Staff data loaded successfully!");
    }


    public static void initializePrices() {
        System.out.println("Overriding existing ticket price settings to default!");
        Prices prices = new Prices(2,30,3,
        4,3,
        5,2,1.5, 
        1.5,2,1.5,1.5,1.5);
        Database.PRICES = prices;
        DatabaseManager.reloadDatabase();
        
        System.out.println("Price data loaded successfully!");
    }   
    

    public static <K, V> void saveUpdateToDatabase(K UUID, V object, HashMap <K, V> data) {
        data.put(UUID, object);
        DatabaseManager.reloadDatabase();
    }


    public static void resetDatabase() {
        Database.resetDatabase();
    }


    public static void reloadDatabase() {
        Database.writeToDatabase();
        Database.remountDatabase();
    }
}
