package controller;

import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.enums.CinemaClass;

import model.CinemaStaff;
import model.MovieGoer;

import model.Movie;
import model.BlockbusterMovie;
import model.ThreeDMovie;
import model.StandardMovie;
import model.MovieSchedule;

import model.Cinema;
import model.StandardCinema;
import model.PlatinumCinema;
import model.IMaxCinema;
import model.Seat;
import model.DateTime;

import database.Database;

import handler.DatabaseHandler;
import handler.InputHandler;

import java.util.ArrayList;

public class CinemaStaffManager{
    /*
     * Method to instantiate a cinemaStaff instance and save to database
     * @param name  name of Staff
     * @param password  staff login password
     * @param staffID   staff login id 
     */
    public CinemaStaffManager(){}
    public static CinemaStaff createCinemaStaff(String name, String password, String username){
        String UUID = String.format("SF%03d", DatabaseHandler.generateUUID(Database.CINEMA_STAFF));
        CinemaStaff cinemaStaff = new CinemaStaff(UUID, name, password, username);
        DatabaseManager.saveUpdateToDatabase(UUID, cinemaStaff, Database.CINEMA_STAFF);
        return cinemaStaff;
    }


    public static int updateExistingMovieDetails(int movieNumber, int detail){
        ArrayList <MovieSchedule> movieSchedules = Database.getValueList(Database.MOVIE_SCHEDULE.values());
        ArrayList <String> movieSchedulesKeyList = Database.getKeyList(Database.MOVIE_SCHEDULE.keySet());

        ArrayList <Movie> movies = Database.getValueList(Database.MOVIE.values());
        ArrayList <String> movieKeyList = Database.getKeyList(Database.MOVIE.keySet());

        if(movieNumber > movies.size()){
            System.out.println("No such movie in database.");
            return 1;
        }
        Movie m = movies.get(movieNumber - 1);
        String movieUUID = movieKeyList.get(movieNumber - 1);

        MovieSchedule ms; 
        String scheduleUUID;

        int scheduleIndex = movieSchedules.indexOf(m);
    
        ms = movieSchedules.get(scheduleIndex);
        scheduleUUID = movieSchedulesKeyList.get(scheduleIndex);

        switch(detail){
            case 1:
            System.out.println("Enter the new name of the movie");
            String newMovieName = InputHandler.stringHandler();
            m.setMovieTitle(newMovieName);
            ms.setMovieOnShow(m);

            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;

            case 2:
            System.out.println("Enter the new Movie Type for " + m.getMovieTitle()); 
            System.out.println("1. Standard Movie");
            System.out.println("2. Blockbuster Movie");
            System.out.println("3. 3D Movie");
            int newMovieType = InputHandler.intHandler();  

            String oldMovieTitle = m.getMovieTitle();
            MovieAgeRating oldMovieAgeRating = m.getMovieAgeRating();
            MovieShowingStatus oldShowingStatus = m.getMovieShowingStatus();
            ArrayList<String> oldMovieCast = m.getMovieCast(); 
            String oldMovieDirector = m.getMovieDirector();
            String oldMovieSynopsis = m.getMovieSynopsis();
            double oldMovieDuration = m.getMovieDuration();

           
            if (newMovieType == 1){
                Movie newMovie = new StandardMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                    oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                    oldMovieDuration);
                ms.setMovieOnShow(newMovie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            } else if (newMovieType == 2){
                Movie newMovie = new BlockbusterMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                    oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                    oldMovieDuration);
                ms.setMovieOnShow(newMovie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            } else if (newMovieType == 3){
                Movie newMovie = new ThreeDMovie(movieUUID, oldMovieTitle, oldMovieAgeRating,
                                                    oldShowingStatus, oldMovieCast, oldMovieDirector, oldMovieSynopsis,
                                                    oldMovieDuration);
                ms.setMovieOnShow(newMovie);
                DatabaseManager.saveUpdateToDatabase(movieUUID, newMovie, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            } else {
                System.out.println("No such movie type.");
                return 1;
            }

            
            return 0;

            case 3:
            System.out.println("Enter the new age rating for " + m.getMovieTitle());
            System.out.println("1. G");
            System.out.println("2. PG");
            System.out.println("3. PG13");
            System.out.println("4. NC16");
            System.out.println("5. M18");
            System.out.println("6. R21");
            int choice = InputHandler.intHandler();
            if (choice < 1 || choice > 6){
                System.out.println("No such age rating.");
                return 1;
            }
            MovieAgeRating newMovieAgeRating = MovieAgeRating.values()[choice - 1];
            m.setMovieAgeRating(newMovieAgeRating);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            
            case 4:
            System.out.println("Enter the new showing status for " + m.getMovieTitle());
            System.out.println("1. COMING_SOON");
            System.out.println("2. PREVIEW");
            System.out.println("3. NOW_SHOWING");
            System.out.println("4. END_OF_SHOWING");
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > 4){
                System.out.println("No such showing status");
                return 1;
            }
            MovieShowingStatus newShowingStatus = MovieShowingStatus.values()[choice - 1];
            
            if (newShowingStatus == MovieShowingStatus.END_OF_SHOWING){
                System.out.println("Deleting " + m.getMovieTitle() + " from movie schedule...");
                Database.MOVIE_SCHEDULE.remove(scheduleUUID);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;    
            }
            m.setMovieShowingStatus(newShowingStatus);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            
            case 5:
            System.out.println("How would you like to configure cast names");
            System.out.println("1. Remove cast name");
            System.out.println("2. Add cast name");

            int innerchoice = InputHandler.intHandler();
            if (innerchoice < 1 || innerchoice > 1){
                System.out.println("Invalid choice.");
                return 1;
            }    
            switch(innerchoice){

                case 1: 
                    System.out.println("Enter which cast number is to be removed. (Enter Cast Number)");
                    for (int i = 0; i < m.getMovieCast().size(); i++){
                        System.out.println("Cast Number " + (i+1) + ". " + m.getMovieCast().get(i));
                    }
                    int castNumber = InputHandler.intHandler();
                    if (castNumber < 1 || castNumber > m.getMovieCast().size()){
                        System.out.println("Invalid cast number.");
                        return 1;
                    }
                    m.getMovieCast().remove(castNumber - 1);
                    ms.setMovieOnShow(m);
                    DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;

                case 2:
                    System.out.println("Enter the name of the cast to be added.");
                    String castName = InputHandler.stringHandler();
                    m.getMovieCast().add(castName);
                    ms.setMovieOnShow(m);
                    DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;
            }

            break;
            
            case 6:
            System.out.println("Enter the new name of the Movie Director of " + m.getMovieTitle() + ".");
            String newDirectorName = InputHandler.stringHandler();
            m.setMovieDirector(newDirectorName);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;

            case 7:
            System.out.println("Enter the new synopsis of " + m.getMovieTitle() + ".");
            String newSynopsis = InputHandler.stringHandler();
            m.setMovieSynopsis(newSynopsis);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;

            case 8:
            System.out.println("Enter new duration of " + m.getMovieTitle() + ".");
            double newMovieDuration = InputHandler.doubleHandler();
            if (newMovieDuration < 0){
                System.out.println("Movie duration cannot be negative.");
                return 1;
            }
            m.setMovieDuration(newMovieDuration);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;

            
            case 9:
            System.out.println("How would you like to configure showing venues");
            System.out.println("1. Remove showing venue");
            System.out.println("2. Add showing venue");

            innerchoice = InputHandler.intHandler();
            if(innerchoice < 1 || innerchoice > 2){
                System.out.println("Invalid choice.");
                return 1;
            }
            switch(innerchoice){
                case 1:
                    System.out.println("Enter which showing venue is to be removed. (Enter ID number)");
                    for (int i = 0; i < ms.getShowingVenues().size(); i++){
                        System.out.println((i + 1) + ": "+ ms.getShowingVenues().get(i).getCinemaClass());
                    }
                    int venueID = InputHandler.intHandler();
                    if (venueID < 1 || venueID > ms.getShowingVenues().size()){
                        System.out.println("Invalid showing venue.");
                        return 1;
                    }
                    ms.removeShowingVenue(venueID- 1);
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                break;

                case 2:
                    System.out.println("Enter which showing venue is to be added. (Enter ID number)");
                    for (int i = 0; i < ms.getShowingVenues().size(); i++){
                        System.out.println((i + 1) + ": "+ ms.getShowingVenues().get(i).getCinemaClass());
                    }
                    venueID = InputHandler.intHandler();
                    if (venueID < 1 || venueID > ms.getShowingVenues().size()){
                        System.out.println("Invalid showing venue.");
                        return 1;
                    }

                    
                    System.out.println("Enter which showing venue type to be added.");
                    System.out.println("1. Standard Cinema");
                    System.out.println("2. Platinum Cinema");
                    System.out.println("3. IMax Cinema");
                    int newVenueType = InputHandler.intHandler(); 
                    
                    System.out.println("Enter the date time for this cinema type");
                    DateTime showingTime = queryDate();
                    ArrayList <DateTime> newShowingTimes = new ArrayList<DateTime>();
                    newShowingTimes.add(showingTime);
                    
                    if (newVenueType == 1){
                        CinemaClass cinemaClass = CinemaClass.STANDARD;
                        ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                        Cinema c = CinemaManager.createStandardCinema(seats);
                        ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                        newCinemaList.add(c);
                        MovieScheduleManager.updateMovieSchedule(m, newCinemaList ,newShowingTimes);
                    } else if (newVenueType == 2){
                        CinemaClass cinemaClass = CinemaClass.PLATINUM;
                        ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                        Cinema c = CinemaManager.createPlatinumCinema(seats);
                        ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                        newCinemaList.add(c);
                        MovieScheduleManager.updateMovieSchedule(m, newCinemaList ,newShowingTimes);
                    } else if (newVenueType == 3){
                        CinemaClass cinemaClass = CinemaClass.IMAX;
                        ArrayList <Seat> seats = DatabaseManager.initializeSeatData(cinemaClass);
                        Cinema c = CinemaManager.createIMaxCinema(seats);
                        ArrayList <Cinema> newCinemaList = new ArrayList<Cinema>();
                        newCinemaList.add(c);
                        MovieScheduleManager.updateMovieSchedule(m, newCinemaList ,newShowingTimes);
                    } else {
                        System.out.println("Invalid showing venue");
                        return 1;
                    }
                    return 0;
                } 
                break;
            

            case 10:
            System.out.println("How would you like to configure showing times?");
            System.out.println("1. Remove showing time");
            System.out.println("2. Add new showing time");
            innerchoice = InputHandler.intHandler();
            if (innerchoice < 1 || innerchoice > 2){
                System.out.println("Invalid choice.");
                return 1;
            }
            switch(innerchoice){
                case 1:
                    System.out.println("Enter which showing time is to be removed. (Enter ID number)");
                    for (int i = 0; i < ms.getShowingTime().size(); i++){
                        System.out.println((i+1) + " . " + "Date: " + ms.getShowingTime().get(i).getYear() + ms.getShowingTime().get(i).getMonth() + ms.getShowingTime().get(i).getDate() + " Time: " + ms.getShowingTime().get(i).getHour() + ms.getShowingTime().get(i).getMinute());
                    }
                    int showingTimeID = InputHandler.intHandler();
                    if (showingTimeID < 1 || showingTimeID > ms.getShowingTime().size()){
                        System.out.println("Invalid showing time.");
                        return 1;
                    }
                    ms.removeShowingTime(showingTimeID- 1);
                    System.out.println("Showing time removed.");
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;

                case 2:
                    System.out.println("Enter the time to be added.");

                    System.out.println("year:");
                    int year = InputHandler.intHandler();
                    System.out.println("month:");
                    int month = InputHandler.intHandler();
                    System.out.println("date:");
                    int date = InputHandler.intHandler();
                    System.out.println("hour:");
                    int hour = InputHandler.intHandler();
                    System.out.println("minute:");
                    int minute = InputHandler.intHandler();
                    System.out.println("day:");
                    int day = InputHandler.intHandler();

                    DateTime newShowingTime = new DateTime(minute, hour, day, date, month, year);
                    ms.addShowingTime(newShowingTime);
                    System.out.println("Showing time added.");
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;
                }
            break;
            
            default:
            break;
        }
        return 1;
    }

