package view;

import handler.InputHandler;

public class StaffConfigSettingsView {
    private StaffConfigPriceView staffConfigPriceView;
    private StaffConfigHolidayView staffConfigHolidayView;
    private StaffConfigPermissionsView staffConfigPermissionsView;

    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the system settings to be configured
                1. Configure pricings.
                2. Configure holidays. 
                3. Configure movie goer permissions
                4. Back   
                """);
        System.out.println("====================================");    
    }
    public void appContent(){
        int choice = -1;

        do {
            this.printMenu();
            choice = InputHandler.intHandler();
            
            switch (choice){
                case 1:
                    this.staffConfigPriceView = new StaffConfigPriceView();
                    this.staffConfigPriceView.appContent();
                    break;
                case 2:
                    this.staffConfigHolidayView = new StaffConfigHolidayView();
                    this.staffConfigHolidayView.appContent();
                    break;
                case 3:
                    this.staffConfigPermissionsView = new StaffConfigPermissionsView();
                    this.staffConfigHolidayView.appContent();
                    break;
            }
        }   while (choice != 4);
    }
}