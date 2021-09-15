package web.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import web.basic.dao.BasicDao;
import web.booking.vo.BookingInfo;
import web.seat.vo.DailySeat;

public interface BookingInfoDao extends BasicDao<BookingInfo, Integer> {
	DailySeat getDailySeatByDay(Date mealDate);
	boolean initDailyBooking(Date mealDate);
	int setDailyBooking(Date mealDate, Integer bookTime, String bookSeat);
	
	List<BookingInfo> getBookMemberInTime(Date mealDate, Integer mealTime);
	
	int updateBookingStatusAndTotalPrice(Connection con, Integer totalPrice, Integer mealId);
}
