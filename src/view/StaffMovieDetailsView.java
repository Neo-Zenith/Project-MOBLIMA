public class StaffMovieDetailsView {
    public void printMenu() {
        System.out.println("====================================");
        System.out.println("Here are the list of movies");
        MainView.printBoilerPlate("""


                """);
    }
    public void appContent(){
        int choice = -1;
        Database db = new Database();

        do {
            this.printMenu();
            movieNumber = InputHandler.intHandler();
            this.staffMovieDetailsView = new staffMovieDetailsView();
            this.staffMovieDetailsView = appContent(movieNumber);
            
        }   while (choice <= this.movieSchedules.size() && choice > 0);
    }
}
