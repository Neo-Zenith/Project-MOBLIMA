package models.staff;
public interface IStaffConfigSystem extends IStaffConfig{
    public void changeTicketPrice(String movieName, String movieType);
    public int addHoliday(HolidayDateTime holiday);
    public int deleteHoliday(HolidayDateTime holiday);
}