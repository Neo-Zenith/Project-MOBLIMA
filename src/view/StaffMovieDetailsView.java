package view;

import handler.InputHandler;
import java.util.ArrayList;
import database.Database;
import model.MovieSchedule;

public class StaffMovieDetailsView {
    private StaffConfigureMovieView staffConfigureMovieView;
    private StaffSystemConfig staffSystemConfig;

    
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Here are the list of movies");
        MainView.printBoilerPlate("""


                """);
        
    }
    public void appContent(){
        ArrayList<MovieSchedule> movieList = Database.getValueList(Database.MOVIE_SCHEDULE.keySet());
        int movieNumber = -1;
        do {
            this.printMenu();
            movieNumber = InputHandler.intHandler();
            if (movieNumber == Database.getValueList(Database.MOVIE_SCHEDULE.keySet()).size() + 1){
                System.out.println("====================================");
                this.staffSystemConfig = new StaffSystemConfig();
                this.staffSystemConfig.appContent();
            }
            else if (movieNumber >= 1 && movieNumber <= movieList.size() + 1){
                System.out.println("====================================");
                this.staffConfigureMovieView = new StaffConfigureMovieView();
                this.staffConfigureMovieView.appContent(movieNumber);
            }
            else {
                System.out.println("====================================");
                System.out.println("Invalid choice");
            }
        }   while (movieNumber != movieList.size() + 1);
    }

    // void printAllMovies(){
    //     ArrayList<MovieSchedule> movieList1 = Database.getValueList(Database.MOVIE_SCHEDULE.keySet());



    // }
}
