package controller;

import model.CinemaStaff;
import model.Movie;
import model.Cinema;
import model.enums.CinemaClass;
import model.Seat;
import model.enums.MovieAgeRating;
import model.enums.MovieShowingStatus;
import model.MovieSchedule;
import model.DateTime;

import database.Database;

import handler.DatabaseHandler;
import handler.InputHandler;

import java.util.ArrayList;
import java.util.Scanner;

public class CinemaStaffManager{
    /*
     * Method to instantiate a cinemaStaff instance and save to database
     * @param name  name of Staff
     * @param password  staff login password
     * @param staffID   staff login id 
     */
    public CinemaStaffManager(){}
    public static void createCinemaStaff(String name, String password, String username){
        String UUID = String.format("SF%03d", DatabaseHandler.generateUUID(Database.CINEMA_STAFF));
        CinemaStaff cinemaStaff = new CinemaStaff(UUID, name, password, username);
        DatabaseManager.saveUpdateToDatabase(UUID, cinemaStaff, Database.CINEMA_STAFF);
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
        if (scheduleIndex != -1){
            boolean editMovieSchedule = true;
            ms = movieSchedules.get(scheduleIndex);
            scheduleUUID = movieSchedulesKeyList.get(scheduleIndex);
        }

        switch(detail){
            case 1:
            System.out.println("Enter the new name of the movie");
            String newMovieName = InputHandler.stringHandler();
            m.setMovieTitle(newMovieName);
            ms.setMovieOnShow(m);

            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            break;

            case 2:
            System.out.println("Enter the new Movie Type for " + m.getMovieTitle()); 

            int newMovieType = InputHandler.intHandler();
            if (newMovieType < 1 || newMovieType > 3){
                System.out.println("No such movie type.");
                return 1;
            }    

            String oldMovieTitle = m.getMovieTitle();
            MovieAgeRating oldMovieAgeRating = m.getMovieAgeRating();
            MovieShowingStatus oldShowingStatus = m.getMovieShowingStatus();
            ArrayList<String> oldMovieCast = m.getMovieCast(); 
            String oldMovieDirector = m.getMovieDirector();
            String oldMovieSynopsis = m.getMovieSynopsis();
            double oldMovieDuration = m.getMovieDuration();
            
            ArrayList<Cinema> oldCinemas = ms.getShowingVenues();
            ArrayList <DateTime> oldShowingTimes = ms.getShowingTime();
            ArrayList<ArrayList<Seat>> oldSeatingPlan = ms.getSeatingPlan();

            Database.MOVIE_SCHEDULE.remove(scheduleUUID);
            Database.MOVIE.remove(movieUUID);

            if (newMovieType == 1){
                Movie newMovie = MovieManager.createMovie(oldMovieTitle, "Blockbuster", oldMovieAgeRating, oldShowingStatus, 
                                                          oldMovieCast, oldMovieDirector, oldMovieSynopsis, oldMovieDuration);
                MovieSchedule newSchedule = MovieScheduleManager.createMovieSchedule(newMovie, oldCinemas, oldSeatingPlan, oldShowingTimes);
            } else if (newMovieType == 2){
                Movie newMovie = MovieManager.createMovie(oldMovieTitle, "ThreeD", oldMovieAgeRating, oldShowingStatus, 
                                                          oldMovieCast, oldMovieDirector, oldMovieSynopsis, oldMovieDuration);
                MovieSchedule newSchedule = MovieScheduleManager.createMovieSchedule(newMovie, oldCinemas, oldSeatingPlan, oldShowingTimes);
            } else if (newMovieType == 3){
                Movie newMovie = MovieManager.createMovie(oldMovieTitle, "Blockbuster", oldMovieAgeRating, oldShowingStatus, 
                                                          oldMovieCast, oldMovieDirector, oldMovieSynopsis, oldMovieDuration);
                MovieSchedule newSchedule = MovieScheduleManager.createMovieSchedule(newMovie, oldCinemas, oldSeatingPlan, oldShowingTimes);
            }
            return 0;
            break;

            case 3:
            System.out.println("Enter the new age rating for " + m.getMovieTitle()+ "(G, PG, PG13, NC16, M18, R21)");
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
            break;
            
            case 4:
            System.out.println("Enter the new showing status for " + m.getMovieTitle()+ "(COMING_SOON, PREVIEW, NOW_SHOWING, END_OF_SHOWING)");
            choice = InputHandler.intHandler();
            if (choice < 1 || choice > 4){
                System.out.println("No such showing status");
                return 1;
            }
            MovieShowingStatus newShowingStatus = MovieShowingStatus.values()[choice - 1];
            
            if (newShowingStatus == MovieShowingStatus.END_OF_SHOWING){
                System.out.println("Deleting " + m.getMovieTitle() + " from movie list...");
                Database.MOVIE_SCHEDULE.remove(scheduleUUID);
                Database.MOVIE.remove(movieUUID);
            }
            m.setMovieShowingStatus(newShowingStatus);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            break;
            
            case 5:
            System.out.println("Would you like to 1.edit, 2.remove or 3.add cast members:");
            int innerchoice = InputHandler.intHandler();
            if (innerchoice < 1 || innerchoice > 3){
                System.out.println("Invalid choice.");
                return 1;
            }    
            switch(innerchoice){
                case 1:
                System.out.println("Enter which cast number is to be updated. (Enter Cast Number)");
                for (int i = 0; i < m.getMovieCast().size(); i++){
                    System.out.println("Cast Number " + (i+1) + ": " + m.getMovieCast().get(i));
                }
                int castNumber = InputHandler.intHandler();
                if (castNumber < 1 || castNumber > m.getMovieCast().size()){
                    System.out.println("Invalid cast number.");
                    return 1;
                }
                System.out.println("Enter the updated name of cast " + castNumber + ".");
                String newCastName = InputHandler.stringHandler();
                m.getMovieCast().remove(castNumber - 1);
                m.getMovieCast().add(newCastName);
                ms.setMovieOnShow(m);
                DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;
                break;

                case 2: 
                System.out.println("Enter which cast number is to be removed. (Enter Cast Number)");
                for (int i = 0; i < m.getMovieCast().size(); i++){
                    System.out.println("Cast Number " + (i+1) + ": " + m.getMovieCast().get(i));
                }
                castNumber = InputHandler.intHandler();
                if (castNumber < 1 || castNumber > m.getMovieCast().size()){
                    System.out.println("Invalid cast number.");
                    return 1;
                }
                m.getMovieCast().remove(castNumber - 1);
                ms.setMovieOnShow(m);
                DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;
                break;

                case 3:
                System.out.println("Enter the name of the cast to be added.");
                String castName = InputHandler.stringHandler();
                m.getMovieCast().add(castName);
                ms.setMovieOnShow(m);
                DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;
                break;
            }

            break;
            
            case 6:
            System.out.println("Enter the new name of the Movie Director");
            String newDirectorName = InputHandler.stringHandler();
            m.setMovieDirector(newDirectorName);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            break;

            case 7:
            System.out.println("Enter the new synopsis of " + m.getMovieTitle() + ".");
            String newSynopsis = InputHandler.stringHandler();
            m.setMovieSynopsis(newSynopsis);
            ms.setMovieOnShow(m);
            DatabaseManager.saveUpdateToDatabase(movieUUID, m, Database.MOVIE);
            DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
            return 0;
            break;

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
            break;

            
            case 9:
            System.out.println("Would you like to 1. edit 2. remove or 3. add showing venues types");
            innerchoice = InputHandler.intHandler();
            if(innerchoice < 1 || innerchoice > 3){
                System.out.println("Invalid choice.");
                return 1;
            }
            switch(innerchoice){
                case 1:
                System.out.println("Enter which showing venue is to be updated its type. (Enter ID number)");
                for (int i = 0; i < ms.getShowingVenues().size(); i++){
                    System.out.println((i + 1) + ": "+ ms.getShowingVenues().get(i).getCinemaClass());
                }
                int venueID = InputHandler.intHandler();
                if (venueID < 1 || venueID > ms.getShowingVenues().size()){
                    System.out.println("Invalid showing venue.");
                    return 1;
                }

                Cinema oldCinema = ms.getShowingVenues().get(venueID - 1);
                double oldCinemaPrice = oldCinema.getCinemaPrice();
                ArrayList <Seat> oldSeats = oldCinema.getSeats();
                ms.getShowingVenues().remove(venueID - 1);

                System.out.println("Enter which showing venue type to be updated to.");
                int newVenueType = InputHandler.intHandler(); 
                if (newVenueType == 1){
                    Cinema c = CinemaManager.createStandardCinema(CinemaClass.STANDARD, oldCinemaPrice, oldSeats);
                    ms.getShowingVenues().add(c);
                } else if (newVenueType == 2){
                    Cinema c = CinemaManager.createPlatinumCinema(CinemaClass.PLATINUM, oldCinemaPrice, oldSeats);
                    ms.getShowingVenues().add(c);
                } else if (newVenueType == 3){
                    Cinema c = CinemaManager.createIMaxCinema(CinemaClass.IMAX, oldCinemaPrice, oldSeats);
                    ms.getShowingVenues().add(c);
                }
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;
                break;

                case 2:
                System.out.println("Enter which showing venue is to be removed. (Enter ID number)");
                for (int i = 0; i < ms.getShowingVenues().size(); i++){
                    System.out.println((i + 1) + ": "+ ms.getShowingVenues().get(i).getCinemaClass());
                }
                venueID = InputHandler.intHandler();
                if (venueID < 1 || venueID > ms.getShowingVenues().size()){
                    System.out.println("Invalid showing venue.");
                    return 1;
                }
                ms.getShowingVenues().remove(venueID- 1);
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                break;

                case 3:
                System.out.println("Enter which showing venue is to be added. (Enter ID number)");
                for (int i = 0; i < ms.getShowingVenues().size(); i++){
                    System.out.println((i + 1) + ": "+ ms.getShowingVenues().get(i).getCinemaClass());
                }
                venueID = InputHandler.intHandler();
                if (venueID < 1 || venueID > ms.getShowingVenues().size()){
                    System.out.println("Invalid showing venue.");
                    return 1;
                }
                if (newVenueType == 1){
                    Cinema c = CinemaManager.createStandardCinema(CinemaClass.STANDARD, ms.getShowingVenues().get(venueID - 1).getCinemaPrice(), ms.getShowingVenues().get(venueID - 1).getSeats());
                    ms.getShowingVenues().add(c);
                } else if (newVenueType == 2){
                    Cinema c = CinemaManager.createPlatinumCinema(CinemaClass.PLATINUM, ms.getShowingVenues().get(venueID - 1).getCinemaPrice(), ms.getShowingVenues().get(venueID - 1).getSeats());
                    ms.getShowingVenues().add(c);
                } else if (newVenueType == 3){
                    Cinema c = CinemaManager.createIMaxCinema(CinemaClass.IMAX, ms.getShowingVenues().get(venueID - 1).getCinemaPrice(), ms.getShowingVenues().get(venueID - 1).getSeats());
                    ms.getShowingVenues().add(c);
                } else {
                    System.out.println("Invalid showing venue type");
                    return 1;
                }
                DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                return 0;
                break;
            } 
            break;
            

            case 10:
            System.out.println("Would you like to 1. edit, 2. remove or 3. add showing times:");
            innerchoice = InputHandler.intHandler();
            if (innerchoice < 1 || innerchoice > 3){
                System.out.println("Invalid choice.");
                return 1;
            }
            switch(innerchoice){
                case 1:
                    System.out.println("Enter which showing time is to be updated. (Enter ID number)");
                    for (int i = 0; i < ms.getShowingTime().size(); i++){
                        System.out.println((i+1) + " : " + "Date: " + ms.getShowingTime().get(i).getYear() + ms.getShowingTime().get(i).getMonth() + ms.getShowingTime().get(i).getDate() + " Time: " + ms.getShowingTime().get(i).getHour() + ms.getShowingTime().get(i).getMinute());
                    }
                    int showingTimeID = InputHandler.intHandler();
                    if (showingTimeID < 1 || showingTimeID > ms.getShowingTime().size()){
                        System.out.println("Invalid showing time.");
                        return 1;
                    }
                    System.out.println("Enter the new showing time");
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
                    
                    ms.getShowingTime().get(showingTimeID -1).setShowingTime(newShowingTime, showingTimeID);
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;
                    break;

                case 2:
                    System.out.println("Enter which showing time is to be removed. (Enter ID number)");
                    for (int i = 0; i < ms.getShowingTime().size(); i++){
                        System.out.println((i+1) + " : " + "Date: " + ms.getShowingTime().get(i).getYear() + ms.getShowingTime().get(i).getMonth() + ms.getShowingTime().get(i).getDate() + " Time: " + ms.getShowingTime().get(i).getHour() + ms.getShowingTime().get(i).getMinute());
                    }
                    showingTimeID = InputHandler.intHandler();
                    if (showingTimeID < 1 || showingTimeID > ms.getShowingTime.size()){
                        System.out.println("Invalid showing time.");
                        return 1;
                    }
                    ms.getShowingTime().remove(showingTimeID- 1);
                    System.out.println("Showing time removed.");
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;
                    break;

                case 3:
                    System.out.println("Enter the time to be added.");

                    System.out.println("year:");
                    year = InputHandler.intHandler();
                    System.out.println("month:");
                    month = InputHandler.intHandler();
                    System.out.println("date:");
                    date = InputHandler.intHandler();
                    System.out.println("hour:");
                    hour = InputHandler.intHandler();
                    System.out.println("minute:");
                    minute = InputHandler.intHandler();
                    System.out.println("day:");
                    day = InputHandler.intHandler();

                    newShowingTime = new DateTime(minute, hour, day, date, month, year);
                    
                    ms.getShowingTime().add(newShowingTime);
                    DatabaseManager.saveUpdateToDatabase(scheduleUUID, ms, Database.MOVIE_SCHEDULE);
                    return 0;
                    break;
                }
            break;
            
            default:
                System.out.println("Invalid choice.");
                return 1;
            break;
        }
    }

