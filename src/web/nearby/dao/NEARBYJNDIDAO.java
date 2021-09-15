package web.nearby.dao;

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

import web.nearby.service.impl.NEARBYDAO_interface;
import web.nearby.vo.NEARBYVO;

public class NEARBYJNDIDAO implements NEARBYDAO_interface{
	
	private static DataSource ds = null;
	
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/peter");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_MEM = "INSERT INTO NEARBY (nearby_name, nearby_explanation, nearby) VALUES(?,?,?);";
	private static final String GET_ALL_MEM = "SELECT nearby_id, nearby_name, nearby_explanation FROM NEARBY order by nearby_id";
	private static final String GET_ONE_MEM = "SELECT nearby_id, nearby_name, nearby_explanation, nearby FROM NEARBY where nearby_id = ?";
	private static final String DELETE = "DELETE FROM ONEDAY where nearby_id = ?";
	private static final String UPDATE = "UPDATE NEARBY set  nearby_name=?, nearby_explanation=?, nearby=? where nearby_id = ?";
	private static final String GET_THREE_MEM = "SELECT picture_name,picture_explanation FROM ONEDAY where picture_id = 1 or picture_id =4 or picture_id =7";
			 
	
	@Override
	public void insert(NEARBYVO NEARBYVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_MEM);

			pstmt.setString(1, NEARBYVO.getNearbyname());
			pstmt.setString(2, NEARBYVO.getNearbyexplanation());
			pstmt.setBytes(3, NEARBYVO.getNearby());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}
	

	@Override
	public void update(NEARBYVO NEARBYVO) {
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態新增欄位
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, NEARBYVO.getNearbyname());
			pstmt.setString(2, NEARBYVO.getNearbyexplanation());
			pstmt.setBytes(3, NEARBYVO.getNearby());
			pstmt.setInt(4,NEARBYVO.getNearbyid());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}


	@Override
	public void delete(Integer nearby_id) {
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態欄位
		try {
			con = ds.getConnection();
			// 動態刪除欄位
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, nearby_id);

			pstmt.executeUpdate();// 執行更改檔案

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}
		

	@Override
	public NEARBYVO findByPrimaryKey(Integer venueid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NEARBYVO> getAll() {
		List<NEARBYVO> list = new ArrayList<NEARBYVO>();
		NEARBYVO NEARBYVO = null;
		
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態欄位
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ALL_MEM);
			rs = pstmt.executeQuery();// 執行更改檔案
			
			while(rs.next()) {
				NEARBYVO = new NEARBYVO();
				NEARBYVO.setNearbyid(rs.getInt("nearby_id"));
				NEARBYVO.setNearbyname(rs.getString("nearby_name"));
				NEARBYVO.setNearbyexplanation(rs.getString("nearby_explanation"));
				
				list.add(NEARBYVO);
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
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
	public List<NEARBYVO> getThree() {
		List<NEARBYVO> list = new ArrayList<NEARBYVO>();
		NEARBYVO NEARBYVO = null;
		
		Connection con = null;// 建立連線
		PreparedStatement pstmt = null;// 動態欄位
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_THREE_MEM);
			rs = pstmt.executeQuery();// 執行更改檔案
			
			while(rs.next()) {
				NEARBYVO = new NEARBYVO();
				NEARBYVO.setNearbyname(rs.getString("nearby_name"));
				NEARBYVO.setNearbyexplanation(rs.getString("nearby_explanation"));
				
				list.add(NEARBYVO);
				
			}

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if(rs != null) {
				try {
					rs.close();
				}catch(SQLException se) {
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
	
//	public static void main(String []args) {
//		NEARBYJNDIDAO nea = new NEARBYJNDIDAO();
//		NEARBYVO neavo = new NEARBYVO();
//		
//		neavo.setNearbyname("TEST");
//		neavo.setNearbyexplanation("test");
//		byte[] pic = neavo.getPictureByteArray("img/a.jpg");
//		neavo.setNearby(pic);
//		
//		nea.insert(neavo);
//	}
}



