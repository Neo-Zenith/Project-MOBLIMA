package view;

import handler.InputHandler;
import java.util.ArrayList;
import model.Movie;
import database.Database;
import model.MovieSchedule;

public class StaffMovieDetailsView {
    private StaffConfigureMovieView staffConfigureMovieView;

    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Here are the list of movies");
        MainView.printBoilerPlate("""


                """);
    }
    public void appContent(){
        int movieNumber = -1;

        do {
            this.printMenu();
            movieNumber = InputHandler.intHandler();
            this.staffConfigureMovieView = new StaffConfigureMovieView();
            this.staffConfigureMovieView.appContent(movieNumber);
            
        }   while (movieNumber != 10);
    }

    void printAllMovies(){
        ArrayList<MovieSchedule> movieList1 = Database.getValueList(Database.MOVIE_SCHEDULE.keySet());



    }
}