    public static int configurePrice(int choice){
        double price;
        switch(choice){
            case 1:
                System.out.println("Enter new price for Standard Cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStandardCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 2:
                System.out.println("Enter new price for Platinum cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultPlatinumCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 3:
                System.out.println("Enter new price for IMax cinemas:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultIMaxCinemaPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 4:
                System.out.println("Enter new price for seats:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultSeatPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 5:
                System.out.println("Enter new price for Blockbuster movies:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                DatabaseManager.reloadDatabase();
                return 0;
            case 6:
                System.out.println("Enter new price for 3D movies:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefault3DMoviePrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 7:
                System.out.println("Enter new price for standard movies:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStandardMoviePrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 8:
                System.out.println("Enter new price weightage for children:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultChildPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 9:
                System.out.println("Enter new price weightage for students:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultStudentPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 10:
                System.out.println("Enter new price weightage for adults:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultChildPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 11:
                System.out.println("Enter new price weightage for senior citizens:");
                price = InputHandler.doubleHandler();   
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setDefaultSeniorCitizenPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 12:
                System.out.println("Enter new price weightage for holidays:");
                price = InputHandler.doubleHandler();
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setHolidayPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            case 13:
                System.out.println("Enter new price weightage for weekends:");
                price = InputHandler.doubleHandler();    
                if (price < 0){
                    System.out.println("Price cannot be negative.");
                    return 1;
                }
                Database.PRICES.setWeekendPrice(price);
                DatabaseManager.reloadDatabase();
                return 0;
            default:
                return 1;
        }
    }

    public static int configureHoliday(int choice){
        DateTime holiday;
        printHolidayList();
        switch(choice){
            case 1:
                System.out.println("Enter holiday date time to be added");
                holiday = queryDate();
                
                for (int i = 0; i < Database.holidays.size(); i++){
                    if (Database.holidays.get(i).getYear() == holiday.getYear() && Database.holidays.get(i).getMonth() == holiday.getMonth() && Database.holidays.get(i).getDate() == holiday.getDate() && Database.holidays.get(i).getHour() == holiday.getHour() && Database.holidays.get(i).getMinute() == holiday.getMinute() && Database.holidays.get(i).getDay() == holiday.getDay()){
                    System.out.println("Holiday already exists!");
                    printHolidayList();
                    return 1;
                }
                }
                Database.holidays.add(holiday);
                System.out.println("Holiday Added");
                printHolidayList();
                DatabaseManager.reloadDatabase();
                return 0;

            case 2:       
                System.out.println("Enter holiday to be removed");
                holiday = queryDate();
                if (Database.holidays.size() == 0){
                    System.out.println("Holiday list is empty!");
                    return 1;
                }
                for (int i = 0; i < Database.holidays.size(); i++){
                    if (Database.holidays.get(i).getYear() == holiday.getYear() && Database.holidays.get(i).getMonth() == holiday.getMonth() && Database.holidays.get(i).getDate() == holiday.getDate() && Database.holidays.get(i).getHour() == holiday.getHour() && Database.holidays.get(i).getMinute() == holiday.getMinute() && Database.holidays.get(i).getDay() == holiday.getDay()){
                        Database.holidays.remove(i);
                        System.out.println("Holiday removed");
                        printHolidayList();
                        DatabaseManager.reloadDatabase();
                        return 0;
                    }
                }
                System.out.println("Holiday cannot be found");
                printHolidayList();
                return 1;
            
            default:
                System.out.println("Invalid choice.");
                return 1;
            }
        }
        
     

    
    public static void printHolidayList(){
        if (Database.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return;
        }
        System.out.println("Here are the list of holidays:");
        for (int i = 0; i < Database.holidays.size(); i++){
            Database.holidays.get(i).printTime();
        }
        System.out.println("");
    }

    public static DateTime queryDate(){
        System.out.println("Minute: ");
        int minute = InputHandler.intHandler();
        System.out.println("Hour: ");
        int hour = InputHandler.intHandler();
        System.out.println("Day: ");
        int day = InputHandler.intHandler();
        System.out.println("Date: ");
        int date = InputHandler.intHandler();
        System.out.println("Month: ");
        int month = InputHandler.intHandler();
        System.out.println("Year: ");
        int year = InputHandler.intHandler();
        DateTime holiday = new DateTime(minute, hour, day, date, month, year);
        return holiday;
    }

    public static void optOutOne(int choice){
        if (choice == 1){
            MovieGoer.setViewTop5OverallRatings(false); 
        }
        else{
            MovieGoer.setViewTop5MovieSales(false);
        }

    }

}