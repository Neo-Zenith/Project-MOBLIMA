import java.util.*;

public class HolidayDateTime extends DateTime{
    
    private boolean isHoliday;
    public static List <DateTime> holidays = new ArrayList<>();
    
    public HolidayDateTime(int minute, int hour, int day, int date, int month, int year){
        super(minute, hour, day, date, month, year);
        isHoliday = true;
    }

}