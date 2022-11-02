package view;

import handler.InputHandler;

public class StaffMovieDetailsView {
    private StaffConfigureMovieView staffConfigureMovieView;

    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Here are the list of movies");
        MainView.printBoilerPlate("""


                """);
    }
    public void appContent(){
        int choice = -1;
        int movieNumber = -1;

        do {
            this.printMenu();
            movieNumber = InputHandler.intHandler();
            this.staffConfigureMovieView = new StaffConfigureMovieView();
            this.staffConfigureMovieView.appContent(movieNumber);
            
        }   while (movieNumber != 10);
    }
}
