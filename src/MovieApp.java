import handler.InputHandler;
import handler.UIHandler;
import view.MainView;
import view.MovieAppView;

public class MovieApp {
    
    private static MovieAppView defaultView = new MovieAppView();
    public static void main(String args[]) {
        UIHandler.clearScreen();
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                               ║");
        System.out.println("║     ╔══╗══╗  ╔════╗  ╔═══╗  ╔      ╔   ╔══╗══╗   ╔════╗       ║");
        System.out.println("║     ║  ║  ║  ║    ║  ║═══╝  ║      ║   ║  ║  ║   ║    ║       ║");
        System.out.println("║     ║  ║  ║  ║    ║  ║═══╗  ║      ║   ║  ║  ║   ║════║       ║");
        System.out.println("║     ╝     ╚  ╚════╝  ╚═══╝  ╚════  ╝   ╝     ╚   ╝    ╚       ║");
        System.out.println("║                                                               ║");
        System.out.println("║       MOvie Booking and LIsting Management Application        ║");
        System.out.println("║                                                               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println("Press any key to continue: ");
        InputHandler.stringHandler();
        defaultView.appContent();
        System.out.println("Thank you for using MOBLIMA!");
    }
}
