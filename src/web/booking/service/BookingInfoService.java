package web.booking.service;

import java.sql.Date;
import java.util.List;

import web.booking.vo.BookingInfo;
import web.dish.vo.Dish;
import web.seat.vo.DailySeat;

public interface BookingInfoService {
	boolean addBooking(BookingInfo info);
	boolean updateBooking(BookingInfo info);
	boolean deleteBooking(Integer id);
	BookingInfo getBookingByKey(Integer id);
	List<BookingInfo> getAll();
	
	boolean bookingSeat(BookingInfo info);
	
	List<BookingInfo> getBookMember(Date mealDate);
}