    public static int configurePrice(int choice){
        int price;
        switch(choice){
            case 1:
                System.out.println("Enter new price for Standard Cinemas:");
                price = InputHandler.intHandler();
                Database.defaultStandardCinemaPrice = price;
                return 0;
                break;
            case 2:
                System.out.println("Enter new price for Platinum cinemas:");
                price = InputHandler.intHandler();
                Database.defaultPlatinumCinemaPrice = price;
                return 0;
                break;
            case 3:
                System.out.println("Enter new price for IMax cinemas:");
                price = InputHandler.intHandler();
                Database.defaultIMaxCinemaPrice = price;
                return 0;
                break;
            case 4:
                System.out.println("Enter new price for seats:");
                price = InputHandler.intHandler();
                Database.defaultSeatPrice = price;
                return 0;
                break;
            case 5:
                System.out.println("Enter new price for Blockbuster movies:");
                price = InputHandler.intHandler();    
                Database.defaultBlockBusterMoviePrice = price;
                return 0;
                break;
            case 6:
                System.out.println("Enter new price for 3D movies:");
                price = InputHandler.intHandler();
                Database.default3DMoviePrice = price;
                return 0;
                break;
            case 7:
                System.out.println("Enter new price for standard movies:");
                price = InputHandler.intHandler();    
                Database.defaultStandardMoviePrice = price;
                return 0;
                break;
            case 8:
                System.out.println("Enter new price weightage for students:");
                price = InputHandler.intHandler();
                Database.defaultStudentPrice = price;
                return 0;
                break;
            case 9:
                System.out.println("Enter new price weightage for adults:");
                price = InputHandler.intHandler();
                Database.defaultAdultPrice = price;
                return 0;
                break;
            case 10:
                System.out.println("Enter new price weightage for senior citizens:");
                price = InputHandler.intHandler();   
                Database.defaultSeniorCitizenPrize = price;
                return 0;
                break;
            case 11:
                System.out.println("Enter new price weightage for holidays:");
                price = InputHandler.intHandler();
                Database.holidayPrice = price;
                return 0;
                break;
            case 12:
                System.out.println("Enter new price weightage for weekends:");
                price = InputHandler.intHandler();    
                Database.weekendPrice = price;
                return 0;
                break;
            default:
                System.out.println("Invalid choice.");
                return 1;    

        }
    }

