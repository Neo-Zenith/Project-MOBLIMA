
public class StaffConfigPriceView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure holidays.
                1. Add holiday
                2. Delete holiday
                3. Back
                """);
        System.out.println("====================================");    
    }
    public void appContent(int movieNumber){
        int choice = -1;
        Database db = new Database();

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            
            CinemaStaffManager.configureHoliday(choice);
        }   while (choice != 3);
    }
}
