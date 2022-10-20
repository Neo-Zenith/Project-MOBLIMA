package models;
import java.util.*;
/*
 * Testbench file. Feel free to test your models here.
 */
public class Testbench {
    public static void main(String [] args) {
        /*
         * Modify the code here to test your models
         */
        TestCinemaCineplexSeat();
    }


    /*
     * Create your own testbench function and run on main()
     */
    public static void TestCinemaCineplexSeat() {
        Cineplex cathay = new Cineplex("Cathay", 1);
        System.out.println(cathay.getCineplexName()); 

        Scanner sc = new Scanner(System.in);
        List <CinemaType> cinemaType = new ArrayList<>();
        int totalNumOfSeats[] = new int[cathay.getNumOfCinema()];
        float cinemaPrice[] = new float[cathay.getNumOfCinema()];
        int numOfRows[] = new int[cathay.getNumOfCinema()];
        int numOfSeatsPerRow[][] = new int[cathay.getNumOfCinema()][];

        for (int i = 0; i < cathay.getNumOfCinema(); i ++) {
            System.out.println("What is the cinema type for cinema ID " + i + " ?");
            System.out.println("========================================");
            System.out.println("1. Platinum Movie Suites");
            System.out.println("2. Standard Cinema");
            System.out.println("========================================");
            int choice = sc.nextInt();
            cinemaType.add((choice == 1) ? CinemaType.PLATINUM: CinemaType.STANDARD);

            System.out.println("What is the total number of seats for cinema ID " + i + " ?");
            totalNumOfSeats[i] = sc.nextInt();

            System.out.println("What is the price of cinema ID " + i + " ?");
            cinemaPrice[i] = sc.nextFloat();

            System.out.println("How many rows of seats are there for cinema ID " + i + " ?");
            numOfRows[i] = sc.nextInt();
            numOfSeatsPerRow[i] = new int[numOfRows[i]];

            System.out.println("How many seats are there per row for cinema ID " + i + " ?");
            for (int j = 0; j < numOfRows[i]; j ++) {
                System.out.println("Number of seats for row " + j);
                numOfSeatsPerRow[i][j] = sc.nextInt();
            }
        }

        cathay.populateCinema(  cinemaType, totalNumOfSeats, 
                                cinemaPrice, numOfRows, numOfSeatsPerRow);
        
        Cinema cinema = cathay.getCinema(0);
        System.out.println("How many rows for each seat type for cinema ID " + 0 + " ?");


        int numOfRowsPerSeatType[] = new int[SeatType.getNumSeatType()];
        float pricePerSeatType[] = new float[SeatType.getNumSeatType()];
        List <SeatType> seatTypes = new ArrayList<>();

        for (int i = 0; i < SeatType.getNumSeatType(); i ++) {
            seatTypes.add(SeatType.class.getEnumConstants()[i]);
            System.out.println("Number of rows for seat type " + SeatType.class.getEnumConstants()[i]);
            numOfRowsPerSeatType[i] = sc.nextInt();

            System.out.println("Price for seat type " + SeatType.class.getEnumConstants()[i]);
            pricePerSeatType[i] = sc.nextFloat();
        }

        cinema.populateSeat(numOfRowsPerSeatType, pricePerSeatType, seatTypes);
        cinema.printFloorMap();
    }
}
