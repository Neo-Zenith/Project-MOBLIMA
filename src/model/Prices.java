package model;

public class Prices {
    
    private double defaultStandardCinemaPrice = 2;
    private double defaultPlatinumCinemaPrice = 30;
    private double defaultIMaxCinemaPrice = 3;
    private double defaultSeatPrice = 4;
    private double defaultBlockbusterMoviePrice = 3;
    private double default3DMoviePrice = 5;
    private double defaultStandardMoviePrice = 2;
    private double defaultChildPrice = 1.5;
    private double defaultStudentPrice = 1.5;
    private double defaultAdultPrice = 2;
    private double defaultSeniorCitizenPrice = 1.5;
    private double holidayPrice = 1.5;
    private double weekendPrice = 1.5;

    public Prices(double defaultStandardCinemaPrice,
                    double defautltPlatinumCinemaPrice,
                    double defaultIMaxCinemaPrice,
                    double defaultSeatPrice,
                    double defaultBlockbusterMoviePrice,
                    double default3DMoviePrice,
                    double defaultStandardMoviePrice,
                    double defaultChildPrice,
                    double defaultStudentPrice,
                    double defaultAdultPrice,
                    double defaultSeniorCitizenPrice,
                    double holidayPrice,
                    double weekendPrice) {
        
        this.setDefault3DMoviePrice(default3DMoviePrice);
        this.setDefaultAdultPrice(defaultAdultPrice);
        this.setDefaultBlockbusterMoviePrice(defaultBlockbusterMoviePrice);
        this.setDefaultChildPrice(defaultChildPrice);
        this.setDefaultIMaxCinemaPrice(defaultIMaxCinemaPrice);
        this.setDefaultPlatinumCinemaPrice(defautltPlatinumCinemaPrice);
        this.setDefaultSeatPrice(defaultSeatPrice);
        this.setDefaultSeniorCitizenPrice(defaultSeniorCitizenPrice);
        this.setDefaultStandardCinemaPrice(defaultStandardCinemaPrice);
        this.setDefaultStandardMoviePrice(defaultStandardMoviePrice);
        this.setDefaultStudentPrice(defaultStudentPrice);
        this.setHolidayPrice(holidayPrice);
        this.setWeekendPrice(weekendPrice);
    }

    public double getDefaultStandardCinemaPrice() {
        return this.defaultStandardCinemaPrice;
    }

    public double getDefaultIMaxCinemaPrice() {
        return this.defaultIMaxCinemaPrice;
    }

    public double getDefaultPlatinumCinemaPrice() {
        return this.defaultPlatinumCinemaPrice;
    }

    public void setDefaultStandardCinemaPrice(double defaultStandardCinemaPrice) {
        this.defaultStandardCinemaPrice = defaultStandardCinemaPrice;
    }

    public void setDefaultIMaxCinemaPrice(double defaultIMaxCinemaPrice) {
        this.defaultIMaxCinemaPrice = defaultIMaxCinemaPrice;
    }

    public void setDefaultPlatinumCinemaPrice(double defaultPlatinumCinemaPrice) {
        this.defaultPlatinumCinemaPrice = defaultPlatinumCinemaPrice;
    }

    public double getDefaultSeatPrice() {
        return this.defaultSeatPrice;
    }

    public void setDefaultSeatPrice(double defaultSeatPrice) {
        this.defaultSeatPrice = defaultSeatPrice;
    }

    public double getDefaultBlockbusterMoviePrice() {
        return this.defaultBlockbusterMoviePrice;
    }

    public double getDefault3DMoviePrice() {
        return this.default3DMoviePrice;
    }

    public double getDefaultStandardMoviePrice() {
        return this.defaultStandardMoviePrice;
    }

    public void setDefaultBlockbusterMoviePrice(double defaultBlockbusterMoviePrice) {
        this.defaultBlockbusterMoviePrice = defaultBlockbusterMoviePrice;
    }

    public void setDefault3DMoviePrice(double default3DMoviePrice) {
        this.default3DMoviePrice = default3DMoviePrice;
    }

    public void setDefaultStandardMoviePrice(double defaultStandardMoviePrice) {
        this.defaultStandardMoviePrice = defaultStandardMoviePrice;
    }

    public double getDefaultChildPrice() {
        return this.defaultChildPrice;
    }

    public double getDefaultStudentPrice() {
        return this.defaultStudentPrice;
    }

    public double getDefaultAdultPrice() {
        return this.defaultAdultPrice;
    }

    public double getDefaultSeniorCitizenPrice() {
        return this.defaultSeniorCitizenPrice;
    }

    public void setDefaultChildPrice(double defaultChildPrice) {
        this.defaultChildPrice = defaultChildPrice;
    }

    public void setDefaultStudentPrice(double defaultStudentPrice) {
        this.defaultStudentPrice = defaultStudentPrice;
    }

    public void setDefaultAdultPrice(double defaultAdultPrice) {
        this.defaultAdultPrice = defaultAdultPrice;
    }

    public void setDefaultSeniorCitizenPrice(double defaultSeniorCitizenPrice) {
        this.defaultSeniorCitizenPrice = defaultSeniorCitizenPrice;
    }

    public double getHolidayPrice() {
        return this.holidayPrice;
    }

    public double getWeekendPrice() {
        return this.weekendPrice;
    }

    public void setHolidayPrice(double holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    public void setWeekendPrice(double weekendPrice) {
        this.weekendPrice = weekendPrice;
    }
}
