package web.booking.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.booking.dao.BookingInfoDao;
import web.booking.vo.BookingInfo;
import web.seat.vo.DailySeat;

public class BookingInfoDaoImpl implements BookingInfoDao {
	private static final String INSERT_STMT = 
		"INSERT INTO BOOKING_INFO (seat_code, member_id, meal_date, meal_time, booking_status, payment_bill, booking_seat_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE BOOKING_INFO set seat_code = ?, member_id = ?, meal_date = ?, meal_time = ?, booking_status = ?, payment_bill = ?, booking_seat_type = ? where meal_id = ?";
	private static final String DELETE_STMT = 
		"DELETE FROM BOOKING_INFO where meal_id = ?";
	private static final String GET_ONE_STMT = 
		"SELECT meal_id, seat_code, member_id, meal_date, meal_time, booking_status, payment_bill, booking_seat_type FROM BOOKING_INFO where meal_id = ?";
	private static final String GET_ALL_STMT = 
		"SELECT meal_id, seat_code, member_id, meal_date, meal_time, booking_status, payment_bill, booking_seat_type FROM BOOKING_INFO order by meal_id";
	private static final String GET_SEAT_AVAILABLE_BY_DAY = 
		"SELECT * FROM DAILY_BOOKING WHERE booking_day = ?";
	private static final String INIT_DAILY_BOOKING =
		"INSERT INTO DAILY_BOOKING VALUES(?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_CURRENT_BOOKING_MEMBER = 
		"SELECT * FROM BOOKING_INFO WHERE meal_date = ? AND meal_time = ? AND seat_code = ? AND booking_status = ?";
	private static final String UPDATE_BILL_AND_STATUS = 
		"UPDATE BOOKING_INFO set booking_status = ?, payment_bill = ? where meal_id = ?";
	
