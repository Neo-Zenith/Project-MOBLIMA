package view;

import handler.InputHandler;

public class StaffConfigPriceView {
    public void printMenu() {
        System.out.println("====================================");
        MainView.printBoilerPlate("""
                Select the pricing to be configured
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
                    System.out.println("====================================");  
                    System.out.println("How would you like to configure MovieType pricings?");
                    System.out.println("1. Configure Standard Movie Price");
                    System.out.println("2. Configure BlockbusterMoviePrice");
                    System.out.println("3. Configure 3D Movie Price");
                    System.out.println("====================================");  
                    break;
                case 2:
                    System.out.println("====================================");  
                    System.out.println("How would you like to configure Cinema pricings?");
                    System.out.println("1. Configure Standard Cinema Price");
                    System.out.println("2. Configure Imax Cinema Price");
                    System.out.println("3. Platinum Cinema Price");
                    System.out.println("====================================");  
                    break;
                case 3:
                    System.out.println("====================================");
                    System.out.println("How would you like to configure Age Group pricings?");  
                    System.out.println("1. Configure Child price");
                    System.out.println("2. Configure Student Price");
                    System.out.println("3. Configure Adult Price");
                    System.out.println("4. Configure Senior Citizen Price");
                    System.out.println("====================================");  
                    break;
                case 4:
                    System.out.println("====================================");     
                    System.out.println("How would you like to configure Seat Type pricings?");
                    System.out.println("====================================");  
                    break;
                case 5:
                    System.out.println("====================================");  
                    System.out.println("How would you like to configure Special Dates pricings?");
                    System.out.println("1. Holiday Price");
                    System.out.println("2. Weekend Price");
                    System.out.println("====================================");  
                    break;
            }
        }   while (choice != 6);
    }
}
