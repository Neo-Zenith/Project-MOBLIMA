package view;

public abstract class MainView {
    
    public abstract void printMenu();
    
    public abstract void appContent();

    public static void printBoilerPlate(String content) {
        System.out.println("------------------------------------");
        System.out.println(content);
        System.out.println("------------------------------------");
    }
}
