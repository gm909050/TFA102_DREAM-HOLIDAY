package web.model;

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

public class SeatStatusDao {
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MAPPING_SEAT_STATUS order by seat_status";
	private static final String GET_ONE_STMT = 
			"SELECT seat_status,seat_status_name FROM MAPPING_SEAT_STATUS where seat_status = ?";
	
	private DataSource dataSource;
	public SeatStatusDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public MappingSeatStatus selectByKey(Integer seatStatus) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		MappingSeatStatus mapping = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, seatStatus);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mapping = new MappingSeatStatus();
				mapping.setSeatStatus(rs.getInt("seat_status"));
				mapping.setSeatStatusName(rs.getString("seat_status_name"));
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
		return mapping;
	}
	
	public List<MappingSeatStatus> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MappingSeatStatus> list = new ArrayList<MappingSeatStatus>();
		MappingSeatStatus mapSeatStatus = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mapSeatStatus = new MappingSeatStatus();
				mapSeatStatus.setSeatStatus(rs.getInt("seat_status"));
				mapSeatStatus.setSeatStatusName(rs.getString("seat_status_name"));
				list.add(mapSeatStatus);
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
