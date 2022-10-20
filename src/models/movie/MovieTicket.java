package models.movie;

import models.cinema.*;

public class MovieTicket {
	private int seatRowID;
	private int seatColumnID;
	private DateTime showingTime;
	private float movieTicketPrice;
	private Cinema showingCinema;
	private Movie movie;
	private Seat seat;

	public MovieTicket(	Seat seat, Cinema showingCinema, Movie movie, DateTime showingTime) {
		this.seatRowID = seat.getRowID();
		this.seatColumnID = seat.getColumnID();
		this.showingTime = showingTime;
		this.showingCinema = showingCinema;
		this.movie = movie;
		this.seat = seat;
	}
	
	public int getSeatRowID() {
		return this.seatRowID;
	}
	
	public int getSeatColumnID() {
		return this.seatColumnID;
	}
	
	public DateTime getShowingTime() {
		return this.showingTime;
	}
	
	public float getTicketPrice() {
		return this.movieTicketPrice;
	}

	// Based on Seat, Time, ageCategory, movieType, day, cinemaClass
	public void calculateTicketPrice(float discount, boolean byPercentage, boolean isHoliday) {
		// insert calculations
		float defaultPrice = (	seat.getSeatPrice() + showingCinema.getCinemaPrice() + 
								movie.getMovieType().getMoviePrice());
		
		float finalPrice = calculateDiscount(discount, defaultPrice, byPercentage, isHoliday);
		
		this.movieTicketPrice = finalPrice;
	}
	

	public float calculateDiscount(	float discount, float defaultPrice,
											boolean byPercentage, boolean isHoliday) {
		float currentMovieTicketPrice = defaultPrice;
		if (showingTime.getDay() == 6 || showingTime.getDay() == 7 || isHoliday) {
			if (byPercentage) {
				currentMovieTicketPrice *= (1 - discount);
				return currentMovieTicketPrice;
			}
			currentMovieTicketPrice -= discount;
		}
		return currentMovieTicketPrice;
	}
}
