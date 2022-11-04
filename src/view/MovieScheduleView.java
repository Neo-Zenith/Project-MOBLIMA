package view;

import model.MovieSchedule;
import controller.MovieScheduleManager;
import handler.InputHandler;
import model.Cinema;
import model.DateTime;
import model.Movie;
import model.MovieGoer;

import java.util.ArrayList;

public class MovieScheduleView {
    private Movie movie;
    private ArrayList<Cinema> cinemaList;
    private MovieSchedule movieSchedule;
    private int num = 0;
    private MovieGoer movieGoer;
    private ArrayList<DateTime> showingTimes;
    private ArrayList<Integer> indexList;
    private SeatingPlanView seatingPlanView;

    public MovieScheduleView(ArrayList<Cinema> cinemaList, Movie movie, MovieGoer movieGoer) {
        this.movie = movie;
        this.cinemaList = cinemaList;
        this.movieSchedule = MovieScheduleManager.filterMovieSchedulesByMovie(movie);
        this.movieGoer = movieGoer;
        indexList = new ArrayList<Integer>();
        showingTimes = new ArrayList<DateTime>();
        for (int j = 0; j < movieSchedule.getShowingVenues().size(); j++) {
            for (int i = 0; i < cinemaList.size(); i++) {
                if (movieSchedule.getShowingVenues().get(j).getUUID().equals(cinemaList.get(i).getUUID())) {
                    indexList.add(j);
                    showingTimes.add(movieSchedule.getShowingTime().get(j));
                }
            }
        }
    }

    public void printShowingTimes() {
        System.out.println("====================================");
        for (int i = 0; i < showingTimes.size(); i++) {
            System.out.print((i + 1) + ". ");
            showingTimes.get(i).printTime();
        }
        System.out.println((showingTimes.size() + 1) + ". Return");
    }

    public void printMenu() {
        MainView.printBoilerPlate("Please select a showing time to view the seating plan: ");
        System.out.println("====================================");
    }

    public void appContent() {
        this.printShowingTimes();
        int choice = -1;
        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            while (choice < 1 || choice > showingTimes.size() + 1) {
                System.out.println("Please enter a valid input");
                choice = InputHandler.intHandler();
            }
            int pointer = indexList.get(choice - 1);
            if (choice == showingTimes.size() + 1) {
                return;
            } else {
                this.seatingPlanView = new SeatingPlanView(this.movieSchedule,
                        this.movieSchedule.getShowingVenues().get(pointer),
                        this.movieSchedule.getSeatingPlan().get(pointer), this.movieGoer);
                this.seatingPlanView.appContent();
            }

            if (MovieMenuView.exit) {
                return;
            }
        } while (choice > 0 && choice <= this.num);

    }
}
