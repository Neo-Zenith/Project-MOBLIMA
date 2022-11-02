public class StaffConfigSettingsView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the system settings to be configured
                1. Configure pricings.
                2. Configure holidays. 
                3. Configure movie goer permissions
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
            
            switch (choice){
                case 1:
                    this.staffConfigPriceView = new StaffConfigPriceView();
                    this.staffConfigPriceView = appContent();
                    break;
                case 2:
                    this.staffConfigHolidayView = new StaffConfigHolidayView();
                    this.staffConfigHolidayView = appContent();
                    break;
                case 3:
                    this.StaffConfigPermissions = new StaffConfigPermissions();
                    this.staffConfigHolidayView = appContent();
                    break;
            }
        }   while (choice != 4);
    }
}