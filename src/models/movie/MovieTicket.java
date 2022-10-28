package models.movie;

import models.cinema.*;

public class MovieTicket {
	private int seatRowID;
	private int seatColumnID;
	private SeatType seatType;
	private DateTime showingTime;
	private double movieTicketPrice;
	private Cinema showingCinema;
	private Movie movie;
	private Seat seat;

	public MovieTicket(	Seat seat, Cinema showingCinema, Movie movie, DateTime showingTime) {
		this.seatRowID = seat.getRowID();
		this.seatColumnID = seat.getColumnID();
		this.seatType = seat.getSeatType();
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

	public SeatType getSeatType() {
		return this.seatType;
	}
	
	public DateTime getShowingTime() {
		return this.showingTime;
	}
	
	public double getTicketPrice() {
		return this.movieTicketPrice;
	}

	// Based on Seat, Time, ageCategory, movieType, day, cinemaClass
	public double calculateTicketPrice(double discount, boolean byPercentage, boolean isHoliday) {
		// insert calculations
		double defaultPrice = (	seat.getSeatPrice() + showingCinema.getCinemaPrice() + 
								movie.getMovieType().getMoviePrice());
		
		double finalPrice = calculateDiscount(discount, defaultPrice, byPercentage, isHoliday);
		
		this.movieTicketPrice = finalPrice;

		return finalPrice;
	}
	

	public double calculateDiscount(double discount, double defaultPrice, boolean byPercentage, boolean isHoliday) {
		double currentMovieTicketPrice = defaultPrice;
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
