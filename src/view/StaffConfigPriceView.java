package view;

import handler.InputHandler;

public class StaffConfigPriceView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                How would you like to configure pricings.
                1. Movie Type
                2. Cinema Type
                3. User Age Group
                4. Seat Type
                5. Special Dates (Weekends/Holidays)
                6. Back     
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
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
            }
        }   while (choice != 6);
    }
}
