package web.seat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import web.seat.dao.SeatDao;
import web.seat.vo.Seat;

public class SeatDaoImpl implements SeatDao {
	private static final String INSERT_STMT = 
		"INSERT INTO SEAT VALUES (?, ?, ?)";
	private static final String UPDATE_STMT = 
		"UPDATE SEAT set seat_type=?, seat_status=? where seat_code = ?";
	private static final String DELETE_STMT = 
//		"DELETE FROM SEAT where seat_code = ?";
		"UPDATE SEAT set seat_status=? where seat_code = ?";
	private static final String GET_ONE_STMT = 
		"SELECT seat_code,seat_type,seat_status FROM SEAT where seat_code = ?";
	private static final String GET_ALL_STMT = 
		"SELECT seat_code,seat_type,seat_status FROM SEAT WHERE seat_code != ? AND seat_status != ? order by seat_code";
	private static final String GET_ALL_SEATCODE = 
		"SELECT seat_code FROM SEAT where seat_code != ?";
	
	private DataSource dataSource;
	public SeatDaoImpl() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(Seat seat) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, seat.getSeatCode());
			pstmt.setInt(2, seat.getSeatType());
			pstmt.setInt(3, seat.getSeatStatus());
			
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
	public int deleteByKey(String seatCode) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(DELETE_STMT);
			
			pstmt.setInt(1, 100);
			pstmt.setString(2, seatCode);
			
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
	public int updateByKey(Seat seat) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UPDATE_STMT);
			
			pstmt.setInt(1, seat.getSeatType());
			pstmt.setInt(2, seat.getSeatStatus());
			pstmt.setString(3, seat.getSeatCode());
			
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
	public Seat selectByKey(String seatCode) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		Seat seat = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, seatCode);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				seat = new Seat();
				seat.setSeatCode(rs.getString("seat_code"));
				seat.setSeatType(rs.getInt("seat_type"));
				seat.setSeatStatus(rs.getInt("seat_status"));
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
		return seat;
	}

	@Override
	public List<Seat> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<Seat> list = new ArrayList<Seat>();
		Seat seat = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			
			pstmt.setString(1, "0");
			pstmt.setInt(2, 100);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				seat = new Seat();
				seat.setSeatCode(rs.getString("seat_code"));
				seat.setSeatType(rs.getInt("seat_type"));
				seat.setSeatStatus(rs.getInt("seat_status"));
				list.add(seat); // Store the row in the list
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
}
