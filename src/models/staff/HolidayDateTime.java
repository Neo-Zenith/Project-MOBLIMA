package models.staff;
import models.movie.DateTime;
import java.util.*;

public class HolidayDateTime extends DateTime{
    
    private boolean isHoliday;
    public static ArrayList <HolidayDateTime> holidays = new ArrayList<>();
    
    public HolidayDateTime(int minute, int hour, int day, int date, int month, int year){
        super(minute, hour, day, date, month, year);
        isHoliday = true;
    }

    public void printHolidayTime() {
        System.out.print(this.getMinute() + " min ");
        System.out.print(this.getHour() + " hr ");
        System.out.print(this.getDay() + " day ");
        System.out.print(this.getDate() + "/");
        System.out.print(this.getMonth() + "/");
        System.out.println(this.getYear());
    }

}