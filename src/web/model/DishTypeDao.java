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

public class DishTypeDao {
	private static final String GET_ALL_STMT = 
			"SELECT * FROM MAPPING_DISH_TYPE order by dish_type";
	private static final String GET_ONE_STMT = 
			"SELECT dish_type,dish_type_name FROM MAPPING_DISH_TYPE where dish_type = ?";
	
	private DataSource dataSource;
	public DishTypeDao() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public MappingDishType selectByKey(Integer dishType) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		MappingDishType mapping = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, dishType);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				mapping = new MappingDishType();
				mapping.setDishType(rs.getInt("dish_type"));
				mapping.setDishTypeName(rs.getString("dish_type_name"));
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
	
	public List<MappingDishType> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		List<MappingDishType> list = new ArrayList<MappingDishType>();
		MappingDishType mapDishType = null;
		ResultSet rs = null;
		
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mapDishType = new MappingDishType();
				mapDishType.setDishType(rs.getInt("dish_type"));
				mapDishType.setDishTypeName(rs.getString("dish_type_name"));
				list.add(mapDishType);
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
