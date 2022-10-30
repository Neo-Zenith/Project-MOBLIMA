package middleware;

import database.ModelType;

public class ExceptionHandler {
    
    public static void modelException() {
        System.out.println("Error! The system cannot read the file specified!");
    } 

    public static void noQueryException() {
        System.out.println("Error! No result matching the query!");
    }

    public static void enforcedSingleQueryException(int queryLength) {
        System.out.println("Expected 1 result but got " + queryLength + " results instead!");
    }

    public static void multiExceptions(Exception e) {
        System.out.println(e.getMessage());
    }
}
