package src.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * DateTime class
 * @author Jonathan Ng
 * @version 1.0
 */
public class DateTime implements Serializable {

    /**
     * Minute of the instance 
     */
    private int minute;

    /**
     * Hour of the instance in 24 hours format
     */
    private int hour;

    /**
     * Day of week of the instance
     */
    private int day;

    /**
     * Date of year of the instance
     */
    private int date;

    /**
     * Month of the instance
     */
    private int month;

    /**
     * Year of the instance
     */
    private int year;
    private static final long serialVersionUID = 7L;

    /**
     * Constructor for DateTime class
     * @param minute is the minute of the Time
     * @param hour is the hour of the Time
     * @param day is the day of the Date (1. Sunday 2. Monday 3. Tuesday 4. Wednesday 5. Thursday 6. Friday 7. Saturday)
     * @param date is the date of the Date
     * @param month is the month of the Date
     * @param year is the year of the Date
     */
    public DateTime(int minute, int hour, int day, int date, int month, int year) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    /**
     * Constructor for DateTime class
     */
    public DateTime() {
        this.minute = Calendar.MINUTE;
        this.hour = Calendar.HOUR;
        this.day = Calendar.DAY_OF_WEEK;
        this.date = Calendar.DAY_OF_MONTH;
        this.month = Calendar.MONTH;
        this.year = Calendar.YEAR;
    }

    /**
     * Gets the minute of the Time 
     * @return Minute of the Time
     */
    public int getMinute() {
        return this.minute;
    }

    /**
     * Gets the hour of the Time
     * @return Hour of the Time
     */
    public int getHour() {
        return this.hour;
    }

    /**
     * Gets the date of the Date
     * @return Date of the Date
     */
    public int getDate() {
        return this.date;
    }

    /**
     * Gets the Day of the Date 
     * @return Day of the Date
     */
    public int getDay() {
        return this.day;
    }

    /**
     * Gets the Month of the Date
     * @return Month of the Date
     */
    public int getMonth() {
        return this.month;
    }

    /**
     * Gets the Year of the Date
     * @return Year of the Date
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Method to print out the date and the time
     */
    public void printTime() {
        System.out.print(this.getDate() + "/");
        System.out.print(this.getMonth() + "/");
        System.out.print(this.getYear() + "  ");

        switch (this.day) {
            case 1:
                System.out.print("Sunday  ");
                break;
            case 2:
                System.out.print("Monday  ");
                break;
            case 3:
                System.out.print("Tuesday  ");
                break;
            case 4:
                System.out.print("Wednesday  ");
                break;
            case 5:
                System.out.print("Thursday  ");
                break;
            case 6:
                System.out.print("Friday  ");
                break;
            case 7:
                System.out.print("Saturday  ");
                break;
            default:
                break;
        }

        System.out.println(String.format("%02d", this.getHour()) + String.format("%02d", this.getMinute()));
    }

    /**
     * Formats a string of the current time
     * @return String of the current time
     */
    public String getTimeNow() {
        String timeNow = "";
        timeNow += this.getDate() + "/";
        timeNow += this.getMonth() + "/";
        timeNow += this.getYear() + "  ";

        switch (this.day) {
            case 1:
               timeNow += "Sunday  ";
                break;
            case 2:
                timeNow += "Monday  ";
                break;
            case 3:
                timeNow += "Tuesday  ";
                break;
            case 4:
                timeNow += "Wednesday  ";
                break;
            case 5:
                timeNow += "Thursday  ";
                break;
            case 6:
                timeNow += "Friday  ";
                break;
            case 7:
                timeNow += "Saturday  ";
                break;
            default:
                break;
        }

        timeNow += String.format("%02d", this.getHour());
        timeNow += String.format("%02d", this.getMinute());

        return timeNow;
    }

    /**
     * Gets the current year
     * @return Year of the current year
     */
    public static int getCurrentYear() {
        return Calendar.YEAR;
    }

    /**
     * Gets the current month
     * @return Month of the current month
     */
    public static int getCurrentMonth() {
        return Calendar.MONTH;
    }

    /**
     * Gets the current day
     * @return Day of the current Day
     */
    public static int getCurrentDay() {
        return Calendar.DAY_OF_WEEK;
    }

    /**
     * Gets the current date
     * @return Date of the current date
     */
    public static int getCurrentDate() {
        return Calendar.DATE;
    }

}