    public static int configureHoliday(int choice){
        DateTime holiday;
        switch(choice){
            case 1:
                System.out.println("Enter holiday date time to be added");
                holiday = queryHoliday();
                
                for (int i = 0; i < DateTime.holidays.size(); i++){
                    if (DateTime.holidays.get(i).getYear() == holiday.getYear() && DateTime.holidays.get(i).getMonth() == holiday.getMonth() && DateTime.holidays.get(i).getDate() == holiday.getDate() && DateTime.holidays.get(i).getHour() == holiday.getHour() && DateTime.holidays.get(i).getMinute() == holiday.getMinute() && DateTime.holidays.get(i).getDay() == holiday.getDay()){
                    System.out.println("Holiday already exists!");
                    printHolidayList();
                    return 1;
                }
                }
                DateTime.holidays.add(holiday);
                System.out.println("Holiday Added");
                printHolidayList();
                return 0;

            case 2:       
                System.out.println("Enter holiday to be removed");
                holiday = queryHoliday();
                if (DateTime.holidays.size() == 0){
                    System.out.println("Holiday list is empty!");
                    return 1;
                }
                for (int i = 0; i < DateTime.holidays.size(); i++){
                    if (DateTime.holidays.get(i).getYear() == holiday.getYear() && DateTime.holidays.get(i).getMonth() == holiday.getMonth() && DateTime.holidays.get(i).getDate() == holiday.getDate() && DateTime.holidays.get(i).getHour() == holiday.getHour() && DateTime.holidays.get(i).getMinute() == holiday.getMinute() && DateTime.holidays.get(i).getDay() == holiday.getDay()){
                        DateTime.holidays.remove(i);
                        System.out.println("Holiday removed");
                        printHolidayList();
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
        if (DateTime.holidays.size() == 0){
            System.out.println("Holiday list is empty!");
            return;
        }
        System.out.println("Here are the list of holidays:");
        for (int i = 0; i < DateTime.holidays.size(); i++){
            DateTime.holidays.get(i).printTime();
        }
        System.out.println("");
    }

    public static DateTime queryHoliday(){
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

}