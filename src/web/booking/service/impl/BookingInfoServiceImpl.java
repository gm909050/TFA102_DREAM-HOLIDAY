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
		//���o DailySeat
//		DailySeat dSeat = dao.getDailySeatByDay(java.sql.Date.valueOf("2021-02-28"));
		DailySeat dSeat = dao.getDailySeatByDay(info.getMealDate());
		System.out.println(dSeat);
		
		int bookTime = info.getMealTime(); //����
		int peopleNum = info.getBookingSeattype(); //�y��H��

		boolean isDailyTableExist = true; //��������������O�_�s�b
		String bookNumber = "000000";
		boolean seatAvailable = false;

		if(dSeat == null) { //��ܮڥ��S���ȡA�|���إߡA�i���l��
			isDailyTableExist = dao.initDailyBooking(info.getMealDate()); //�إߦ��\�|�אּfalse
			seatAvailable = isDailyTableExist; //�ت��\�A��M����m�i��
		} else { //���Ӥ���A�i�H����
				switch(bookTime) {
					case 1: //���\
						String breakfastSeat = dSeat.getBreakfastSeat();
						String breakfastBooking = dSeat.getBreakfastBooking();
						seatAvailable = isSeatAvailable(breakfastSeat, breakfastBooking, peopleNum);
						System.out.println("���\�O�_���Ŧ�:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = breakfastBooking;
						}
						break;
					case 2: //���\
						String lunchSeat = dSeat.getLunchSeat(); 
						String lunchBooking = dSeat.getLunchBooking();
						seatAvailable = isSeatAvailable(lunchSeat, lunchBooking, peopleNum);
						System.out.println("���\�O�_���Ŧ�:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = lunchBooking;
						}
						break;
					case 3: //���\
						String dinnerSeat = dSeat.getDinnerSeat(); 
						String dinnerBooking = dSeat.getDinnerBooking();
						seatAvailable = isSeatAvailable(dinnerSeat, dinnerBooking, peopleNum);
						System.out.println("���\�O�_���Ŧ�:"+seatAvailable);
						if(seatAvailable) {
							bookNumber = dinnerBooking;
						}
				}
		}

		System.out.println("���s�b�_: "+isDailyTableExist+"���Ŧ�: "+seatAvailable);
		if(isDailyTableExist && seatAvailable) { //�y��ƶq�i�q��B�`����(�w�s�b)
			
			try {
				//1. �i��q��	
				int result1 = dao.insert(info);
				
				//2. �y���`���������y��ƥ[1
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
		if(Integer.parseInt(numOfBookSeat) < Integer.parseInt(numOfTotalSeat)) { //�q���<�y���
			return true;
		} else { //�q��� >=�p��y���(���y)
			return false;
		}
	}
	private String queryCondition(String bookNumber, Integer seatType) {
		// bookNumber: XXXXXX, seatType: �y���,  bookTime: ����
		String numOfBookSeat = bookNumber.substring(seatType-2, seatType);
		int newBookNum = Integer.parseInt(numOfBookSeat)+1;
		String resultStr = null;
		switch(seatType) {
			case 2: //2�H
				resultStr = (newBookNum < 10 ? "0"+newBookNum : newBookNum)+bookNumber.substring(seatType);
				break;
			case 4: //4�H
				resultStr = bookNumber.substring(0, seatType-2)+(newBookNum < 10 ? "0"+newBookNum : newBookNum)+bookNumber.substring(seatType);
				break;
			case 6: //6�H
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
