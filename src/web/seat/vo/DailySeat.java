package web.seat.vo;

import java.sql.Date;

public class DailySeat {
	private Date bookingDay;
	private String breakfastSeat;
	private String breakfastBooking;
	private String lunchSeat;
	private String lunchBooking;
	private String dinnerSeat;
	private String dinnerBooking;
	public Date getBookingDay() {
		return bookingDay;
	}
	public void setBookingDay(Date bookingDay) {
		this.bookingDay = bookingDay;
	}
	public String getBreakfastSeat() {
		return breakfastSeat;
	}
	public void setBreakfastSeat(String breakfastSeat) {
		this.breakfastSeat = breakfastSeat;
	}
	public String getBreakfastBooking() {
		return breakfastBooking;
	}
	public void setBreakfastBooking(String breakfastBooking) {
		this.breakfastBooking = breakfastBooking;
	}
	public String getLunchSeat() {
		return lunchSeat;
	}
	public void setLunchSeat(String lunchSeat) {
		this.lunchSeat = lunchSeat;
	}
	public String getLunchBooking() {
		return lunchBooking;
	}
	public void setLunchBooking(String lunchBooking) {
		this.lunchBooking = lunchBooking;
	}
	public String getDinnerSeat() {
		return dinnerSeat;
	}
	public void setDinnerSeat(String dinnerSeat) {
		this.dinnerSeat = dinnerSeat;
	}
	public String getDinnerBooking() {
		return dinnerBooking;
	}
	public void setDinnerBooking(String dinnerBooking) {
		this.dinnerBooking = dinnerBooking;
	}
	@Override
	public String toString() {
		return "DailySeat [bookingDay=" + bookingDay +
				", breakfastSeat=" + breakfastSeat +
				", breakfastBooking=" + breakfastBooking +
				", lunchSeat=" + lunchSeat +
				", lunchBooking=" + lunchBooking +
				", dinnerSeat=" + dinnerSeat
				+ ", dinnerBooking=" + dinnerBooking + "]";
	}
}
