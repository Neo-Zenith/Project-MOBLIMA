package model;

import java.io.Serializable;
import java.util.Calendar;

public class DateTime implements Serializable {
    private int minute;
    private int hour;
    private int day;
    private int date;
    private int month;
    private int year;
    private static final long serialVersionUID = 7L;

    public DateTime(int minute, int hour, int day, int date, int month, int year) {
        this.minute = minute;
        this.hour = hour;
        this.day = day;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public DateTime() {
        this.minute = Calendar.MINUTE;
        this.hour = Calendar.HOUR;
        this.day = Calendar.DAY_OF_WEEK;
        this.date = Calendar.DAY_OF_MONTH;
        this.month = Calendar.MONTH;
        this.year = Calendar.YEAR;
    }

    public int getMinute() {
        return this.minute;
    }

    public int getHour() {
        return this.hour;
    }

    public int getDate() {
        return this.date;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public int getYear() {
        return this.year;
    }

    public void printTime() {
        System.out.print(this.getMinute() + " min ");
        System.out.print(this.getHour() + " hr ");
        System.out.print(this.getDay() + " day ");
        System.out.print(this.getDate() + "/");
        System.out.print(this.getMonth() + "/");
        System.out.println(this.getYear());
    }
}
