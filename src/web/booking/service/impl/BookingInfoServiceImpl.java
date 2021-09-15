package web.booking.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import web.booking.dao.BookingInfoDao;
import web.booking.dao.impl.BookingInfoDaoImpl;
import web.booking.service.BookingInfoService;
import web.booking.vo.BookingInfo;
import web.seat.vo.DailySeat;

public class BookingInfoServiceImpl implements BookingInfoService {

	BookingInfoDao dao;
	
	public BookingInfoServiceImpl() {
		dao = new BookingInfoDaoImpl();
	}
	
	@Override
	public boolean addBooking(BookingInfo info) {
		return dao.insert(info) > 0;
	}

	@Override
	public boolean updateBooking(BookingInfo info) {
		return dao.updateByKey(info) > 0;
	}

	@Override
	public boolean deleteBooking(Integer id) {
		return dao.deleteByKey(id) > 0;
	}

	@Override
	public BookingInfo getBookingByKey(Integer id) {
		return dao.selectByKey(id);
	}

	@Override
	public List<BookingInfo> getAll() {
		return dao.selectAll();
	}
	
	
	@Override
	public boolean bookingSeat(BookingInfo info) {
		//取得 DailySeat
//		DailySeat dSeat = dao.getDailySeatByDay(java.sql.Date.valueOf("2021-02-28"));
		DailySeat dSeat = dao.getDailySeatByDay(info.getMealDate());
		System.out.println(dSeat);
		
		int bookTime = info.getMealTime(); //場次
		int peopleNum = info.getBookingSeattype(); //座位人數

		boolean isDailyTableExist = true; //日期對應的紀錄是否存在
		String bookNumber = "000000";
		boolean seatAvailable = false;

		if(dSeat == null) { //表示根本沒有值，尚未建立，進行初始化
			isDailyTableExist = dao.initDailyBooking(info.getMealDate()); //建立成功會改為false
			seatAvailable = isDailyTableExist; //建表成功，當然有位置可選
		} else { //有該日期，可以取值
				switch(bookTime) {
					case 1: //早餐
						String breakfastSeat = dSeat.getBreakfastSeat();
						String breakfastBooking = dSeat.getBreakfastBooking();
						seatAvailable = isSeatAvailable(breakfastSeat, breakfastBooking, peopleNum);
						System.out.println("早餐是否有空位:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = breakfastBooking;
						}
						break;
					case 2: //午餐
						String lunchSeat = dSeat.getLunchSeat(); 
						String lunchBooking = dSeat.getLunchBooking();
						seatAvailable = isSeatAvailable(lunchSeat, lunchBooking, peopleNum);
						System.out.println("午餐是否有空位:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = lunchBooking;
						}
						break;
					case 3: //晚餐
						String dinnerSeat = dSeat.getDinnerSeat(); 
						String dinnerBooking = dSeat.getDinnerBooking();
						seatAvailable = isSeatAvailable(dinnerSeat, dinnerBooking, peopleNum);
						System.out.println("晚餐是否有空位:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = dinnerBooking;
						}
				}
		}

		System.out.println("表格存在否: "+isDailyTableExist+"有空位: "+seatAvailable);
		if(isDailyTableExist && seatAvailable) { //座位數量可訂位且總覽表(已存在)
			
			try {
				//1. 進行訂位	
				int result1 = dao.insert(info);
				
				//2. 座位總覽對應的座位數加1
				String newBookSeat = queryCondition(bookNumber, peopleNum);
				int result2 = dao.setDailyBooking(info.getMealDate(), bookTime, newBookSeat);
				return (result1>0) && (result2>0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return false;
	}

	private boolean isSeatAvailable(String totalSeat, String bookNumber, Integer seatType) {
		String numOfTotalSeat =  totalSeat.substring(seatType-2, seatType);
		String numOfBookSeat = bookNumber.substring(seatType-2, seatType);
		if(Integer.parseInt(numOfBookSeat) < Integer.parseInt(numOfTotalSeat)) { //訂位數<座位數
			return true;
		} else { //訂位數 >=小於座位數(滿座)
			return false;
		}
	}
	private String queryCondition(String bookNumber, Integer seatType) {
		// bookNumber: XXXXXX, seatType: 座位數,  bookTime: 場次
		String numOfBookSeat = bookNumber.substring(seatType-2, seatType);
		int newBookNum = Integer.parseInt(numOfBookSeat)+1;
		String resultStr = null;
		switch(seatType) {
			case 2: //2人
				resultStr = (newBookNum < 10 ? "0"+newBookNum : newBookNum)+bookNumber.substring(seatType);
				break;
			case 4: //4人
				resultStr = bookNumber.substring(0, seatType-2)+(newBookNum < 10 ? "0"+newBookNum : newBookNum)+bookNumber.substring(seatType);
				break;
			case 6: //6人
				resultStr = bookNumber.substring(0, seatType-2)+(newBookNum < 10 ? "0"+newBookNum : newBookNum);
				break;
		}
		return resultStr;
	}

	@Override
	public List<BookingInfo> getBookMember(Date mealDate) {
		
		
		String format = "HH:mm:ss";
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime localTime = LocalTime.now();
        Integer timeZone = null;
		
		try {
			java.util.Date bStart = new SimpleDateFormat(format).parse("08:30:00");
			Calendar bStartTime = Calendar.getInstance();
			bStartTime.setTime(bStart);
			java.util.Date bEnd = new SimpleDateFormat(format).parse("10:30:00");
			Calendar bEndTime = Calendar.getInstance();
			bEndTime.setTime(bEnd);
			
			java.util.Date lStart = new SimpleDateFormat(format).parse("11:30:00");
			Calendar lStartTime = Calendar.getInstance();
			lStartTime.setTime(lStart);
			java.util.Date lEnd = new SimpleDateFormat(format).parse("14:30:00");
			Calendar lEndtTime = Calendar.getInstance();
			lEndtTime.setTime(lEnd);
			
			java.util.Date dStart = new SimpleDateFormat(format).parse("17:30:00");
			Calendar dStartTime = Calendar.getInstance();
			dStartTime.setTime(dStart);
			java.util.Date dEnd = new SimpleDateFormat(format).parse("19:30:00");
			Calendar dEndTime = Calendar.getInstance();
			dEndTime.setTime(dEnd);

//			java.util.Date now = new SimpleDateFormat(format).parse(dtf.format(localTime));
			java.util.Date now = new SimpleDateFormat(format).parse("09:00:00");
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(now);
			
			if (nowTime.after(bStartTime) && nowTime.before(bEndTime)) {
				System.out.println("breakfast");
				timeZone = 1;
			} else if(nowTime.after(lStartTime) && nowTime.before(lEndtTime)) {
				System.out.println("lunch");
				timeZone = 2;
			} else if(nowTime.after(dStartTime) && nowTime.before(dEndTime)) {
				System.out.println("dinner");
				timeZone = 3;
			} else {
				System.out.println("not right time");
				timeZone = -1;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(timeZone > 0) {
//			return dao.getBookMemberInTime(mealDate, timeZone);
			return dao.getBookMemberInTime(java.sql.Date.valueOf("2021-09-07"), 2);
		} 
		return null;
	}
}
