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

public class SeatTypeDao {
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MAPPING_SEAT_TYPE order by seat_type";
	private static final String GET_ONE_STMT = 
			"SELECT seat_type,seat_type_name FROM MAPPING_SEAT_TYPE where seat_type = ?";
	
	private DataSource dataSource;
	public SeatTypeDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public MappingSeatType selectByKey(Integer seatType) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		MappingSeatType mapping = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, seatType);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mapping = new MappingSeatType();
				mapping.setSeatType(rs.getInt("seat_type"));
				mapping.setSeatTypeName(rs.getString("seat_type_name"));
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
	
	public List<MappingSeatType> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MappingSeatType> list = new ArrayList<MappingSeatType>();
		MappingSeatType mapSeatType = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mapSeatType = new MappingSeatType();
				mapSeatType.setSeatType(rs.getInt("seat_type"));
				mapSeatType.setSeatTypeName(rs.getString("seat_type_name"));
				list.add(mapSeatType);
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
