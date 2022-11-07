package view;

import model.MovieSchedule;
import controller.CinemaManager;
import controller.MovieManager;
import controller.MovieScheduleManager;
import handler.InputHandler;
import handler.UIHandler;
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
    private String errorMessage;

    public MovieScheduleView(ArrayList<Cinema> cinemaList, Movie movie, MovieGoer movieGoer) {
        this.movie = movie;
        this.cinemaList = cinemaList;
        this.movieSchedule = MovieScheduleManager.getMovieScheduleByMovie(movie);
        this.movieGoer = movieGoer;
        indexList = new ArrayList<Integer>();
        showingTimes = new ArrayList<DateTime>();
        for (int j = 0; j < movieSchedule.getShowingVenues().size(); j++) {
            for (int i = 0; i < cinemaList.size(); i++) {
                if (movieSchedule.getShowingVenues().get(j).equals(cinemaList.get(i).getUUID())) {
                    indexList.add(j);
                    showingTimes.add(movieSchedule.getShowingTime().get(j));
                }
            }
        }
        this.errorMessage = "";
    }

    public boolean printShowingTimes() {
        String content = "\n";

        if (MovieManager.movieBookable(this.movie)) {
            int count = 0;
            for (int i = 0; i < this.showingTimes.size(); i++) {
                String index = String.format("%02d. ", i + 1);
                DateTime showingTime = this.showingTimes.get(i);
                String payload = String.format(index + "%s\n", showingTime.getTimeNow());
                content = content + payload;
                count = i + 1;
            }

            String index = String.format("%02d. ", count + 1);
            String payload = String.format(index + "Quit and return back");
            content = content + payload;
            MainView.printMenuContent(content);
            return true;
        }
        else {
            content += "This movie is currently not available for booking!";
            MainView.printMenuContent(content);
            return false;
        }
    }

    public boolean printMenu() {
        MainView.printBoilerPlate("Showing Schedule for " + this.movie.getMovieTitle());
        return this.printShowingTimes();
    }

    public void appContent() {
        int choice = -1;

        do {
            if (MovieMenuView.exit) {
                this.errorMessage = "";
                return;
            }
            
            UIHandler.clearScreen();
            System.out.println(this.errorMessage);
            if (this.printMenu()) {
                choice = InputHandler.intHandler();
                if (choice < 1 || choice > showingTimes.size() + 1) {
                    this.errorMessage = "Error! Please enter a valid input";
                    continue;
                }
            
                if (choice == showingTimes.size() + 1) {
                    this.errorMessage = "";
                    return;
                } 
                else {
                    int pointer = indexList.get(choice - 1);
                    Cinema cinema = CinemaManager.getCinemaByUUID(this.movieSchedule.getShowingVenues().get(pointer));
                    this.seatingPlanView = new SeatingPlanView(this.movieSchedule,
                            cinema, this.movieSchedule.getSeatingPlan().get(pointer), this.movieGoer);
                    this.errorMessage = "";
                    this.seatingPlanView.appContent();
                }
            } else {
                System.out.println("Press any key to return");
                InputHandler.stringHandler();
                this.errorMessage = "";
                return;
            } 
        } while (true);

    }
}