	private DataSource dataSource;
	public BookingInfoDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(BookingInfo info) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, info.getSeatCode()); //default
			pstmt.setInt(2, info.getMemberId());
			pstmt.setDate(3, info.getMealDate());
			pstmt.setInt(4, info.getMealTime());
			pstmt.setInt(5, info.getBookingStatus()); //default
			pstmt.setInt(6, info.getPayBill()); //default
			pstmt.setInt(7, info.getBookingSeattype());
			
			return pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public int deleteByKey(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, id);
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public int updateByKey(BookingInfo info) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setString(1, info.getSeatCode());
			pstmt.setInt(2, info.getMemberId());
			pstmt.setDate(3, info.getMealDate());
			pstmt.setInt(4, info.getMealTime());
			pstmt.setInt(5, info.getBookingStatus());
			pstmt.setInt(6, info.getPayBill());
			pstmt.setInt(7, info.getBookingSeattype());
			pstmt.setInt(8, info.getMealId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
//			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}

	@Override
	public BookingInfo selectByKey(Integer id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		BookingInfo info = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				info = new BookingInfo();
				info.setMealId(rs.getInt("meal_id"));
				info.setSeatCode(rs.getString("seat_code"));
				info.setMemberId(rs.getInt("member_id"));
				info.setMealDate(rs.getDate("meal_date"));
				info.setMealTime(rs.getInt("meal_time"));
				info.setBookingStatus(rs.getInt("booking_status"));
				info.setPayBill(rs.getInt("payment_bill"));
				info.setBookingSeattype(rs.getInt("booking_seat_type"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return info;
	}

	@Override
	public List<BookingInfo> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<BookingInfo> list = new ArrayList<BookingInfo>();
		BookingInfo info = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				info = new BookingInfo();
				info.setMealId(rs.getInt("meal_id"));
				info.setSeatCode(rs.getString("seat_code"));
				info.setMemberId(rs.getInt("member_id"));
				info.setMealDate(rs.getDate("meal_date"));
				info.setMealTime(rs.getInt("meal_time"));
				info.setBookingStatus(rs.getInt("booking_status"));
				info.setPayBill(rs.getInt("payment_bill"));
				info.setBookingSeattype(rs.getInt("booking_seat_type"));
				list.add(info); // Store the row in the list
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public DailySeat getDailySeatByDay(Date mealDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		DailySeat dSeatVO = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_SEAT_AVAILABLE_BY_DAY);
			
			pstmt.setDate(1, mealDate);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dSeatVO = new DailySeat();
				dSeatVO.setBookingDay(mealDate);
				dSeatVO.setBreakfastSeat(rs.getString("breakfast_seat"));
				dSeatVO.setBreakfastBooking(rs.getString("breakfast_booking"));
				dSeatVO.setLunchSeat(rs.getString("lunch_seat"));
				dSeatVO.setLunchBooking(rs.getString("lunch_booking"));
				dSeatVO.setDinnerSeat(rs.getString("dinner_seat"));
				dSeatVO.setDinnerBooking(rs.getString("dinner_booking"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return dSeatVO;
	}
	
	private static final String TOTAL_SEAT_TWO = "50";
	private static final String TOTAL_SEAT_FOUR = "50";
	private static final String TOTAL_SEAT_SIX = "50";
	
	@Override
	public boolean initDailyBooking(Date mealDate) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INIT_DAILY_BOOKING);
			
			String initSeatNumber = TOTAL_SEAT_TWO+TOTAL_SEAT_FOUR+TOTAL_SEAT_SIX;
			
			pstmt.setDate(1, mealDate);
			pstmt.setString(2, initSeatNumber);
			pstmt.setString(3, "000000");
			pstmt.setString(4, initSeatNumber);
			pstmt.setString(5, "000000");
			pstmt.setString(6, initSeatNumber);
			pstmt.setString(7, "000000");
			
			return pstmt.executeUpdate() > 0;
			
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return false;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return false;
				}
			}
		}
	}

	@Override
	public int setDailyBooking(Date mealDate, Integer bookTime, String bookSeat) {
		
		String QUERY_STMT = "UPDATE DAILY_BOOKING SET "+ timeQuery(bookTime) +" WHERE booking_day = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(QUERY_STMT);
			
			pstmt.setString(1, bookSeat);
			pstmt.setDate(2, mealDate);

			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}
	
	private String timeQuery(int bookTime) {
		String result = null;
		switch (bookTime) {
		case 1:
			result = "breakfast_booking = ?";
			break;
		case 2:
			result = "lunch_booking = ?";
			break;
		case 3:
			result = "dinner_booking = ?";
			break;
		}
		return result;
	}

	@Override
	public List<BookingInfo> getBookMemberInTime(Date mealDate, Integer mealTime) {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<BookingInfo> list = new ArrayList<BookingInfo>();
		BookingInfo info = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_CURRENT_BOOKING_MEMBER);
			
			pstmt.setDate(1, mealDate);
			pstmt.setInt(2, mealTime);
			pstmt.setString(3, "0");
			pstmt.setInt(4, 0);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				info = new BookingInfo();
				info.setMealId(rs.getInt("meal_id")); //場次編號
				info.setMemberId(rs.getInt("member_id")); //會員編號
				info.setMealDate(rs.getDate("meal_date")); //用餐日期
				info.setMealTime(rs.getInt("meal_time")); //用餐時段
				info.setBookingSeattype(rs.getInt("booking_seat_type")); //預約座人數
				list.add(info); // Store the row in the list
			}
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public int updateBookingStatusAndTotalPrice(Connection con, Integer totalPrice, Integer mealId) {
		PreparedStatement pstmt = null;
		System.out.println("updateBookingStatusAndTotalPrice");
		try {
			pstmt = con.prepareStatement(UPDATE_BILL_AND_STATUS);
			
			pstmt.setInt(1, 4);
			pstmt.setInt(2, totalPrice);
			pstmt.setInt(3, mealId);
	
			return pstmt.executeUpdate();
		} catch (SQLException se) {
			try {
				// 發生例外即進行rollback動作
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
			return -1;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
					return -1;
				}
			}
		}
	}
}
