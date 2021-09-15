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

public class DishStatusDao {
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MAPPING_DISH_STATUS order by dish_status";
	private static final String GET_ONE_STMT = 
			"SELECT dish_status,dish_status_name FROM MAPPING_DISH_STATUS where dish_status = ?";
	
	private DataSource dataSource;
	public DishStatusDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public MappingDishStatus selectByKey(Integer dishStatus) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		MappingDishStatus mapping = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, dishStatus);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mapping = new MappingDishStatus();
				mapping.setDishStatus(rs.getInt("dish_status"));
				mapping.setDishStatusName(rs.getString("dish_status_name"));
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
	
	public List<MappingDishStatus> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MappingDishStatus> list = new ArrayList<MappingDishStatus>();
		MappingDishStatus mapDishStatus = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mapDishStatus = new MappingDishStatus();
				mapDishStatus.setDishStatus(rs.getInt("dish_status"));
				mapDishStatus.setDishStatusName(rs.getString("dish_status_name"));
				list.add(mapDishStatus);
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
