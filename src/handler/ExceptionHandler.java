package src.handler;

import java.util.ArrayList;

/**
 * Collection of helper functions to deal with exceptions
 * 
 * @author Lee Juin
 * @version 1.0
 */
public class ExceptionHandler {

    /**
     * Constructor
     */
    public ExceptionHandler() {
    }

    /**
     * Handler to handle potential situation of type mismatch.
     * i.e., system expects an int but got a {@code String} instead
     * 
     * @param o1 object of type 1
     * @param o2 object of type 2
     * @return {@code true} if type 1 == type 2; {@code false} otherwise
     */
    public static boolean TypeMismatchHandler(Object o1, Object o2) {
        if (o1.getClass() == o2.getClass()) {
            return true;
        } else {
            System.out.println("Error! Unexpected input type!");
            System.out.println("System is expecting " + o1.getClass() + "type");
            return false;
        }
    }

    /**
     * Handler to handle situation when user attempts to access outside index of an
     * array
     * i.e., 5 selections but given int 6 by user
     * 
     * @param <E>       generic object for ArrayList
     * @param index     index that the user is trying to access
     * @param arrayList ArrayList of generic objects
     * @return {@code true} if the index is within the array, {@code false}
     *         otherwise
     */
    public static <E> boolean OutOfBoundHandler(int index, ArrayList<E> arrayList) {
        if (index < arrayList.size() && index >= 0) {
            return true;
        } else {
            System.out.println("Error! Index out of bound!");
            System.out.println("System expected input from 0 to " + arrayList.size() + "but got " + index + "instead");
            return false;
        }
    }

}
