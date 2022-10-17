import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class MovieTicket {
	private int seatID;
	private int showDate;
	private int showTime;
	private float price;
	private boolean seatStatus;

	public MovieTicket(int numTickets) {
		this.seatID = -1;
		this.showDate = -1;
		this.showTime = -1;
		this.price = -1;
		this.seatStatus = false;
	}
	
	
	public void setTicket(int seatID, int showDate, int showTime, boolean seatStatus, float price, AgeCategory category, MovieType movieType, CinemaClass cinemaClass) {
		this.seatID = seatID;
		this.showDate = showDate;
		this.showTime = showTime;
		this.seatStatus = true;
		this.price = calculatePrice(seatID, category, movieType, cinemaClass, showTime, showDate);
	}
	
	public int getSeatID() {
		return seatID;
	}
	
	public int getShowingDate() {
		return showDate;
	}
	
	public int getShowingTime() {
		return showTime;
	}
	
	public float getPrice() {
		return price;
	}

	// Based on Seat, Time, ageCategory, movieType, day, cinemaClass
	public float calculatePrice(int seatID, AgeCategory category, MovieType movieType, CinemaClass cinemaClass, int showTime, int showDate) {
		// insert calculations
		return price;
	}
	
	
}
