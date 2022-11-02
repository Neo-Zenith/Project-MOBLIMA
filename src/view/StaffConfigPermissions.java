public class StaffConfigPriceView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure movie goer permissions.
                1. Opt out list by overall ratings
                2. Opt out list by movie sales
                3. Back
                """);
        System.out.println("====================================");    
    }
    public void appContent(int movieNumber){
        int choice = -1;
        Database db = new Database();

        do {
            this.printMenu();
            int choice = InputHandler.intHandler();
            CinemaStaffManager.optOutOne(choice);
            if (choice == 1){
                System.out.println("Movie goer can no longer view top 5 based on overall ratings.");       
            }
            else {
                System.out.println("Movie goer can no longer view top 5 based on movie sales.");       
            }
        }   while (choice != 3);
    }
}
